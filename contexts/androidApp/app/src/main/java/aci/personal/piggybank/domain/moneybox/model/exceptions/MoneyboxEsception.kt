package aci.personal.piggybank.domain.moneybox.model.exceptions

sealed class MoneyboxException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

data class InvalidMoneyboxNameException(
    val value: String
) : MoneyboxException("The name <$value> is not valid")

data class InvalidMoneyboxFundsException(
    val value: Double
) : MoneyboxException("The value <$value> is not valid fund amount")

data class InvalidMoneyboxSalaryException(
    val value: Double
) : MoneyboxException("The value <$value> is not valid salary amount")

data class InvalidMoneyboxSavedException(
    val value: Double
) : MoneyboxException("The value <$value> is not valid money saved amount")

data class InvalidMoneyboxExpendedException(
    val value: Double
) : MoneyboxException("The value <$value> is not valid money expended amount")

data class InvalidMoneyboxYearException(
    val value: Int
) : MoneyboxException("The value <$value> is not valid year")
