package aci.personal.piggybank.domain.moneybox.model.valueObjects

import aci.personal.piggybank.domain.moneybox.model.exceptions.InvalidMoneyboxYearException
import java.time.LocalDateTime

data class MoneyboxYear(val value: Int) {
    init {
        validate()
    }

    fun validate() {
        if (value < 2023 || value > LocalDateTime.now().year) {
            throw InvalidMoneyboxYearException(value)
        }
    }
}
