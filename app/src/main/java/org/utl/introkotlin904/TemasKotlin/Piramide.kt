package org.utl.introkotlin904.TemasKotlin

// Realizar un programa para generar una piramide de asteriscos en base a la cantidad que ingrese el usuario
// Se termina cuando se ingrese un 0

fun generarPiramide(altura: Int) {
    for (i in 1..altura) {
        for (j in 1..(altura - i)) {
            print(" ")
        }
        for (k in 1..(2 * i - 1)) {
            print("*")
        }

        println()
    }
}

fun main() {
    var numero: Int
    println("Programa de piramide de asteriscos")
    println("Ingrese un numero para generar una piramide de esa altura (0 para salir)")

    do {
        print("\nIngrese un numero: ")

        try {
            numero = readln().toInt()

            if (numero > 0) {
                println("\nPiramide de altura $numero:")
                generarPiramide(numero)
            } else if (numero < 0) {
                println("Por favor ingrese un numero positivo o 0 para salir.")
            }

        } catch (e: Exception) {
            println("Entrada invalida, ingrese un numero entero.")
            numero = -1
        }

    } while (numero != 0)

    println("Saliendo del programa...")
}
