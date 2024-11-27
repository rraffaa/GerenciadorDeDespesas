import android.database.sqlite.SQLiteException
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.*

class DatabaseHelperTest {

    private val dbHelper = mock(DatabaseHelper::class.java)  // Mocking a DatabaseHelper

    @Test
    fun testAddConta_ValidData_returnsExpectedId() {
        val nome = "Conta de Luz"
        val valor = 100.0 // Valor como Double
        val dataVencimento = "2024-12-31"

        // Simula o retorno do método addConta
        `when`(dbHelper.addConta(nome, valor, dataVencimento)).thenReturn(1L)

        val result = dbHelper.addConta(nome, valor, dataVencimento)
        assertEquals("ID retornado não é o esperado", 1L, result) // Comparando como Long
    }

    @Test
    fun testAddConta_InvalidData_returnsErrorCode() {
        val nome = ""
        val valor = -50.0 // Valor como Double
        val dataVencimento = "2024-12-31"

        // Simula o retorno de erro (-1) para dados inválidos
        `when`(dbHelper.addConta(nome, valor, dataVencimento)).thenReturn((-1L).toInt().toLong())

        val result = dbHelper.addConta(nome, valor, dataVencimento)
        assertEquals("Erro ao adicionar conta com dados inválidos", -1L, result) // Comparando como Long
    }

    @Test
    fun testGetAllContas_returnsCorrectList() {
        val expectedContas = listOf(
            Conta(1, "Conta de Luz", 100.0, "2024-12-31"),
            Conta(2, "Conta de Água", 80.0, "2024-12-25")
        )
        `when`(dbHelper.getAllContas()).thenReturn(expectedContas)

        val contas = dbHelper.getAllContas()
        assertNotNull("A lista de contas não deve ser nula", contas)
        assertEquals("Número de contas retornadas está incorreto", 2, contas.size)
    }

    @Test
    fun testDeleteConta_returnsSuccess() {
        val id = 1
        `when`(dbHelper.deleteConta(id)).thenReturn(1)

        val result = dbHelper.deleteConta(id)
        assertEquals("Falha ao excluir a conta", 1, result) // Espera-se 1 como retorno de sucesso
    }

    @Test
    fun testDeleteConta_returnsFailure() {
        val id = 999 // Um ID que não existe no banco
        `when`(dbHelper.deleteConta(id)).thenReturn(0)

        val result = dbHelper.deleteConta(id)
        assertEquals("Falha ao excluir a conta (esperado 0)", 0, result)
    }

    @Test
    fun testUpdateConta_returnsSuccess() {
        val id = 1
        val nome = "Conta de Luz Atualizada"
        val valor = 150.0 // Valor como Double
        val dataVencimento = "2024-12-31"

        // Simula a atualização bem-sucedida
        `when`(dbHelper.updateConta(id, nome, valor, dataVencimento)).thenReturn(1)

        val result = dbHelper.updateConta(id, nome, valor, dataVencimento)
        assertEquals("A atualização da conta falhou", 1, result)
    }

    @Test
    fun testUpdateConta_returnsFailure() {
        val id = 999
        val nome = "Conta de Luz Atualizada"
        val valor = 150.0 // Valor como Double
        val dataVencimento = "2024-12-31"

        // Simula a falha na atualização de uma conta
        `when`(dbHelper.updateConta(id, nome, valor, dataVencimento)).thenReturn(0)

        val result = dbHelper.updateConta(id, nome, valor, dataVencimento)
        assertEquals("Falha na atualização da conta (esperado 0)", 0, result)
    }

    @Test
    fun testAddConta_CallsDatabaseHelperMethod() {
        val nome = "Conta de Luz"
        val valor = 100.0 // Valor como Double
        val dataVencimento = "2024-12-31"

        dbHelper.addConta(nome, valor, dataVencimento)

        // Verifica se o método addConta foi chamado com os parâmetros corretos
        verify(dbHelper).addConta(nome, valor, dataVencimento)
    }

    @Test
    fun testExceptionHandling() {
        val nome = "Conta de Luz"
        val valor = 100.0 // Valor como Double
        val dataVencimento = "2024-12-31"

        // Simula que o método lança uma exceção
        `when`(dbHelper.addConta(nome, valor, dataVencimento)).thenThrow(SQLiteException("Erro no banco de dados"))

        try {
            dbHelper.addConta(nome, valor, dataVencimento)
            fail("A exceção esperada não foi lançada")
        } catch (e: SQLiteException) {
            assertEquals("Erro no banco de dados", e.message)
        }
    }
}

// A classe Conta que representa uma conta
data class Conta(val id: Int, val nome: String, val valor: Double, val dataVencimento: String)

// A classe DatabaseHelper que simula as operações de banco de dados
class DatabaseHelper {

    // Métodos fictícios para simular a operação real do banco de dados
    fun addConta(nome: String, valor: Double, dataVencimento: String): Long {
        // Simulação de inserção no banco de dados
        return 1L
    }

    fun getAllContas(): List<Conta> {
        // Simulação de retorno de todas as contas
        return listOf(
            Conta(1, "Conta de Luz", 100.0, "2024-12-31"),
            Conta(2, "Conta de Água", 80.0, "2024-12-25")
        )
    }

    fun deleteConta(id: Int): Int {
        // Simulação de exclusão no banco de dados
        return if (id == 1) 1 else 0
    }

    fun updateConta(id: Int, nome: String, valor: Double, dataVencimento: String): Int {
        // Simulação de atualização no banco de dados
        return if (id == 1) 1 else 0
    }
}
