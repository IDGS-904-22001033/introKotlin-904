package org.utl.introkotlin904.tema1app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.R

class Ejemplo2Activity : AppCompatActivity() {
    private lateinit var etNumeroA: EditText
    private lateinit var etNumeroB: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvOperacion: TextView
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejemplo2)

        etNumeroA = findViewById(R.id.etNumeroA)
        etNumeroB = findViewById(R.id.etNumeroB)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvOperacion = findViewById(R.id.tvOperacion)
        tvResultado = findViewById(R.id.tvResultado)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularMultiplicacionComoSuma(view: android.view.View) {
        val inputA = etNumeroA.text.toString()
        val inputB = etNumeroB.text.toString()

        try {
            val a = inputA.toInt()
            val b = inputB.toInt()

            if (a <= 0 || b <= 0) {
                Toast.makeText(this, "Por favor, ingresa valores positivos", Toast.LENGTH_SHORT).show()
                return
            }
            val resultado = a * b
            val mostrarOperacion = generarOperacionSuma(a, b)
            tvOperacion.text = mostrarOperacion
            tvResultado.text = "Resultado: $resultado"

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Error al realizar la operación con los numeros ingresados", Toast.LENGTH_SHORT).show()
        }
    }

     fun generarOperacionSuma(a: Int, b: Int): String {
        val sumas = StringBuilder()
        for (i in 1..b) {
            sumas.append(a)
            if (i < b) {
                sumas.append(" + ")
            }
        }
        return "$sumas = ${a * b}"
    }
}