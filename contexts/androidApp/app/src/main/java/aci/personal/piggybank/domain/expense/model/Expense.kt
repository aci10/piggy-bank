package aci.personal.piggybank.domain.expense.model

import aci.personal.piggybank.data.database.expense.ExpenseEntity
import aci.personal.piggybank.data.model.expense.ExpenseModel
import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseMoney
import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseName
import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseType
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class Expense(
    val id: UUID,
    val name: ExpenseName,
    val date: LocalDateTime,
    val money: ExpenseMoney,
    val programmed: Boolean,
    val type: ExpenseType
) {
    companion object {
        fun from(
            id: UUID,
            name: String,
            date: Long,
            money: Double,
            programmed: Boolean,
            type: String
        ): Expense {
            return Expense(
                id,
                ExpenseName(name),
                LocalDateTime.ofEpochSecond(
                    date,
                    0,
                    ZoneOffset.UTC
                ),
                ExpenseMoney(money),
                programmed,
                ExpenseType.from(type)
            )
        }
    }
}

fun ExpenseModel.toDomain() =
    Expense.from(
        id = UUID.fromString(id),
        name,
        date,
        money,
        programmed,
        type
    )

fun ExpenseEntity.toDomain() =
    Expense.from(
        id = UUID.fromString(id),
        name,
        date,
        money,
        programmed,
        type
    )
