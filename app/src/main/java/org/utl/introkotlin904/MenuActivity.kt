package org.utl.introkotlin904

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.Tem2App.Ejemplo2Activity
import org.utl.introkotlin904.practicaCinepolis.Practica_Cinepolis
import org.utl.introkotlin904.tema1app.Ejemplo1Activity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val btnEjemplo1 = findViewById<Button>(R.id.btn1)
        val btnEjemploCinepolis = findViewById<Button>(R.id.btn2)
        val btnEjemplo2 = findViewById<Button>(R.id.btn3)

        btnEjemplo1.setOnClickListener { navigateToEjemplo1() }
        btnEjemploCinepolis.setOnClickListener { navigateToCinepolis() }
        btnEjemplo2.setOnClickListener { navigateToEjemplo2() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun navigateToEjemplo1() {
        val intent = Intent(this, Ejemplo1Activity::class.java)
        startActivity(intent)
    }

    private fun navigateToCinepolis(){
        val intent = Intent(this, Practica_Cinepolis::class.java)
        startActivity(intent)

    }
    private fun navigateToEjemplo2(){
        val intent = Intent(this, Ejemplo2Activity::class.java)
        startActivity(intent)

    }
}