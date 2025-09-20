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
            print(arr[i][j].padEnd(5)+" ")
            arr[i][j].split("").forEach{item -> uniqueDigits.add(item)}
        }
        println()
    }

    print("\nУникальные цифры в массиве: ")
    for (i in uniqueDigits) {
        print("$i ")
    }

    println("\nКоличество уникальных цифр в массиве: ${uniqueDigits.size-1}\n")
}

fun num2() {
    val arr: Array<Array<String>> = Array(5) {i ->
        Array(5) {
            j ->
            println("Введите значение для строки ${i+1} столбца ${j+1}: ")
            readln()
        }
    }

    println("\nВаш массив: ")
    for (i in arr.indices) {
        for (j in arr[i].indices) {
            print(arr[i][j].padEnd(5)+" ")
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
        j++;
    }

    println("\nМассив симметричен относительно главной диагонали\n")
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

            else -> println(
                "Введите правильный номер задачи (1-6) или завершите программу (0)"
            )
        }
    }
}