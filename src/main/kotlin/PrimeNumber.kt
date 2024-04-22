import kotlin.math.sqrt

const val MAX_VALUE = 1_000_000

open class PrimeNumber {

    companion object {
        fun isPrime(number: Int): Boolean {
            if (number < 0 || number > MAX_VALUE)
                throw IllegalArgumentException("Must between 0 and $MAX_VALUE")

            if (number < 2) return false
            if (number < 4) return true

            val max = sqrt(number.toDouble()).toInt()

            for (divisor in 2..max) {
                if (number % divisor == 0) return false
            }

            return true
        }

        private val primeNumberBuffer = BooleanArray(MAX_VALUE + 1) {true}
        private var isPrimeNumberBufferReady = false

        private fun prepareBuffer() {
            primeNumberBuffer[0] = false
            primeNumberBuffer[1] = false
            val max = sqrt(MAX_VALUE.toDouble()).toInt()
            var divisor = 2
            while (divisor <= max) {
                if (primeNumberBuffer[divisor]) {
                    var i = divisor * divisor
                    while (i <= MAX_VALUE) {
                        primeNumberBuffer[i] = false
                        i += divisor
                    }
                }
                divisor++
            }
            isPrimeNumberBufferReady = true
        }

        fun isPrimeBuffered(number: Int): Boolean {
            if (number < 0 || number > MAX_VALUE)
                throw IllegalArgumentException("Must between 0 and $MAX_VALUE")

            if (!isPrimeNumberBufferReady) prepareBuffer()
            return primeNumberBuffer[number]
        }
    }
}

fun main() {
    var c = 0
    for (i in 2..MAX_VALUE) {
        if (PrimeNumber.isPrime(i)){
            c++
        }
    }
    println("$c")

    c = 0
    for (i in 2..MAX_VALUE) {
        if (PrimeNumber.isPrimeBuffered(i)){
            c++
        }
    }
    println("$c")
}