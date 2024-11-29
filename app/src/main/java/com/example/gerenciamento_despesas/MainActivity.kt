package com.example.gerenciamento_despesas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.gerenciamento_despesas.ui.theme.Gerenciamento_DespesasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Gerenciamento_DespesasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    // Obtendo o contexto com LocalContext.current
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // Adicionando padding ao redor do conteúdo
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Saudação para o usuário
        Greeting(name = "Bem-vindo")

        // Botão para navegar para o Dashboard
        Button(
            onClick = {
                // Navegação para a tela do Dashboard
                val intent = Intent(context, DashboardActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp) // Adicionando margem superior ao botão
        ) {
            Text(text = "Adicionar Conta")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Olá $name!",
        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Gerenciamento_DespesasTheme {
        Greeting("Usuário")
    }
}
