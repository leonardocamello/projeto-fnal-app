package br.edu.ifpe.agroplay.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.edu.ifpe.agroplay.data.local.AppDatabase
import br.edu.ifpe.agroplay.data.local.Fazenda
import br.edu.ifpe.agroplay.data.local.ItemEstoque
import br.edu.ifpe.agroplay.data.repository.ItemEstoqueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.ExperimentalCoroutinesApi

data class ItemUiState(
    val id: Int = 0,
    val nome: String = "",
    val quantidade: String = "",
    val localArmazenamento: String = "",
    val fazendaId: Int? = null,
    val nomeErro: String? = null,
    val quantidadeErro: String? = null,
    val localErro: String? = null,
    val isEntryValid: Boolean = false
)

data class FazendaUiState(
    val id: Int = 0,
    val proprietario: String = "",
    val fazenda: String = "",
    val localidade: String = "",
    val proprietarioErro: String? = null,
    val fazendaErro: String? = null,
    val localidadeErro: String? = null
)

class InventoryViewModel(private val repository: ItemEstoqueRepository) : ViewModel() {

    private val _currentFazendaId = MutableStateFlow<Int?>(null)
    val currentFazendaId: StateFlow<Int?> = _currentFazendaId

    var currentFazenda by mutableStateOf<Fazenda?>(null)
        private set

    @OptIn(ExperimentalCoroutinesApi::class)
    val inventoryUiState: StateFlow<List<ItemEstoque>> =
        _currentFazendaId.flatMapLatest { id ->
            if (id == null) kotlinx.coroutines.flow.flowOf(emptyList())
            else repository.getItemsByFazendaStream(id)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val fazendasUiState: StateFlow<List<Fazenda>> =
        repository.getAllFazendasStream()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    var fazendaUiState by mutableStateOf(FazendaUiState())
        private set

    fun selectFazenda(id: Int) {
        viewModelScope.launch {
            val fazenda = repository.getFazendaById(id)
            currentFazenda = fazenda
            _currentFazendaId.value = id
        }
    }

    fun updateUiState(newItemUiState: ItemUiState) {
        itemUiState = newItemUiState.copy(
            nomeErro = if (newItemUiState.nome.isBlank()) "Informe o nome do item." else null,
            quantidadeErro = validateQuantidade(newItemUiState.quantidade),
            localErro = if (newItemUiState.localArmazenamento.isBlank()) "Informe onde o item está guardado." else null
        )
    }

    fun updateFazendaUiState(newFazendaUiState: FazendaUiState) {
        fazendaUiState = newFazendaUiState.copy(
            proprietarioErro = if (newFazendaUiState.proprietario.isBlank()) "Informe: proprietário" else null,
            fazendaErro = if (newFazendaUiState.fazenda.isBlank()) "Informe: fazenda" else null,
            localidadeErro = if (newFazendaUiState.localidade.isBlank()) "Informe: localidade" else null
        )
    }

    private fun validateQuantidade(qtd: String): String? {
        val value = qtd.toIntOrNull()
        return when {
            value == null -> "Use um número inteiro igual ou maior que zero."
            value < 0 -> "Use um número inteiro igual ou maior que zero."
            else -> null
        }
    }

    private fun validateInput(): Boolean {
        val nomeValido = itemUiState.nome.isNotBlank()
        val qtdValida = itemUiState.quantidade.toIntOrNull()?.let { it >= 0 } ?: false
        val localValido = itemUiState.localArmazenamento.isNotBlank()
        
        updateUiState(itemUiState)
        
        return nomeValido && qtdValida && localValido
    }

    private fun validateFazendaInput(): Boolean {
        val pValido = fazendaUiState.proprietario.isNotBlank()
        val fValido = fazendaUiState.fazenda.isNotBlank()
        val lValido = fazendaUiState.localidade.isNotBlank()
        
        updateFazendaUiState(fazendaUiState)
        
        return pValido && fValido && lValido
    }

    suspend fun saveItem(): Boolean {
        if (validateInput()) {
            repository.insertItem(itemUiState.toItemEstoque().copy(fazendaId = _currentFazendaId.value))
            return true
        }
        return false
    }

    suspend fun updateItem(): Boolean {
        if (validateInput()) {
            repository.updateItem(itemUiState.toItemEstoque())
            return true
        }
        return false
    }

    suspend fun deleteItem() {
        repository.deleteItem(itemUiState.toItemEstoque())
    }

    suspend fun saveFazenda(): Boolean {
        if (validateFazendaInput()) {
            val id = repository.insertFazenda(fazendaUiState.toFazenda())
            selectFazenda(id.toInt())
            return true
        }
        return false
    }

    fun loadItem(item: ItemEstoque) {
        itemUiState = item.toItemUiState()
    }

    fun resetUiState() {
        itemUiState = ItemUiState()
    }

    fun resetFazendaUiState() {
        fazendaUiState = FazendaUiState()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as android.app.Application)
                val database = AppDatabase.getDatabase(application)
                InventoryViewModel(ItemEstoqueRepository(database.itemEstoqueDao()))
            }
        }
    }
}

fun ItemEstoque.toItemUiState(): ItemUiState = ItemUiState(
    id = id,
    nome = nome,
    quantidade = quantidade.toString(),
    localArmazenamento = localArmazenamento,
    fazendaId = fazendaId
)

fun ItemUiState.toItemEstoque(): ItemEstoque = ItemEstoque(
    id = id,
    nome = nome.trim(),
    quantidade = quantidade.toIntOrNull() ?: 0,
    localArmazenamento = localArmazenamento.trim(),
    fazendaId = fazendaId
)

fun FazendaUiState.toFazenda(): Fazenda = Fazenda(
    id = id,
    proprietario = proprietario.trim(),
    fazenda = fazenda.trim(),
    localidade = localidade.trim()
)
