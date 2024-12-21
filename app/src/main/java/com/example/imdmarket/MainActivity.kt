package com.example.imdmarket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("IMDMarketPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn",false)

        if(isLoggedIn){
            irParaMenu()
        }else {
            irParaLogin()
        }
    }

    private fun irParaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irParaMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}