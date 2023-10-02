package com.example.atividadekotlin02

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "veiculos")
data class Veiculo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "placa")          var placa: String,
    @ColumnInfo(name = "marca")          var marca: String,
    @ColumnInfo(name = "modelo")         var modelo: String,
    @ColumnInfo(name = "ano_fabricacao") var ano_fabricacao: String,
    @ColumnInfo(name = "preco")          var preco: Double,
)