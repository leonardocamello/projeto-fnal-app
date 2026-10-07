package br.edu.ifpe.agroplay.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fazendas")
data class FazendaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val cidade: String
)
