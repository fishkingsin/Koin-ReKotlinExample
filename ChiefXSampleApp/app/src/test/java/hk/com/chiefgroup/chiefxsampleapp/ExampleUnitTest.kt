package hk.com.chiefgroup.chiefxsampleapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val result2 = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter { it % 2 == 0 }
            .map { it * 10 }
            .reduce { a, b -> Int
                a + b
            }
println(result2)

    }
}