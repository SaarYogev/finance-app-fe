@file:JsModule("@material-ui/core/TextField")

package new_expense

import org.w3c.dom.events.Event
import react.RClass
import react.RProps

@JsName("default")
external val TextField: RClass<TextFieldProps>

external interface TextFieldProps : RProps {
    var type: String?
    var label: String?
    var defaultValue: String?
    var value: String?
    var autoFocus: Boolean
    var onChange: (Event) -> Unit
}
