package hopeapps.dedev.core.domain.util

//fun main() {
//
//val successResult: Result<Int, MyError> = Result.Success(42)
//
//val mappedSuccess: Result<String, MyError> = successResult.map { it.toString() }
//
//    println((mappedSuccess as Result.Success).data)
//
//val errorResult: Result<Int, MyError> = Result.Error(MyError.NetworkError)
//val mappedError: Result<String, MyError> = errorResult.map { it.toString() }
//}
//
//sealed class MyError : Error {
//    object NetworkError : MyError()
//    object UnknownError : MyError()
//}