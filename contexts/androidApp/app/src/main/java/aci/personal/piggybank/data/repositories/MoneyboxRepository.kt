package aci.personal.piggybank.data.repositories

import aci.personal.piggybank.data.database.moneybox.MoneyboxDao
import aci.personal.piggybank.data.database.moneybox.MoneyboxEntity
import aci.personal.piggybank.data.model.moneybox.toModel
import aci.personal.piggybank.data.network.moneybox.MoneyboxService
import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.domain.moneybox.model.toDomain
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxYear
import java.time.Month
import java.util.UUID
import javax.inject.Inject

class MoneyboxRepository @Inject constructor(
    private val api: MoneyboxService,
    private val dao: MoneyboxDao
) {
    // GET
    suspend fun getAllMoneyboxesFromApi(
        month: Month,
        year: MoneyboxYear,
        userId: UUID
    ): List<Moneybox> {
        val response = api.getAllMoneyboxes(
            month = month.value,
            year = year.value,
            userId = userId.toString()
        )
        return response.map {
            it.toDomain()
        }
    }

    suspend fun getAllMoneyboxesFromDatabase(month: Month, year: MoneyboxYear): List<Moneybox> {
        val response = dao.getAllMoneyboxes(month = month.value, year = year.value)
        return response.map {
            it.toDomain()
        }
    }

    // PUT
    suspend fun insertMoneyboxesIntoApi(moneyboxes: List<Moneybox>, userId: UUID) {
        api.insertMoneyboxes(moneyboxes.map { it.toModel(userId) })
    }

    suspend fun insertMoneyboxesIntoDatabase(moneyboxes: List<MoneyboxEntity>) {
        dao.insertMoneyboxes(moneyboxes)
    }

    // DELETE
    suspend fun cleanMoneyboxesOfDatabase(month: Month, year: MoneyboxYear) {
        dao.cleanMoneyboxes(month = month.value, year = year.value)
    }
}
