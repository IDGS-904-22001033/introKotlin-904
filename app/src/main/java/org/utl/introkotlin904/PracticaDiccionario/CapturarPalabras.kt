package org.utl.introkotlin904.PracticaDiccionario

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.R
import java.io.OutputStreamWriter

class CapturarPalabras : AppCompatActivity() {
    private lateinit var edtIngles: EditText
    private lateinit var edtEspañol: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnRegresar: Button
    private var fileUri: Uri? = null
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_capturar_palabras)

        sharedPreferences = getSharedPreferences("diccionario_prefs", MODE_PRIVATE)

        edtIngles = findViewById(R.id.edtIngles)
        edtEspañol = findViewById(R.id.edtEspañol)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnRegresar = findViewById(R.id.btnRegresar)

        recuperarUriArchivo()

        btnRegresar.setOnClickListener {
            val intent = Intent(this, PracticaDiccionario::class.java)
            startActivity(intent)
            finish()
        }

        btnGuardar.setOnClickListener {
            guardarPalabras()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun recuperarUriArchivo() {
        val uriString = sharedPreferences.getString("archivo_diccionario_uri", null)
        if (uriString != null) {
            fileUri = Uri.parse(uriString)
            Toast.makeText(this, "Archivo del diccionario cargado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarUriArchivo(uri: Uri) {
        val editor = sharedPreferences.edit()
        editor.putString("archivo_diccionario_uri", uri.toString())
        editor.apply()
    }

    private fun abrirCrearArchivo() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "diccionario.txt")
        }
        crearArchivo.launch(intent)
    }

    private val crearArchivo =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.also { uri ->
                    fileUri = uri

                    val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    contentResolver.takePersistableUriPermission(uri, takeFlags)

                    guardarUriArchivo(uri)
                    Toast.makeText(this, "Archivo creado, vuelva a guardar las palabras", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "No se pudo crear el archivo.", Toast.LENGTH_SHORT).show()
            }
        }

    private fun guardarPalabras() {
        val ingles = edtIngles.text.toString().trim()
        val español = edtEspañol.text.toString().trim()

        if (ingles.isEmpty() || español.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambas palabras.", Toast.LENGTH_SHORT).show()
            return
        }

        if (fileUri == null) {
            mostrarDialogoSeleccionarArchivo()
            return
        }

        try {
            contentResolver.openOutputStream(fileUri!!, "wa")?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.append("$ingles-$español\n")
                    writer.flush()
                }
            }

            mostrarAlerta("Palabras Almacenadas", "Ambas palabras '$español - $ingles' han sido agregados al diccionario.")

            edtIngles.text.clear()
            edtEspañol.text.clear()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error al guardar las palabras: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun mostrarDialogoSeleccionarArchivo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Archivo de Diccionario")
        builder.setMessage("No se ha seleccionado un archivo para el diccionario. ¿Deseas crear o seleccionar un archivo?")
        builder.setPositiveButton("Crear/Seleccionar") { dialog, _ ->
            abrirCrearArchivo()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun mostrarAlerta(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    companion object {
        fun getSharedFileUri(context: android.content.Context): Uri? {
            val prefs = context.getSharedPreferences("diccionario_prefs", MODE_PRIVATE)
            val uriString = prefs.getString("archivo_diccionario_uri", null)
            return if (uriString != null) Uri.parse(uriString) else null
        }
    }
}