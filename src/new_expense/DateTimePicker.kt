@file:JsModule("@material-ui/core/Button")

package new_expense

import react.RClass
import react.RProps
import kotlin.js.Date

@JsName("default")
external val DateTimePicker: RClass<DateTimePickerProps>

external interface DateTimePickerProps : RProps {
    @JsName("value")
    var valu: Date

    var onChange: () -> Unit
}

