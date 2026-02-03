
package com.example.homeagent.data

import android.content.Context
import android.content.Intent
import com.example.homeagent.data.model.AgentHealth
import com.example.homeagent.network.AgentApiClient

class AgentRepository(private val context: Context) {

    private val apiClient = AgentApiClient()

    suspend fun getHealth(): Result<AgentHealth> = apiClient.getHealth()

    fun startAgent() {
        runTermuxCommand("sv up termux-agent")
    }

    fun stopAgent() {
        runTermuxCommand("sv down termux-agent")
    }

    fun restartAgent() {
        runTermuxCommand("sv restart termux-agent")
    }

    private fun runTermuxCommand(command: String) {
        val intent = Intent().apply {
            setClassName("com.termux", "com.termux.app.RunCommandService")
            action = "com.termux.RUN_COMMAND"
            putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/runit")
            putExtra("com.termux.RUN_COMMAND_ARGS", command.split(" ").toTypedArray())
            putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/usr/var/service")
        }
        context.startService(intent)
    }
}
