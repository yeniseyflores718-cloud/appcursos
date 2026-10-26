package com.example.appcursos.modelo

class clsDetalleCursoModelo() {

    suspend fun obtenerDetalleCurso(idCurso: Int): dataDetalleCurso?{
        val cursos = RetrofitCliente.apiService.obtenerTodosCursos()

        return cursos.firstOrNull(){it.idCurso == idCurso}
    }
}