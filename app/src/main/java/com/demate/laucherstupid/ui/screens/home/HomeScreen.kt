package com.demate.laucherstupid.ui.screens.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.demate.laucherstupid.R
import com.demate.laucherstupid.ui.components.LauncherBackHandler

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    LauncherBackHandler(
        enabled = true,
        showExitDialog = false,
        onExitLauncher = {
        }
    )

    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val shouldRequestImei = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q

    val permissionGranted = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted: Boolean ->
        permissionGranted.value = granted
        if (granted) {
            viewModel.refreshImei()
        }
    }

    LaunchedEffect(shouldRequestImei) {
        if (shouldRequestImei) {
            if (permissionGranted.value) {
                viewModel.refreshImei()
            } else {
                permissionLauncher.launch(Manifest.permission.READ_PHONE_STATE)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = stringResource(R.string.utility_model_description),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Icon(
                    imageVector = Icons.Rounded.Home,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(96.dp)
                )

                Text(
                    text = stringResource(R.string.welcome_message),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = stringResource(R.string.device_registration_message),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = uiState.deviceSerial.uppercase(),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = uiState.deviceImei.takeIf { it.isNotBlank() }?.uppercase()
                        ?: stringResource(R.string.imei_not_available),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp, vertical = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(7.dp),
                onClick = { }
            ) {
                Text(
                    text = stringResource(R.string.txt_start),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        // Preview com dados mockados
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .align(Alignment.TopCenter)
                    .background(MaterialTheme.colorScheme.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    // Mirror the changes in preview for accurate layout
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Descrição do Modelo",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Icon(
                        imageVector = Icons.Rounded.Home,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(96.dp)
                    )

                    Text(
                        text = "Mensagem de Boas-vindas",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "Mensagem de Registro do Dispositivo",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "123456789abcdef",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "IMEI-EXEMPLO-12345",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Conteúdo da segunda coluna (25%)
            }
        }
    }
}
