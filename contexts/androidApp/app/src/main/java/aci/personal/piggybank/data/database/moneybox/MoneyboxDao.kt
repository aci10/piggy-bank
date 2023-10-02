package aci.personal.piggybank.data.database.moneybox

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoneyboxDao {
    @Query(
        value = "SELECT * FROM moneybox_table " +
            "WHERE month=:month AND year=:year " +
            "ORDER BY name DESC"
    )
    suspend fun getAllMoneyboxes(month: Int, year: Int): List<MoneyboxEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoneyboxes(moneyboxes: List<MoneyboxEntity>)

    @Query(value = "DELETE FROM moneybox_table WHERE month=:month AND year=:year")
    suspend fun cleanMoneyboxes(month: Int, year: Int)
}
