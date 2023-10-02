package aci.personal.piggybank.data.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY lastSessionDate DESC LIMIT 1")
    suspend fun getLastUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<UserEntity>)
}
