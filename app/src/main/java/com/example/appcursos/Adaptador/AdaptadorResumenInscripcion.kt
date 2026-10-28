package com.example.appcursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcursos.R
import com.example.appcursos.modelo.dataResumenInscripcion

class AdaptadorResumenInscripcion(
    private var listaInscripciones: List<dataResumenInscripcion>
) : RecyclerView.Adapter<AdaptadorResumenInscripcion.InscripcionViewHolder>() {

    class InscripcionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCurso: TextView = itemView.findViewById(R.id.txtCursoInscrito)
        val tvCarrera: TextView = itemView.findViewById(R.id.txtCarrera)
        val tvTotalInscritos: TextView = itemView.findViewById(R.id.txtTotalInscritos)
        val tvCupo: TextView = itemView.findViewById(R.id.txtCupoMaximo)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCursoResumen)
        val barraProgreso: ProgressBar = itemView.findViewById(R.id.barraProgresoCupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InscripcionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resumen_inscripcion, parent, false)
        return InscripcionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InscripcionViewHolder, position: Int) {
        val curso = listaInscripciones[position]

        holder.tvCurso.text = curso.curso
        holder.tvCarrera.text = curso.carrera
        holder.tvTotalInscritos.text = "Inscritos: " + curso.totalInscritos
        holder.tvCupo.text = "Cupo máximo: " + (curso.cupoMaximo ?: 0)

        val baseUrl = "http://192.168.10.126/cursos_api/"
        val urlImagenCompleta = baseUrl + curso.urlImagenPrincipal

        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)

        val cupo = curso.cupoMaximo ?: 0
        holder.barraProgreso.max = if (cupo > 0) cupo else 1
        holder.barraProgreso.progress = curso.totalInscritos.coerceIn(0, holder.barraProgreso.max)
    }

    override fun getItemCount(): Int {
        return listaInscripciones.size
    }

    fun actualizarLista(nuevaLista: List<dataResumenInscripcion>) {
        listaInscripciones = nuevaLista
        notifyDataSetChanged()
    }
}

