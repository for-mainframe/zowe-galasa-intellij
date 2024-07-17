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

package org.zowe.galasa.plugin.intellij.ctl

import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

// TODO: doc
class GalasaCTLToolWindowFactory : ToolWindowFactory {

   // TODO: doc
   override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
      val consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).console
      consoleView.component.toolTipText

      val contentFactory = ContentFactory.getInstance()
      val content = contentFactory.createContent(consoleView.component, "Galasa CTL Output", false)
      toolWindow.contentManager.addContent(content)
      toolWindow.stripeTitle = "Galasa CTL Output"
      toolWindow.title = "Galasa Tests"
   }

}
