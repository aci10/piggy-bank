package aci.personal.piggybank.data.database.expense

import aci.personal.piggybank.domain.expense.model.Expense
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZoneOffset

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "money") val money: Double,
    @ColumnInfo(name = "programmed") val programmed: Boolean,
    @ColumnInfo(name = "type") val type: String
)

fun Expense.toDatabase() =
    ExpenseEntity(
        id.toString(),
        name.value,
        date.toEpochSecond(ZoneOffset.UTC),
        money.value,
        programmed,
        type.name
    )
