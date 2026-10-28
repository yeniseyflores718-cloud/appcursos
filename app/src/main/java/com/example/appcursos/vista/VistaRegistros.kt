package com.example.appcursos.vista

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.R
import com.example.appcursos.modelo.dataResumenInscripcion
import com.example.appcursos.Adaptador.AdaptadorResumenInscripcion
import com.example.appcursos.presentador.clsVerRegistroPresentador

class VistaRegistros : AppCompatActivity() {

    private lateinit var txtTituloEstadoInscripciones: TextView
    private lateinit var spnFiltroCarrera: Spinner
    private lateinit var txtMostrarContador: TextView
    private lateinit var rvTarjetasCursos: RecyclerView
    private lateinit var btnVolverCatalogo: Button

    private lateinit var presentador: clsVerRegistroPresentador


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_registros)

        txtTituloEstadoInscripciones =findViewById(R.id.txtTituloEstadoInscripciones)
        spnFiltroCarrera = findViewById(R.id.spnFiltroCarrera)
        txtMostrarContador = findViewById(R.id.txtMostrarContador)
        rvTarjetasCursos = findViewById(R.id.rvTarjetasCursos)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)

        rvTarjetasCursos.layoutManager = LinearLayoutManager(this)
        btnVolverCatalogo.setOnClickListener { finish() }

        presentador = clsVerRegistroPresentador(this)
        presentador.obtenerResumenInscripcion()

    }
    fun mostrarInscripciones(inscripciones: List<dataResumenInscripcion>){
        rvTarjetasCursos.adapter = AdaptadorResumenInscripcion(inscripciones)
        txtMostrarContador.text = "Total de inscripciones: " + inscripciones.size
    }
    fun mostrarError(mensaje : String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

}