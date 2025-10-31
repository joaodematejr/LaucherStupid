package com.demate.laucherstupid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.demate.laucherstupid.navigation.AppNavigation
import com.demate.laucherstupid.ui.theme.LauncherStupidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LauncherStupidTheme {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        /* scrim = */ MaterialTheme.colorScheme.primary.toArgb()
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        /* scrim = */ MaterialTheme.colorScheme.primary.toArgb()
                    )
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
