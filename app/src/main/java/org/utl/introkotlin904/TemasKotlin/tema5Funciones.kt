package org.utl.introkotlin904.TemasKotlin

fun saludo(){
    println("Hola mundo")
}

fun suma(a:Int,b:Int){
    println("La suma de $a + $b es ${a+b}")
}

fun resta(a:Int,b:Int):Int{
    return a-b
}

fun resta2(a:Int, b:Int)=a-b

fun main() {
    saludo()
    suma(2,3)
    println("La resta de 2-3 es ${resta(2,3)}")
}