package com.example.appcursos.vista
import android.widget.ArrayAdapter
import android.widget.AdapterView
import com.example.appcursos.modelo.dataCarreras
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
import com.example.appcursos.modelo.clsCarrerasModelo
import com.example.appcursos.presentador.clsVerRegistroPresentador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class VistaRegistros : AppCompatActivity() {

    private lateinit var txtTituloEstadoInscripciones: TextView
    private lateinit var spnFiltroCarrera: Spinner
    private lateinit var txtMostrarContador: TextView
    private lateinit var rvTarjetasCursos: RecyclerView
    private lateinit var btnVolverCatalogo: Button

    private lateinit var presentador: clsVerRegistroPresentador

    private var listaInscripciones: List<dataResumenInscripcion> = emptyList()
    private var listaCarreras: List<dataCarreras> = emptyList()

    private val modeloCarreras = clsCarrerasModelo()


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

        cargarCarreras()
        spnFiltroCarrera.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                filtrarCursos()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }
    fun mostrarInscripciones(inscripciones: List<dataResumenInscripcion>){
        listaInscripciones = inscripciones
        filtrarCursos()
    }
    fun mostrarError(mensaje : String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun cargarCarreras(){
        CoroutineScope(Dispatchers.Main).launch{
            try {
                listaCarreras = modeloCarreras.obtenerCarreras()

                val adaptador = ArrayAdapter(this@VistaRegistros, android.R.layout.simple_spinner_dropdown_item, listaCarreras)
                spnFiltroCarrera.adapter = adaptador
            }catch (e: Exception){
                mostrarError("Error al cargar las carreras: "+ e.localizedMessage)
            }
        }
    }
    private fun filtrarCursos(){
        val posicion = spnFiltroCarrera.selectedItemPosition

        if (posicion == 0){
            rvTarjetasCursos.adapter = AdaptadorResumenInscripcion(listaInscripciones)
            var totalInscritos = 0

            for (curso in listaInscripciones){
                totalInscritos = totalInscritos + curso.totalInscritos
            }

            txtMostrarContador.text = "Total de inscritos: " + totalInscritos

        }else{
            val carreraSeleccionada = listaCarreras[posicion]
            val cursosFiltrados = listaInscripciones.filter {
                it.idCarrera == carreraSeleccionada.id
            }

            rvTarjetasCursos.adapter = AdaptadorResumenInscripcion(cursosFiltrados)
            var totalInscritos = 0

            for (curso in listaInscripciones){
                totalInscritos = totalInscritos+curso.totalInscritos
            }

            txtMostrarContador.text = "Total de inscritos : " + totalInscritos
        }
    }

}