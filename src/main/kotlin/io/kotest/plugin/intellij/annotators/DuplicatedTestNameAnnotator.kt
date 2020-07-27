package io.kotest.plugin.intellij.annotators

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.kotest.plugin.intellij.psi.enclosingKtClass
import io.kotest.plugin.intellij.psi.isTestFile
import io.kotest.plugin.intellij.psi.specStyle

class DuplicatedTestNameAnnotator : Annotator {
   override fun annotate(element: PsiElement, holder: AnnotationHolder) {
      // we only care about test files
      if (!element.containingFile.isTestFile()) return

      // only change when the test itself has been modified
      val ktclass = element.enclosingKtClass()
      if (ktclass != null) {
         val style = ktclass.specStyle()
         if (style != null) {
            val test = style.test(element)
            if (test != null) {
               if (!test.name.contains('$')) {
                  val tests = style.tests(ktclass)
                  val duplicated = tests.count { it.test.name == test.name } > 1
                  if (duplicated) {
                     holder.newAnnotation(HighlightSeverity.WARNING, "Duplicated test name").range(test.psi).create()
                  }
               }
            }
         }
      }
   }
}
