package login

//import logo.logo
import communication.ServerCommunicator
import communication.ServerPaths
import kotlinx.coroutines.ExperimentalCoroutinesApi
import new_expense.newExpenseForm
import react.*
import react.dom.div
import react.dom.h1

private const val CLIENT_ID = "350848068386-imdrgn09f5hb6skhm37tel9v23bbuki9.apps.googleusercontent.com"

@ExperimentalCoroutinesApi
class App(private val serverCommunicator: ServerCommunicator = ServerCommunicator(), private val serverPaths: ServerPaths = ServerPaths()) : RComponent<RProps, App.State>() {
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
        println(idToken)
//        ServerCommunicator().sendGetToServer(serverPaths.expenses, idToken)
        setState {
            tokenId = idToken
        }
    }

}

@ExperimentalCoroutinesApi
fun RBuilder.app() = child(App::class) {}
