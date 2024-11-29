package com.example.gerenciamento_despesas.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gerenciamento_despesas.Expense
import com.example.gerenciamento_despesas.DatabaseHelper

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = DatabaseHelper(application)
    private val _expenses = MutableLiveData<List<Expense>>()  // Use Expense aqui
    val expenses: LiveData<List<Expense>> get() = _expenses

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        _expenses.value = dbHelper.getAllContas().map { expense ->
            Expense(
                expense.id,
                expense.nome,
                expense.valor,
                expense.dataVencimento,
                expense.categoria,
                expense.comentarios ?: ""  // Se comentarios for nulo, usa uma string vazia
            )
        }
    }

    fun refreshExpenses() {
        loadExpenses()
    }

    fun deleteExpense(expense: Expense) {
        dbHelper.deleteConta(expense.id)
        loadExpenses()
    }
}
