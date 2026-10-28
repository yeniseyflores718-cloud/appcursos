package com.example.appcursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcursos.R
import com.example.appcursos.modelo.dataCursos
import com.example.appcursos.vista.MainActivity

class CursosAdaptador (
    private val activity: MainActivity,
    private var listaCursos:List<dataCursos>
): RecyclerView.Adapter<CursosAdaptador.CursosViewHolder>(){
    class CursosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvCurso: TextView =itemView.findViewById(R.id.txtNombreCurso)
        val tvSalon: TextView=itemView.findViewById(R.id.txtSalon)
        val imgCurso: ImageView=itemView.findViewById(R.id.imgCurso)
        val btnVerDetalle: Button = itemView.findViewById(R.id.btnDetalle)
        val btnRegistrarse: Button = itemView.findViewById(R.id.btnRegistrarse)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursosViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)
        return CursosViewHolder(view)
    }
    override fun onBindViewHolder(holder: CursosViewHolder, position: Int) {
        val curso = listaCursos[position]
        holder.tvCurso.text = curso.nombre
        holder.tvSalon.text = "Salón: ${curso.espacio}"
        val baseUrl = "http://192.168.200.214/cursos_api/"
        val urlImagenCompleta = baseUrl + curso.urlimagenprincipal
        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery) // Imagen por defecto mientras carga
            .error(android.R.drawable.stat_notify_error)     // Imagen si falla la carga
            .into(holder.imgCurso)
        holder.btnVerDetalle.setOnClickListener {
            activity.verDetalleCurso(curso)
        }

        holder.btnRegistrarse.setOnClickListener {
            activity.abrirRegistroCurso(curso)
        }
    }
    override fun getItemCount(): Int {
        return listaCursos.size
    }
    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}