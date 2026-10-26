package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistroResponse(
    @SerialName("exito") val exito: Boolean,
    @SerialName("mensaje") val mensaje: String
)
