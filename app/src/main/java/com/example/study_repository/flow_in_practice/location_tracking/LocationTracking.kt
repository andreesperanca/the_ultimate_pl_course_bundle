package com.example.study_repository.flow_in_practice.location_tracking

import android.content.Context
import android.location.Location
import com.example.study_repository.flow_fundamentals.LocationObserver
import com.example.study_repository.flow_in_practice.timer.timeAndEmit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.runningReduce
import kotlinx.coroutines.flow.zip
import kotlin.time.Duration
import kotlin.time.DurationUnit

fun Context.locationTracking() {
    val observer = LocationObserver(this)

    timeAndEmit(3f)
        // Running Reduce
        // Esse operador nesse caso faz uma soma de todos os valores anteriores no caso o totalElapsedTime,
        // O primeiro parâmetro da lambda e o newElapsedTime que é o valor mais recente emitido
        // newElapsedTime

        //runningReduce: Acumula os tempos decorridos emitidos, somando o valor mais recente
        .runningReduce { totalElapsedTime, newElapsedTime ->
            totalElapsedTime + newElapsedTime
        }
        // Combina o fluxo de tempos acumulados com um fluxo de localizações observadas
        // (observer.observeLocation), criando pares (totalDuration, location).

        // Zip é diferente do combine porque ele sincroniza os dois valores, nesse caso duração e
        // localização então o primeiro valor do flow de duração é zipado com primeiro valor emitido
        // pelo flow de duração e assim consecutivamente caso uns dos flows emita mais valores que
        // o outro esses valores serão ignorados
        .zip(observer.observeLocation(1000L)) { totalDuration, location ->
            totalDuration to location
        }

        //Imprime no console a localização rastreada e o tempo decorrido desde o início.
        .onEach { (totalDuration, location) ->
            println("Location (${location.latitude}, ${location.longitude}) was tracked " +
                    "after ${totalDuration.inWholeMilliseconds} milliseconds.")
        }
        //  Cria uma lista acumulada de todos os pares (Duration, Location) até o momento.
        // Esse operador é parecido com o runningReduce porém permite iniciar o valor por parâmetro
        .runningFold(initial = emptyList<Pair<Duration, Location>>()) { locations, newLocation ->
            locations + newLocation
        }
        //
        .map { allLocations ->
            // zipWithNext é um operador que emparelha cada elemento da lista com o próximo,
            // retornando um par de elementos consecutivos.
            allLocations.zipWithNext { (duration1, location1), (duration2, location2) ->
                val distance = location1.distanceTo(location2)
                val durationDifference = (duration2 - duration1).toDouble(DurationUnit.HOURS)


                // Verifica se a diferença de tempo é maior que 0. Caso seja, calcula a velocidade média;
                // caso contrário, a velocidade é definida como 0.
                if(durationDifference > 0.0) {
                    ((distance / 1000.0) / durationDifference)
                } else 0.0
            }.average() // Calcula a média das velocidades obtidas para todos os pares de localizações consecutivas.
        }
        .onEach { avgSpeed ->
            println("Average speed is $avgSpeed km/h")
        }
        .launchIn(GlobalScope)

}