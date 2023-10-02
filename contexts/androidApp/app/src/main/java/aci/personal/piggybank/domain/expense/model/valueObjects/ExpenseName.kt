package aci.personal.piggybank.domain.expense.model.valueObjects

import aci.personal.piggybank.domain.expense.model.exceptions.InvalidExpenseNameException

data class ExpenseName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.length > 50) {
            throw InvalidExpenseNameException(value)
        }
    }
}
