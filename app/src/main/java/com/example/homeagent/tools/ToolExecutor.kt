package com.example.homeagent.tools

interface ToolExecutor {
    fun execute(command: ToolCommand): ExecutionResult
}
