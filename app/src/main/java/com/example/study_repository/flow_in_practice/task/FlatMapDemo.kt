package com.example.study_repository.flow_in_practice.task

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun flatMapDemo() {
    flow<Int> {
        emit(1)
        delay(500L)
        emit(2)
        delay(500L)
        emit(3)
    }.flatMapLatest {
//        O operador flatMapLatest cria um novo fluxo para cada valor emitido pelo fluxo original.
//        Se o fluxo original emitir um novo valor antes que o fluxo atual termine, o fluxo anterior
//        é cancelado e o novo fluxo é iniciado. Portanto, apenas as últimas emissões do fluxo interno
//        serão observadas, e todas as emissões anteriores serão descartadas.
        flow {
            emit("One")
            delay(1000L)
            emit("Two")
            delay(1000L)
            emit("Three")
        }
    }
        .onEach {
            println("Emission is $it")
        }
        .launchIn(GlobalScope)
}