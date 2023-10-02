package aci.personal.piggybank.domain.moneybox.model.valueObjects

import aci.personal.piggybank.domain.moneybox.model.exceptions.InvalidMoneyboxExpendedException

data class MoneyboxSpent(val value: Double) {
    init {
        validate()
    }

    private fun validate() {
        if (value < 0.0) {
            throw InvalidMoneyboxExpendedException(value)
        }
    }
}
