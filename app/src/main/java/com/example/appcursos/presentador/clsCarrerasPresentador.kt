package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsCarrerasModelo
import com.example.appcursos.modelo.clsCursosModelo
import com.example.appcursos.modelo.dataCarreras
import com.example.appcursos.modelo.dataCursos
import com.example.appcursos.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class clsCarrerasPresentador (val vista: MainActivity){
    private val modelo= clsCarrerasModelo()
    private val modeloCursos= clsCursosModelo()
    private var listaCarreras:List<dataCarreras> =emptyList()
    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                listaCarreras=modelo.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)
            }catch (e: Exception){
                vista.mostrarError("Error"+ e.localizedMessage)
            }
        }
    }
    fun seleccionarCarrera(pocision: Int){
        if(pocision>=0 &&pocision<listaCarreras.size){
            val carrera=listaCarreras[pocision]
            cargarCursos(carrera.id)
        }
    }
    private fun cargarCursos(categoriaId: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val productos = modeloCursos.obtenerCursosPorCategoria(categoriaId)
                vista.mostrarCursos(productos)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar cursos: " + e.localizedMessage)

            }
        }
    }



}