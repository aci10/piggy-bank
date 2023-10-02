package aci.personal.piggybank.domain.expense.model.valueObjects

import kotlin.random.Random

class ExpenseMoneyMother {
    companion object {
        fun createRandom() = ExpenseMoney(Random.nextDouble(0.1, 100000.0))

        fun createInvalidRandom() = ExpenseMoney(Random.nextDouble(-100000.0, 0.0))
    }
}
