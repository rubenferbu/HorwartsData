package com.serbatic.hogwartsdata.casos

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.serbatic.hogwartsdata.R
import com.serbatic.hogwartsdata.casas.CasasFavoritasActivity
import com.serbatic.hogwartsdata.login.LoginActivity

class Utils {
    companion object{
        fun salir(activity: Activity) {
            val dialog = AlertDialog.Builder(activity)
                .setTitle(R.string.titulo_salir)
                .setMessage(R.string.mensaje_sali)
                .setNegativeButton(R.string.cancelar) { view, _ ->
                    view.dismiss()
                }
                .setPositiveButton(R.string.aceptar) { view, _ ->
                    val sharedPreferences = activity.getSharedPreferences("hogwarts", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor?.putBoolean("sesion_abierta", false)
                    editor?.apply()
                    val intent = Intent(activity, LoginActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
                .setCancelable(false)
                .create()

            dialog.show()
        }
    }
}