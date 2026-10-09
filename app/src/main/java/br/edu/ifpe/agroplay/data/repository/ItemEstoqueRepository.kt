package br.edu.ifpe.agroplay.data.repository

import br.edu.ifpe.agroplay.data.local.Fazenda
import br.edu.ifpe.agroplay.data.local.ItemEstoque
import br.edu.ifpe.agroplay.data.local.ItemEstoqueDao
import kotlinx.coroutines.flow.Flow

class ItemEstoqueRepository(private val itemEstoqueDao: ItemEstoqueDao) {
    fun getItemsByFazendaStream(fazendaId: Int): Flow<List<ItemEstoque>> = 
        itemEstoqueDao.getItemsByFazenda(fazendaId)

    fun getAllItemsStream(): Flow<List<ItemEstoque>> = itemEstoqueDao.getAllItems()

    suspend fun getItemStream(id: Int): ItemEstoque? = itemEstoqueDao.getItemById(id)

    suspend fun insertItem(item: ItemEstoque) = itemEstoqueDao.insertItem(item)

    suspend fun deleteItem(item: ItemEstoque) = itemEstoqueDao.deleteItemById(item.id)

    suspend fun updateItem(item: ItemEstoque) = itemEstoqueDao.updateItem(item)

    // Fazenda operations
    suspend fun insertFazenda(fazenda: Fazenda) = itemEstoqueDao.insertFazenda(fazenda)

    fun getAllFazendasStream(): Flow<List<Fazenda>> = itemEstoqueDao.getAllFazendas()

    suspend fun getFazendaById(id: Int): Fazenda? = itemEstoqueDao.getFazendaById(id)
}
