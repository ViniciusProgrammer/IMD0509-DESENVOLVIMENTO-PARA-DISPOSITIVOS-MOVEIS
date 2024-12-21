package com.example.imdmarket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etSenha = findViewById<EditText>(R.id.etSenha)

        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val senha = etSenha.text.toString()

            if(validarLogin(usuario,senha)){
                val prefs = getSharedPreferences("IMDMarketPrefs", Context.MODE_PRIVATE)
                prefs.edit().putBoolean("isLoggedIn", true).apply()

                irParaMenu()
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validarLogin(usuario: String, senha: String): Boolean {
        return usuario == "admin" && senha == "admin"
    }

    private fun irParaMenu(){
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}