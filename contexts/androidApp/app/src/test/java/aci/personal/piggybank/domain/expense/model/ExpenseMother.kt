package aci.personal.piggybank.domain.expense.model

import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseMoneyMother
import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseNameMother
import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseType
import aci.personal.piggybank.domain.expense.model.valueObjects.ExpenseTypeDefault
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class ExpenseMother {
    companion object {
        fun createRandomList(length: Int): List<Expense> {
            val size = if (length > 0) length else Random.nextInt(1, 10)
            return buildList(size) {
                for (i in 0..size) {
                    add(createRandom())
                }
            }
        }

        fun createRandom(): Expense = Expense(
            UUID.randomUUID(),
            ExpenseNameMother.createRandom(),
            LocalDateTime.now(),
            ExpenseMoneyMother.createRandom(),
            false,
            ExpenseType.from(ExpenseTypeDefault.Other.name)
        )

        fun createRandomWithInvalidName(): Expense = Expense(
            UUID.randomUUID(),
            ExpenseNameMother.randomInvalidLongError(),
            LocalDateTime.now(),
            ExpenseMoneyMother.createRandom(),
            false,
            ExpenseType.from(ExpenseTypeDefault.Other.name)
        )

        fun createRandomWithInvalidMoneyValue(): Expense = Expense(
            UUID.randomUUID(),
            ExpenseNameMother.randomInvalidLongError(),
            LocalDateTime.now(),
            ExpenseMoneyMother.createInvalidRandom(),
            false,
            ExpenseType.from(ExpenseTypeDefault.Other.name)
        )
    }
}
