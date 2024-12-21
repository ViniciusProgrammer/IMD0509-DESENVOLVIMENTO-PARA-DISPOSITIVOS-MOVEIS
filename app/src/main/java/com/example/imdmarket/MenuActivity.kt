package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnCadastrarProdutos).setOnClickListener {
            val intent = Intent(this, CadastroProdutoActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnListarProdutos).setOnClickListener {
            val intent = Intent(this, ListagemProdutoActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAlterarProdutos).setOnClickListener {
            val intent = Intent(this, AlterarProdutoActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnDeletarProdutos).setOnClickListener {
            val intent = Intent(this, ExcluirProdutoActivity::class.java)
            startActivity(intent)
        }
    }
}