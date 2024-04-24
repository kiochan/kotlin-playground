import java.util.*

class Distance(initialMeterValue: Double = 0.0) {
    companion object {
        fun from (baseUnit: Unit) = Distance().also {
            it.unit = baseUnit
            it.value = 1.0
        }
    }

    var unit = Unit.METER

    private var meterValue = initialMeterValue
    var value: Double
        get() = meterValue / unit.factor
        set(value) {
            meterValue = value * unit.factor
        }

    operator fun plus(other: Distance) = Distance(meterValue + other.meterValue).also {
        it.unit = unit
    }

    operator fun minus(other: Distance) = Distance(meterValue - other.meterValue).also {
        it.unit = unit
    }

    operator fun times(other: Double) = Distance(meterValue * other).also {
        it.unit = unit
    }

    operator fun div(other: Double) = Distance(meterValue / other).also {
        it.unit = unit
    }

    operator fun rem(other: Double) = Distance(value % other).also {
        it.unit = unit
    }

    operator fun compareTo(other: Distance): Int =
        when {
            this == other -> 0
            meterValue > other.meterValue -> 1
            else -> -1
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Distance

        return kotlin.math.abs(meterValue - other.meterValue) < 1E-9
    }

    override fun hashCode(): Int {
        return meterValue.hashCode()
    }

    fun toString(digits: Int) = "${String.format("%.${digits}f", value)} ${unit.symbol}"
    override fun toString() = toString(2)

    fun convertTo (newUnit: Unit): Distance = from(newUnit).also {it.meterValue = this.meterValue}
}

fun main() {
    val a = Distance.from(Unit.FOOT).also { it.value = 1.0 }
    val b = Distance.from(Unit.ZHANG).also { it.value = 1.0 }
    val c = Distance.from(Unit.JO).also { it.value = 1.0 }

    val sum = (a + b + c).convertTo(Unit.METER)

    println("$a + $b + $c = $sum")
}