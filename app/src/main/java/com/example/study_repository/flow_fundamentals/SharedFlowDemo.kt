package com.example.study_repository.flow_fundamentals

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun sharedFlowDemo() {


    //Cold ( Frio )
//    Frio (cold):
//
//    O código dentro do flow {} só será executado quando alguém o coletar (chamar collect()).
//    Cada coletor inicia uma nova execução do fluxo. Isso significa que, se dois coletores estiverem ouvindo o mesmo fluxo, eles terão execuções separadas.
//    Unidirecional:
//
//    Os valores fluem de um produtor para um consumidor.
//    Você não pode enviar valores ao fluxo diretamente.
//    Simples e Estático:
//
//    Você define a lógica de produção no bloco flow {}.
//    Não é usado para compartilhar eventos com vários consumidores simultâneos.

    val flow = flow<Int> {
        // Lófica
        emit(1)
        emit(2)
        emit(3)
        emit(4)
    }.onEach {
//        println(it)
    }.launchIn(GlobalScope)


    //Hot
//    Existe independentemente de haver coletores ouvindo.
//    Os valores são emitidos assim que você chama emit ou tryEmit, e qualquer coletor ativo no momento receberá esses valores.
//    Multidirecional:
//
//    Permite que vários produtores enviem valores simultaneamente.
//    Pode ter múltiplos coletores, e todos os coletores ativos recebem os mesmos valores emitidos.
//    Buffer e Configurações de Overflow:
//
//    Com o parâmetro extraBufferCapacity, você pode configurar um buffer para armazenar valores que ainda não foram processados por coletores.
//    Você pode escolher como lidar com situações de buffer cheio usando onBufferOverflow (DROP_OLDEST, DROP_LATEST, ou SUSPEND).


//    Quando Usar Cada Um
//    Use flow:
//    Quando você precisa produzir dados sob demanda.
//    Quando a lógica de produção precisa ser executada separadamente para cada coletor.
//    Para fluxos simples, como leitura de dados de uma API ou banco de dados.
//
//
//    Use MutableSharedFlow:
//    Quando você precisa compartilhar eventos ou estados entre múltiplos coletores.
//    Quando os valores precisam ser emitidos dinamicamente e em tempo real.
//    Para casos como notificações, eventos de UI, ou comunicação entre componentes de um sistema.


//    O buffer pode armazenar até extraBufferCapacity valores adicionais antes de aplicar a política de overflow.
//    Quando o buffer está cheio, a política definida em onBufferOverflow decide como lidar com novos valores emitidos:
//    Descartar o mais antigo (DROP_OLDEST).
//    Descartar o mais recente (DROP_LATEST).
//    Suspender o emissor até que haja espaço (SUSPEND).

    val sharedFlow = MutableSharedFlow<Int>(
        extraBufferCapacity = 3, // Permite armazenar até 3 valores extras
        onBufferOverflow = BufferOverflow.DROP_OLDEST // Descarta o mais antigo quando o buffer estiver cheio
    )

    GlobalScope.launch {
        sharedFlow.onEach {
            println("Collector 1: $it")
            delay(1000L)
        }.launchIn(GlobalScope)

    }

    GlobalScope.launch {
        repeat(10) {
            delay(100L)
            sharedFlow.emit(it)
        }
    }
}