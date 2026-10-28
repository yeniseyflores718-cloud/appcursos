package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsCursosModelo
import com.example.appcursos.vista.RegistroCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsCursosPresentador(private val vista: RegistroCurso) {
    private val modeloCursos = clsCursosModelo()
    fun cargarDetalleCurso(cursoId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val curso = withContext(Dispatchers.IO) {
                    modeloCursos.obtenerCursoPorId(cursoId)
                }

                if (curso != null) {
                    vista.mostrarDatosCurso(curso.nombre, curso.urlimagenprincipal)
                } else {
                    vista.mostrarMensajeError("No se encontró información del curso")
                }
            } catch (e: Exception) {
                vista.mostrarMensajeError("Error al cargar datos del curso: ${e.localizedMessage}")
            }
        }
    }


    fun validarYRegistrar(idCurso: Int, matricula: String, fecha: String, correo: String) {
        if (matricula.isEmpty() || fecha.isEmpty() || correo.isEmpty()) {
            vista.mostrarMensajeError("Por favor, llena todos los campos")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modeloCursos.registrarCurso(idCurso, matricula, fecha, correo)
                }

                if (respuesta.exito) {
                    vista.mostrarMensajeExito(respuesta.mensaje)
                } else {
                    vista.mostrarMensajeError(respuesta.mensaje)
                }

            } catch (e: Exception) {
                vista.mostrarMensajeError("Error de conexión: ${e.localizedMessage ?: "Error desconocido"}")
            }
        }
    }
}