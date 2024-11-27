package com.seuapp.gerenciamento_despesas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gerenciamento_despesas.DashboardActivity
import com.example.gerenciamento_despesas.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (isValidCredentials(email, password)) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                Toast.makeText(this, "Credenciais inválidas. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && email.contains("@")
    }
}
