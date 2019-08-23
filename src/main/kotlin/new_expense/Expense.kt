package new_expense

import kotlinx.serialization.Serializable
import kotlin.js.Date

@Serializable
data class Expense(val amount:Int, val type:String, val paymentMethod:String, val paymentDate: Date)