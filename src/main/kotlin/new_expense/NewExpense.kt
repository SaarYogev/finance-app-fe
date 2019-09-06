package new_expense

import communication.ServerCommunicator
import communication.ServerPaths
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
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
import moment.moment

@ExperimentalCoroutinesApi
class NewExpense : RComponent<NewExpenseProps, RState>(), CoroutineScope {
    private val serverCommunicator: ServerCommunicator = ServerCommunicator()
    private val serverPaths: ServerPaths = ServerPaths()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    override fun RBuilder.render() {
        h1 {
            +"New Expense"
        }
        var amount = ""
        div {
            TextField {
                attrs {
                    label = "Amount"
                    type = "number"
                    autoFocus = true
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
        var date = moment().format().substringBeforeLast('+')
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
    }

    private fun saveExpenseFromForm(amount: String?, type: String?, paymentMethod: String?, date: String?) {
        if (amount != null && type != null && paymentMethod != null && date != null) {
            println(amount)
            println(type)
            println(paymentMethod)
            println(date)
            val expense = Expense(amount.toInt(), type, paymentMethod, Date(date), props.tokenId)
            val expenseJson = JSON.stringify(expense)
            serverCommunicator.postToServer(serverPaths.expenses, expenseJson)
        }
    }

}

interface NewExpenseProps: RProps {
    var tokenId: String
}

@ExperimentalCoroutinesApi
fun RBuilder.newExpenseForm(tokenId: String) = child(NewExpense::class) {
    attrs.tokenId = tokenId
}

fun Event.getInputValue(): String = (this.target as HTMLInputElement).value