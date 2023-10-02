package aci.personal.piggybank.domain.user.usecases

import aci.personal.piggybank.data.repositories.UserRepository
import javax.inject.Inject

class GetLastUserLogedUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getAppUserFromDatabase()
}
