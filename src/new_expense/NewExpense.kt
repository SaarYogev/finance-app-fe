package new_expense

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.input

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
            input {}
        }
        div {
            +"Type\t"
            input {}
        }
        div {
            +"Payment Method\t"
            input {}
        }
//        div {
//            +"Date\t"
//            DateTimePicker { }
//        }
//        div {
//            +"Charge date\t"
//            DateTimePicker { }
//        }
    }
}

fun RBuilder.newExpenseForm(startFrom: Int = 0) = child(NewExpense::class) {
    attrs.startFrom = startFrom
}
