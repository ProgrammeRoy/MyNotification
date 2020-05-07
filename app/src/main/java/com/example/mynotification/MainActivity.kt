package com.example.mynotification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.*
import androidx.core.app.NotificationManagerCompat
import com.example.mynotification.Service.NotificationHelper
import com.example.mynotification.Service.NotificationHelper.createSampleDataNotification
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


object CANAL {
    val NOMBRE_IMPORTANCIA_MIN = "NOMBRE_IMPORTANCIA_MIN"
    val NOMBRE_IMPORTANCIA_DEFAULT = "NOMBRE_IMPORTANCIA_DEFAULT"
    val NOMBRE_IMPORTANCIA_MAX = "NOMBRE_IMPORTANCIA_MAX"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Created channel 1
        NotificationHelper.createNotificationChannel(
            applicationContext,
            NotificationManagerCompat.IMPORTANCE_MAX,
            true,
            CANAL.NOMBRE_IMPORTANCIA_MAX,
            "Canal de noticicacion principal como mensajes urgentes (con popup)",
             true,
            longArrayOf(0,1000,1000,1000,1000)
        )

        //Created channel 2
        NotificationHelper.createNotificationChannel(
            applicationContext,
            NotificationManagerCompat.IMPORTANCE_DEFAULT,
            true,
            CANAL.NOMBRE_IMPORTANCIA_DEFAULT,
            "Canal de para mensajes informativos (solo mostrará el icono)",
            false,
            longArrayOf(0,2000,2000,2000,2000)
        )

        //Created channel 3
        NotificationHelper.createNotificationChannel(
            applicationContext,
            NotificationManagerCompat.IMPORTANCE_MIN,
            true,
            CANAL.NOMBRE_IMPORTANCIA_MIN,
            "Canal de para mensajes con importancia minima",
            false,
            longArrayOf(0,3000,3000,3000,3000)
        )
    }

    //Click button to selected channel
    fun sendOnChannel1(v: View) {

        var canal : String

        if (rbIMPORTANCE_MIN.isChecked) {
            canal = CANAL.NOMBRE_IMPORTANCIA_MIN;
        } else if (rbIMPORTANCE_DEFAULT.isChecked) {
            canal = CANAL.NOMBRE_IMPORTANCIA_DEFAULT;
        } else if (rbIMPORTANCE_MAX.isChecked) {
            canal = CANAL.NOMBRE_IMPORTANCIA_MAX;
        } else {
            throw Exception("Este tipo no está configurado")
        }

        createSampleDataNotification(
            this@MainActivity,
            canal,
            "Title",
            "Message Message Message Message Message Message Message Message Message Message Message ",
            "Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text Big text ",
            false,
            PRIORITY_MAX
        )
    }


}
