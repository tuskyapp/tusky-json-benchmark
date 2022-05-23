package at.connyduck.tusky.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.connyduck.tusky.entity.gson.TimelineStatus
import at.connyduck.tusky.json.UtcDateTypeAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class JsonDecodingBenchmark {

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, UtcDateTypeAdapter())
        .create()

    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    private val testData = loadTestData()

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun decodeWithGson() {
        benchmarkRule.measureRepeated {
            val statusList: List<TimelineStatus> = gson.fromJson(testData, object : TypeToken<List<TimelineStatus>>() {}.type)
        }
    }

    @Test
    @ExperimentalStdlibApi
    fun decodeWithMoshi() {
        benchmarkRule.measureRepeated {
            val statusList: List<at.connyduck.tusky.entity.moshi.TimelineStatus>? = moshi.adapter<List<at.connyduck.tusky.entity.moshi.TimelineStatus>>().fromJson(testData)
        }
    }

    private fun loadTestData(): String {

        val sb = StringBuilder()

        val br = BufferedReader(InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream("test.json")))

        var line: String? = br.readLine()
        while (line != null) {
            sb.append(line)
            sb.append('\n')
            line = br.readLine()
        }

        br.close()

        return sb.toString()
    }
}