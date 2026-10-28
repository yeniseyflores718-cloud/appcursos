package com.example.appcursos.presentador
import com.example.appcursos.modelo.clsVerRegistroModelo
import com.example.appcursos.modelo.dataResumenInscripcion
import com.example.appcursos.vista.VistaRegistros
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsVerRegistroPresentador (val vista: VistaRegistros) {
    val modelo = clsVerRegistroModelo()
    fun obtenerResumenInscripcion(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cursos = modelo.obtenerResumenInscripcion()
                vista.mostrarInscripciones(cursos)
            }catch (e: Exception){
                vista.mostrarError("Error al obtener las inscripciones: " + e.localizedMessage)
            }
        }
    }

}