package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExcluirProdutoActivity : AppCompatActivity() {
    private lateinit var produtoDAO: ProdutoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_excluir_produto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        produtoDAO = ProdutoDAO(this)

        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val btnDeletar = findViewById<Button>(R.id.btnDeletar)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar) // Novo botão

        btnDeletar.setOnClickListener {
            val codigo = etCodigo.text.toString()

            if (codigo.isEmpty()) {
                Toast.makeText(this, "Código do produto é obrigatório!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val produtos = produtoDAO.listarProdutos()
            val produtoExistente = produtos.find { it.codigo == codigo }

            if (produtoExistente != null) {
                val sucesso = produtoDAO.deletarProduto(codigo)
                if (sucesso) {
                    Toast.makeText(this, "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                    limparCampos(etCodigo)
                } else {
                    Toast.makeText(this, "Erro ao deletar o produto!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
                limparCampos(etCodigo)
            }
        }

        btnLimpar.setOnClickListener {
            limparCampos(etCodigo)
        }

        btnVoltar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun limparCampos(vararg campos: EditText) {
        campos.forEach { it.text.clear() }
    }
}
