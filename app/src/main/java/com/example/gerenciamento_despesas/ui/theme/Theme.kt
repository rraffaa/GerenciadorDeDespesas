package com.example.gerenciamento_despesas.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Outros padrões de cores para substituir
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Gerenciamento_DespesasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Cores dinâmicas estão disponíveis no Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    // Aviso sobre suporte a cores dinâmicas
    if (dynamicColor && Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
        Log.w("Theme", "Cores dinâmicas não são suportadas nesta versão do Android. Usando cores estáticas.")
    }

    // Definição do esquema de cores
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            try {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            } catch (e: Exception) {
                // Em caso de erro, usar esquemas estáticos
                Log.e("Theme", "Erro ao aplicar cores dinâmicas: ${e.message}. Usando cores estáticas.")
                if (darkTheme) DarkColorScheme else LightColorScheme
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
