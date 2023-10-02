package aci.personal.piggybank.data.repositories

import aci.personal.piggybank.data.database.user.UserDao
import aci.personal.piggybank.domain.user.model.User
import aci.personal.piggybank.domain.user.model.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    suspend fun getAppUserFromDatabase(): User {
        val users = userDao.getLastUser()
        return if (users.isNotEmpty()) {
            users[0].toDomain()
        } else {
            User.createRandom()
        }
    }
}
