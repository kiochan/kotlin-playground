import java.util.*

enum class Unit(val factor: Double, val symbol: String) {
    // International units
    METER(1.0, "m"), // 1 meter
    KILOMETER(1000.0, "km"), // 1 kilometer is 1000 meters
    CENTIMETER(0.01, "cm"), // 1 centimeter is 0.01 meters
    MILLIMETER(0.001, "mm"), // 1 millimeter is 0.001 meters

    // Imperial units
    INCH(0.0254, "in"), // 1 inch is 0.0254 meters
    FOOT(0.3048, "ft"), // 1 foot is 0.3048 meters
    YARD(0.9144, "yd"), // 1 yard is 0.9144 meters
    MILE(1_609.344, "mi"), // 1 mile is 1,609.344 meters

    // Chinese units
    CHI(1.0 / 3.0, "尺(ZH)"), // 1 chi (尺) is 1 / 3 meters
    ZHANG(10.0 / 3.0, "丈(ZH)"), // 1 zhang (丈) is 10 / 3 meters
    CUN(1.0 / 30.0, "寸(ZH)"), // 1 cun (寸) is 1 / 30 meters
    LI(500.0, "里(ZH)"), // 1 li (里) is 500 meters

    // Japanese Shaku-Kan system
    SHAKU(3.030303, "尺(JP)"), // 1 shaku (尺) is approximately 3.030303 meters
    KEN(1.818182, "間(JP)"), // 1 ken (間) is approximately 1.818182 meters
    JO(3.030303 , "丈(JP)"), // 1 jo (丈) is approximately 3.030303 meters
    CHO(109.090109 , "丈(JP)"), // 1 cho (町) is approximately 109.090109 meters
    RII(3927.2723927, "里(JP)"); // 1 ri (里) is approximately 3927.2723927 meters

    override fun toString() = name.lowercase(Locale.getDefault())
}