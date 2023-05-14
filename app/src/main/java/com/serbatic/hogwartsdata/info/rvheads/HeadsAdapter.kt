package com.serbatic.hogwartsdata.info.rvheads

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.databinding.ItemHeadsBinding

class HeadsAdapter (private val headsResponse: List<HeadsResponse>, private val nombreCasa: String): RecyclerView.Adapter<HeadsAdapter.headsHolder>() {

    private var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadsAdapter.headsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeadsAdapter.headsHolder(layoutInflater.inflate(R.layout.item_heads, parent, false))
    }

    override fun onBindViewHolder(holder: HeadsAdapter.headsHolder, position: Int) {
        val item:HeadsResponse = headsResponse[position]

        holder.nombre.text = item.nombre
        holder.apellido.text = item.apellido

        val sharedPreferences = holder.itemView.context?.getSharedPreferences("casaFavorita$nombreCasa", Context.MODE_PRIVATE)
        if (sharedPreferences?.getString("nombre","") == item.nombre) {
            holder.fav.setImageResource(R.drawable.ic_favorite_24)
        } else {
            holder.fav.setImageResource(R.drawable.ic_favorite_border_24)
        }

        holder.fav.setOnClickListener {

            // Actualizar la imagen del botón
            if (sharedPreferences?.getString("nombre","") != item.nombre) {
                holder.fav.setImageResource(R.drawable.ic_favorite_24)
                val posicionAnterior = sharedPreferences?.getInt("posicion", -1)!!

                // Guardar información en las preferencias compartidas
                val editor = sharedPreferences.edit()
                editor?.putString("nombre", item.nombre)
                editor?.putString("apellido", item.apellido)
                editor?.putInt("posicion", holder.adapterPosition)
                editor?.apply()

                if (posicionAnterior != -1) {
                    notifyItemChanged(posicionAnterior)
                }
            } else {
                holder.fav.setImageResource(R.drawable.ic_favorite_border_24)
                // Eliminar información de las preferencias compartidas
                val editor = sharedPreferences.edit()
                editor.remove("nombre")
                editor.remove("apellido")
                editor.remove("posicion")
                editor.apply()
            }
        }

    }

    override fun getItemCount(): Int = headsResponse.size

    class headsHolder(private val view: View):RecyclerView.ViewHolder(view){
        private  val binding = ItemHeadsBinding.bind(view)

       var nombre = binding.textNombre
       var apellido = binding.textApellido
       var fav = binding.buttonFab
    }

}