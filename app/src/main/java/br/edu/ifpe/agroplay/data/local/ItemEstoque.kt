package br.edu.ifpe.agroplay.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itens_estoque")
data class ItemEstoque(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val quantidade: Int,
    val localArmazenamento: String,
    val fazendaId: Int? = null
)
