package aci.personal.piggybank.data.network.moneybox

import aci.personal.piggybank.data.model.moneybox.MoneyboxModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MoneyboxService @Inject constructor(private val api: MoneyboxApiClient) {
    suspend fun getAllMoneyboxes(
        month: Int,
        year: Int,
        userId: String
    ): List<MoneyboxModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<MoneyboxModel>> =
                api.getAllMoneyboxes(
                    userId = userId,
                    month = month,
                    year = year
                )

            response.body() ?: emptyList()
            emptyList()
        }
    }

    suspend fun insertMoneyboxes(moneyboxes: List<MoneyboxModel>) {
        withContext(Dispatchers.IO) {
            api.insertMoneyboxes(moneyboxes)
        }
    }
}
