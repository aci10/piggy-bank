package aci.personal.piggybank.domain.moneybox.model.valueObjects

import aci.personal.piggybank.domain.moneybox.model.exceptions.InvalidMoneyboxSalaryException

data class MoneyboxSalary(val value: Double) {
    init {
        validate()
    }

    private fun validate() {
        if (value < 0.0) {
            throw InvalidMoneyboxSalaryException(value)
        }
    }
}
