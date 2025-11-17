fun num1() {
    println("Программа подсчитывает количество различных цифр в массиве, значения которого вводятся с клавиатуры.")
    var rows = ""
    var columns = ""
    while (true) {
        println("Введите количество строк в массиве: ")
        rows = readln()
        if(rows.toIntOrNull() == null || rows.toIntOrNull() == 0) {
            println("Неверное количество строк")
        } else {
            break
        }
    }

    while (true) {
        println("Введите количество столбцов в массиве: ")
        columns = readln()
        if(columns.toIntOrNull() == null || columns.toIntOrNull() == 0) {
            println("Неверное количество столбцов")
        } else {
            break
        }
    }

    val arr: Array<Array<Int>> = Array(rows.toInt()) {Array(columns.toInt()) {0}}

    for (i in 0 until rows.toInt()) {
        for (j in 0 until columns.toInt()) {
            while (true) {
                println("Введите значение для строки ${i+1} столбца ${j+1}")
                val item = readln()
                if(item.toIntOrNull() == null) {
                    println("Ошибка ввода данных ")
                } else {
                    arr[i][j] = item.toInt()
                    break
                }
            }
        }
    }

    val uniqueDigits: MutableSet<Char> = mutableSetOf()
    println("\nВаш массив: ")
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            print(arr[i][j].toString().padEnd(5) + " ")
            arr[i][j].toString().toCharArray().forEach { item -> uniqueDigits.add(item) }
        }
        println()
    }

    print("\nУникальные цифры в массиве: ")
    for (i in uniqueDigits) {
        print("$i ")
    }

    println("\nКоличество уникальных цифр в массиве: ${uniqueDigits.size}\n")
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
        for (j in arr.indices) {
            print(arr[i][j].padEnd(5) + " ")
        }
        println()
    }

    for (i in arr.indices) {
        for (j in i+1 until arr.size) {
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

    var action = ""
    while (action != "З" && action != "Р") {
        println("Введите З чтобы зашифровать или Р чтобы расшифровать")
        action = readln().uppercase()
    }

    var keyWord = ""

    while (true) {
        println("Введите ключевое слово: ")
        keyWord = readln().uppercase().replace(" ", "")

        if (keyWord.isEmpty()) {
            println("Ключевое слово не может быть пустым")
        } else if (keyWord.contains(Regex("[^А-ЯЁа-яё]"))) {
            println("Программа работает только с кириллицей")
        } else break
    }

    var word = ""

    while (true) {
        println("Введите слово: ")
        word = readln().uppercase().replace(" ", "")

        if (word.isEmpty()) {
            println("Слово не может быть пустым")
        } else if (word.contains(Regex("[^А-ЯЁа-яё]"))) {
            println("Программа работает только с кириллицей")
        } else break
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
    println("Программа ищет пересечения числовых массивов. Пример: [1, 2, 3, 2, 0] и [5, 1, 2, 7, 3, 2, 2] -> [1, 2, 2, 3]")

    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    val result = mutableListOf<Int>()

    var input = ""

    println("Введите числа первого массива. Чтобы ввести число нажмите Enter. Чтобы завершить ввод введите \\: ")
    while (true) {
        input = readln()
        if (input == "\\") break
        while (true) {
            if(input.toIntOrNull() == null) {
                println("Ошибка ввода данных, попробуйте ещё раз")
                break
            } else {
                list1.add(input.toIntOrNull() ?: 0)
                break
            }
        }
    }

    println("Введите числа второго массива. Чтобы ввести число нажмите Enter. Чтобы завершить ввод введите \\: ")
    while (true) {
        input = readln()
        if (input == "\\") break
        while (true) {
            if(input.toIntOrNull() == null) {
                println("Ошибка ввода данных, попробуйте ещё раз")
                break
            } else {
                list2.add(input.toIntOrNull() ?: 0)
                break
            }
        }
    }

    for (i in list1) {
        for (j in list2) {
            if (i == j) {
                result.add(i)
                list2.remove(j)
               break
            }
        }
    }

    println(result.sorted())
}

fun num5 () {
    println("Программа ищет группы слов из одинаковых букв. \" Пример: [\"eat\", \"tea\", \"tan\", \"ate\", \"nat\", \"bat\"] -> \n\"ate\", \"eat\", \"tea\"\n\"nat\", \"tan\"\n\"bat\" ")

    val words = mutableListOf<String>()
    val groups = mutableMapOf<String, MutableList<String>>()

    println("Введите слова. Чтобы ввести слово нажмите Enter. Чтобы завершить ввод введите \\.")
    var input = ""
    while (true) {
        input = readln()
        if (input == "\\") break
        words.add(input.replace(" ", ""))
    }

    for (word in words) {
        val keyWord = word.toCharArray().sorted().joinToString("")

        if (groups.containsKey(keyWord)) {
            groups[keyWord]?.add(word)
        } else {
            groups[keyWord] = mutableListOf(word)
        }
    }

    for (group in groups.values) {
        println(group)
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
                num5()
            }

            else -> println(
                "Введите правильный номер задачи (1-5) или завершите программу (0)"
            )

        }
    }
}
