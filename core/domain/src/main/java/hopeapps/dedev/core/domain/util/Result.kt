package hopeapps.dedev.core.domain.util

sealed interface Result<out D, out E: Error> {
    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: hopeapps.dedev.core.domain.util.Error>(val error: E): Result<Nothing, E>
}

//Permite transformar um Result<T, E> em um Result<R, E>, aplicando uma função map ao dado T (caso seja um Success).
//Se for um erro, ele mantém o erro sem modificações.

//val successResult: Result<Int, MyError> = Result.Success(42)
//
//val mappedSuccess: Result<String, MyError> = successResult.map { it.toString() }
//
//println(mappedSuccess) // Saída: Result.Success(data=42)
//
//// Exemplo de erro: O erro deve permanecer inalterado
//val errorResult: Result<Int, MyError> = Result.Error(MyError.NetworkError)
//
//val mappedError: Result<String, MyError> = errorResult.map { it.toString() }

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

fun <T, E: Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}
//val success: Result<Int, MyError> = Result.Success(10)
//val mappedResult: Result<String, MyError> = success.map { it.toString() }
// mappedResult será Result.Success("10")


//val success: Result<Int, MyError> = Result.Success(42)
//val empty: EmptyResult<MyError> = success.asEmptyDataResult()
// empty será Result.Success(Unit)

//val error: Result<Int, MyError> = Result.Error(MyError())
//val emptyError: EmptyResult<MyError> = error.asEmptyDataResult()
// emptyError será Result.Error(MyError())
typealias EmptyResult<E> = Result<Unit, E>

