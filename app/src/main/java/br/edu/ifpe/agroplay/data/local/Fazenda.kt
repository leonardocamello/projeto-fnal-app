package br.edu.ifpe.agroplay.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fazendas")
data class Fazenda(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val proprietario: String,
    val fazenda: String,
    val localidade: String
)
