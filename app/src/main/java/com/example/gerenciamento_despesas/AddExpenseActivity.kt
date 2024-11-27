package com.example.gerenciamento_despesas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        dbHelper = DatabaseHelper(this)

        // Referência aos campos no layout
        val etName = findViewById<EditText>(R.id.etExpenseName)
        val etAmount = findViewById<EditText>(R.id.etExpenseAmount)
        val etDate = findViewById<EditText>(R.id.etExpenseDate)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val etComments = findViewById<EditText>(R.id.etComments) // Alterado para o ID correto
        val btnSave = findViewById<Button>(R.id.btnSaveExpense)

        // Evento de clique no botão Salvar
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val amount = etAmount.text.toString().trim()
            val date = etDate.text.toString().trim()
            val category = etCategory.text.toString().trim()
            val comments = etComments.text.toString().trim()  // Captura os comentários

            // Validação dos campos
            when {
                name.isEmpty() -> {
                    Toast.makeText(this, getString(R.string.error_name_required), Toast.LENGTH_LONG).show()
                }
                amount.isEmpty() || amount.toDoubleOrNull() == null || amount.toDouble() <= 0 -> {
                    Toast.makeText(this, getString(R.string.error_valid_amount), Toast.LENGTH_LONG).show()
                }
                !isValidDate(date) -> {
                    Toast.makeText(this, getString(R.string.error_invalid_date), Toast.LENGTH_LONG).show()
                }
                category.isEmpty() -> {
                    Toast.makeText(this, getString(R.string.error_category_required), Toast.LENGTH_LONG).show()
                }
                else -> {
                    try {
                        // Normaliza a data para o formato ISO antes de salvar
                        val normalizedDate = normalizeDate(date)

                        // Adiciona a despesa ao banco de dados, incluindo comentários
                        dbHelper.addConta(name, amount.toDouble(), normalizedDate, category, comments.ifEmpty { null })
                        Toast.makeText(this, getString(R.string.expense_added_successfully), Toast.LENGTH_SHORT).show()
                        finish() // Fecha a atividade após salvar
                    } catch (e: Exception) {
                        Toast.makeText(
                            this,
                            getString(R.string.error_saving_expense, e.message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    // Função para validar se a data está no formato correto (dd/MM/yyyy)
    private fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false
        return try {
            dateFormat.parse(date) != null
        } catch (e: Exception) {
            false
        }
    }

    // Função para normalizar a data para o formato ISO (yyyy-MM-dd)
    private fun normalizeDate(date: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val isoFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate = dateFormat.parse(date)
        return isoFormat.format(parsedDate ?: Date()) // Retorna a data no formato ISO
    }
}
