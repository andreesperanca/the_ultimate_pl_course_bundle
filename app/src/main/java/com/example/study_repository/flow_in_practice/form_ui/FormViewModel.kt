package com.example.study_repository.flow_in_practice.form_ui

import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class FormViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    val canRegister: StateFlow<Boolean> = email
        .debounce(500L)
        // Debounce é uma ferramenta útil para quando o flow emite muitos valores em rápida sucessão,
        // permitindo que apenas o último evento dentro de um período de tempo configurado seja emitido.


        //O combine não "espera" os fluxos emitirem valores de maneira pareada, e, quando um fluxo
        // emite mais de um valor, ele sempre pega o valor mais recente do outro fluxo e combina os dois.
        // diferente do zip
        .combine(
            password
        )
        // O operador combine no contexto de Flows no Kotlin permite combinar dois flows
        // Sempre que qualquer um dos fluxos emitir um novo valor, o operador combine será acionado.



        { email, password ->
            val isValidEmail = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
            val isValidPassword = password.any { !it.isLetterOrDigit() } && password.length > 9

            //Retornará se é válido email e senha do usuário
            isValidPassword && isValidEmail
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = false
        )


    //SCOPE
    // Define o escopo de coleta do Flow.

    // STARTED
    // Especifica quando o StateFlow começa e para de compartilhar valores:
    // Enquanto houver assinantes (coletors): o fluxo emitirá valores.
    // Timeout de 5000ms: se todos os assinantes forem desconectados, o fluxo continuará ativo por mais 5 segundos antes de ser interrompido (cancelado).

    //initialValue
    //Valor inicial


    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }
}