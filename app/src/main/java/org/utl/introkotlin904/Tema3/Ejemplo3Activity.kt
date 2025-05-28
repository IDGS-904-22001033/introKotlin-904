package org.utl.introkotlin904.Tema3

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
import org.w3c.dom.Text

class Ejemplo3Activity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var btn1: Button
    private var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejemplo4)

        editText = findViewById<EditText>(R.id.et1)
        num = (Math.random()*10001).toInt()
        val cadena = num.toString()
        val notificacion = Toast.makeText(this, cadena, Toast.LENGTH_LONG)
        notificacion.show()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1 = findViewById<Button>(R.id.btn1)
        btn1.setOnClickListener {
            controlar(it)
        }

    }

fun controlar(view: android.view.View) {
    val valorIngresado : String = editText.text.toString()
    val valor = valorIngresado.toInt()

    if(valor == null){
        val notificacion = Toast.makeText(this, "Correcto", Toast.LENGTH_LONG)
        notificacion.show()
    }
    else{
        val notificacion = Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG)
        notificacion.show()
    }
}

}