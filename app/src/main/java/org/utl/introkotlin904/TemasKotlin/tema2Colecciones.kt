package org.utl.introkotlin904.TemasKotlin

/*
lista
Map
MutableMap
MutableSet
SetOf
MutableSetOf


val readOnlyFiguras= listOf("cuadrado","triangulo","circulo")
println(readOnlyFiguras)
var mutableFiguras = mutableListOf("cuadrado","triangulo","circulo")
val readOnlyFiguras= listOf("cuadrado","triangulo","circulo")
val mutableFiguras:List<String> = figura

val fruntas = setOf("manzana","banana","naranja")
val frutas= mutableSetOf("manzana","banana","naranja")

val coches= mapOf("uno" to 1,"dos" to 2, "tres" to 3)
println(coches)
val coches2= mutableMapOf("uno" to 1,"dos" to 2, "tres" to 3)
println(coches2)


* */


fun main (){
    val readOnlyFiguras= listOf("cuadrado","triangulo","circulo")
    println(readOnlyFiguras)
    println("la primera figura es ${readOnlyFiguras[0]}")
    println("el primer elemnto es ${readOnlyFiguras.first()}")
    println("numero de elementos ${readOnlyFiguras.count()} items")
    println(readOnlyFiguras)

    var figura: MutableList<String> = mutableListOf("cuadrado2", "triangulo2", "circulo2")
    println(figura)
    figura.add("pentagono2")
    println(figura)
    figura.remove("cuadrado2")
    println(figura)
}