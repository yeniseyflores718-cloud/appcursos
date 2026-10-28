package com.example.appcursos.modelo

class clsVerRegistroModelo {
    suspend fun obtenerResumenInscripcion(): List<dataResumenInscripcion>{
        return RetrofitCliente.apiService.obtenerResumenInscripcion()
    }
}