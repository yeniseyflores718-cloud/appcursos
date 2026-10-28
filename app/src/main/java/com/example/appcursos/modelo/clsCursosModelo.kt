package com.example.appcursos.modelo
class clsCursosModelo {
    suspend fun obtenerCursosPorCategoria(carreraId: Int): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }
    suspend fun obtenerCursoPorId(cursoId: Int): dataCursos {
        return RetrofitCliente.apiService.obtenerCursoPorId(cursoId)
    }

    suspend fun registrarCurso(idCurso: Int, matricula: String, fecha: String, correo: String): RegistroResponse {
        val request = RegistroRequest(idCurso, matricula, fecha, correo)
        return RetrofitCliente.apiService.registrarInscripcion(request)
    }
}