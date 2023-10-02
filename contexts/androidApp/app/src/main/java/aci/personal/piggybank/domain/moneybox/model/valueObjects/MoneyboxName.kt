package aci.personal.piggybank.domain.moneybox.model.valueObjects

import aci.personal.piggybank.domain.moneybox.model.exceptions.InvalidMoneyboxNameException

data class MoneyboxName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.length > 50) {
            throw InvalidMoneyboxNameException(value)
        }
    }
}
