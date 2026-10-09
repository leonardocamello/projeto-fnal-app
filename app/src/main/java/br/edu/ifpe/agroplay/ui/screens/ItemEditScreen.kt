package br.edu.ifpe.agroplay.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.edu.ifpe.agroplay.ui.InventoryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    itemId: Int,
    viewModel: InventoryViewModel,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val inventoryList by viewModel.inventoryUiState.collectAsState()
    val item = inventoryList.find { it.id == itemId }
    
    var showDeleteDialog by remember { mutableStateOf(false) }

    // Carrega o item no ViewModel ao abrir a tela
    LaunchedEffect(item) {
        item?.let { viewModel.loadItem(it) }
    }

    if (item == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Item não encontrado") },
                    navigationIcon = {
                        IconButton(onClick = onNavigateUp) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Item não encontrado.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = navigateBack) {
                        Text("Voltar")
                    }
                }
            }
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(item.nome) },
                    navigationIcon = {
                        IconButton(onClick = onNavigateUp) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                        }
                    },
                    actions = {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Excluir",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ItemInputForm(
                    itemUiState = viewModel.itemUiState,
                    onValueChange = viewModel::updateUiState,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (viewModel.updateItem()) {
                                onShowSnackbar("Item atualizado")
                                navigateBack()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Salvar")
                }
                
                OutlinedButton(
                    onClick = navigateBack,
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text("Cancelar")
                }
            }
        }
    }

    if (showDeleteDialog && item != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Excluir \"${item.nome}\"?") },
            text = { Text("Essa ação não pode ser desfeita.") },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        coroutineScope.launch {
                            viewModel.deleteItem()
                            onShowSnackbar("Item excluído")
                            navigateBack()
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Excluir")
                }
            }
        )
    }
}
