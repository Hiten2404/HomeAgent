package com.example.homeagent.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AgentHealth(
    val status: String,
    val message: String? = null
)
