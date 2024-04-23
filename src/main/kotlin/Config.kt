class Config(private val file: String) {
    // `object{}.javaClass.getResourceAsStream` works too
    // `ResourceBundle.getBundle("Messages", locale)` also works
        val map: Map<String, String> by lazy {
            this::class.java.getResourceAsStream(file)?.use { stream ->
                stream.bufferedReader().useLines { lines ->
                    lines.map { it.split("=") }
                        .filter { it.size == 2 }
                        .map { (key, value) -> key.trim() to value.trim() }
                        .filter { (key, value) -> key.isNotEmpty() && value.isNotEmpty() }
                        .toMap()
                }
            } ?: emptyMap()
        }
    }