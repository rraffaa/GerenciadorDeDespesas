package com.example.gerenciamento_despesas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FilterExpenseActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_expense)

        dbHelper = DatabaseHelper(this)

        val etCategoryFilter = findViewById<EditText>(R.id.etCategoryFilter)
        val btnFilter = findViewById<Button>(R.id.btnFilter)
        val tvFilteredResults = findViewById<TextView>(R.id.tvFilteredResults)

        btnFilter.setOnClickListener {
            val category = etCategoryFilter.text.toString()
            if (category.isEmpty()) {
                tvFilteredResults.text = "Por favor, insira uma categoria para filtrar."
            } else {
                val filteredContas = dbHelper.getContasByCategoria(category)
                if (filteredContas.isEmpty()) {
                    tvFilteredResults.text = "Nenhuma despesa encontrada para a categoria: $category"
                } else {
                    // Exibir os resultados
                    val results = filteredContas.joinToString(separator = "\n") {
                        "ID: ${it.id}, Nome: ${it.nome}, Valor: R$${it.valor}, Data: ${it.dataVencimento}"
                    }
                    tvFilteredResults.text = results
                }
            }
        }
    }
}
