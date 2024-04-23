import java.util.Locale

class LangCode(val code: String) {

    companion object {
        private const val RESOURCE_NAME = "lang-defs.conf"
        private const val NOT_FOUND_TEXT = "LANG_NOT_FOUND"

        private var loadedDefinitions: Map<String, String>? = null

        private val definitions: Map<String, String>
            get() {
                if (loadedDefinitions == null) {
                    loadedDefinitions = Config(RESOURCE_NAME).map
                }
                return loadedDefinitions!!
            }
    }

    override fun toString() = definitions[code] ?: NOT_FOUND_TEXT
}

fun main() {
    val codes = listOf(Locale.GERMAN.toString(), "en", "de", "zh", "fr", "jp")

    for (code in codes) {
        val langCode = LangCode(code)
        println("$code = ${langCode.code} => $langCode")
    }
}