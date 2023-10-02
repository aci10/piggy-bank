package aci.personal.piggybank.domain.moneybox.usecases

import aci.personal.piggybank.data.database.moneybox.toDatabase
import aci.personal.piggybank.data.repositories.MoneyboxRepository
import aci.personal.piggybank.domain.moneybox.model.Moneybox
import javax.inject.Inject

class InsertMoneyboxesUseCase @Inject constructor(
    private val repository: MoneyboxRepository
) {
    suspend operator fun invoke(moneyboxes: List<Moneybox>) {
        repository.insertMoneyboxesIntoDatabase(moneyboxes.map { it.toDatabase() })
    }
}
