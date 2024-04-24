data class Node(val id: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Node
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}