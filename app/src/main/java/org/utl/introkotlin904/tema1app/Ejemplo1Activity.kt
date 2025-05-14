package org.utl.introkotlin904.tema1app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.R

class Ejemplo1Activity: AppCompatActivity() {
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var tv1: TextView
    private lateinit var btn1: Button

    private lateinit var radioGrupo: RadioGroup
    private lateinit var rb1: RadioButton
    private lateinit var rb2: RadioButton
    private lateinit var rb3: RadioButton
    private lateinit var rb4: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejemplo1)

         et1 = findViewById<EditText>(R.id.et1)
         et2 = findViewById<EditText>(R.id.et2)
         tv1 = findViewById<TextView>(R.id.tv1)
         btn1 = findViewById<Button>(R.id.btn1)

        radioGrupo = findViewById(R.id.radioGrupo)
        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        rb3 = findViewById(R.id.rb3)
        rb4 = findViewById(R.id.rb4)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun sumar(){
        val valor1 = et1.text.toString()
        val valor2 = et2.text.toString()
        val resultado = valor1.toDouble() + valor2.toDouble()
        tv1.text = resultado.toString()

    }

    fun resta(){
        val valor1 = et1.text.toString()
        val valor2 = et2.text.toString()
        val resultado = valor1.toDouble() - valor2.toDouble()
        tv1.text = resultado.toString()
    }

    fun multiplicacion(){
        val valor1 = et1.text.toString()
        val valor2 = et2.text.toString()
        val resultado = valor1.toDouble() * valor2.toDouble()
        tv1.text = resultado.toString()
    }
    fun division(){
        val valor1 = et1.text.toString()
        val valor2 = et2.text.toString()
        val resultado = valor1.toDouble() / valor2.toDouble()
        if (valor2.toDouble() == 0.0){
            Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
        }
        tv1.text = resultado.toString()
    }

    fun seleccionarOperacionBasica(view: android.view.View){
        val radioButtonSeleccionado = when {
            rb1.isChecked -> R.id.rb1
            rb2.isChecked -> R.id.rb2
            rb3.isChecked -> R.id.rb3
            rb4.isChecked -> R.id.rb4
            else -> -1
        }

        when (radioButtonSeleccionado) {
            R.id.rb1 -> multiplicacion()
            R.id.rb2 -> division()
            R.id.rb3 -> sumar()
            R.id.rb4 -> resta()
            else -> {
                Toast.makeText(this, "Por favor selecciona una operación", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Realizar un programa para poder calcular a x b y obtener su multiplicacion por sumas

}