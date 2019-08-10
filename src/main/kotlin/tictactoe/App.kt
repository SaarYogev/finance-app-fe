package tictactoe

//import logo.logo
import new_expense.newExpenseForm
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.code
import react.dom.div
import react.dom.h2
import react.dom.p

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div("App-header") {
//            logo()
            h2 {
                +"Welcome to React with Kotlin"
            }
        }
        p("App-intro") {
            +"To get started, edit "
            code { +"package tictactoe\n\nimport logo.logo\nimport new_expense.newExpenseForm\nimport react.RBuilder\nimport react.RComponent\nimport react.RProps\nimport react.RState\nimport react.dom.code\nimport react.dom.div\nimport react.dom.h2\nimport react.dom.p\n\nclass App : RComponent<RProps, RState>() {\n    override fun RBuilder.render() {\n        div(\"App-header\") {\n            logo()\n            h2 {\n                +\"Welcome to React with Kotlin\"\n            }\n        }\n        p(\"App-intro\") {\n            +\"To get started, edit \"\n            code { +\"tictactoe/App.kt\" }\n            +\" and save to reload.\"\n        }\n        div(\"New-expense-form\") {\n            newExpenseForm()\n        }\n    }\n}\n\nfun RBuilder.tictactoe() = child(App::class) {}\n" }
            +" and save to reload."
        }
        div("New-expense-form") {
            newExpenseForm()
        }
    }
}

fun RBuilder.app() = child(App::class) {}
