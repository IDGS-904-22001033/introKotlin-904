package org.utl.introkotlin904.TemasKotlin

// Realizar una aplicacion con un menu que me permita seleccionar diferentes operaciones basicas
// Además de solo utilizar do while
fun sumaCalculadora(a: Int, b: Int): Int {
    return a + b
}

fun restaCalculadora(a: Int, b: Int): Int {
    return a - b
}

fun multiplicacion(a: Int, b: Int): Int {
    return a * b
}

fun division(a: Int, b: Int): Double {
    return a.toDouble() / b.toDouble()
}

fun main() {
    var op = 0

    while (op != 5) {
        println("\nBienvenido a calculadora, ingrese la opción que quiera realizar:")
        println("1. Sumar")
        println("2. Restar")
        println("3. Multiplicar")
        println("4. Dividir")
        println("5. Salir")

        print("\nSeleccione una opcion: ")

        try {
            op = readln().toInt()
            if (op == 5) {
                println("Saliendo del programa...")
                break
            }
            if (op in 1..4) {
                print("Ingrese el primer numero: ")
                val num1 = readln().toInt()
                print("Ingrese el segundo numero: ")
                val num2 = readln().toInt()

                when (op) {
                    1 -> println("Resultado de la suma es: = ${sumaCalculadora(num1, num2)}")
                    2 -> println("Resultado de la resta es: = ${restaCalculadora(num1, num2)}")
                    3 -> println(
                        "Resultado de la multiplicacion es: = ${
                            multiplicacion(
                                num1,
                                num2
                            )
                        }"
                    )

                    4 -> {
                        if (num2 == 0) {
                            println("Error, no se puede dividir entre cero")
                        } else {
                            println("Resultado de la division es: = ${division(num1, num2)}")
                        }
                    }
                }
            } else if (op != 5) {
                println("Opción no válida. Por favor ingrese un numero entre 1 y 5.")
            }
        } catch (e: Exception) {
            println("Error, entrada invalida. Por favor intente con ingrese un numero.")
            readln()
            op = 0
        }
    }
}