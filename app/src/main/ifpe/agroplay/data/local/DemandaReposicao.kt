package br.edu.ifpe.agroplay.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demandas_reposicao")
data class DemandaReposicao(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val itemId: Long,
    val quantidadeSolicitada: Int,
    val status: String = "PENDENTE"
)
