package tictactoe

//import logo.logo
import new_expense.newExpenseForm
import react.*
import react.dom.div
import react.dom.h1

private const val CLIENT_ID = "350848068386-imdrgn09f5hb6skhm37tel9v23bbuki9.apps.googleusercontent.com"

class App : RComponent<RProps, App.State>() {
    interface State : RState {
        var isLoggedIn: Boolean
    }

    private var profile: Profile? = null


    override fun RBuilder.render() {
        div("App-header") {
            //            logo()
            h1 {
                +"Expenses Manager"
            }
        }
        if (state.isLoggedIn) {
            div("New expense form") {
                newExpenseForm()
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

    private fun onFail(error: String, details: String) {
        println("failed to sign in. error: $error. details: $details")
    }

    private fun onSignIn(googleUser: GoogleUser) {
        profile = googleUser.getBasicProfile()
        setState {
            isLoggedIn = true
        }
    }

}

fun RBuilder.app() = child(App::class) {}
