package com.example.study_repository.flow_fundamentals

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

fun flowDemo() {
    val flow = flow<Int> {
        println("Collection started!")
        delay(1000L)
        emit(1)
        delay(2000L)
        emit(2)
        delay(3000L)
        emit(3)
    }.shareIn(
        scope = GlobalScope, // Escopo onde o fluxo será ativo
        started = SharingStarted.Eagerly,  // Quando o fluxo começa a compartilhar dados
        replay = 3 // Quantos itens reemitir para novos coletores
    )

//    scope: Define o escopo onde o fluxo será ativo. Geralmente, é o escopo da ViewModel ou Activity no Android.
//    started: Controla como e quando o fluxo começa a compartilhar os dados:
//    SharingStarted.Lazily: O fluxo começa apenas quando há pelo menos um coletor.
//    SharingStarted.Eagerly: O fluxo começa imediatamente, independente de coletores.
//    SharingStarted.WhileSubscribed(): O fluxo começa enquanto houver coletores ativos.
//    replay: Número de itens anteriores que serão enviados a novos coletores.
//    O replay é útil para garantir que novos coletores não percam eventos importantes que já aconteceram antes de eles se inscreverem no fluxo.



}