package com.example.homeagent.tools

sealed class ToolCommand {
    data class MediaControl(
        val action: MediaAction,
        val target: MediaTarget
    ) : ToolCommand()
}

enum class MediaAction {
    PLAY,
    PAUSE,
    NEXT,
    PREVIOUS
}

enum class MediaTarget {
    PHONE,
    TV,
    SPOTIFY
}
