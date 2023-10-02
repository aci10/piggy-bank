package aci.personal.piggybank.domain.moneybox.usecases

import aci.personal.piggybank.data.database.moneybox.toDatabase
import aci.personal.piggybank.data.repositories.MoneyboxRepository
import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxYear
import java.time.Month
import java.util.UUID
import javax.inject.Inject

class GetMoneyboxesUseCase @Inject constructor(
    private val repository: MoneyboxRepository
) {
    suspend operator fun invoke(month: Month, year: MoneyboxYear, userId: UUID): List<Moneybox> {
        val moneyboxes = repository.getAllMoneyboxesFromApi(
            month = month,
            year = year,
            userId = userId
        )

        return if (moneyboxes.isNotEmpty()) {
            repository.cleanMoneyboxesOfDatabase(month = month, year = year)
            repository.insertMoneyboxesIntoDatabase(moneyboxes.map { it.toDatabase() })
            moneyboxes
        } else {
            repository.getAllMoneyboxesFromDatabase(month = month, year = year)
        }
    }
}
