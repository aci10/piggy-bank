package aci.personal.piggybank.domain.user.model.exceptions

sealed class UserException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

data class InvalidUserNameException(
    val value: String
) : UserException("The user name <$value> is not valid")
