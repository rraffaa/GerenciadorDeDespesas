package com.example.gerenciamento_despesas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.gerenciamento_despesas.ui.adapters.ExpenseAdapter
import com.example.gerenciamento_despesas.ui.viewmodel.ExpenseViewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenseViewModel: ExpenseViewModel by viewModels()

    // Launcher para o AddExpenseActivity
    private val addExpenseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                expenseViewModel.refreshExpenses()  // Atualiza a lista de despesas
            }
        }

    // Launcher para o EditExpenseActivity
    private val editExpenseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                expenseViewModel.refreshExpenses()  // Atualiza a lista de despesas
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inicializa o adaptador e configura a lista de despesas
        expenseAdapter = ExpenseAdapter(this, mutableListOf(), ::editExpense, ::deleteExpense)
        val lvExpenses = findViewById<ListView>(R.id.lvExpenses)
        lvExpenses.adapter = expenseAdapter

        // Observa as mudanças na lista de despesas através do LiveData
        expenseViewModel.expenses.observe(this) { expenses ->
            expenseAdapter.updateList(expenses)
        }

        // Configura o botão de adicionar despesa
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        btnAddExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            addExpenseLauncher.launch(intent)  // Usando o launcher para adicionar despesa
        }
    }

    private fun editExpense(expense: Expense) {
        val intent = Intent(this, AddExpenseActivity::class.java)
        intent.putExtra("expense_id", expense.id)
        editExpenseLauncher.launch(intent)  // Usando o launcher para editar despesa
    }

    private fun deleteExpense(expense: Expense) {
        expenseViewModel.deleteExpense(expense)
        Toast.makeText(this, R.string.expense_deleted_successfully, Toast.LENGTH_SHORT).show()
    }
}
