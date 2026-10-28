package com.example.appcursos.interfaceApi

import com.example.appcursos.modelo.RegistroRequest
import com.example.appcursos.modelo.RegistroResponse
import com.example.appcursos.modelo.dataCarreras
import com.example.appcursos.modelo.dataCursos
import retrofit2.http.Body
import com.example.appcursos.modelo.dataDetalleCurso
import com.example.appcursos.modelo.dataResumenInscripcion
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>
    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carrera_Id: Int):List<dataCursos>

    @GET("obtener_curso_por_id.php")
    suspend fun obtenerCursoPorId(@Query("id_curso") cursoId: Int): dataCursos
    @POST("registrar_inscripcion.php")
    suspend fun registrarInscripcion(@Body request: RegistroRequest): RegistroResponse
    @GET("curso_detalle.php")
    suspend fun detalleCurso(@Query("curso_id") curso_Id: Int):List<dataDetalleCurso>
    @GET("resumen_inscripcion.php")
    suspend fun obtenerResumenInscripcion(): List<dataResumenInscripcion>
}