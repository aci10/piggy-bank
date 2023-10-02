package aci.personal.piggybank.data.database.expense

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table ORDER BY name DESC")
    suspend fun getAllExpenses(): List<ExpenseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(expenses: List<ExpenseEntity>)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()
}
