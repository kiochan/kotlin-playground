import java.time.Duration

class Routes (private val file: String) {
    companion object {
        const val DATA_FILE = "route-plan"
        private val parseRegex = Regex("(\\S+)\\s+-\\s+(\\S+)\\s+(\\d+) hr\\s+(\\d+) min \\((\\d+) km\\)")
    }

    private val data: List<Triple<Pair<String, String>, Duration, Float>> by lazy {
        this::class.java.getResourceAsStream(file)?.use { stream ->
            stream.bufferedReader().useLines { lines ->
                lines
                    .mapNotNull { line -> parseRegex.find(line) }
                    .map { result ->
                        val (from, to, hours, minutes, distance) = result.destructured
                        Triple(
                            Pair(from.trim(), to.trim()),
                            Duration.ofHours(hours.toLong()).plusMinutes(minutes.toLong()),
                            distance.toFloat()
                        )
                    }
                    .toList()
            }
        } ?: emptyList()
    }

    private val distanceGraph = Graph()
    private val durationGraph = Graph()

    init {
        for ((pair, duration, distance) in data) {
            val (first, second) = pair
            distanceGraph.addEdge(Edge(Node(first), Node(second), duration.toMinutes().toDouble()))
            durationGraph.addEdge(Edge(Node(first), Node(second), distance.toDouble()))
        }
    }

    fun printAllCity () {
        println("Available cities are: ")
        println(distanceGraph.getNodes().joinToString { it.id })
    }

    fun hasCity (cityId: String) = distanceGraph.getNodes().find { it.id == cityId } != null

    fun printBestPath (originId: String, destinationId: String) {
        val dstPath = distanceGraph.path(originId, destinationId)
        val drtPath = durationGraph.path(originId, destinationId)

        if (dstPath == null || drtPath == null) {
            println("there is no solution for $originId -> $destinationId")
            return
        }

        val duration = Duration.ofMinutes(drtPath.totalWeight.toLong())

        println("best route for time: ${duration.toHours().toString().padStart(2, '0')}:${duration.toMinutesPart().toString().padStart(2, '0')}")
        println(drtPath.nodes.joinToString { it.id })
        println("best route for shortest distance: ${dstPath.totalWeight.toInt()} km")
        println(dstPath.nodes.joinToString(" -> ") { it.id })
    }
}

fun main() {
    val routes = Routes(Routes.DATA_FILE)
    var origin: String? = null
    var destination: String? = null

    while (origin == null) {
        print("origin: ")
        val input = readln()
        if (routes.hasCity(input)) origin = input
        else routes.printAllCity()
    }

    while (destination == null) {
        print("destination: ")
        val input = readln()
        if (routes.hasCity(input)) destination = input
        else routes.printAllCity()
    }

    routes.printBestPath(origin, destination)
}