package com.example.atividadekotlin02

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define a classe VeiculoDB como um banco de dados Room
@Database(entities = [Veiculo::class], version = 1)
abstract class VeiculoDB : RoomDatabase() {
    // Declara uma função abstrata que retorna o DAO (Data Access Object) para a entidade Veiculo
    abstract fun getVeiculosDAO(): VeiculoDAO

    companion object {
        private lateinit var INSTANCE: VeiculoDB

        // Obtém ou cria uma instância única do banco de dados Room
        fun getInstance(context: Context): VeiculoDB {
            if (!::INSTANCE.isInitialized) {
                // Inicializa a instância do banco de dados se ainda não estiver inicializada
                INSTANCE = Room.databaseBuilder(context, VeiculoDB::class.java, "veiculos")
                    .allowMainThreadQueries() // Permite consultas na thread principal (não recomendado para produção)
                    .build()
            }

            return INSTANCE
        }
    }
}
