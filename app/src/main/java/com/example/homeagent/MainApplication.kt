package com.example.homeagent

import android.app.Application
import com.example.homeagent.server.ServerManager

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ServerManager.start(this)
    }
}
