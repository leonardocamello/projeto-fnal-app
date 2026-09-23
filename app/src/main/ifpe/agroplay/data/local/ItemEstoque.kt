package br.edu.ifpe.agroplay.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itens_estoque")
data class ItemEstoque(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val quantidade: Int,
    val localArmazenamento: String
)
