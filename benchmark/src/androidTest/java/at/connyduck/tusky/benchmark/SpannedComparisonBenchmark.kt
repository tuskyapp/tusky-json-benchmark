package at.connyduck.tusky.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.core.text.parseAsHtml
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.connyduck.tusky.stringEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class SpannedComparisonBenchmark {

    private val html = """
        Android 13 will support per-app language preferences. That is cool because there will finally be an official api for it, but of course <a href="https://chaos.social/tags/Tusky" class="mention hashtag status-link" rel="noopener noreferrer" target="_blank">#<span>Tusky</span></a> already has the feature anyway.<br><a href="https://developer.android.com/about/versions/13/features/app-languages" rel="noopener noreferrer" target="_blank" class="status-link unhandled-link" title="https://developer.android.com/about/versions/13/features/app-languages"><span class="invisible">https://</span><span class="ellipsis">developer.android.com/about/ve</span><span class="invisible">rsions/13/features/app-languages</span></a>
    """.trimIndent()

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun custom_equals_1() {
        val a =  "<p>Test<br> Test</p>".parseAsHtml()
        val b =  "<p>Test<br> Test</p>".parseAsHtml()
        benchmarkRule.measureRepeated {
            println(a.stringEquals(b))
        }
    }

    @Test
    fun string_equals_1() {
        val a =  "<p>Test<br> Test</p>".parseAsHtml()
        val b =  "<p>Test<br> Test</p>".parseAsHtml()
        benchmarkRule.measureRepeated {
            println(a.toString() == b.toString())
        }
    }

    @Test
    fun custom_equals_2() {
        val a =  html.parseAsHtml()
        val b =  html.parseAsHtml()
        benchmarkRule.measureRepeated {
            println(a.stringEquals(b))
        }
    }

    @Test
    fun string_equals_2() {
        val a =  html.parseAsHtml()
        val b =  html.parseAsHtml()
        benchmarkRule.measureRepeated {
            println(a.toString() == b.toString())
        }
    }
}