package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataResumenInscripcion(
    @SerialName("id_curso") val idCurso: Int = 0,
    @SerialName("id_carrera") val idCarrera: Int = 0,
    @SerialName("codigo") val codigo: String = "",
    @SerialName("curso") val curso: String = "",
    @SerialName("cupo_maximo") val cupoMaximo: Int? = null,
    @SerialName("url_imagen_principal") val urlImagenPrincipal: String = "",
    @SerialName("carrera") val carrera: String = "",
    @SerialName("total_inscritos") val totalInscritos: Int = 0
)
