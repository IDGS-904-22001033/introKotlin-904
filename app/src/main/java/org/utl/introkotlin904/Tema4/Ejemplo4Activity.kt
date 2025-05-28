package org.utl.introkotlin904.Tema4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.R
import org.w3c.dom.Text
import java.io.FileNotFoundException

class Ejemplo4Activity : AppCompatActivity() {

    private val fileName = "datos.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejemplo5)

        val inputText = findViewById<EditText>(R.id.inputText)
        val outputText = findViewById<TextView>(R.id.outputText)
        val btnBorrar = findViewById<Button>(R.id.btnBorrar)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnLeer = findViewById<Button>(R.id.readButton)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBorrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmación")
            builder.setMessage("¿Quieres borrar el texto?")
            builder.setPositiveButton("Si") { _, _ ->
                try{
                    openFileOutput("datos.txt", MODE_PRIVATE).use {
                        it.write("".toByteArray())
                    }
                    Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show()

                }catch (e: Exception){
                    e.printStackTrace()
                    Toast.makeText(this,"Error al borrar los datos", Toast.LENGTH_SHORT).show()
                    }
                }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        btnGuardar.setOnClickListener {
            val texto = inputText.text.toString()
            try {
                openFileOutput(fileName, MODE_PRIVATE).use {
                    it.write(texto.toByteArray())
                }
                inputText.text.clear()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
            }
        }

        btnLeer.setOnClickListener {
            try {
                val contenido = openFileInput(fileName).bufferedReader().useLines { lines ->
                    lines.joinToString("\n")
                }
                outputText.text = contenido
            }
            catch(e: FileNotFoundException){
                outputText.text = "Archivo no encontrado"
            }

            catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al leer los datos", Toast.LENGTH_SHORT).show()
            }
        }

        btnGuardar.setOnClickListener {
            val texto = inputText.text.toString()
            try{
                openFileOutput(fileName, MODE_PRIVATE).use {
                    it.write(texto.toByteArray())
                }
                inputText.text.clear()
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
            }
        }



    }
//
//    private fun verificarPalindromo(texto: String): Boolean {
//        val textoLimpio = texto.toLowerCase().replace(Regex("[^a-zA-Z0-9]"), "")
//        val longitud = textoLimpio.length
//        for (i in 0 until longitud / 2) {
//            if (textoLimpio[i] != textoLimpio[longitud - 1 - i]) {
//                return false
//            }
//    }
//        return true
//    }


}