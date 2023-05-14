package com.serbatic.hogwartsdata.login


import android.content.Intent
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.casas.CasasFavoritasActivity
import com.serbatic.hogwartsdata.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializador()
    }
    fun inicializador(){

        // Obtener el valor almacenado en SharedPreferences
        val sharedPreferences = getSharedPreferences("hogwarts", MODE_PRIVATE)
        val sesionAbierta = sharedPreferences.getBoolean("sesion_abierta", false)

        if (sesionAbierta) {
            // Si el usuario no está logeado, iniciar la actividad de login
            val intent = Intent(this, CasasFavoritasActivity::class.java)
            startActivity(intent)
            // Finalizar la actividad principal para que el usuario no pueda regresar a ella presionando "Atrás"
            finish()
        }
        binding.buttonLogin.setOnClickListener {
            val name = binding.editTextUserLogin.text.toString()
            val pass = binding.editTextPassLogin.text.toString()
            if ((name == "Paco" && pass == "1111") || (name == "Raquel" && pass == "2222")){
                val intent = Intent(this, CasasFavoritasActivity::class.java)
                startActivity(intent)

                val sharedPreferences = it.context?.getSharedPreferences("hogwarts", MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putBoolean("sesion_abierta", true)
                editor?.apply()
                finish()

            }else{
                val dialog = AlertDialog.Builder(this)
                    .setTitle(R.string.titulo_login)
                    .setMessage(R.string.mensaje_login)
                    .setNegativeButton(R.string.aceptar) { view, _ ->
                        view.dismiss()
                    }
                    .setCancelable(false)
                    .create()

                dialog.show()
            }

        }
    }
}