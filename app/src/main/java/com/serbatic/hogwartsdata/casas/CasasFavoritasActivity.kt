package com.serbatic.hogwartsdata.casas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.serbatic.hogwartsdata.login.LoginActivity
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.api.Service
import com.serbatic.hogwartsdata.casos.Utils
import com.serbatic.hogwartsdata.databinding.ActivityCasasFavoriatasBinding
import okhttp3.internal.Util
import org.json.JSONArray

class CasasFavoritasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCasasFavoriatasBinding
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: CasaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCasasFavoriatasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.include.myToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.exit_to_app_24)
        supportActionBar?.title = getText(R.string.titulo_casas_favoritos)

        toolbar.setNavigationOnClickListener {
            Utils.salir(this)
        }

        obtenerCasas()
    }

    override fun onResume() {
        obtenerCasas()
        super.onResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.myToolbar -> {
                Utils.salir(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun obtenerCasas() {

        val apiService = Service()

        apiService.getCasas {

            val listaDatos = arrayListOf<CasaResponse>()

            for (position in 0 until JSONArray(it).length()) {
                listaDatos.add(
                    CasaResponse(
                        id = JSONArray(it).getJSONObject(position).getString("id"),
                        nombre = JSONArray(it).getJSONObject(position).getString("name")
                    )
                )
            }
            //Montamos la visualizacion del recycle
            adapter = CasaAdapter(listaDatos)
            binding.rvcasa.layoutManager = GridLayoutManager(this,2)
            binding.rvcasa.adapter = adapter
        }

    }
}