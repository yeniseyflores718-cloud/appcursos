package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("id_curso")val id: Int=0,
    @SerialName("id_carrera") val idCarrera: Int=0,
    @SerialName("nombre")val nombre:String="",
    @SerialName("espacio")val espacio: String="",
    @SerialName("url_imagen_principal")val urlimagenprincipal: String=""
)
