package com.example.homeagent.server

import android.content.Context
import com.example.homeagent.tools.MediaControlExecutor
import com.example.homeagent.tools.ToolExecutor

class ExecutorRegistry(context: Context) {

    private val executors: Map<String, ToolExecutor> = mapOf(
        "media" to MediaControlExecutor(context)
        // Future executors will be registered here
    )

    fun getExecutor(toolName: String): ToolExecutor? {
        return executors[toolName]
    }
}
