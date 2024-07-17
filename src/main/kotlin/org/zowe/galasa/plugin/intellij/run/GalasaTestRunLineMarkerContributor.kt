package org.zowe.galasa.plugin.intellij.run

import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.PsiClassImpl
import com.intellij.psi.impl.source.PsiJavaFileImpl
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.psi.*
import org.zowe.galasa.plugin.intellij.GalasaTest
import org.zowe.galasa.plugin.intellij.actions.RunGalasaTestSuiteAction

/** Returns a [RunLineMarkerContributor.Info] if the element is a test case */
class GalasaTestRunLineMarkerContributor : RunLineMarkerContributor() {

   // icons list https://jetbrains.design/intellij/resources/icons_list/
   private val icon = AllIcons.RunConfigurations.TestState.Run

   // TODO: doc
   override fun getInfo(element: PsiElement): Info? {
      // the docs say to only run a line marker for a leaf
      if (element is LeafPsiElement && element.context?.context?.context is PsiClassImpl) {
         return getInfoIfGalasaTest(element)
      }
      return null
      // TODO: mark test source roots after pom.xml analyze
      // if (!testMode && !ModuleUtil.hasTestSourceRoots(element.project)) return null
      // if (!testMode && !element.containingFile.isTestFile()) return null
//      return when (element.context) {
//         // rule out some common entries that can't possibly be test markers for performance
////         is KtAnnotationEntry, is KtDeclarationModifierList, is KtImportDirective, is KtImportList, is KtPackageDirective -> null
//         else -> getInfoIfGalasaTest(element as PsiMethod)
//      }
   }

   /**
    * Returns an [Info] if this element is a Galasa test that is enabled.
    * Disabled tests are handled by the [DisabledTestLineMarker]
    */
   private fun getInfoIfGalasaTest(element: LeafPsiElement): Info? {
      val isGalasaTestClass = element.context is PsiAnnotation && (element.context as PsiAnnotation).qualifiedName == "dev.galasa.Test"
      if (!isGalasaTestClass) return null
//      val testMethod = element.context?.context?.context as PsiMethod
      val testClass = element.context?.context?.context as PsiClassImpl
      val packagePsi = testClass.context
      if (packagePsi !is PsiJavaFileImpl) return null
      val packageName = packagePsi.packageName
      val testClassName = testClass.name ?: return null
      val testSuite = GalasaTest(packageName, testClassName)
      return icon(testSuite)
   }

// TODO: for methods, but Galasa is not yet support separate methods run
//   /**
//    * Returns an [Info] if this element is a Galasa test that is enabled.
//    * Disabled tests are handled by the [DisabledTestLineMarker]
//    */
//   private fun getInfoIfGalasaTest(element: LeafPsiElement): Info? {
//      val isGalasaTestMethod = element.context is PsiAnnotation && (element.context as PsiAnnotation).qualifiedName == "dev.galasa.Test"
//      if (!isGalasaTestMethod) return null
//      val testMethod = element.context?.context?.context as PsiMethod
//      // TODO: find parent if already registered
//      val testClass = testMethod.context as PsiClassImpl
//      val testSuite = GalasaTest(testClass.name ?: "<anonymous>", null, GalasaTestType.Spec)
//      val test = GalasaTest(testMethod.name, testSuite, GalasaTestType.Case)
//      return icon(test)
//   }

   /** Returns an [Info] to use for the given [GalasaTest] */
   private fun icon(test: GalasaTest): Info {
      return Info(
         icon,
         { "Run ${test.className}" },
         RunGalasaTestSuiteAction(test),
//         DebugGalasaTestCaseAction(test.name)
      )
   }
}
