package com.example.appcursos.presentador

import com.example.appcursos.vista.DetalleCursoActivity
import com.example.appcursos.modelo.dataDetalleCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.appcursos.modelo.clsDetalleCursoModelo
import kotlinx.coroutines.withContext


class clsDetalleCursoPresentador(val vista: DetalleCursoActivity) {
    private val modelo = clsDetalleCursoModelo()

    fun cargarImagenes(idCurso: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val imagenes = withContext(Dispatchers.IO) {
                    modelo.obtenerImagenes(idCurso)
                }
                vista.mostrarImagenes(imagenes)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar imágenes: ${e.message}")
            }
        }
    }

}