package aci.personal.piggybank.data.network.expense

import aci.personal.piggybank.data.model.expense.ExpenseModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ExpenseService @Inject constructor(private val api: ExpenseApiClient) {
    suspend fun getExpenses(): List<ExpenseModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<ExpenseModel>> = api.getAllExpenses()

            response.body() ?: emptyList()
            emptyList()
        }
    }

    suspend fun insertExpenses(expenses: List<ExpenseModel>) {
        withContext(Dispatchers.IO) {
            api.insertExpenses(expenses)
        }
    }
}
