package org.utl.introkotlin904.PracticaDiccionario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.R

class PracticaDiccionario : AppCompatActivity() {
    lateinit var btnCapturarPalabras: Button
    lateinit var btnBuscarPalabras: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practica_diccionario)

        btnCapturarPalabras = findViewById(R.id.btnCapturarPalabras)
        btnBuscarPalabras = findViewById(R.id.btnBuscarPalabras)

        btnCapturarPalabras.setOnClickListener {
            val intent = Intent(this, CapturarPalabras::class.java)
            startActivity(intent)
        }

        btnBuscarPalabras.setOnClickListener {
            val intent = Intent(this, BuscarPalabras::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}