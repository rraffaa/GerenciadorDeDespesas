package com.example.gerenciamento_despesas

import android.util.Log

class DatabaseException(message: String) : Exception(message)

// Certifique-se de que a função isValidDate esteja definida em algum lugar do seu código
fun isValidDate(date: String): Boolean {
    // Lógica para verificar se a data é válida
    if (date.isNotEmpty()) {
        // Simples verificação ou regex para validar o formato
        return true
    }
    return false
}

fun addConta(nome: String, valor: Double, dataVencimento: String): Long {
    try {
        if (nome.isEmpty() || valor <= 0 || !isValidDate(dataVencimento)) {
            throw DatabaseException("Dados inválidos.")
        }
        // Código para adicionar a conta no banco e retornar um ID, por exemplo
        return 1L // Exemplo de retorno de ID
    } catch (e: DatabaseException) {
        Log.e("DatabaseHelper", e.message.toString())
        return -1
    }
}
