buildscript {
   repositories {
      mavenCentral()
   }
}

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   java
   id("org.jetbrains.intellij") version "1.17.2"
   kotlin("jvm") version "1.9.22"
}

repositories {
   mavenCentral()
   mavenLocal()
   maven("https://oss.sonatype.org/content/repositories/snapshots")
   maven("https://www.jetbrains.com/intellij-repository/snapshots")
}

version = "0.0.1"

intellij {
   version.set("IC-2024.1.1")
   plugins.addAll("java", "org.jetbrains.plugins.gradle", "org.jetbrains.kotlin", "org.jetbrains.idea.maven")
//   !Development only!
//   downloadSources.set(true)
}

dependencies {
   implementation(libs.jaxb.api)
   implementation(libs.javax.activation)

   // this is needed to use the launcher in 4.2.0, in 4.2.1+ the launcher is built
   // into the engine dep which should already be on the classpath
   implementation(libs.runtime.kotest.framework.launcher)

   // needed for the resource files which are loaded into java light tests
   testImplementation(libs.test.kotest.framework.api)
   testImplementation(libs.test.kotest.assertions.core)
}

kotlin {
   compilerOptions {
      jvmToolchain(JavaLanguageVersion.of("17").asInt())
   }
}

tasks {
   patchPluginXml {
      version.set("${project.version}")
      sinceBuild.set("241.15989")
      untilBuild.set("242.*")
   }

   test {
      isScanForTestClasses = false
      // Only run tests from classes that end with "Test"
      include("**/*Test.class")
      include("**/*Tests.class")
   }
}
