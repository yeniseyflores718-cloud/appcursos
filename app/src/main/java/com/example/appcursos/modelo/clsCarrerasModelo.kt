package com.example.appcursos.modelo

class clsCarrerasModelo {
    suspend fun obtenerCarreras():List<dataCarreras>{
        val datos = RetrofitCliente.apiService.obtenerCarreras()

        val listaFinal=mutableListOf(dataCarreras(0,"Todas las carreras",null))
        listaFinal.addAll(datos)

        return listaFinal
    }
}