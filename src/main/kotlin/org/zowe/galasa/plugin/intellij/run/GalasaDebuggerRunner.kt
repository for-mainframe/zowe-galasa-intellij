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

package org.zowe.galasa.plugin.intellij.run

import io.kotest.plugin.intellij.KotestRunConfiguration

import com.intellij.execution.JavaTestFrameworkDebuggerRunner
import com.intellij.execution.configurations.RunProfile
import org.zowe.galasa.plugin.intellij.frameworkName

// TODO: doc
class GalasaDebuggerRunner : JavaTestFrameworkDebuggerRunner() {

   override fun validForProfile(profile: RunProfile): Boolean {
      // TODO: change
      return profile is KotestRunConfiguration
   }

   override fun getThreadName(): String = frameworkName
   override fun getRunnerId(): String = "ZoweGalasaDebug"
}
