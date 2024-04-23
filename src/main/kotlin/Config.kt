class Config(private val file: String) {
    // `object{}.javaClass.getResourceAsStream` works too
    // `ResourceBundle.getBundle("Messages", locale)` also works
    val map: Map<String, String> = this::class.java.getResourceAsStream(file)
        ?.bufferedReader()?.use { reader ->
            reader.lineSequence()
                .map { it.split("=") }
                .filter { parts -> parts.size == 2 }
                .map { (key, value) -> key.trim() to value.trim() }
                .filter { (key, value) -> key.isNotEmpty() && value.isNotEmpty() }
                .toMap()
        } ?: HashMap<String,String>()
}