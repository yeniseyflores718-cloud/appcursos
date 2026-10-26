package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsCursosModelo
import com.example.appcursos.vista.RegistroCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCursosPresentador(private val vista: RegistroCurso) {
    private val modeloCursos = clsCursosModelo()

    fun cargarDetalleCurso(cursoId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val curso = modeloCursos.obtenerCursoPorId(cursoId)
                if (curso != null) {
                    vista.mostrarDatosCurso(curso.nombre, curso.urlimagenprincipal)
                } else {
                    vista.mostrarMensajeError("No se encontró información del curso")
                }
            } catch (e: Exception) {
                vista.mostrarMensajeError("Error al cargar el curso: " + e.localizedMessage)
            }
        }
    }

    fun validarYRegistrar(matricula: String, fecha: String, correo: String) {
        if (matricula.isEmpty() || fecha.isEmpty() || correo.isEmpty()) {
            vista.mostrarMensajeError("Por favor, completa todos los campos")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val exito = modeloCursos.registrarCurso(matricula, fecha, correo)

                if (exito) {
                    vista.mostrarMensajeExito("¡Inscripción registrada correctamente!")
                } else {
                    vista.mostrarMensajeError("No se pudo realizar el registro")
                }

            } catch (e: Exception) {
                vista.mostrarMensajeError("Error de conexión: ${e.localizedMessage}")
            }
        }
    }
}