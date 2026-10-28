package com.example.appcursos.vista

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import com.example.appcursos.R
import com.example.appcursos.modelo.dataDetalleCurso
import com.example.appcursos.presentador.clsCursosPresentador

class RegistroCurso : AppCompatActivity() {
    private lateinit var imgCursoRegistro: ImageView
    private lateinit var lblNombreCurso: TextView
    private lateinit var txtMatriculaAlumno: EditText
    private lateinit var txtFechaRegistro: EditText
    private lateinit var txtCorreoInstitucional: EditText
    private lateinit var btnConfirmarInscripcion: Button
    private lateinit var btnCancelar: Button

    private lateinit var presentador: clsCursosPresentador
    private var cursoId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_curso)

        txtMatriculaAlumno = findViewById(R.id.txtMatriculaAlumno)
        txtFechaRegistro = findViewById(R.id.txtFechaRegistro)
        txtCorreoInstitucional = findViewById(R.id.txtCorreoInstitucional)
        btnConfirmarInscripcion = findViewById(R.id.btnConfirmarInscripcion)
        btnCancelar = findViewById(R.id.btnCancelar)
        imgCursoRegistro = findViewById(R.id.imgCursoRegistro)
        lblNombreCurso = findViewById(R.id.lblNombreCurso)

        presentador = clsCursosPresentador(this)
        txtFechaRegistro.setText(java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date()))
        txtMatriculaAlumno.doOnTextChanged { texto, _, _, _ ->
            txtCorreoInstitucional.setText(if (texto.isNullOrEmpty()) "" else "${texto.toString().trim()}@uthh.edu.mx")
        }

        cursoId = intent.getIntExtra("id_curso", intent.getIntExtra("curso_id", 1))

        presentador.cargarDetalleCurso(cursoId)

        btnConfirmarInscripcion.setOnClickListener(this::ejecutarConfirmarInscripcion)
        btnCancelar.setOnClickListener(this::ejecutarCancelar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun ejecutarConfirmarInscripcion(v: View) {
        val matricula = txtMatriculaAlumno.text.toString().trim()
        val fecha = txtFechaRegistro.text.toString().trim()
        val correo = txtCorreoInstitucional.text.toString().trim()

        presentador.validarYRegistrar(cursoId, matricula, fecha, correo)
    }

    private fun ejecutarCancelar(v: View) {
        val alerta = AlertDialog.Builder(this)
        alerta.setTitle("Cancelar inscripción")
        alerta.setMessage("¿Estás seguro de que deseas cancelar la inscripción?")
        alerta.setPositiveButton("Sí") { dialog, _ -> finish() }
        alerta.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
        alerta.show()
    }

    fun mostrarDatosCurso(nombreCurso: String, urlImagen: String) {
        lblNombreCurso.text = nombreCurso
        lblNombreCurso.setTextColor(Color.BLACK)

        if (!urlImagen.isNullOrEmpty()) {
            val baseUrl = "http://192.168.10.126/cursos_api/"
            val rutaLimpia = urlImagen.removePrefix("/")
            val urlFinal = if (urlImagen.startsWith("http")) urlImagen else baseUrl + rutaLimpia

            Glide.with(this)
                .load(urlFinal)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(imgCursoRegistro)
        }
    }

    fun mostrarMensajeExito(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        finish()
    }

    fun mostrarMensajeError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}