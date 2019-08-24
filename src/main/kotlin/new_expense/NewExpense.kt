package new_expense

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import kotlin.coroutines.CoroutineContext
import kotlin.js.Date

interface TickerProps : RProps {
    var startFrom: Int
}

interface TickerState : RState {
    var secondsElapsed: Int
}

class NewExpense(props: TickerProps) : RComponent<TickerProps, TickerState>(props), CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    override fun TickerState.init(props: TickerProps) {
        secondsElapsed = props.startFrom
    }

    var timerID: Int? = null

    override fun RBuilder.render() {
        h1 {
            +"New Expense"
            pingServer()
        }
        var amount = "0"
        div {
            TextField {
                attrs {
                    label = "Amount"
                    defaultValue = amount
                    onChange = { event -> amount = event.getInputValue() }
                }
            }
        }
        var paymentType = ""
        div {
            TextField {
                attrs {
                    label = "Type"
                    defaultValue = paymentType
                    onChange = { event -> paymentType = event.getInputValue() }
                }
            }
        }
        var paymentMethod = ""
        div {
            TextField {
                attrs {
                    label = "Payment Method"
                    defaultValue = paymentMethod
                    onChange = { event -> paymentMethod = event.getInputValue() }
                }
            }
        }
        var date = "2017-05-24T10:30"
        div {
            TextField {
                attrs {
                    type = "datetime-local"
                    defaultValue = date; label = "Date"
                    onChange = { event -> date = event.getInputValue() }
                }
            }
        }
        Button {
            attrs {
                disabled = false
                onClick = { saveExpenseFromForm(amount, paymentType, paymentMethod, date) }
                color = "primary"
            }
            +"Save"
        }
//        div {
//            +"Charge date\t"
//            DateTimePicker { }
//        }
    }

    private fun pingServer() {
        val corsAnywhere = "https://cors-anywhere.herokuapp.com/"
        val backendUrl = corsAnywhere + "https://finance-app-be.herokuapp.com/expense"
        val client = HttpClient(Js)
        launch {
            try {
                client.get<String> {
                    url(backendUrl)
                }
            } catch (e: Exception) {
                println("oops, exception: $e")
            }
        }
    }

    private fun saveExpenseFromForm(amount: String?, type: String?, paymentMethod: String?, date: String?) {
        if (amount != null && type != null && paymentMethod != null && date != null) {
            println(amount)
            println(type)
            println(paymentMethod)
            println(date)
            val expense = Expense(amount.toInt(), type, paymentMethod, Date(date))
            val expenseJson = JSON.stringify(expense)
            println(expense)
            postToServer(expenseJson)
        }

    }

    private fun postToServer(expenseJson: String) {
        val corsAnywhere = "https://cors-anywhere.herokuapp.com/"
        val backendUrl = corsAnywhere + "https://finance-app-be.herokuapp.com/expenseString"
        val client = HttpClient(Js)
        launch {
            try {
                client.post<String> {
                    url(backendUrl)
                    body = expenseJson
                }
            } catch (e: Exception) {
                println("oops, exception: $e")
            }
        }
    }
}

fun RBuilder.newExpenseForm(startFrom: Int = 0) = child(NewExpense::class) {
    attrs.startFrom = startFrom
}

fun Event.getInputValue(): String = (this.target as HTMLInputElement).value

fun <T> jsonAs(): T = js("({})") as T