package aci.personal.piggybank.domain.expense.model.valueObjects

class ExpenseType(
    val name: String,
    val title: String,
    val icon: String
) {
    companion object {
        fun from(name: String): ExpenseType =
            when (name) {
                ExpenseTypeDefault.Leisure.name -> ExpenseType(
                    ExpenseTypeDefault.Leisure.name,
                    ExpenseTypeDefault.Leisure.title,
                    ExpenseTypeDefault.Leisure.icon
                )
                ExpenseTypeDefault.Taxes.name -> ExpenseType(
                    ExpenseTypeDefault.Taxes.name,
                    ExpenseTypeDefault.Taxes.title,
                    ExpenseTypeDefault.Taxes.icon
                )
                ExpenseTypeDefault.Health.name -> ExpenseType(
                    ExpenseTypeDefault.Health.name,
                    ExpenseTypeDefault.Health.title,
                    ExpenseTypeDefault.Health.icon
                )
                ExpenseTypeDefault.Payroll.name -> ExpenseType(
                    ExpenseTypeDefault.Payroll.name,
                    ExpenseTypeDefault.Payroll.title,
                    ExpenseTypeDefault.Payroll.icon
                )
                ExpenseTypeDefault.Food.name -> ExpenseType(
                    ExpenseTypeDefault.Food.name,
                    ExpenseTypeDefault.Food.title,
                    ExpenseTypeDefault.Food.icon
                )
                ExpenseTypeDefault.Other.name -> ExpenseType(
                    ExpenseTypeDefault.Other.name,
                    ExpenseTypeDefault.Other.title,
                    ExpenseTypeDefault.Other.icon
                )
                else -> ExpenseType(
                    ExpenseTypeDefault.Other.name,
                    ExpenseTypeDefault.Other.title,
                    ExpenseTypeDefault.Other.icon
                )
            }
    }
}

sealed class ExpenseTypeDefault(
    val name: String,
    val title: String,
    val icon: String
) {
    object Leisure : ExpenseTypeDefault("leisure", "Leisure", "")
    object Taxes : ExpenseTypeDefault("taxes", "Taxes", "")
    object Health : ExpenseTypeDefault("health", "Health", "")
    object Payroll : ExpenseTypeDefault("payroll", "Payroll", "")
    object Food : ExpenseTypeDefault("food", "Food", "")
    object Other : ExpenseTypeDefault("other", "Other", "")
}
