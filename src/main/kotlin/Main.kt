fun num1(lines: String, columns: String) {
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

    var action = "-1"
    while (action != "З" && action != "Р") {
        println("Введите з чтобы зашифровать или р чтобы расшифровать")
        action = readln().uppercase()
    }

    var keyWord = ""

    while (keyWord.isEmpty()) {
        println("Ключевое слово и текст не могут быть пустыми")
        println("Введите ключевое слово: ")
        keyWord = readln().uppercase()
    }

    var word = ""

    while (word.isEmpty()) {
        println("Ключевое слово и текст не могут быть пустыми")
        println("Введите текст: ")
        word = readln().uppercase()
    }

    when (action) {
        "З" -> {
            for (i in 0 until word.length) {
                val keyWordIndex = i % keyWord.length
                val keyLetter = keyWord[keyWordIndex]
                var wordLetter = word[i]

                val cypherKeyLetterIndex = cypher[keyLetter]
                val cypherWordLetterIndex = cypher[wordLetter]

                var encryptedWordLetterIndex = ((cypherWordLetterIndex ?: 0) + (cypherKeyLetterIndex ?: 0)) % 33

                if(((cypherWordLetterIndex ?: 0) + (cypherKeyLetterIndex ?: 0)) % 33 == 0)
                encryptedWordLetterIndex = 1

                val encryptedWordLetter = cypher.entries.find { it.value == encryptedWordLetterIndex }
                result += encryptedWordLetter?.key
            }
            println("Результат шифрования: $result")
        }

        "Р" -> {
            for (i in 0 until word.length) {
                val keyWordIndex = i % keyWord.length
                val keyLetter = keyWord[keyWordIndex]
                var wordLetter = word[i]

                val cypherKeyLetterIndex = cypher[keyLetter]
                val cypherWordLetterIndex = cypher[wordLetter]

                var encryptedWordLetterIndex = (((cypherKeyLetterIndex ?: 0) - (cypherWordLetterIndex ?: 0)) + 33) % 33


                val encryptedWordLetter = cypher.entries.find { it.value == encryptedWordLetterIndex }
                result += encryptedWordLetter?.key
            }ы
            println("Результат расшифрования: $result")
        }

        else -> {
        }
    }
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
                println("Программа выполняет подсчитывает количество различных цифр в массиве, значения которого вводятся с клавиатуры.")
                println("Введите количество строк в матрице: ")
                val lines = readln()
                println("Введите количество столбцов в матрице: ")
                val columns = readln()
                num1(lines, columns)
            }

            "2" -> {
                println("Программа определяет симметричен ли массив размером 5х5 относительно главной диагонали.")
                num2()
            }

            "3" -> {
                num3()
            }

            else -> println(
                "Введите правильный номер задачи (1-5) или завершите программу (0)"
            )

        }
    }
}