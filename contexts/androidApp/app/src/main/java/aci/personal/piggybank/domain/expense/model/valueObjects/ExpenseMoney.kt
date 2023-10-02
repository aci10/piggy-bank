package aci.personal.piggybank.domain.expense.model.valueObjects

import aci.personal.piggybank.domain.expense.model.exceptions.InvalidExpenseMoneyException

data class ExpenseMoney(val value: Double) {
    init {
        validate()
    }

    private fun validate() {
        if (value < 0.0) {
            throw InvalidExpenseMoneyException(value)
        }
    }
}
