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

import io.kotest.plugin.intellij.toolwindow.KotestTestExplorerService
import io.kotest.plugin.intellij.toolwindow.TestExplorerTreeSelectionListener
import io.kotest.plugin.intellij.toolwindow.expandAllNodes

import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.openapi.project.Project
import com.intellij.ui.treeStructure.Tree
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeSelectionModel
import org.zowe.galasa.plugin.intellij.toolwindow.TestExplorerService.ModelListener

class TestFileTree(project: Project) : Tree(), ModelListener {
//   private val testExplorerTreeSelectionListener = TestExplorerTreeSelectionListener(project)
//   private val kotestTestExplorerService: KotestTestExplorerService = project.getService(KotestTestExplorerService::class.java)

   init {
//      selectionModel.selectionMode = TreeSelectionModel.SINGLE_TREE_SELECTION
//      showsRootHandles = true
//      isRootVisible = false
//      cellRenderer = NodeRenderer()
//      // listens to changes in the selections
//      addTreeSelectionListener(testExplorerTreeSelectionListener)
//      kotestTestExplorerService.registerModelListener(this)
   }

   override fun setModel(treeModel: TreeModel) {
      val expanded = isExpanded(0)
      super.setModel(treeModel)
//      expandAllNodes()
      setModuleGroupNodeExpandedState(expanded)
   }

   private fun setModuleGroupNodeExpandedState(expanded: Boolean) {
      if (expanded) expandRow(0) else collapseRow(0)
   }
}

