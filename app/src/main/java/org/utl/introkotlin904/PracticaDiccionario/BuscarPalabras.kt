package org.utl.introkotlin904.PracticaDiccionario

import android.content.Intent
import android.net.Uri
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
import java.io.BufferedReader
import java.io.InputStreamReader

class BuscarPalabras : AppCompatActivity() {
    private lateinit var radioGrupo: RadioGroup
    private lateinit var radioEspaniol: RadioButton
    private lateinit var radioIngles: RadioButton
    private lateinit var edtBuscarPalabra: EditText
    private lateinit var btnBuscar: Button
    private lateinit var edtMostrarMensaje: TextView
    private lateinit var btnRegresarMenu: Button
    private lateinit var tvPalabraMostrada: TextView
    private var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buscar_palabras)

        radioGrupo = findViewById(R.id.radioGroup2)
        radioEspaniol = findViewById(R.id.idRadioEspaniol)
        radioIngles = findViewById(R.id.idRadioIngles)
        edtBuscarPalabra = findViewById(R.id.edtBuscarPalabra)
        edtMostrarMensaje = findViewById(R.id.edtMostrarMensaje)
        btnBuscar = findViewById(R.id.button)
        btnRegresarMenu = findViewById(R.id.btnRegresarMenu)
        tvPalabraMostrada = findViewById(R.id.tvPalabraMostrada)

        fileUri = intent.getParcelableExtra("FILE_URI")?: CapturarPalabras.getSharedFileUri(this)

        btnBuscar.setOnClickListener {
            buscarPalabra()
        }

        btnRegresarMenu.setOnClickListener {
            val intent = Intent(this, PracticaDiccionario::class.java)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun buscarPalabra() {
        val palabraBuscada = edtBuscarPalabra.text.toString().trim()

        if (palabraBuscada.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa una palabra para buscar.", Toast.LENGTH_SHORT).show()
            return
        }

        if (radioGrupo.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Por favor, selecciona el idioma de traducción.", Toast.LENGTH_SHORT).show()
            return
        }

        if (fileUri == null) {
            Toast.makeText(this, "No se ha encontrado el archivo del diccionario.", Toast.LENGTH_LONG).show()
            return
        }

        val buscarTraduccionEspanol = radioEspaniol.isChecked

        try {
            contentResolver.openInputStream(fileUri!!)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var linea: String? = reader.readLine()
                    var palabraEncontrada: String? = null

                    while (linea != null) {
                        val partes = linea.split("-")
                        if (partes.size == 2) {
                            val palabraIngles = partes[0].trim()
                            val palabraEspanol = partes[1].trim()

                            if (buscarTraduccionEspanol) {
                                if (palabraIngles.equals(palabraBuscada, ignoreCase = true)) {
                                    palabraEncontrada = palabraEspanol
                                    break
                                }
                            } else {
                                if (palabraEspanol.equals(palabraBuscada, ignoreCase = true)) {
                                    palabraEncontrada = palabraIngles
                                    break
                                }
                            }
                        }
                        linea = reader.readLine()
                    }

                    if (palabraEncontrada != null) {
                        edtMostrarMensaje.setText("Se encontró palabra")
                        tvPalabraMostrada.text = palabraEncontrada
                    } else {
                        edtMostrarMensaje.setText("Palabra no encontrada")
                        tvPalabraMostrada.text = ""
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al leer el archivo: ${e.message}", Toast.LENGTH_LONG).show()
            edtMostrarMensaje.setText("Error al buscar")
            tvPalabraMostrada.text = ""
        }
    }
}