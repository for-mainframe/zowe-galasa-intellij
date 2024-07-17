package io.kotest.plugin.intellij.toolwindow

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.project.Project

class FilterCallbacksAction(
   project: Project,
) : ToggleAction("Filter Callbacks", null, AllIcons.Nodes.Controller) {
   private val kotestTestExplorerService: KotestTestExplorerService = project.getService(KotestTestExplorerService::class.java)

   override fun getActionUpdateThread() = ActionUpdateThread.EDT

   override fun isSelected(e: AnActionEvent): Boolean = kotestTestExplorerService.showCallbacks

   override fun setSelected(
      e: AnActionEvent,
      state: Boolean,
   ) {
      kotestTestExplorerService.showCallbacks = state
   }
}

class FilterModulesAction(
   project: Project,
) : ToggleAction("Filter Modules", null, AllIcons.Nodes.ModuleGroup) {
   private val kotestTestExplorerService: KotestTestExplorerService = project.getService(KotestTestExplorerService::class.java)

   override fun getActionUpdateThread() = ActionUpdateThread.EDT

   override fun isSelected(e: AnActionEvent): Boolean = kotestTestExplorerService.showModules

   override fun setSelected(
      e: AnActionEvent,
      state: Boolean,
   ) {
      kotestTestExplorerService.showModules = state
   }
}

class FilterTagsAction(
   project: Project,
) : ToggleAction("Filter Tags", null, AllIcons.Nodes.Tag) {
   override fun getActionUpdateThread() = ActionUpdateThread.EDT

   private val kotestTestExplorerService: KotestTestExplorerService = project.getService(KotestTestExplorerService::class.java)

   override fun isSelected(e: AnActionEvent): Boolean = kotestTestExplorerService.showTags

   override fun setSelected(
      e: AnActionEvent,
      state: Boolean,
   ) {
      kotestTestExplorerService.showTags = state
   }
}

class FilterIncludesAction(
   project: Project,
) : ToggleAction("Filter Includes", null, AllIcons.Nodes.Tag) {
   private val kotestTestExplorerService: KotestTestExplorerService = project.getService(KotestTestExplorerService::class.java)

   override fun getActionUpdateThread() = ActionUpdateThread.EDT

   override fun isSelected(e: AnActionEvent): Boolean = kotestTestExplorerService.showIncludes

   override fun setSelected(
      e: AnActionEvent,
      state: Boolean,
   ) {
      kotestTestExplorerService.showIncludes = state
   }
}

class NavigateToNodeAction(
   project: Project,
) : ToggleAction("Autoscroll To Source", null, AllIcons.General.AutoscrollToSource) {
   private val kotestTestExplorerService: KotestTestExplorerService = project.getService(KotestTestExplorerService::class.java)

   override fun getActionUpdateThread() = ActionUpdateThread.EDT

   override fun isSelected(e: AnActionEvent): Boolean = kotestTestExplorerService.autoscrollToSource

   override fun setSelected(
      e: AnActionEvent,
      state: Boolean,
   ) {
      kotestTestExplorerService.autoscrollToSource = state
   }
}
