package new_expense

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
        div {
            TextField {
                attrs { label = "Amount" }
            }
        }
        div {
            TextField {
                attrs { label = "Type" }
            }
        }
        div {
            TextField {
                attrs { label = "Payment Method" }
            }
        }
        div {
            TextField {
                attrs { type = "datetime-local"; defaultValue = "2017-05-24T10:30"; label = "Date" }
            }
        }
        Button {
            attrs { disabled = false; onClick = {}; color = "primary" }
            +"You clicked 0 times"
        }
//        div {
//            +"Charge date\t"
//            DateTimePicker { }
//        }
    }
}

fun RBuilder.newExpenseForm(startFrom: Int = 0) = child(NewExpense::class) {
    attrs.startFrom = startFrom
}
