data class Edge(val start: Node, val end: Node, val weight: Double = 1.0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Edge

        return (start == other.start && end == other.end && weight == other.weight) ||
                (start == other.end && end == other.start && weight == other.weight)
    }

    override fun hashCode(): Int {
        return 31 * (start.hashCode() + end.hashCode()) + weight.hashCode()
    }
}