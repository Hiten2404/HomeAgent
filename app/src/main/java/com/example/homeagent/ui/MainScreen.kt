package com.example.homeagent.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeagent.data.model.AgentHealth
import com.example.homeagent.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val agentHealth by viewModel.agentHealth.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home Agent Controller") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatusIndicator(agentHealth)
            HealthCheckResponse(agentHealth)
            ActionButtons(viewModel)
        }
    }
}

@Composable
fun StatusIndicator(agentHealth: Result<AgentHealth>?) {
    val (statusText, color) = when {
        agentHealth == null -> "Checking..." to Color.Gray
        agentHealth.isSuccess -> "Running" to Color.Green
        else -> "Stopped / Error" to Color.Red
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(20.dp)
            .padding(4.dp)
            .background(color, shape = MaterialTheme.shapes.small)) {}
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Agent Status: $statusText", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun HealthCheckResponse(agentHealth: Result<AgentHealth>?) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Health Check Response", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = agentHealth?.fold(
                onSuccess = { it.toString() },
                onFailure = { it.message ?: "Unknown error" }
            ) ?: "No response yet.")
        }
    }
}

@Composable
fun ActionButtons(viewModel: MainViewModel) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = { viewModel.startAgent() }, modifier = Modifier.fillMaxWidth()) {
            Text("Start Agent")
        }
        Button(onClick = { viewModel.stopAgent() }, modifier = Modifier.fillMaxWidth()) {
            Text("Stop Agent")
        }
        Button(onClick = { viewModel.restartAgent() }, modifier = Modifier.fillMaxWidth()) {
            Text("Restart Agent")
        }
        OutlinedButton(onClick = { viewModel.refreshStatus() }, modifier = Modifier.fillMaxWidth()) {
            Text("Refresh Status")
        }
    }
}