/*
 * Copyright (c) 2024 IBA Group.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   IBA Group
 *   Zowe Community
 */

package org.zowe.galasa.plugin.intellij.actions

import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessOutputTypes
import com.intellij.execution.ui.ConsoleView
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.wm.ToolWindowManager
import org.jetbrains.idea.maven.execution.MavenRunner
import org.jetbrains.idea.maven.execution.MavenRunnerParameters
import org.jetbrains.idea.maven.project.MavenProjectsManager
import org.zowe.galasa.plugin.intellij.GalasaTest
import java.io.IOException

// TODO: doc, finalize
class RunGalasaTestSuiteAction(private val test: GalasaTest)
   : AnAction("Run ${test.className}", null, AllIcons.RunConfigurations.TestState.Run) {

   override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

   // TODO: doc
   override fun actionPerformed(e: AnActionEvent) {
      val project = e.project ?: return
      val mavenProjectsManager = MavenProjectsManager.getInstance(project)
      val mavenProject = mavenProjectsManager.projects.firstOrNull() ?: return
      val mavenRunnerParameters = MavenRunnerParameters(
         true,
         mavenProject.directory,
         mavenProject.file.path,
         listOf("clean", "install"),
         mavenProjectsManager.explicitProfiles.enabledProfiles,
         mavenProjectsManager.explicitProfiles.disabledProfiles
      )
      val mavenRunner = MavenRunner.getInstance(project)
      mavenRunner.run(mavenRunnerParameters, null) {
         ApplicationManager.getApplication().invokeLater {
            runGalasaCTLTask(project)
         }
      }
   }

   // TODO: copmute it
   val galasaCTLPath = "D:\\IBA\\IJMP\\galasa-tests\\org.zowe.glsvsc\\galasactl-windows-x86_64.exe"
   val galasaProjectRoot = "org.zowe.glsvsc"
   val obr = "mvn:$galasaProjectRoot/$galasaProjectRoot.obr/0.0.1-SNAPSHOT/obr"

   // TODO: doc
   private fun runGalasaCTLTask(project: Project) {
      val javaHome = System.getProperty("JAVA_HOME")
      if (javaHome == "") throw Exception("JAVA_HOME is not set. Please, provide a path to Java 11")
      val osName = System.getProperty("os.name").lowercase()
      val classPath = "${test.packageName}/${test.path()}"
      val commands = when {
         osName.contains("win") -> arrayOf(
            galasaCTLPath, "runs", "submit", "local", "--log", "-", "--obr", obr, "--class", classPath
         )
         else -> throw UnsupportedOperationException("Unsupported OS: $osName")
      }

      val consoleView = getGalasaCTLConsoleView(project)
      try {
         val processBuilder = ProcessBuilder(*commands)
         val process = processBuilder.start()

         val processHandler = OSProcessHandler(process, commands.joinToString(" "))
         processHandler.addProcessListener(object : ProcessAdapter() {
            override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
               val contentType = when (outputType) {
                  ProcessOutputTypes.STDOUT -> ConsoleViewContentType.NORMAL_OUTPUT
                  ProcessOutputTypes.STDERR -> ConsoleViewContentType.ERROR_OUTPUT
                  else -> ConsoleViewContentType.SYSTEM_OUTPUT
               }
               consoleView.print(event.text, contentType)
            }
         })
         processHandler.startNotify()
      } catch (e: IOException) {
         consoleView.print("Error starting process: ${e.message}\n", ConsoleViewContentType.ERROR_OUTPUT)
      }
   }

   // TODO: doc
   private fun getGalasaCTLConsoleView(project: Project): ConsoleView {
      val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("org.zowe.galasa.plugin.intellij.ctl.GalasaCTLToolWindowFactory")
         ?: throw Exception("There is no definition for Galasa CTL console tool window")
      val content = toolWindow.contentManager.getContent(0)
         ?: throw Exception("There is no content for Galasa CTL console")
      val consoleView = content.component as ConsoleView

      if (!toolWindow.isActive) {
         toolWindow.activate(null)
      }
      consoleView.clear()
      return consoleView
   }

}
