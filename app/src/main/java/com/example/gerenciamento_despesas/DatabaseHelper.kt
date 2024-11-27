package com.example.gerenciamento_despesas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Classe para representar uma despesa
data class Conta(
    val id: Int,
    val nome: String,
    val valor: Double,
    val dataVencimento: String,
    val categoria: String,
    val comentarios: String? // Novo campo para armazenar os comentários
)

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "despesas.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "contas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "nome"
        private const val COLUMN_VALUE = "valor"
        private const val COLUMN_DATE = "dataVencimento"
        private const val COLUMN_CATEGORY = "categoria"
        private const val COLUMN_COMMENTS = "comentarios" // Nova coluna
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Criação da tabela com a nova coluna para comentários
        val createTable = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_NAME TEXT NOT NULL,
            $COLUMN_VALUE REAL NOT NULL,
            $COLUMN_DATE TEXT NOT NULL,
            $COLUMN_CATEGORY TEXT NOT NULL,
            $COLUMN_COMMENTS TEXT
        )
    """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Método para adicionar uma despesa com comentários
    fun addConta(nome: String, valor: Double, dataVencimento: String, categoria: String, comentarios: String?) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, nome)
            put(COLUMN_VALUE, valor)
            put(COLUMN_DATE, dataVencimento)
            put(COLUMN_CATEGORY, categoria)
            put(COLUMN_COMMENTS, comentarios) // Adicionando os comentários
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Método para buscar todas as despesas
    fun getAllContas(): List<Conta> {
        val contas = mutableListOf<Conta>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val conta = Conta(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VALUE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENTS)) // Recuperando os comentários
            )
            contas.add(conta)
        }
        cursor.close()
        db.close()
        return contas
    }

    // Método para buscar despesas por categoria
    fun getContasByCategoria(categoria: String): List<Conta> {
        val contas = mutableListOf<Conta>()
        val db = readableDatabase
        val selection = "$COLUMN_CATEGORY = ?"
        val selectionArgs = arrayOf(categoria)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        while (cursor.moveToNext()) {
            val conta = Conta(
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VALUE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENTS)) // Recuperando os comentários
            )
            contas.add(conta)
        }
        cursor.close()
        db.close()
        return contas
    }
}
