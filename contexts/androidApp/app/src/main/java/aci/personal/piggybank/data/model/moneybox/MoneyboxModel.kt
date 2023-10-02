package aci.personal.piggybank.data.model.moneybox

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class MoneyboxModel(
    @SerializedName("id") val id: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("month") val month: Int,
    @SerializedName("year") val year: Int,
    @SerializedName("funds") val funds: Double,
    @SerializedName("salary") val salary: Double,
    @SerializedName("saved") val saved: Double,
    @SerializedName("spent") val spent: Double
)

fun Moneybox.toModel(userId: UUID) = MoneyboxModel(
    id = id.toString(),
    userId = userId.toString(),
    name = name.value,
    month = month.value,
    year = year.value,
    funds = funds.value,
    salary = salary.value,
    saved = saved.value,
    spent = spent.value
)
