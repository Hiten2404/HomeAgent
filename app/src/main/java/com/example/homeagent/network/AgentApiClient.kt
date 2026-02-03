
package com.example.homeagent.network

import com.example.homeagent.data.model.AgentHealth
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AgentApiClient {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getHealth(): Result<AgentHealth> {
        return try {
            val response = client.get("http://127.0.0.1:3000/health")
            Result.success(response.body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
