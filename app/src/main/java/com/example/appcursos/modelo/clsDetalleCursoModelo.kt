package com.example.appcursos.modelo

class clsDetalleCursoModelo() {
    suspend fun obtenerImagenes(idCurso: Int): List<dataDetalleCurso> {
        return RetrofitCliente.apiService.detalleCurso(idCurso)
    }

}