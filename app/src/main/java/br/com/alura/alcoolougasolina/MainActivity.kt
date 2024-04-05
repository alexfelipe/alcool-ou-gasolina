package br.com.alura.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import br.com.alura.alcoolougasolina.ui.theme.AlcoolOuGasolinaTheme

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
    var valorGasolina by remember {
        mutableStateOf("")
    }
    var valorAlcool by remember {
        mutableStateOf("")
    }

    val temGasolinaEAlcool = valorGasolina.isNotBlank() && valorAlcool.isNotBlank()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF00BCD4))
            .verticalScroll(rememberScrollState()),
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
                valorGasolina = it
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
                valorAlcool = it
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