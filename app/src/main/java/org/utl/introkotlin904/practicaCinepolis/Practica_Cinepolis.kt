package org.utl.introkotlin904.practicaCinepolis

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.utl.introkotlin904.R
import java.text.NumberFormat
import java.util.Locale

class Practica_Cinepolis : AppCompatActivity() {

     lateinit var nombreCliente: EditText
     lateinit var compradores: EditText
     lateinit var cantBoletos: EditText
     lateinit var pagoTotal: TextView
     lateinit var btnCalcular: Button
     lateinit var radioGroup: RadioGroup
     lateinit var btnSi: RadioButton
     lateinit var btnNo: RadioButton

     val precioUnitario = 12.00  // $12.00 por boleto
     val descuento3a5 = 0.10     // 10% para 3-5 boletos
     val descuentoMas5 = 0.15    // 15% para más de 5 boletos
     val descuentoCineco = 0.10  // 10% adicional con tarjeta CINECO
     val maxBoletosPorPersona = 7 // Máximo 7 boletos por persona

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practica_cinepolis)

        nombreCliente = findViewById(R.id.nombreCliente)
        compradores = findViewById(R.id.compradores)
        cantBoletos = findViewById(R.id.cantBoletos)
        pagoTotal = findViewById(R.id.pagoTotal)
        btnCalcular = findViewById(R.id.btnCalcular)
        radioGroup = findViewById(R.id.radioGroup)
        btnSi = findViewById(R.id.btnSi)
        btnNo = findViewById(R.id.btnNo)
        btnNo.isChecked = true

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularPago(view: android.view.View) {
        // Validar campos de Cliente, compradores y de la cantidad de los boletos a comprar
        val nombre = nombreCliente.text.toString().trim()
        if (nombre.isEmpty()) {
            Toast.makeText(this,"Ingrese el nombre del comprador", Toast.LENGTH_SHORT).show()
            return
        }
        val numCompradores = try {
            compradores.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this,"Agregue un número que sea válido de compradores", Toast.LENGTH_SHORT).show()
            return
        }
        if (numCompradores <= 0) {
            Toast.makeText(this,"Debe existir al menos un comprador", Toast.LENGTH_SHORT).show()
            return
        }
        val numBoletos =
        try {
            cantBoletos.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Agregue un número que sea válido de boletos", Toast.LENGTH_SHORT).show()
            return
        }
        // validar el número de boletos x persona
        val limiteBoletos = maxBoletosPorPersona * numCompradores
        if (numBoletos < 1 || numBoletos > limiteBoletos) {
            Toast.makeText(this, "Sólo se puede comprar entre 1 y $limiteBoletos boletos (Máximo $maxBoletosPorPersona boletos por persona)", Toast.LENGTH_LONG).show()
            return
        }
        val total = calcularPagoConDescuentos(numBoletos)
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
        pagoTotal.text = formatoMoneda.format(total)
        mostrarResumenCompra(nombre, numBoletos, total)
    }

     fun calcularPagoConDescuentos(numBoletos: Int): Double {
        // Aplicar descuentos según la cantidad de boletos comprados y tarjeta CINECO si se cuenta con este
        var total = numBoletos * precioUnitario
        total = when {
            numBoletos in 3..5 -> total * (1 - descuento3a5)
            numBoletos > 5 -> total * (1 - descuentoMas5)
            else -> total
        }
        if (radioGroup.checkedRadioButtonId == R.id.btnSi) {
            total *= (1 - descuentoCineco)
        }
        return total
    }

     fun mostrarResumenCompra(nombre: String, numBoletos: Int, total: Double) {
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
        val tieneDescuento = radioGroup.checkedRadioButtonId == R.id.btnSi

        val mensaje = """
        Nombre del comprador: $nombre
        Boletos comprados: $numBoletos
        Total a pagar: ${formatoMoneda.format(total)}
        ${if (tieneDescuento) 
            "✓ Descuento por tarjeta CINECO (10% adicional)" 
        else "✗ Sin tarjeta CINECO"}""".trimIndent()

        val builder = AlertDialog.Builder(this)
            .setTitle("Ticket de compra")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.8).toInt(),
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}