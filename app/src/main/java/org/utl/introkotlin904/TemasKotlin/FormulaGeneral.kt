package org.utl.introkotlin904.TemasKotlin
import kotlin.math.sqrt

// Hacer un programa que permita calcular la formula general
class ResultadoFormula(val cantidadSoluciones: Int, val x1: Double, val x2: Double)

fun formulaGeneral(a: Double, b: Double, c: Double): ResultadoFormula {
    val elementosRaiz = b * b - 4 * a * c

    return when {
        elementosRaiz < 0 -> {
            // No hay soluciones reales
            ResultadoFormula(0, 0.0, 0.0)
        }
        elementosRaiz == 0.0 -> {
            // Una solución única
            val x = -b / (2 * a)
            ResultadoFormula(1, x, x)
        }
        else -> {
            // Dos soluciones distintas
            val x1 = (-b + sqrt(elementosRaiz)) / (2 * a)
            val x2 = (-b - sqrt(elementosRaiz)) / (2 * a)
            ResultadoFormula(2, x1, x2)
        }
    }
}

fun main() {
    println("Calculo de Formula general (formula cuadratica)")
    println("Para una ecuacion de la forma ax2 + bx + c = 0")

    try {
        print("Ingrese el valor de a: ")
        val a = readln().toDouble()

        print("Ingrese el valor de b: ")
        val b = readln().toDouble()

        print("Ingrese el valor de c: ")
        val c = readln().toDouble()

        if (a == 0.0) {
            println("Error 'a' no puede ser cero. La ecuacion no es cuadratica.")
            return
        }
        val resultado = formulaGeneral(a, b, c)

        when (resultado.cantidadSoluciones) {
            0 -> println("La ecuacion no tiene soluciones reales.")
            1 -> println("La ecuacion tiene una unica solucion: x = ${resultado.x1}")
            2 -> {
                println("Las soluciones de la ecuacion son:")
                println("x₁ = ${resultado.x1}")
                println("x₂ = ${resultado.x2}")
            }
        }
    } catch (e: Exception) {
        println("Error entrada invalida, por favor intente con ingrese numeros validos.")
    }
}