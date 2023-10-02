package com.example.atividadekotlin02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividadekotlin02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var veiculoAdapter: VeiculoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout da atividade a partir do arquivo XML usando o binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o adaptador para a RecyclerView
        veiculoAdapter = VeiculoAdapter(this)

        // Define o layout da RecyclerView como LinearLayoutManager
        binding.rcvVeiculos.layoutManager = LinearLayoutManager(this)

        // Define o adaptador para a RecyclerView
        binding.rcvVeiculos.adapter = veiculoAdapter

        // Configura um clique no botão "Adicionar Novo Veículo" para iniciar a atividade de cadastro
        binding.btnAdicionarNovoVeiculo.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        // Atualiza o adaptador quando a atividade está prestes a ficar visível
        veiculoAdapter.atualizarAdapter()
    }
}
