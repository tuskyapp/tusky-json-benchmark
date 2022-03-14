package at.connyduck.tusky.benchmark

import android.text.Spanned
import android.util.Log
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.connyduck.tusky.entity.FullStatus
import at.connyduck.tusky.entity.TimelineStatus
import at.connyduck.tusky.json.SpannedTypeAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class JsonDecodingBenchmark {

    private val gson = GsonBuilder().registerTypeAdapter(
        Spanned::class.java, SpannedTypeAdapter()
    ).create()

    private val testData = loadTestData()

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun decodeWithGson_Full() {
        benchmarkRule.measureRepeated {
            val statusList: List<FullStatus> = gson.fromJson(testData, object : TypeToken<List<FullStatus>>() {}.type)
        }
    }

    @Test
    fun decodeWithGson_Timeline() {
        benchmarkRule.measureRepeated {
            val statusList: List<TimelineStatus> = gson.fromJson(testData, object : TypeToken<List<TimelineStatus>>() {}.type)
        }
    }

    private fun loadTestData(): String {

        val sb = StringBuilder()

        val br = BufferedReader(InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream("test.json")))

        try {
            var line: String? = br.readLine()
            while (line != null) {
                sb.append(line)
                sb.append('\n')
                line = br.readLine()
            }
        } catch (e: IOException) {
            Log.w("LicenseActivity", e)
        }

        br.close()

        return sb.toString()
    }
}