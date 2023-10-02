package com.example.atividadekotlin02

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class VeiculoAdapter(val context: Context) : RecyclerView.Adapter<VeiculoViewHolder>() {
    val dao = VeiculoDB.getInstance(context).getVeiculosDAO()
    var listaVeiculos = dao.buscarVeiculos()

    // Cria e infla a visualização de cada item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiculoViewHolder {
        val veiculoLayout = LayoutInflater.from(context)
            .inflate(R.layout.veiculo_layout, parent, false)
        val veiculoViewHolder = VeiculoViewHolder(veiculoLayout)
        return veiculoViewHolder
    }

    // Preenche os dados de cada item da lista com base nos dados do veículo
    override fun onBindViewHolder(holder: VeiculoViewHolder, position: Int) {
        val veiculo = listaVeiculos[position]

        // Define os textos exibidos nos elementos da lista com os dados do veículo
        holder.txtVeiculo.text = "Veiculo: ${veiculo.marca} - ${veiculo.modelo}"
        holder.txtAnoFabricacaoVeiculo.text = "Ano Fabricação: ${veiculo.ano_fabricacao}"

        // Formata o preço do veículo como moeda brasileira
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        holder.txtPrecoVeiculo.text = "Preço Venda: ${currencyFormat.format(veiculo.preco)}"

        // Configura um clique longo para editar o veículo quando pressionado
        holder.txtVeiculo.setOnLongClickListener {
            var intent = Intent(context, CadastroActivity::class.java)
            intent.putExtra("id", veiculo.id)
            context.startActivity(intent)
            true
        }
    }

    // Retorna a quantidade de itens na lista
    override fun getItemCount(): Int {
        return listaVeiculos.size
    }

    // Atualiza a lista de veículos e notifica a RecyclerView sobre a mudança
    fun atualizarAdapter() {
        listaVeiculos = dao.buscarVeiculos()
        notifyDataSetChanged()
    }
}