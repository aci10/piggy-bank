package aci.personal.piggybank.domain.user.model.valueObjects

import aci.personal.piggybank.domain.user.model.exceptions.InvalidUserNameException

data class UserName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.length > 50) {
            throw InvalidUserNameException(value)
        }
    }
}
