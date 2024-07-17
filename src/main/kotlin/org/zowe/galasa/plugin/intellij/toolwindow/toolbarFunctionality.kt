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

package org.zowe.galasa.plugin.intellij.toolwindow

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.Project
import org.zowe.galasa.plugin.intellij.actions.RunGalasaTestAction
import javax.swing.JComponent

// TODO: doc
fun createToolbar(
   toolbarOwner: JComponent,
   tree: TestFileTree,
   project: Project,
): JComponent {
   val actionManager = ActionManager.getInstance()
   val toolbar =
      actionManager.createActionToolbar(
         ActionPlaces.STRUCTURE_VIEW_TOOLBAR,
         createActionGroup(tree, project),
         true,
      )
   toolbar.targetComponent = toolbarOwner
   return toolbar.component
}

// TODO: doc
private fun createActionGroup(
   tree: TestFileTree,
   project: Project,
): DefaultActionGroup {
   val result = DefaultActionGroup()
   result.add(RunGalasaTestAction("Run", AllIcons.Actions.Execute, tree, project, "Run"))
   result.add(RunGalasaTestAction("Debug", AllIcons.Actions.StartDebugger, tree, project, "Debug"))
   result.add(RunGalasaTestAction("Run with coverage", AllIcons.General.RunWithCoverage, tree, project, "Coverage"))
   result.addSeparator()
   result.add(ExpandAllAction(tree))
   result.add(CollapseAllAction(tree))
//   result.addSeparator()
//   result.add(FilterCallbacksAction(project))
//   result.add(FilterIncludesAction(project))
//   result.add(FilterModulesAction(project))
//   result.add(FilterTagsAction(project))
//   result.addSeparator()
//   result.add(NavigateToNodeAction(project))
   return result
}

class CollapseAllAction(
   private val tree: TestFileTree,
) : AnAction("Collapse All", null, AllIcons.Actions.Collapseall) {
   override fun actionPerformed(e: AnActionEvent) {
      tree.collapseTopLevelNodes()
   }
}

class ExpandAllAction(
   private val tree: TestFileTree,
) : AnAction("Expand All", null, AllIcons.Actions.Expandall) {
   override fun actionPerformed(e: AnActionEvent) {
      tree.expandAllNodes()
   }
}
