package com.example.homeagent.tools

import android.content.Context
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.util.Log
import com.example.homeagent.service.MediaNotificationListener

class MediaControlExecutor(
    private val context: Context
) : ToolExecutor {

    override fun execute(command: ToolCommand): ExecutionResult {

        if (command !is ToolCommand.MediaControl) {
            return ExecutionResult(false, "Invalid command type")
        }

        val controllers = MediaNotificationListener.getMediaControllers()

        if (controllers.isEmpty()) {
            return ExecutionResult(false, "No active media session")
        }

        val controller: MediaController = controllers[0]
        val transport = controller.transportControls

        when (command.action) {
            MediaAction.PLAY -> transport.play()
            MediaAction.PAUSE -> transport.pause()
            MediaAction.NEXT -> transport.skipToNext()
            MediaAction.PREVIOUS -> transport.skipToPrevious()
        }

        Log.d("MediaExecutor", "Sent ${command.action}")

        return ExecutionResult(
            true,
            "Media command executed: ${command.action}"
        )
    }
}