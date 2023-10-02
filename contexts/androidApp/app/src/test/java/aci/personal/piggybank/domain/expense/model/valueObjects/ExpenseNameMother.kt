package aci.personal.piggybank.domain.expense.model.valueObjects

import aci.personal.piggybank.domain.utils.StringFactory
import kotlin.random.Random

class ExpenseNameMother {
    companion object {
        fun createRandom(): ExpenseName = ExpenseName(
            StringFactory.random(Random.nextInt(1, 50))
        )

        fun randomInvalidLongError() = ExpenseName(
            StringFactory.random(Random.nextInt(51, 100))
        )

        fun invalidEmptyError() = ExpenseName("")
    }
}
