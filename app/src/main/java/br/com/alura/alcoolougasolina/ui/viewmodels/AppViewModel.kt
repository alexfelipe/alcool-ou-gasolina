package br.com.alura.alcoolougasolina.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppUiState(
    val alcool: String = "",
    val gasolina: String = "",
    val onGasolinaChange: (String) -> Unit = {},
    val onAlcoolChange: (String) -> Unit = {}
) {
    fun temGasolinaEAlcool(): Boolean {
        return gasolina.isNotBlank()
                && alcool.isNotBlank()
    }
}

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onGasolinaChange = { gasolina ->
                    _uiState.value = _uiState.value.copy(gasolina = gasolina)
                },
                onAlcoolChange = { alcool ->
                    _uiState.update {
                        it.copy(alcool = alcool)
                    }
                }
            )
        }
    }

//    private val _alcool = MutableStateFlow("")
//    val alcool = _alcool.asStateFlow()
//    private val _gasolina = MutableStateFlow("")
//    val gasolina = _gasolina.asStateFlow()
//
//    fun onAlcoolChange(value: String) {
//        _alcool.update {
//            value
//        }
//    }
//
//    fun onGasolinaChange(value: String) {
//        _gasolina.update {
//            value
//        }
//    }
//
//    fun temGasolinaEAlcool(): Boolean {
//        return gasolina.value.isNotBlank() && alcool.value.isNotBlank()
//    }


}