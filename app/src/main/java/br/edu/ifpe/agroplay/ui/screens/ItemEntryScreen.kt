package br.edu.ifpe.agroplay.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.edu.ifpe.agroplay.ui.InventoryViewModel
import br.edu.ifpe.agroplay.ui.ItemUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    viewModel: InventoryViewModel,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.resetUiState()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Novo Item") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    if (viewModel.saveItem()) {
                        onShowSnackbar("Item adicionado")
                        navigateBack()
                    }
                }
            },
            onCancelClick = navigateBack,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun ItemEntryBody(
    itemUiState: ItemUiState,
    onItemValueChange: (ItemUiState) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ItemInputForm(
            itemUiState = itemUiState,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        ) {
            Text("Salvar")
        }
        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        ) {
            Text("Cancelar")
        }
    }
}

@Composable
fun ItemInputForm(
    itemUiState: ItemUiState,
    modifier: Modifier = Modifier,
    onValueChange: (ItemUiState) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = itemUiState.nome,
            onValueChange = { onValueChange(itemUiState.copy(nome = it)) },
            label = { Text("Nome do produto ou insumo") },
            placeholder = { Text("Ex.: Adubo NPK 10-10-10") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = itemUiState.nomeErro != null,
            supportingText = { 
                if (itemUiState.nomeErro != null) {
                    Text(text = itemUiState.nomeErro, color = MaterialTheme.colorScheme.error)
                }
            }
        )
        OutlinedTextField(
            value = itemUiState.quantidade,
            onValueChange = { onValueChange(itemUiState.copy(quantidade = it)) },
            label = { Text("Quantidade") },
            placeholder = { Text("0") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = itemUiState.quantidadeErro != null,
            supportingText = { 
                if (itemUiState.quantidadeErro != null) {
                    Text(text = itemUiState.quantidadeErro, color = MaterialTheme.colorScheme.error)
                }
            }
        )
        OutlinedTextField(
            value = itemUiState.localArmazenamento,
            onValueChange = { onValueChange(itemUiState.copy(localArmazenamento = it)) },
            label = { Text("Local de armazenamento") },
            placeholder = { Text("Ex.: Galpão 2, prateleira B") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            isError = itemUiState.localErro != null,
            supportingText = { 
                if (itemUiState.localErro != null) {
                    Text(text = itemUiState.localErro, color = MaterialTheme.colorScheme.error)
                }
            }
        )
    }
}
