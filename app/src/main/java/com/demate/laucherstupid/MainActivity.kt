package com.demate.laucherstupid

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.demate.laucherstupid.config.ModelConfig
import com.demate.laucherstupid.features.GestureArea
import com.demate.laucherstupid.features.ModelInfoCard
import com.demate.laucherstupid.features.PremiumFeaturesBadge
import com.demate.laucherstupid.features.PremiumHeader
import com.demate.laucherstupid.ui.theme.LaucherStupidTheme

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: android.graphics.drawable.Drawable,
    val launchIntent: Intent?
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar comportamento do botão voltar para manter o launcher ativo
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Não fazer nada para manter o launcher ativo
            }
        })

        setContent {
            LaucherStupidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    LauncherHome(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LauncherHome(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val apps = remember { mutableStateOf<List<AppInfo>>(emptyList()) }
    val modelSettings = ModelConfig.getCurrentModelSettings()

    LaunchedEffect(Unit) {
        apps.value = getInstalledApps(context)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Header específico do modelo
        if (ModelConfig.isL4Model()) {
            PremiumHeader()
            PremiumFeaturesBadge()
        } else {
            // Header padrão para L3
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // Informações do modelo atual
        ModelInfoCard()

        // Grid de aplicativos com configuração dinâmica
        LazyVerticalGrid(
            columns = GridCells.Fixed(modelSettings.gridColumns),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(apps.value) { app ->
                AppIcon(
                    app = app,
                    iconSize = modelSettings.iconSize,
                    onClick = {
                        app.launchIntent?.let { intent ->
                            context.startActivity(intent)
                        }
                    }
                )
            }
        }

        // Área de gestos para L4
        if (ModelConfig.isL4Model()) {
            GestureArea()
        }
    }
}

@Composable
fun AppIcon(app: AppInfo, iconSize: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = remember(app.icon) {
            app.icon.toBitmap(iconSize + 24, iconSize + 24).asImageBitmap()
        }

        Image(
            bitmap = bitmap,
            contentDescription = app.name,
            modifier = Modifier.size(iconSize.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = app.name,
            fontSize = if (iconSize > 50) 12.sp else 10.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width((iconSize + 16).dp)
        )
    }
}

private fun getInstalledApps(context: android.content.Context): List<AppInfo> {
    val packageManager = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN, null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }

    val resolveInfoList: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)

    return resolveInfoList.map { resolveInfo ->
        val activityInfo = resolveInfo.activityInfo
        val appName = resolveInfo.loadLabel(packageManager).toString()
        val packageName = activityInfo.packageName
        val icon = resolveInfo.loadIcon(packageManager)

        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)

        AppInfo(
            name = appName,
            packageName = packageName,
            icon = icon,
            launchIntent = launchIntent
        )
    }.sortedBy { it.name }
}
