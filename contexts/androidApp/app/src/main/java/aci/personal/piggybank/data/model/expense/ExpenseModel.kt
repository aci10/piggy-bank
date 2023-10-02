package aci.personal.piggybank.data.model.expense

import aci.personal.piggybank.domain.expense.model.Expense
import com.google.gson.annotations.SerializedName
import java.time.ZoneOffset
import java.util.UUID

data class ExpenseModel(
    @SerializedName("id") val id: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("date") val date: Long,
    @SerializedName("money") val money: Double,
    @SerializedName("programmed") val programmed: Boolean,
    @SerializedName("type") val type: String
)

fun Expense.toModel(userId: UUID) =
    ExpenseModel(
        id.toString(),
        userId.toString(),
        name.value,
        date.toEpochSecond(ZoneOffset.UTC),
        money.value,
        programmed,
        type.name
    )
