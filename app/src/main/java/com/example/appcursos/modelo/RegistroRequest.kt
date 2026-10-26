package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistroRequest(
    @SerialName("matricula") val matricula: String,
    @SerialName("fecha") val fecha: String,
    @SerialName("correo") val correo: String
)