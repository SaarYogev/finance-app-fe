package login

//import logo.logo
import communication.ServerCommunicator
import communication.ServerPaths
import kotlinx.coroutines.ExperimentalCoroutinesApi
import new_expense.newExpenseForm
import react.*
import react.dom.div
import react.dom.h1

private const val CLIENT_ID = "378599466850-opgj7bf4u2ik9au5rhk5fefr82av8a2j.apps.googleusercontent.com"

@ExperimentalCoroutinesApi
class App: RComponent<RProps, App.State>() {
    private val serverCommunicator: ServerCommunicator = ServerCommunicator()
    private val serverPaths: ServerPaths = ServerPaths()
    interface State : RState {
        var tokenId: String?
    }

    override fun RBuilder.render() {
        div("App-header") {
            //            logo()
            h1 {
                +"Expenses Manager"
            }
        }
        val tokenId = state.tokenId
        if (tokenId != null) {
            div("New expense form") {
                newExpenseForm(tokenId)
            }
        } else {
            GoogleLogin {
                attrs {
                    clientId = CLIENT_ID
                    buttonText = "Sign in"
                    onSuccess = this@App::onSignIn
                    onFailure = this@App::onFail
                }
            }
        }
    }

    private fun onFail(failureDetails: GoogleLoginFailureDetails) {
        println("failed to sign in. error: ${failureDetails.error}. details: ${failureDetails.details}")
    }

    private fun onSignIn(googleUser: GoogleUser) {
        val idToken = googleUser.getAuthResponse().id_token
        // pings server to wake him up
        serverCommunicator.sendGetToServer(serverPaths.expenses, idToken)
        setState {
            tokenId = idToken
        }
    }

}

@ExperimentalCoroutinesApi
fun RBuilder.app() = child(App::class) {}
