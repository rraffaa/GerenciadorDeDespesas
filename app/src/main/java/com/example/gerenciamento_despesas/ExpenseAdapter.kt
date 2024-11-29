package com.example.gerenciamento_despesas.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.gerenciamento_despesas.Expense // Importando a classe Expense
import com.example.gerenciamento_despesas.R

class ExpenseAdapter(
    context: Context,
    private var expenses: MutableList<Expense>,
    private val editExpense: (Expense) -> Unit,
    private val deleteExpense: (Expense) -> Unit
) : ArrayAdapter<Expense>(context, R.layout.item_expense, expenses) {

    // Sobrescreve o método getView para customizar o layout de cada item
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false)

        val expense = expenses[position]

        // Referenciando os elementos corretamente com os IDs fornecidos no layout
        val txtName = view.findViewById<TextView>(R.id.expenseName)
        val txtAmount = view.findViewById<TextView>(R.id.expenseAmount)
        val txtDate = view.findViewById<TextView>(R.id.expenseDate)
        val txtCategory = view.findViewById<TextView>(R.id.expenseCategory)
        val txtComments = view.findViewById<TextView>(R.id.expenseComments)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)

        // Preenchendo os campos com as informações da despesa
        txtName.text = expense.name
        txtAmount.text = context.getString(R.string.expense_amount, expense.amount)
        txtDate.text = expense.dueDate
        txtCategory.text = expense.category
        txtComments.text = expense.notes

        // Configurando o botão de editar
        btnEdit.setOnClickListener { editExpense(expense) }

        // Configurando o botão de excluir
        btnDelete.setOnClickListener { deleteExpense(expense) }

        return view
    }

    // Método para atualizar a lista de despesas e notificar a interface
    fun updateList(expenses: List<Expense>) {
        // Só atualiza se a lista realmente mudou
        if (this.expenses != expenses) {
            this.expenses = expenses.toMutableList()  // Converte a lista para MutableList
            notifyDataSetChanged()  // Notifica que os dados mudaram
        }
    }
}
