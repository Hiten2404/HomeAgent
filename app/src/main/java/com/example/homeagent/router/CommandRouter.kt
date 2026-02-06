package com.example.homeagent.router

import android.content.Context
import com.example.homeagent.tools.ExecutionResult
import com.example.homeagent.tools.MediaControlExecutor
import com.example.homeagent.tools.ToolCommand
import com.example.homeagent.tools.ToolExecutor

class CommandRouter(context: Context) {

    private val executors: Map<Class<out ToolCommand>, ToolExecutor>

    init {
        // Register all executors here
        executors = mapOf(
            ToolCommand.MediaControl::class.java to MediaControlExecutor(context)
        )
    }

    fun route(command: ToolCommand): ExecutionResult {
        val executor = executors[command.javaClass]
            ?: return ExecutionResult(
                false,
                "No executor registered for command: ${command.javaClass.simpleName}"
            )

        return try {
            executor.execute(command)
        } catch (e: Exception) {
            ExecutionResult(false, "Executor failed: ${e.message}")
        }
    }
}