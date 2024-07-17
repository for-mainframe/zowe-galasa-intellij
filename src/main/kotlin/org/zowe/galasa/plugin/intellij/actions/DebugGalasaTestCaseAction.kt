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

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

// TODO: doc, finalize
class DebugGalasaTestCaseAction(name: String) : AnAction("Debug $name", null, AllIcons.Actions.StartDebugger) {

   override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

   override fun actionPerformed(e: AnActionEvent) {
      println("Debugging Galasa test")
   }

}
