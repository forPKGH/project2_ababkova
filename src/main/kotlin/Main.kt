fun num1() {
    println("Программа выполняет подсчитывает количество различных цифр в массиве, значения которого вводятся с клавиатуры.")
    println("Введите количество строк в матрице: ")
    val lines = readln()
    println("Введите количество столбцов в матрице: ")
    val columns = readln()

    val arr: Array<Array<String>> = Array(lines.toInt()) { i ->
        Array(columns.toInt()) { j ->
            println("Введите значение для строки ${i + 1}, столбца ${j + 1}: ")
            readln()
        }
    }

    val uniqueDigits: MutableSet<String> = mutableSetOf()
    println("\nВаш массив: ")
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            print(arr[i][j].padEnd(5) + " ")
            arr[i][j].split("").forEach { item -> uniqueDigits.add(item) }
        }
        println()
    }

    print("\nУникальные цифры в массиве: ")
    for (i in uniqueDigits) {
        print("$i ")
    }

    println("\nКоличество уникальных цифр в массиве: ${uniqueDigits.size - 1}\n")
}

fun num2() {
    println("Программа определяет симметричен ли массив размером 5х5 относительно главной диагонали.")

    val arr: Array<Array<String>> = Array(5) { i ->
        Array(5) { j ->
            println("Введите значение для строки ${i + 1} столбца ${j + 1}: ")
            readln()
        }
    }

    println("\nВаш массив: ")
    for (i in arr.indices) {
        for (j in i + 1 until arr[i].size) {
            print(arr[i][j].padEnd(5) + " ")
        }
        println()
    }

    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] != arr[j][i]) {
                println("\nМассив НЕ симметричен относительно главной диагонали\n")
                return
            }
        }
    }

    println("\nМассив симметричен относительно главной диагонали\n")
}

fun num3() {
    println("Программа зашифровывает или расшифровывает кириллический текст")

    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
    val numbers: Array<Int> = arrayOf(21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17)
    var result = ""

    val cypher = alphabet.indices.associate { i ->
        alphabet[i] to numbers[i]
    }

    val reverseChypher = numbers.indices.associate { i ->
        numbers[i] to alphabet[i]
    }

    var action = "-1"
    while (action != "З" && action != "Р") {
        println("Введите з чтобы зашифровать или р чтобы расшифровать")
        action = readln().uppercase()
    }

    var keyWord = ""

    while (keyWord.isEmpty()) {
        println("Введите ключевое слово: ")
        keyWord = readln().uppercase()

        if (keyWord.isEmpty()) {
            println("Ключевое слово может быть пустым")
        }
    }

    var word = ""

    while (word.isEmpty()) {
        println("Введите текст: ")
        word = readln().uppercase()

        if (word.isEmpty()) {
            println("Текст не может быть пустым")
        }
    }

    when (action) {
        "З" -> {
            for (i in word.indices) {
                val keyWordIndex = i % keyWord.length
                val keyLetter = keyWord[keyWordIndex]
                val wordLetter = word[i]

                val cypherKeyLetterIndex = cypher[keyLetter] ?: 0
                val cypherWordLetterIndex = cypher[wordLetter] ?: 0

                var encryptedWordLetterIndex = (cypherWordLetterIndex + cypherKeyLetterIndex) % 33

                if(encryptedWordLetterIndex == 0)
                encryptedWordLetterIndex = 33

                val encryptedWordLetter = reverseChypher[encryptedWordLetterIndex]

                result += encryptedWordLetter
            }
            println("Результат шифрования: $result")
        }

        "Р" -> {
            for (i in word.indices) {
                val keyWordIndex = i % keyWord.length
                val keyLetter = keyWord[keyWordIndex]
                val wordLetter = word[i]

                val cypherKeyLetterIndex = cypher[keyLetter] ?: 0
                val cypherWordLetterIndex = cypher[wordLetter] ?: 0

                var decryptedWordLetterIndex = (cypherWordLetterIndex - cypherKeyLetterIndex + 33) % 33
                if(decryptedWordLetterIndex == 0)
                    decryptedWordLetterIndex = 33

                val decryptedWordLetter = reverseChypher[decryptedWordLetterIndex]

                result += decryptedWordLetter
            }
            println("Результат расшифрования: $result")
        }
    }
}

fun num4 () {

}
fun main() {
    while (true) {
        println("Введите номер задачи (1-5) или 0 чтобы завершить")
        when (readln()) {
            "0" -> {
                println("Выполнение завершено")
                return
            }

            "1" -> {
                num1()
            }

            "2" -> {
                num2()
            }

            "3" -> {
                num3()
            }

            "4" -> {
                num4()
            }

            "5" -> {
//                num5()
            }

            else -> println(
                "Введите правильный номер задачи (1-5) или завершите программу (0)"
            )

        }
    }
}