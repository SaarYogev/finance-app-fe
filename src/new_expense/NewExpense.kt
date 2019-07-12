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
            +"Amount\t"
            TextField {}
        }
        div {
            +"Type\t"
            TextField {}
        }
        div {
            +"Payment Method\t"
            TextField {}
        }
        div {
            +"Date\t"
            TextField {
                attrs { type = "datetime-local"; defaultValue = "2017-05-24T10:30" }
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
