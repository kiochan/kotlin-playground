class Graph {
    private val nodes = mutableListOf<Node>()
    private val edges = mutableListOf<Edge>()

    private fun addNode(node: Node) {
        if (!nodes.contains(node)) {
            nodes.add(node)
        }
    }

    fun addEdge(edge: Edge) {
        if (!edges.any { it == edge }) {
            edges.add(edge)
            addNode(edge.start)
            addNode(edge.end)
        }
    }

    fun getNodes(): List<Node> = nodes

    fun path(startId: String, endId: String): Path? {
        val startNode = nodes.find { it.id == startId } ?: return null
        val endNode = nodes.find { it.id == endId } ?: return null

        val weights = mutableMapOf<Node, Double>().withDefault { Double.MAX_VALUE }
        val previousNodes = mutableMapOf<Node, Node?>()
        val unvisited = nodes.toMutableSet()

        weights[startNode] = 0.0

        while (unvisited.isNotEmpty()) {
            val currentNode = unvisited.minByOrNull { weights.getValue(it) } ?: break
            unvisited.remove(currentNode)

            // 这里处理所有与currentNode连接的边，无论是起点还是终点
            for (edge in edges.filter { it.start == currentNode || it.end == currentNode }) {
                val neighbor = if (edge.start == currentNode) edge.end else edge.start  // 确定邻居节点
                if (neighbor in unvisited) {
                    val newDist = weights.getValue(currentNode) + edge.weight
                    if (newDist < weights.getValue(neighbor)) {
                        weights[neighbor] = newDist
                        previousNodes[neighbor] = currentNode
                    }
                }
            }
        }

        val path = generatePath(previousNodes, endNode)
        val totalWeight = weights[endNode] ?: return null

        return Path(path, totalWeight)
    }

    private fun generatePath(previousNodes: Map<Node, Node?>, endNode: Node): List<Node> {
        val path = mutableListOf<Node>()
        var current: Node? = endNode
        while (current != null) {
            path.add(current)
            current = previousNodes[current]
        }
        path.reverse()
        return path
    }
}