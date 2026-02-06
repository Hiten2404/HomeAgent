package com.example.homeagent

import android.content.Context
import java.io.File

object FileLogger {
    private var logFile: File? = null

    fun init(context: Context) {
        logFile = File(context.filesDir, "openclaw.log")
    }

    fun log(tag: String, message: String) {
        logFile?.appendText("$tag: $message\n")
    }
}
