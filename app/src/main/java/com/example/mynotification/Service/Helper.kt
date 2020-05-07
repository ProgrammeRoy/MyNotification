package com.example.mynotification.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mynotification.MainActivity
import com.example.mynotification.R

object NotificationHelper {
    private const val ADMINISTER_REQUEST_CODE = 2019

    //Notification set the visual and mandatory behavior
    fun createNotificationChannel(
        context: Context,
        importance: Int,
        showBadge: Boolean,
        name: String,
        description: String,
        enableVibration : Boolean,
        arrayVibration: LongArray
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)
            channel.vibrationPattern = arrayVibration
//            channel.enableVibration(enableVibration)
            //LIGHT DON'T WORK
//            channel.enableLights(true)
//            channel.lightColor = Color.GRAY
            //Register the channel with the system
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }
    }

    var id: Int = 1001
    fun createSampleDataNotification(
        context: Context,
        name: String,
        title: String,
        message: String,
        bigText: String,
        autoCancel: Boolean,
        myPriority: Int
    ) {
        val channelId = "${context.packageName}-$name"
        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            //Small Icon
            setSmallIcon(R.drawable.ic_add_alert_black_24dp)
            //Title
            setContentTitle(title)
            //Message simple
//            setContentText(message)
            //BigText (replace contentText)
            setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            //LargeIcon DON'T WORK
            setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.polloalabrasa))
            //Inbox Style
//            setStyle(NotificationCompat.InboxStyle()
//                .addLine(message)
//                .addLine("text1")
//                .addLine("text2"))


            setAutoCancel(autoCancel)
            priority = myPriority

            //Pass to Activity when user push notification
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            setContentIntent(pendingIntent)

        }

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(id, notificationBuilder.build())
//        id += id
    }
}