package com.example.appcursos.vista

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appcursos.R
import com.example.appcursos.modelo.dataDetalleCurso
import com.example.appcursos.presentador.clsCursosPresentador
import com.example.appcursos.presentador.clsDetalleCursoPresentador

class DetalleCursoActivity : AppCompatActivity() {

    private lateinit var imgDeCurso: ImageView
    private lateinit var txtnombreDelcurso: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacio: TextView
    private lateinit var txtHoras: TextView
    private lateinit var txtDias: TextView
    private lateinit var presentador: clsDetalleCursoPresentador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_curso)

        imgDeCurso = findViewById(R.id.imgDeCurso)
        txtnombreDelcurso = findViewById(R.id.txtnombreDelcurso)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtEspacio = findViewById(R.id.txtEspacio)
        txtHoras = findViewById(R.id.txtHoras)
        txtDias = findViewById(R.id.txtDias)

        presentador = clsDetalleCursoPresentador(this)
        //Datos enviados desde MainActivity
        val idCurso = intent.getIntExtra("id_curso", 0)
        val nombre = intent.getStringExtra("nombre") ?: ""
        val codigo = intent.getStringExtra("codigo") ?: ""
        val descripcion = intent.getStringExtra("descripcion") ?: ""
        val espacio = intent.getStringExtra("espacio") ?: ""
        val horas = intent.getIntExtra("total_horas", 0)
        val dias = intent.getStringExtra("dias_atencion") ?: ""

        txtnombreDelcurso.text = nombre
        txtCodigo.text = "Código: $codigo"
        txtDescripcion.text = descripcion
        txtEspacio.text = espacio
        txtHoras.text = "$horas Horas"
        txtDias.text = dias

        // La portada sale de imagenes_curso (2ª imagen)
        presentador = clsDetalleCursoPresentador(this)
        if (idCurso != 0) {
            presentador.cargarImagenes(idCurso)
        }
    }


    fun mostrarImagenes(imagenes: List<dataDetalleCurso>) {
        val portada = imagenes.getOrNull(1) ?: imagenes.firstOrNull()
        portada?.let {
            val baseUrl = "http://192.168.200.14/cursos_api/"
            Glide.with(this)
                .load(baseUrl + it.urlImagen)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(imgDeCurso)
        }
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}