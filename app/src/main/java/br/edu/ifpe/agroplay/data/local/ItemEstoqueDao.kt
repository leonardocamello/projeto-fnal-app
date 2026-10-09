package br.edu.ifpe.agroplay.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemEstoqueDao {
    @Query("SELECT * FROM itens_estoque WHERE fazendaId = :fazendaId ORDER BY id ASC")
    fun getItemsByFazenda(fazendaId: Int): Flow<List<ItemEstoque>>

    @Query("SELECT * FROM itens_estoque ORDER BY id ASC")
    fun getAllItems(): Flow<List<ItemEstoque>>

    @Query("SELECT * FROM itens_estoque WHERE id = :id")
    suspend fun getItemById(id: Int): ItemEstoque?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemEstoque): Long

    @Update
    suspend fun updateItem(item: ItemEstoque)

    @Query("DELETE FROM itens_estoque WHERE id = :id")
    suspend fun deleteItemById(id: Int)

    // Fazenda operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFazenda(fazenda: Fazenda): Long

    @Query("SELECT * FROM fazendas ORDER BY id ASC")
    fun getAllFazendas(): Flow<List<Fazenda>>

    @Query("SELECT * FROM fazendas WHERE id = :id")
    suspend fun getFazendaById(id: Int): Fazenda?
}
