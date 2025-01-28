package com.example.study_repository.flow_in_practice.websocket

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch

class WebSocketClient(
    private val httpClient: HttpClient
) {
    private var session: WebSocketSession? = null

    suspend fun sendMessage(text: String) {
        session?.send(text)
    }

    fun listenToSocket(url: String): Flow<String> {
        return callbackFlow {

            session = httpClient.webSocketSession(
                urlString = url
            )

            session?.let { session ->
                session
                    .incoming
                    //Consome a sessão de dados vinda do webSocket como um hot flow
                    .consumeAsFlow()
                    // Retorna um flow contendo apenas valores que são instâncias do valor passado
                    // type R
                    .filterIsInstance<Frame.Text>()
                    .collect {
                        send(it.readText())
                    }
            }

                // Calls the specified function block with this value as its receiver and returns its result
                ?: run {
                session?.close()
                session = null
                close()
            }

            //Suspends the current coroutine until the channel is either closed or cancelled and
            // invokes the given block before resuming the coroutine
            awaitClose {
                launch(NonCancellable) {
                    session?.close()
                    session = null
                }
            }
        }
    }
}