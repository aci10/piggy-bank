package aci.personal.piggybank.domain.expense.model.exceptions

sealed class ExpenseException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

data class InvalidExpenseNameException(
    val value: String
) : ExpenseException("The name <$value> is not valid")

data class InvalidExpenseMoneyException(
    val value: Double
) : ExpenseException("The quantity <$value> is not valid")

data class InvalidExpenseTypeException(
    val value: String
) : ExpenseException("The type <$value> is not valid")
