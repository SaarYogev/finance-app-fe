package tictactoe

//import logo.logo
import new_expense.newExpenseForm
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("App-header") {
//            logo()
            h1 {
                +"Expenses Manager"
            }
        }
        div("New-expense-form") {
            newExpenseForm()
        }
    }
}

fun RBuilder.app() = child(App::class) {}
