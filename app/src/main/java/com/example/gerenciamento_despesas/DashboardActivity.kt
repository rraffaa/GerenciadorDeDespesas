package com.example.gerenciamento_despesas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dbHelper = DatabaseHelper(this)

        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        val lvExpenses = findViewById<ListView>(R.id.lvExpenses)

        // Botão para adicionar nova despesa
        btnAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        // Carregar lista de despesas
        loadExpenses(lvExpenses)
    }

    private fun loadExpenses(listView: ListView) {
        val contas = dbHelper.getAllContas() // Lista de objetos Conta
        val adapter = ExpenseAdapter(this, contas) // Passando a lista de Conta para o adaptador
        listView.adapter = adapter
    }
}
