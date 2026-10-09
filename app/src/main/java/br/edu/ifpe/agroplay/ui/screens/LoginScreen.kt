package br.edu.ifpe.agroplay.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.edu.ifpe.agroplay.ui.InventoryViewModel
import br.edu.ifpe.agroplay.ui.FazendaUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: InventoryViewModel,
    onNavigateToFarmList: () -> Unit,
    onLoginSuccess: () -> Unit,
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.fazendaUiState

    LaunchedEffect(Unit) {
        viewModel.resetFazendaUiState()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agro Play") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Entrar na Fazenda",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = uiState.proprietario,
                onValueChange = { viewModel.updateFazendaUiState(uiState.copy(proprietario = it)) },
                label = { Text("Nome do proprietário") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.proprietarioErro != null,
                supportingText = { uiState.proprietarioErro?.let { Text(it) } }
            )

            OutlinedTextField(
                value = uiState.fazenda,
                onValueChange = { viewModel.updateFazendaUiState(uiState.copy(fazenda = it)) },
                label = { Text("Nome da fazenda") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.fazendaErro != null,
                supportingText = { uiState.fazendaErro?.let { Text(it) } }
            )

            OutlinedTextField(
                value = uiState.localidade,
                onValueChange = { viewModel.updateFazendaUiState(uiState.copy(localidade = it)) },
                label = { Text("Localidade") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.localidadeErro != null,
                supportingText = { uiState.localidadeErro?.let { Text(it) } }
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.saveFazenda()) {
                            onShowSnackbar("Fazenda cadastrada com sucesso!")
                            onLoginSuccess()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text("Entrar")
            }

            TextButton(
                onClick = onNavigateToFarmList,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver logins")
            }
        }
    }
}
