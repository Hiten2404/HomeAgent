package com.example.homeagent.server

import android.content.Context
import android.util.Log
import com.example.homeagent.data.model.AgentHealth
import com.example.homeagent.router.CommandRouter
import com.example.homeagent.tools.MediaAction
import com.example.homeagent.tools.MediaTarget
import com.example.homeagent.tools.ToolCommand
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.JSONObject

object KtorServer {

    private var server: NettyApplicationEngine? = null

    fun start(context: Context) {
        if (server != null) return

        server = embeddedServer(Netty, port = 8080) {
            module(context)
        }.start(wait = false)

        Log.d("KtorServer", "Ktor server started on port 8080")
    }

    private fun Application.module(context: Context) {

        // 🔴 REQUIRED FOR JSON
        install(ContentNegotiation) {
            json()
        }

        routing {

            // ✅ HEALTH ENDPOINT
            get("/health") {
                call.respond(AgentHealth(status = "ok"))
            }

            // ✅ TOOL ENDPOINT
            post("/tool") {

                val body = call.receiveText()
                Log.d("KtorServer", "Received: $body")

                try {
                    val json = JSONObject(body)

                    val action = json.getString("action")
                    val target = json.getString("target")

                    val command = ToolCommand.MediaControl(
                        action = MediaAction.valueOf(action.uppercase()),
                        target = MediaTarget.valueOf(target.uppercase())
                    )

                    val router = CommandRouter(context)
                    val result = router.route(command)

                    call.respondText(
                        """{"success":${result.success},"message":"${result.message}"}""",
                        contentType = io.ktor.http.ContentType.Application.Json
                    )

                } catch (e: Exception) {
                    Log.e("KtorServer", "Routing failed", e)
                    call.respondText(
                        """{"success":false,"message":"error"}""",
                        contentType = io.ktor.http.ContentType.Application.Json
                    )                }
            }
        }
    }
}