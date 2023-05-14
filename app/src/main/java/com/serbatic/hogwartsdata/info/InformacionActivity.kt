package com.serbatic.hogwartsdata.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.api.Service
import com.serbatic.hogwartsdata.casos.Utils
import com.serbatic.hogwartsdata.databinding.ActivityInformacionBinding
import com.serbatic.hogwartsdata.info.rvheads.HeadsAdapter
import com.serbatic.hogwartsdata.info.rvheads.HeadsResponse
import com.serbatic.hogwartsdata.info.rvtraits.TraitsAdapter
import com.serbatic.hogwartsdata.info.rvtraits.TraitsResponse
import org.json.JSONObject

class InformacionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformacionBinding
    private lateinit var toolbar: Toolbar
    private lateinit var traits: TraitsAdapter
    private lateinit var heads: HeadsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.include.myToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.exit_to_app_24)
        supportActionBar?.title = getText(R.string.titulo_informacion)

        toolbar.setNavigationOnClickListener {
            Utils.salir(this)
        }
        obtenerinfo()
    }

    private fun obtenerinfo(){

        val casaId = intent.getStringExtra("id").toString()
        val apiService = Service()

        apiService.getid(casaId) {

            binding.texColoVAl.text = JSONObject(it).getString("houseColours")
            binding.texFundadorVal.text = JSONObject(it).getString("founder")
            binding.texAnimalVal.text = JSONObject(it).getString("animal")
            binding.texElementoVal.text = JSONObject(it).getString("element")
            binding.texFantasmaVal.text = JSONObject(it).getString("ghost")
            binding.texsalacomunVal.text = JSONObject(it).getString("commonRoom")

           //Montamos la peticion del Listado Ragos y Caracteristicas lo mencionamos como Traits
            val traitsDatos = arrayListOf<TraitsResponse>()

            for (position in 0 until JSONObject(it).getJSONArray("traits").length()) {
                traitsDatos.add(
                    TraitsResponse(
                        id = JSONObject(it).getJSONArray("traits").getJSONObject(position).getString("id"),
                        nombre = JSONObject(it).getJSONArray("traits").getJSONObject(position).getString("name")
                    )
                )
            }
            //Montamos la visualizacion del recycle de Traits
            traits = TraitsAdapter(traitsDatos)
            binding.rvrasgosval.layoutManager = GridLayoutManager(this, 1)
            binding.rvrasgosval.adapter = traits

            //Montamos la peticion del Listado Ragos y Caracteristicas lo mencionamos como Traits
            val headsDatos = arrayListOf<HeadsResponse>()

            for (position in 0 until JSONObject(it).getJSONArray("heads").length()) {
                headsDatos.add(
                    HeadsResponse(
                        id = JSONObject(it).getJSONArray("heads").getJSONObject(position).getString("id"),
                        nombre = JSONObject(it).getJSONArray("heads").getJSONObject(position).getString("firstName"),
                        apellido = JSONObject(it).getJSONArray("heads").getJSONObject(position).getString("lastName")
                    )
                )
            }
            //Montamos la visualizacion del recycle de Traits
            heads = HeadsAdapter(headsDatos, intent.getStringExtra("nombreCasa").toString())
            binding.rvLideres.layoutManager = GridLayoutManager(this, 1)
            binding.rvLideres.adapter = heads
        }
    }
}