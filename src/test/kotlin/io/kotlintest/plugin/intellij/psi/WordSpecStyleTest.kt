package io.kotlintest.plugin.intellij.psi

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.icons.AllIcons
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase
import io.kotlintest.shouldBe
import java.nio.file.Paths

class WordSpecStyleTest : LightCodeInsightFixtureTestCase() {

  override fun getTestDataPath(): String {
    val path = Paths.get("./src/test/resources/").toAbsolutePath()
    return path.toString()
  }

  fun testGutterIcons() {

    myFixture.configureByFile("/wordspec.kt")

    val gutters = myFixture.findAllGutters()
    gutters.size shouldBe 7

    gutters[0].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[0].tooltipText shouldBe "[KotlinTest] some should context"
    (gutters[0] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 136

    gutters[1].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[1].tooltipText shouldBe "[KotlinTest] some should context should test something"
    (gutters[1] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 173

    gutters[2].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[2].tooltipText shouldBe "[KotlinTest] some should context should allow config"
    (gutters[2] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 227

    gutters[3].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[3].tooltipText shouldBe "[KotlinTest] with capital When"
    (gutters[3] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 287

    gutters[4].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[4].tooltipText shouldBe "[KotlinTest] with capital When when and capital Should"
    (gutters[4] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 320

    gutters[5].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[5].tooltipText shouldBe "[KotlinTest] with capital When when and capital Should should test something"
    (gutters[5] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 358

    gutters[6].icon shouldBe AllIcons.RunConfigurations.TestState.Run
    gutters[6].tooltipText shouldBe "[KotlinTest] with capital When when and capital Should should allow config"
    (gutters[6] as LineMarkerInfo.LineMarkerGutterIconRenderer<*>).lineMarkerInfo.startOffset shouldBe 417

  }
}