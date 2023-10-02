package aci.personal.piggybank.data.network.expense

import aci.personal.piggybank.data.model.expense.ExpenseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val DOMAIN = "expenses"

interface ExpenseApiClient {
    @GET("/$DOMAIN/")
    suspend fun getAllExpenses(): Response<List<ExpenseModel>>

    @POST("/$DOMAIN/")
    suspend fun insertExpenses(@Body expenses: List<ExpenseModel>): Response<String>
}
