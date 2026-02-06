package com.example.homeagent.service

import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class MediaNotificationListener : NotificationListenerService() {

    companion object {
        private var controllers: List<MediaController> = emptyList()

        fun getMediaControllers(): List<MediaController> {
            return controllers
        }
    }

    private lateinit var mediaSessionManager: MediaSessionManager

    override fun onCreate() {
        super.onCreate()
        mediaSessionManager = getSystemService(MEDIA_SESSION_SERVICE) as MediaSessionManager
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        updateControllers()
        Log.d("MediaListener", "Connected. Sessions: ${controllers.size}")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        updateControllers()
    }

    private fun updateControllers() {
        try {
            val cn = android.content.ComponentName(this, MediaNotificationListener::class.java)
            controllers = mediaSessionManager.getActiveSessions(cn)
        } catch (e: Exception) {
            Log.e("MediaListener", "Failed to get sessions", e)
        }
    }
}