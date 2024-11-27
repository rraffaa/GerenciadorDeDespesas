package com.example.gerenciamento_despesas

import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testCreateRecord() {
        val record = Record("Conta de Luz", 150.0, "2024-11-15")
        assertNotNull(record)
        assertEquals("Conta de Luz", record.name)
        assertEquals(150.0, record.amount, 0.0)
        assertEquals("2024-11-15", record.dueDate)
    }

    @Test
    fun testInvalidAmount() {
        val invalidAmount = -50.0
        try {
            Record("Conta Invalida", invalidAmount, "2024-11-15")
            fail("Deveria ter lançado uma exceção para valor inválido")
        } catch (e: IllegalArgumentException) {
            assertEquals("O valor não pode ser negativo", e.message)
        }
    }

    @Test
    fun testUpdateRecord() {
        val record = Record("Conta de Água", 80.0, "2024-11-20")
        record.amount = 90.0
        assertEquals(90.0, record.amount, 0.0)
    }

    @Test
    fun testDeleteRecord() {
        val records = mutableListOf(
            Record("Conta de Luz", 150.0, "2024-11-15"),
            Record("Conta de Água", 80.0, "2024-11-20")
        )
        records.removeAt(0)
        assertEquals(1, records.size)
    }
}
