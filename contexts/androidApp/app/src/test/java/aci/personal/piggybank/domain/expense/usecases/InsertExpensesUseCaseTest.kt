package aci.personal.piggybank.domain.expense.usecases

import aci.personal.piggybank.data.database.expense.toDatabase
import aci.personal.piggybank.data.model.expense.toModel
import aci.personal.piggybank.data.repositories.ExpenseRepository
import aci.personal.piggybank.domain.expense.model.Expense
import aci.personal.piggybank.domain.expense.model.ExpenseMother
import aci.personal.piggybank.domain.expense.model.exceptions.InvalidExpenseMoneyException
import aci.personal.piggybank.domain.expense.model.exceptions.InvalidExpenseNameException
import aci.personal.piggybank.domain.user.model.User
import aci.personal.piggybank.domain.user.model.UserMother
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class InsertExpensesUseCaseTest {
    @RelaxedMockK
    private lateinit var expenseRepository: ExpenseRepository

    private lateinit var insertExpensesUseCase: InsertExpensesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        insertExpensesUseCase = InsertExpensesUseCase(expenseRepository)
    }

    @Test
    fun `should insert a new expense into the api and the local database`() = runBlocking {
        val expense = `given a new expense`()
        // and the user creator
        val user = UserMother.createRandom()

        `when we request its creation`(expense, user)
        `then it should be inserted into the api and the local database`(expense, user)
    }

    private fun `given a new expense`(): Expense = ExpenseMother.createRandom()

    private suspend fun `when we request its creation`(expense: Expense, user: User) =
        insertExpensesUseCase(listOf(expense), user)

    private fun `then it should be inserted into the api and the local database`(
        expense: Expense,
        user: User
    ) {
        coVerify(exactly = 1) {
            expenseRepository.insertExpensesInApi(
                listOf(expense.toModel(userId = user.id))
            )
        }
        coVerify(exactly = 1) {
            expenseRepository.insertExpensesInDatabase(
                listOf(expense.toDatabase())
            )
        }
    }

    @Test
    fun `should throw an error for bringing an incorrect expense name`() {
        assertThrows(InvalidExpenseNameException::class.java) {
            ExpenseMother.createRandomWithInvalidName()
        }
    }

    @Test
    fun `should throw an error for bringing an incorrect amount of money`() {
        assertThrows(InvalidExpenseMoneyException::class.java) {
            ExpenseMother.createRandomWithInvalidMoneyValue()
        }
    }
}
