
package com.example.homeagent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.homeagent.ui.MainScreen
import com.example.homeagent.ui.theme.HomeAgentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeAgentTheme {
                MainScreen()
            }
        }
    }
}