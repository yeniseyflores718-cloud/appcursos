package com.example.appcursos.vista

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.Adaptador.CursosAdaptador
import com.example.appcursos.R
import com.example.appcursos.modelo.dataCarreras
import com.example.appcursos.modelo.dataCursos
import com.example.appcursos.presentador.clsCarrerasPresentador

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: clsCarrerasPresentador
    private lateinit var spinnerCarreras: Spinner
    private lateinit var recyclerCursos: RecyclerView
    private lateinit var adapterCursos: CursosAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinnerCarreras=findViewById(R.id.spnCarreras)
        recyclerCursos = findViewById(R.id.rvLista)

        recyclerCursos.layoutManager = GridLayoutManager(this, 2)
        adapterCursos = CursosAdaptador(this, emptyList())
        recyclerCursos  .adapter = adapterCursos
        presenter= clsCarrerasPresentador(this)

        configurarListeners()
        presenter.iniciar()
    }
    private fun configurarListeners(){
        spinnerCarreras.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>?,view: View?, position:Int,id:Long){
                presenter.seleccionarCarrera(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    fun mostrarCarreras(carreras:List<dataCarreras>){
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarreras.adapter=adapter
    }
    fun mostrarMensajeExito(id:Int,nombre:String){
        Toast.makeText(this,"ID"+ id.toString()+"-Seleccionaste:"+ nombre, Toast.LENGTH_SHORT).show()

    }
    fun mostrarError(mensaje:String){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
    }

    fun mostrarCursos(curso: List<dataCursos>) {
        adapterCursos.actualizarLista(curso)
    }
}