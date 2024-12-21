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

class CadastroProdutoActivity : AppCompatActivity() {
    private lateinit var produtoDAO: ProdutoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_produto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        produtoDAO = ProdutoDAO(this)

        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etNome = findViewById<EditText>(R.id.etNome)
        val etDescricao = findViewById<EditText>(R.id.etDescricao)
        val etEstoque = findViewById<EditText>(R.id.etEstoque)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar) // Novo botão

        btnSalvar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            val nome = etNome.text.toString()
            val descricao = etDescricao.text.toString()
            val estoque = etEstoque.text.toString()

            if(codigo.isEmpty() || nome.isEmpty()
                || descricao.isEmpty() || estoque.isEmpty()){
                Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val produto = Produto(codigo, nome, descricao, estoque.toInt())
            val produtos = produtoDAO.listarProdutos()
            val produtoExistente = produtos.find { it.codigo == codigo }

            if (produtoExistente != null) {
                Toast.makeText(this, "Produto já Cadastrado!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sucesso = produtoDAO.adicionarProduto(produto)
            if(sucesso){
                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                limparCampos(etCodigo,etNome,etDescricao,etEstoque)
            }else{
                Toast.makeText(this, "Erro ao cadastrar produto!", Toast.LENGTH_SHORT).show()
            }
        }

        btnLimpar.setOnClickListener {
            limparCampos(etCodigo,etNome,etDescricao,etEstoque)
        }

        btnVoltar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun limparCampos(vararg campos: EditText){
        campos.forEach { it.text.clear() }
    }
}