package aci.personal.piggybank.domain.moneybox.model.valueObjects

import aci.personal.piggybank.domain.moneybox.model.exceptions.InvalidMoneyboxSavedException

data class MoneyboxSaved(val value: Double) {
    init {
        validate()
    }

    private fun validate() {
        if (value < 0.0) {
            throw InvalidMoneyboxSavedException(value)
        }
    }
}
