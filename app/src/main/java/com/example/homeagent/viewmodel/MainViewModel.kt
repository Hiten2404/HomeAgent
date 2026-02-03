package com.example.homeagent.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeagent.data.AgentRepository
import com.example.homeagent.data.model.AgentHealth
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AgentRepository(application)

    private val _agentHealth = MutableStateFlow<Result<AgentHealth>?>(null)
    val agentHealth: StateFlow<Result<AgentHealth>?> = _agentHealth.asStateFlow()

    private var pollingJob: Job? = null

    init {
        startPolling()
    }

    private fun startPolling() {
        pollingJob?.cancel()
        pollingJob = viewModelScope.launch {
            while (isActive) {
                refreshStatus()
                delay(5000)
            }
        }
    }

    fun refreshStatus() {
        viewModelScope.launch {
            _agentHealth.value = repository.getHealth()
        }
    }

    fun startAgent() {
        repository.startAgent()
    }

    fun stopAgent() {
        repository.stopAgent()
    }

    fun restartAgent() {
        repository.restartAgent()
    }
}