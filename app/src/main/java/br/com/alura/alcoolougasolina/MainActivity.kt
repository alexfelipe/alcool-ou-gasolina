package br.com.alura.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.alura.alcoolougasolina.ui.theme.AlcoolOuGasolinaTheme
import br.com.alura.alcoolougasolina.ui.viewmodels.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlcoolOuGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val viewModel: AppViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val valorGasolina = uiState.gasolina
    val valorAlcool = uiState.alcool
    val temGasolinaEAlcool = uiState.temGasolinaEAlcool()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF00BCD4))
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Álcool ou Gasolina?",
            style = LocalTextStyle.current.copy(
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        AnimatedVisibility(visible = temGasolinaEAlcool) {
            if (temGasolinaEAlcool) {
                val ehGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7
                Text(
                    text = if (ehGasolina) "Gasolina" else "Álcool",
                    style = LocalTextStyle.current.copy(
                        color = if (ehGasolina) Color.Red else Color.Magenta,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = valorGasolina, onValueChange = {
                uiState.onGasolinaChange(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = {
                Text(text = "Gasolina")
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextField(
            value = valorAlcool, onValueChange = {
                uiState.onAlcoolChange(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = {
                Text(text = "Álcool")
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AlcoolOuGasolinaTheme {
        App()
    }
}