package com.serbatic.hogwartsdata.info.rvtraits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.databinding.ItemTraitsroudBinding

class TraitsAdapter (private val traitsResponse: List<TraitsResponse>): RecyclerView.Adapter<TraitsAdapter.traitsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraitsAdapter.traitsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TraitsAdapter.traitsHolder(layoutInflater.inflate(R.layout.item_traitsroud, parent, false))
    }

    override fun onBindViewHolder(holder: TraitsAdapter.traitsHolder, position: Int) {
        val item:TraitsResponse = traitsResponse[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = traitsResponse.size

    class traitsHolder( val view: View):RecyclerView.ViewHolder(view){
        private  val binding = ItemTraitsroudBinding.bind(view)

        fun render(traitsResponse: TraitsResponse){
            binding.textrasgos.text = traitsResponse.nombre
        }
    }
}