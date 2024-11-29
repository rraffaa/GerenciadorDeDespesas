package com.example.gerenciamento_despesas

data class Expense(
    val id: Int,                // ID da despesa
    val name: String,           // Nome da despesa (Ex: Conta de Luz)
    val amount: Double,         // Valor da despesa
    val dueDate: String,        // Data de vencimento
    val category: String,       // Categoria da despesa (Ex: Alimentação)
    val notes: String           // Notas sobre a despesa
)
