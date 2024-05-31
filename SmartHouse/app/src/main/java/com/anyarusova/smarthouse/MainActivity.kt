package com.anyarusova.smarthouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.anyarusova.smarthouse.ui.view.SmartHomeScreen
import com.anyarusova.smarthouse.viewmodel.SmartHomeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartHomeScreen(viewModel = SmartHomeViewModel(application))
        }
    }
}
