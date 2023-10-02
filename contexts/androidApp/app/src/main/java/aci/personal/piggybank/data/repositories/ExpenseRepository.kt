package aci.personal.piggybank.data.repositories

import aci.personal.piggybank.data.database.expense.ExpenseDao
import aci.personal.piggybank.data.database.expense.ExpenseEntity
import aci.personal.piggybank.data.model.expense.ExpenseModel
import aci.personal.piggybank.data.network.expense.ExpenseService
import aci.personal.piggybank.domain.expense.model.Expense
import aci.personal.piggybank.domain.expense.model.toDomain
import javax.inject.Inject

class ExpenseRepository @Inject constructor(
    private val api: ExpenseService,
    private val expenseDao: ExpenseDao
) {
    suspend fun getAllExpensesFromApi(): List<Expense> {
        val response = api.getExpenses()
        return response.map {
            it.toDomain()
        }
    }

    suspend fun getAllExpensesFromDatabase(): List<Expense> {
        val response = expenseDao.getAllExpenses()
        return response.map {
            it.toDomain()
        }
    }

    suspend fun insertExpensesInApi(expenses: List<ExpenseModel>) {
        api.insertExpenses(expenses)
    }

    suspend fun insertExpensesInDatabase(expenses: List<ExpenseEntity>) =
        expenseDao.insertAll(expenses)

    suspend fun cleanExpensesOfDatabase() =
        expenseDao.deleteAllExpenses()
}
