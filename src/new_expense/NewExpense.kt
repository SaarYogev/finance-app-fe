package new_expense

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1

interface TickerProps : RProps {
    var startFrom: Int
}

interface TickerState : RState {
    var secondsElapsed: Int
}

class NewExpense(props: TickerProps) : RComponent<TickerProps, TickerState>(props) {
    override fun TickerState.init(props: TickerProps) {
        secondsElapsed = props.startFrom
    }

    var timerID: Int? = null

    override fun RBuilder.render() {
        h1 {
            +"New Expense"
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

    private fun saveExpenseFromForm(amount: String?, type: String?, paymentMethod: String?, date: String?) {
        if (amount == null || type == null || paymentMethod == null || date == null) {
            println("Fail")
        }
        println(amount)
        println(type)
        println(paymentMethod)
        println(date)
    }
}

fun RBuilder.newExpenseForm(startFrom: Int = 0) = child(NewExpense::class) {
    attrs.startFrom = startFrom
}

fun Event.getInputValue(): String = (this.target as HTMLInputElement).value