import java.lang.Exception
import java.util.*

class VocabularyQuestion {
    private val question: String;
    private val answer: String
    private val options = LinkedList<String>()
    companion object {
        private const val OPTION_LEN = 3
        val vocabulary: Map<String, String> = Config("vocabulary.conf").map
    }

    init {
        val shuffled = vocabulary.entries.shuffled().take(OPTION_LEN)
        val word = shuffled.first()
        question = word.key
        answer = word.value

        shuffled.shuffled().forEach { options.add(it.value) }
    }

    fun print() {
        println("Wie heißt \"$question\" in Japanisch?")
        options.forEachIndexed { index, value ->
            println("${index + 1}) $value")
        }
    }

    fun ask(): String {
        var inputNumber = 0
        while (true) {
            print("Bitte geben Sie die Nummer der Option ein: ")
            try {
                inputNumber = readln().toInt()
            } catch (_: Exception) {}

            if (inputNumber in 1..OPTION_LEN)  break
            println("Die von Ihnen angegebene Option liegt außerhalb des Bereichs.")
        }

        return options[inputNumber - 1]
    }

    fun check(inputAnswer: String): Boolean {
        if (inputAnswer == answer) {
            println("√  \"$inputAnswer\" ist richtig.")
            return true
        }
        println("×  \"$inputAnswer\" ist falsch.")
        return false
    }
}

fun askQuestion() {
    val question = VocabularyQuestion()
    question.print()
    var inputAnswer: String
    do {
        inputAnswer = question.ask()
    } while (!question.check(inputAnswer))
}

fun main() {
    while (true) {
        askQuestion()
    }
}