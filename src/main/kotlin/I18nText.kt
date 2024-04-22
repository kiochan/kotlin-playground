import java.util.Locale

class I18nText {
    companion object {
        val local: String
            get () {
                return Locale.getDefault().language
            }
    }

    private val translations = HashMap<String, String>()
    private val default: String
        get () {
            return this.translations[""]!!
        }

    private constructor() {}
    constructor(text: String) {
        this.translations[""] = text
        this.translations[I18nText.local] = text
    }
    constructor(text: String, local: String) {
        this.translations[""] = text
        this.translations[local] = text
    }

    fun addTranslate(local: String, translation: String): I18nText {
        val copy = this.clone()
        copy.translations[local] = translation
        return copy
    }

    override fun toString(): String {
        return this.toString(I18nText.local)
    }

    private fun clone(): I18nText {
        val newI18n = I18nText()
        for ((k, v) in this.translations) {
            newI18n.translations[k] = v
        }
        return newI18n
    }
    fun toString(local: String): String {
        val translation = this.translations[local]
        if (translation === null) {
            return "TRANSLATION_NOT_FOUNT[\"${this.default}\"]"
        }

        return translation
    }
}

fun main() {
    println("current env lang: ${I18nText.local}")
    val helloWorldText = I18nText("Hallo, Welt")
        .addTranslate("en", "Hello, world")
        .addTranslate("zh", "你好，世界")

    for( code in arrayOf("de", "en", "fr", "zh")) {
        println("$code => ${helloWorldText.toString(code)}")
    }
}