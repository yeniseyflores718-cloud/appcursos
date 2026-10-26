package com.example.appcursos.presentador

import com.example.appcursos.vista.DetalleCursoActivity
import com.example.appcursos.modelo.dataDetalleCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.appcursos.modelo.clsDetalleCursoModelo


class clsDetalleCursoPresentador(val vista: DetalleCursoActivity) {

    val modelo = clsDetalleCursoModelo()

    fun obtenerDetalleCurso(idCurso: Int){
        CoroutineScope(Dispatchers.Main).launch{
            try {

                val curso = modelo.obtenerDetalleCurso(idCurso)

                if (curso!= null){
                    vista.mostrarDetalleCurso(curso)
                }else{
                    vista.mostrarError("No se pudo encontrar el curso")
                }

            }catch (e: Exception){
                vista.mostrarError("Error al obtener los detalles: "+e.localizedMessage)
            }

        }

    }
}