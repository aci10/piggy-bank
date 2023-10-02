package aci.personal.piggybank.domain.expense.usecases

import aci.personal.piggybank.data.database.expense.toDatabase
import aci.personal.piggybank.data.repositories.ExpenseRepository
import aci.personal.piggybank.domain.expense.model.Expense
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(private val repository: ExpenseRepository) {
    suspend operator fun invoke(): List<Expense> {
        val expenses = repository.getAllExpensesFromApi()

        return if (expenses.isNotEmpty()) {
            repository.cleanExpensesOfDatabase()
            repository.insertExpensesInDatabase(expenses.map { it.toDatabase() })
            expenses
        } else {
            repository.getAllExpensesFromDatabase()
        }
    }
}
