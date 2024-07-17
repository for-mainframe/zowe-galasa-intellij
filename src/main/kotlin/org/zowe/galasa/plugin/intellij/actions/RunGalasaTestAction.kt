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

import io.kotest.plugin.intellij.toolwindow.nodeDescriptor

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.jetbrains.rd.util.printlnError
import javax.swing.Icon
import javax.swing.JTree

// TODO: doc
class RunGalasaTestAction(
   text: String,
   icon: Icon,
   private val tree: JTree,
   private val project: Project,
   private val executorId: String
) : AnAction(text, null, icon) {

   override fun actionPerformed(e: AnActionEvent) {
      printlnError("Run action triggered")
//      runNode(tree, project, executorId, true)
   }

   override fun update(e: AnActionEvent) {
      if (e.isFromActionToolbar) {
         e.presentation.isEnabled = when (tree.selectionPath?.nodeDescriptor()) {
//            is SpecNodeDescriptor -> true
//            is TestNodeDescriptor -> true
//            is ModuleNodeDescriptor -> true
            else -> false
         }
      }
   }

   override fun getActionUpdateThread() = ActionUpdateThread.BGT

}
