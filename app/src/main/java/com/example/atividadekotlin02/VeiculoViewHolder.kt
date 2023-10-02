package com.example.atividadekotlin02

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Classe responsável por representar a visão (view) de um item na RecyclerView
class VeiculoViewHolder(veiculoLayout: View) :  RecyclerView.ViewHolder(veiculoLayout) {
    // Declaração de propriedades (TextViews) para exibir os dados do veículo
    val txtVeiculo = veiculoLayout.findViewById<TextView>(R.id.txtVeiculo)
    val txtAnoFabricacaoVeiculo = veiculoLayout.findViewById<TextView>(R.id.txtAnoFabricacaoVeiculo)
    val txtPrecoVeiculo = veiculoLayout.findViewById<TextView>(R.id.txtPrecoVeiculo)
}
