package com.example.gerenciamento_despesas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ExpenseAdapter(private val context: Context, private val expenses: List<Conta>) : BaseAdapter() {

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

        // Utilização de strings do arquivo strings.xml
        nameTextView.text = expense.nome
        amountTextView.text = context.getString(R.string.expense_amount, expense.valor)
        dateTextView.text = context.getString(R.string.expense_date, expense.dataVencimento)

        return view
    }
}
