package aci.personal.piggybank.domain.expense.usecases

import aci.personal.piggybank.data.database.expense.toDatabase
import aci.personal.piggybank.data.model.expense.toModel
import aci.personal.piggybank.data.repositories.ExpenseRepository
import aci.personal.piggybank.domain.expense.model.Expense
import aci.personal.piggybank.domain.user.model.User
import javax.inject.Inject

class InsertExpensesUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend operator fun invoke(expenses: List<Expense>, user: User) {
        repository.insertExpensesInApi(expenses.map { it.toModel(user.id) })
        repository.cleanExpensesOfDatabase()
        repository.insertExpensesInDatabase(expenses.map { it.toDatabase() })
    }
}
