package com.serbatic.hogwartsdata.casas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.databinding.ItemCasasBinding
import com.serbatic.hogwartsdata.info.InformacionActivity

class CasaAdapter(private val casaResponse:List<CasaResponse>):RecyclerView.Adapter<CasaAdapter.CasaHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CasaHolder(layoutInflater.inflate(R.layout.item_casas, parent, false))
    }

    override fun getItemCount(): Int = casaResponse.size

    override fun onBindViewHolder(holder: CasaHolder, position: Int) {
        val item:CasaResponse = casaResponse[position]
        holder.render(item)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, InformacionActivity::class.java)
            intent.putExtra("id", item.id)
            intent.putExtra("nombreCasa", item.nombre)
            holder.itemView.context.startActivity(intent)
        }
    }

    class CasaHolder( val view:View):RecyclerView.ViewHolder(view){
        private  val binding = ItemCasasBinding.bind(view)

        fun render(casaResponse: CasaResponse){
            binding.textviewNombreCasa.text = casaResponse.nombre
            val sharedPreferences = view.context?.getSharedPreferences("casaFavorita${casaResponse.nombre}", Context.MODE_PRIVATE)
            binding.textviewPersonajeFavorito.text = sharedPreferences?.getString("nombre","") + sharedPreferences?.getString("apellido","")
        }
    }
}
