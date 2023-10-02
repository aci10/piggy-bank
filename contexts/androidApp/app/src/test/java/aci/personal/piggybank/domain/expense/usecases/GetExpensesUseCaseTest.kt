package aci.personal.piggybank.domain.expense.usecases

import aci.personal.piggybank.data.repositories.ExpenseRepository
import aci.personal.piggybank.domain.expense.model.Expense
import aci.personal.piggybank.domain.expense.model.ExpenseMother
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetExpensesUseCaseTest {
    @RelaxedMockK
    private lateinit var expenseRepository: ExpenseRepository

    private lateinit var getExpensesUseCase: GetExpensesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getExpensesUseCase = GetExpensesUseCase(expenseRepository)
    }

    // -------------------------------------------------------------------------------------
    @Test
    fun `should return the expenses from the local database when the api returns nothing`() =
        runBlocking {
            `given an empty expenses list from the api`()
            `when we request the list of the last expenses posted`()
            `then we should obtain the expenses saved on the local database`()
        }

    private fun `given an empty expenses list from the api`() {
        coEvery { expenseRepository.getAllExpensesFromApi() } returns emptyList()
    }

    private suspend fun `when we request the list of the last expenses posted`() =
        getExpensesUseCase()

    private fun `then we should obtain the expenses saved on the local database`() {
        coVerify(exactly = 1) { expenseRepository.getAllExpensesFromDatabase() }
    }

    // -------------------------------------------------------------------------------------
    @Test
    fun `should return the values obtained from the api when it returns something`() =
        runBlocking {
            val expenses = `given a list of expenses from the api`()
            val response = `when we request the list of the last expenses posted`()
            `then we should replace the expenses of the database`(expenses, response)
        }

    private fun `given a list of expenses from the api`(): List<Expense> {
        val expenses = ExpenseMother.createRandomList(0)

        coEvery { expenseRepository.getAllExpensesFromApi() } returns expenses

        return expenses
    }

    private suspend fun `then we should replace the expenses of the database`(
        expenses: List<Expense>,
        response: List<Expense>
    ) {
        coVerify(exactly = 1) { expenseRepository.cleanExpensesOfDatabase() }
        coVerify(exactly = 1) { expenseRepository.insertExpensesInDatabase(any()) }
        coVerify(exactly = 0) { expenseRepository.getAllExpensesFromDatabase() }

        assert(response == expenses)
    }
}
