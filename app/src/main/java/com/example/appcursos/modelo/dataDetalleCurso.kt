
package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleCurso(
    @SerialName("id") val id: Int,
    @SerialName("curso_id") val idCurso: Int,
    @SerialName("url_imagen") val urlImagen: String
)
