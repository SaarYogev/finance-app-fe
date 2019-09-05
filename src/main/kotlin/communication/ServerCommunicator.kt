package communication

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.URLProtocol
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

private const val CORS_ANYWHERE_URL = "cors-anywhere.herokuapp.com/"
private const val BACKEND_URL = CORS_ANYWHERE_URL + "https://finance-app-be.herokuapp.com"

@ExperimentalCoroutinesApi
class ServerCommunicator : CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    fun sendGetToServer(path: String, idToken: String): String {
        val client = HttpClient(Js)
        var value = ""
        launch {
            value = async {
                try {
                    return@async client.get<String> {
                        url(URLProtocol.HTTPS.name, BACKEND_URL, path = path, block = {
                            parameter("tokenId", idToken)
                        })
                    }
                } catch (e: Exception) {
                    println("oops, exception: $e")
                    return@async ""
                }
            }.await()
        }
        return value
    }

    fun postToServer(path: String, payload: String): String {
        val client = HttpClient(Js)
        var value = ""
        launch {
            value = async {
                try {
                    return@async client.post<String> {
                        url(URLProtocol.HTTPS.name, BACKEND_URL, path = path)
                        body = payload
                    }
                } catch (e: Exception) {
                    println("oops, exception: $e")

                    return@async ""
                }
            }.await()
        }
        return value
    }
}

data class ServerPaths(val expenses: String = "/expenses", val login: String = "/login")