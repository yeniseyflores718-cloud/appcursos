package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCursos(
    @SerialName("id_curso") val id: Int,
    @SerialName("id_carrera") val idCarrera: Int = 0,
    @SerialName("codigo") val codigo: String = "",
    @SerialName("nombre") val nombre: String,
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("espacio") val espacio: String = "",
    @SerialName("total_horas") val totalHoras: Int? = 0,
    @SerialName("dias_atencion") val diasAtencion: String = "",
    @SerialName("url_imagen_principal") val urlimagenprincipal: String = ""
)
