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

package org.zowe.galasa.plugin.intellij

import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.TestSourcesFilter
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile

/** Returns true if this [PsiFile] is located in a test source */
fun PsiFile.isTestFile(): Boolean {
   return virtualFile.isTestFile(project)
}

/** Returns true if this [VirtualFile] is located in a test source */
fun VirtualFile.isTestFile(project: Project): Boolean {
   // TODO: adjust handling of pom.xml
   return true
//   return TestSourcesFilter.isTestSources(this, project)
}
