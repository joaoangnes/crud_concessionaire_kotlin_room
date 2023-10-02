package com.example.atividadekotlin02

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VeiculoDAO {

    // Insere um veículo no banco de dados e retorna o ID gerado
    @Insert
    fun salvarVeiculo(veiculo: Veiculo): Long

    // Deleta um veículo do banco de dados
    @Delete
    fun deletarVeiculo(veiculo: Veiculo)

    // Atualiza as informações de um veículo no banco de dados
    @Update
    fun atualizarVeiculo(veiculo: Veiculo)

    // Seleciona todos os veículos da tabela "veiculos" no banco de dados
    @Query("SELECT * FROM veiculos")
    fun buscarVeiculos(): List<Veiculo>

    // Seleciona um veículo específico com base no ID fornecido
    @Query("SELECT * FROM veiculos WHERE id = :id")
    fun buscarVeiculo(id: Int): Veiculo

    // Conta o número total de veículos na tabela "veiculos"
    @Query("SELECT COUNT(*) FROM veiculos")
    fun totalVeiculos(): Int
}
