package aci.personal.piggybank.data.database.moneybox

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moneybox_table")
data class MoneyboxEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "month") val month: Int,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "funds") val funds: Double,
    @ColumnInfo(name = "salary") val salary: Double,
    @ColumnInfo(name = "saved") val saved: Double,
    @ColumnInfo(name = "expended") val spent: Double
)

fun Moneybox.toDatabase() = MoneyboxEntity(
    id = id.toString(),
    name = name.value,
    month = month.value,
    year = year.value,
    funds = funds.value,
    salary = salary.value,
    saved = saved.value,
    spent = spent.value
)
