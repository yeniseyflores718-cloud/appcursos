package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCarreras(
    @SerialName("id_carrera") val id:Int=0,
    @SerialName("nombre")val curso:String="",
    @SerialName("logo") val logo: String? = null
){
    override fun toString(): String{
        return curso
    }
}
