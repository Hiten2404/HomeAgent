package com.example.homeagent.server

import android.content.Context
import android.util.Log

object ServerManager {

    private var started = false

    fun start(context: Context) {
        if (started) return
        started = true

        Thread {
            KtorServer.start(context)
        }.start()
    }
}