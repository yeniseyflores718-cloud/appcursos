package com.example.appcursos.modelo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.Adaptador.CursosAdaptador
import com.example.appcursos.vista.MainActivity

class clsCursosModelo {
    suspend fun obtenerCursosPorCategoria(carreraId: Int): List<dataCursos> {
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }
}