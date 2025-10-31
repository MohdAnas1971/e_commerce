package com.store.ecommerceapplication.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.store.ecommerceapplication.R

class NotificationManager(private val context: Context) {
    
    companion object {
        private const val TAG = "NotificationManager"
        const val CHANNEL_ID = "ecomart_notifications"
        const val CHANNEL_NAME = "EcoMart Notifications"
        const val CHANNEL_DESCRIPTION = "Notifications for EcoMart app"
    }
    
    init {
        createNotificationChannel()
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESCRIPTION
                enableLights(true)
                enableVibration(true)
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            
            Log.d(TAG, "Notification channel created")
        }
    }
    
    fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        }
    }
    
    fun showNotification(
        id: Int = System.currentTimeMillis().toInt(),
        title: String,
        content: String,
        bigText: String? = null
    ) {
        if (!hasNotificationPermission()) {
            Log.w(TAG, "No notification permission")
            return
        }
        
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        
        bigText?.let {
            builder.setStyle(NotificationCompat.BigTextStyle().bigText(it))
        }
        
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(id, builder.build())
        }
        
        Log.d(TAG, "Notification shown: $title")
    }
    
    fun showWelcomeNotification() {
        showNotification(
            title = "Welcome to EcoMart! 🛍️",
            content = "Discover amazing products and deals",
            bigText = "Welcome to EcoMart! Explore our wide range of products, get exclusive deals, and enjoy a seamless shopping experience. Happy shopping!"
        )
    }
    
    fun showProductNotification(productName: String) {
        showNotification(
            title = "New Product Alert! 🎉",
            content = "Check out: $productName",
            bigText = "A new product '$productName' is now available! Don't miss out on this amazing deal. Tap to view details."
        )
    }
    
    fun showOfferNotification(discount: String) {
        showNotification(
            title = "Special Offer! 💰",
            content = "Get $discount off on selected items",
            bigText = "Limited time offer! Get $discount discount on selected products. Shop now and save big on your favorite items!"
        )
    }
}
