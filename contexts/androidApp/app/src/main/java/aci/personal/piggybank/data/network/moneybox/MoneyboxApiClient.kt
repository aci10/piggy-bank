package aci.personal.piggybank.data.network.moneybox

import aci.personal.piggybank.data.model.moneybox.MoneyboxModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val DOMAIN = "moneybox"

interface MoneyboxApiClient {
    @GET("/$DOMAIN/")
    suspend fun getAllMoneyboxes(
        @Query("user_id") userId: String,
        @Query("month") month: Int,
        @Query("year") year: Int
    ): Response<List<MoneyboxModel>>

    @FormUrlEncoded
    @POST("/$DOMAIN/")
    suspend fun insertMoneyboxes(@Body moneyboxes: List<MoneyboxModel>)
}
