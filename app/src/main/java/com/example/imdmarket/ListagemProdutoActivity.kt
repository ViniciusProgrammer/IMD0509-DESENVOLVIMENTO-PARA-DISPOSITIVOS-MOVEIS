package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListagemProdutoActivity : AppCompatActivity() {
    private lateinit var produtoDAO: ProdutoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listagem_produto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        produtoDAO = ProdutoDAO(this)

        val recyclerView = findViewById<RecyclerView>(R.id.rvProdutos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val produtos = produtoDAO.listarProdutos()
        val adapter = ProdutoAdapter(produtos)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}