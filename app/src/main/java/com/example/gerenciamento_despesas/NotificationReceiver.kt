package com.example.gerenciamento_despesas

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.gerenciamento_despesas.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "notify_001")
            .setContentTitle("Conta a Vencer")
            .setContentText("Você tem uma conta a vencer em breve.")
            .setSmallIcon(R.drawable.ic_notification)
            .build()

        notificationManager.notify(1, notification)
    }
}
