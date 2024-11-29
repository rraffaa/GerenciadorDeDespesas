package com.example.gerenciamento_despesas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var expenses: MutableList<Conta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inicializa a lista de despesas
        expenses = getExpenses()

        // Configura o Adapter
        expenseAdapter = ExpenseAdapter(this, expenses, ::editExpense, ::deleteExpense)
        val lvExpenses = findViewById<ListView>(R.id.lvExpenses)
        lvExpenses.adapter = expenseAdapter

        // Configura o botão de adicionar despesa
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        btnAddExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }
    }

    // Função para editar a despesa
    private fun editExpense(expense: Conta) {
        // Aqui você pode abrir um novo Activity ou Dialog para editar a despesa
        val intent = Intent(this, AddExpenseActivity::class.java)
        intent.putExtra("expense_id", expense.id)
        startActivity(intent)
    }

    // Função para deletar a despesa
    private fun deleteExpense(expense: Conta) {
        // Aqui você pode remover a despesa da lista ou do banco de dados
        expenses.remove(expense)
        expenseAdapter.notifyDataSetChanged()
    }

    // Função fictícia para obter despesas
    private fun getExpenses(): MutableList<Conta> {
        return mutableListOf(
            Conta(1, "Conta de Luz", 100.0, "2024-12-01"),
            Conta(2, "Supermercado", 250.0, "2024-12-05")
        )
    }
}
