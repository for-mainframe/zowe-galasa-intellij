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

import com.intellij.ide.util.treeView.PresentableNodeDescriptor
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreePath

fun JTree.expandAllNodes() = expandAllNodes(0, rowCount)

fun JTree.expandAllNodes(startingIndex: Int, rowCount: Int) {
   for (i in startingIndex until rowCount) {
      expandRow(i)
   }
   if (getRowCount() != rowCount) {
      expandAllNodes(rowCount, getRowCount())
   }
}

@Suppress("UNCHECKED_CAST")
fun JTree.collapseTopLevelNodes() {
   val root = model.root as DefaultMutableTreeNode
   for (node in root.children().toList() as List<DefaultMutableTreeNode>) {
      val path = TreePath(node.path)
      this.collapsePath(path)
   }
}

fun TreePath.nodeDescriptor(): PresentableNodeDescriptor<*>? {
   return when (val last = lastPathComponent) {
      is DefaultMutableTreeNode -> when (val obj = last.userObject) {
         is PresentableNodeDescriptor<*> -> obj
         else -> null
      }
      else -> null
   }
}
