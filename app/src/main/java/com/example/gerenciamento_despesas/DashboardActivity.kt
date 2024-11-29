package com.example.gerenciamento_despesas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var expenses: MutableList<Conta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inicializa a lista de despesas
        expenses = getExpenses()

        // Configura o Adapter, passando os métodos de editar e deletar como parâmetros
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
        val intent = Intent(this, AddExpenseActivity::class.java)
        intent.putExtra("expense_id", expense.id)  // Passa o ID da despesa para edição
        startActivity(intent)
    }

    // Função para deletar a despesa
    private fun deleteExpense(expense: Conta) {
        val db = DatabaseHelper(this)
        db.deleteConta(expense.id)  // Atualiza o método para usar deleteConta
        expenses.remove(expense)  // Remove da lista local
        expenseAdapter.notifyDataSetChanged()  // Atualiza a lista
        Toast.makeText(this, R.string.expense_deleted_successfully, Toast.LENGTH_SHORT).show()
    }

    // Função fictícia para obter despesas (substituir por acesso ao banco de dados real)
    private fun getExpenses(): MutableList<Conta> {
        return mutableListOf(
            Conta(1, "Conta de Luz", 100.0, "2024-12-01", "Contas Mensais", "Pagar até o vencimento."),
            Conta(2, "Supermercado", 250.0, "2024-12-05", "Alimentação", "Verificar promoções antes.")
        )
    }
}