package com.demate.laucherstupid.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.system.exitProcess

/**
 * Componente para controlar o botão voltar do sistema em um launcher de pagamento
 * Pode ser configurado para diferentes comportamentos dependendo do contexto
 */
@Composable
fun LauncherBackHandler(
    enabled: Boolean = true,
    showExitDialog: Boolean = true,
    onExitLauncher: (() -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = enabled) {
        if (showExitDialog) {
            showDialog = true
        } else {
            onExitLauncher?.invoke() ?: exitProcess(0)
        }
    }

    if (showDialog) {
        ExitConfirmationDialog(
            onConfirm = {
                showDialog = false
                onExitLauncher?.invoke() ?: exitProcess(0)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}

@Composable
private fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Sair do Launcher")
        },
        text = {
            Text("Tem certeza que deseja sair do launcher de pagamento?")
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) {
                Text("Sair")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}
