package com.example.appcursos.interfaceApi

import com.example.appcursos.modelo.dataCarreras
import com.example.appcursos.modelo.dataCursos
import com.example.appcursos.modelo.dataDetalleCurso
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>
    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carrera_Id: Int):List<dataCursos>
    @GET("curso_detalle.php")
    suspend fun detalleCurso(@Query("curso_id") curso_Id: Int):List<dataDetalleCurso>
}