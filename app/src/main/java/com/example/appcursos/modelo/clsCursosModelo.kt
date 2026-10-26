package com.example.appcursos.modelo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.Adaptador.CursosAdaptador
import com.example.appcursos.vista.MainActivity

class clsCursosModelo {

    suspend fun obtenerCursosPorCategoria(carreraId: Int): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }
    suspend fun obtenerCursoPorId(cursoId: Int): dataCursos {
        return RetrofitCliente.apiService.obtenerCursoPorId(cursoId)
    }

    // 2. Registrar la inscripción empaquetando los datos en el RegistroRequest
    suspend fun registrarCurso(matricula: String, fecha: String, correo: String): Boolean {
        // Creamos el objeto request que espera tu @Body en el ApiService
        val request = RegistroRequest(matricula, fecha, correo)

        // Hacemos la petición
        val respuesta = RetrofitCliente.apiService.registrarInscripcion(request)

        // Retornamos el indicador de éxito que venga en tu respuesta (ej. respuesta.exito)
        return respuesta.exito
    }
}