package aci.personal.piggybank.domain.moneybox.model.valueObjects

import aci.personal.piggybank.domain.moneybox.model.exceptions.InvalidMoneyboxFundsException

data class MoneyboxFunds(val value: Double) {
    init {
        validate()
    }

    private fun validate() {
        if (value < 0.0) {
            throw InvalidMoneyboxFundsException(value)
        }
    }
}
