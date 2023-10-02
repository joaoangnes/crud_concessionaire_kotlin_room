package com.example.atividadekotlin02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.atividadekotlin02.databinding.ActivityCadastroBinding
import java.text.DecimalFormat

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var veiculoTemp: Veiculo
    private val dao = VeiculoDB.getInstance(this).getVeiculosDAO()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtém o ID do veículo passado como extra
        id = intent.getIntExtra("id", 0)

        // Verifica se há um ID válido para edição e preenche os campos com os dados do veículo
        if (id > 0) {
            veiculoTemp = dao.buscarVeiculo(id)
            // Torna o botão de deleção visivel
            binding.btnDeletarVeiculo.visibility = View.VISIBLE
            binding.txtLabel.text = "Editar Veiculo"
            binding.edtPlacaVeiculo.setText(veiculoTemp.placa)
            binding.edtMarcaVeiculo.setText(veiculoTemp.marca)
            binding.edtModeloVeiculo.setText(veiculoTemp.modelo)
            binding.edtAnoFabricacaoVeiculo.setText(veiculoTemp.ano_fabricacao)
            binding.edtPrecoVeiculo.setText(veiculoTemp.preco.toString())
        }

        // Configura um clique no botão "Salvar Veículo"
        binding.btnSalvarVeiculo.setOnClickListener {
            validarDados()
        }

        // Configura um clique no botão "Deletar Veículo" (visível apenas durante a edição)
        binding.btnDeletarVeiculo.setOnClickListener {
            veiculoTemp?.let {
                dao.deletarVeiculo(it)
                Util.exibirToast(this, "Veiculo excluído com sucesso!")
                finish()
            } ?: Util.exibirToast(this, "Não foi possível deletar o veículo, pois ele não existe.")
        }
    }


    // Função para validar os dados e salvar ou atualizar o veículo
    private fun validarDados() {
        if (binding.edtPlacaVeiculo.text.isEmpty() ||
            binding.edtMarcaVeiculo.text.isEmpty() ||
            binding.edtModeloVeiculo.text.isEmpty() ||
            binding.edtAnoFabricacaoVeiculo.text.isEmpty() ||
            binding.edtPrecoVeiculo.text.isEmpty()) {
            Util.exibirToast(this, "Preencha todos os campos")
            return
        }

        val veiculo = Veiculo(
            id,
            binding.edtPlacaVeiculo.text.toString(),
            binding.edtMarcaVeiculo.text.toString(),
            binding.edtModeloVeiculo.text.toString(),
            binding.edtAnoFabricacaoVeiculo.text.toString(),
            binding.edtPrecoVeiculo.text.toString().toDouble()
        )

        if (id > 0) {
            // Atualiza os dados do veículo no banco de dados
            dao.atualizarVeiculo(veiculo)
            finish()
        } else {
            // Salva um novo veículo no banco de dados
            if (dao.salvarVeiculo(veiculo) > 0) {
                Util.exibirToast(this, "Veículo cadastrado com sucesso!")
                finish()
            }
        }
    }
}
