@file:JsModule("@material-ui/core/Button")

package new_expense

import react.RClass
import react.RProps

@JsName("default")
external val Button: RClass<ButtonProps>

external interface ButtonProps : RProps {
    var disabled: Boolean
    var onClick: () -> Unit
    var color: String
}
