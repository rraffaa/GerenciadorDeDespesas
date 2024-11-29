package com.example.gerenciamento_despesas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ExpenseAdapter(
    private val context: Context,
    private val expenses: List<Conta>,
    private val onEdit: (Conta) -> Unit,
    private val onDelete: (Conta) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int {
        return expenses.size
    }

    override fun getItem(position: Int): Any {
        return expenses[position]
    }

    override fun getItemId(position: Int): Long {
        return expenses[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false)
        val expense = expenses[position]

        val nameTextView: TextView = view.findViewById(R.id.expenseName)
        val amountTextView: TextView = view.findViewById(R.id.expenseAmount)
        val dateTextView: TextView = view.findViewById(R.id.expenseDate)
        val categoryTextView: TextView = view.findViewById(R.id.expenseCategory)
        val commentsTextView: TextView = view.findViewById(R.id.expenseComments)
        val editButton: Button = view.findViewById(R.id.btnEdit)
        val deleteButton: Button = view.findViewById(R.id.btnDelete)

        // Preencher com as informações da despesa
        nameTextView.text = expense.nome
        amountTextView.text = context.getString(R.string.expense_amount, expense.valor)
        dateTextView.text = expense.dataVencimento
        categoryTextView.text = expense.categoria
        commentsTextView.text = expense.comentarios ?: context.getString(R.string.hint_expense_comments)

        // Ação do botão Editar
        editButton.setOnClickListener {
            onEdit(expense)
            Toast.makeText(context, "Editar: ${expense.nome}", Toast.LENGTH_SHORT).show()
        }

        // Ação do botão Deletar
        deleteButton.setOnClickListener {
            onDelete(expense)
            Toast.makeText(context, "Deletar: ${expense.nome}", Toast.LENGTH_SHORT).show()
            val dbHelper = DatabaseHelper(context)
            dbHelper.deleteConta(expense.id)
        }

        return view
    }
}
