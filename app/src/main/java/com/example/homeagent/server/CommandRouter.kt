package com.example.homeagent.server

import android.content.Context
import android.util.Log
import com.example.homeagent.tools.MediaAction
import com.example.homeagent.tools.MediaTarget
import com.example.homeagent.tools.ToolCommand
import com.example.homeagent.tools.MediaControlExecutor
import com.example.homeagent.tools.ExecutionResult
import org.json.JSONObject

class CommandRouter(private val context: Context) {

    fun route(json: String): ExecutionResult {
        return try {
            val obj = JSONObject(json)
            val tool = obj.getString("tool").lowercase()

            when (tool) {
                "media" -> handleMedia(obj)
                else -> ExecutionResult(false, "Unknown tool: $tool")
            }

        } catch (e: Exception) {
            Log.e("CommandRouter", "Routing crash", e)
            ExecutionResult(false, e.message ?: "error")
        }
    }

    private fun handleMedia(obj: JSONObject): ExecutionResult {

        val actionStr = obj.optString("action")
        val targetStr = obj.optString("target")

        val command = ToolCommand.MediaControl(
            action = MediaAction.valueOf(actionStr.uppercase()),
            target = MediaTarget.valueOf(targetStr.uppercase())
        )

        val executor = MediaControlExecutor(context)
        return executor.execute(command)
    }
}