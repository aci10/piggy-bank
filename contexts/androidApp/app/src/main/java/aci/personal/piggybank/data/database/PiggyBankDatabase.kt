package aci.personal.piggybank.data.database

import aci.personal.piggybank.data.database.expense.ExpenseDao
import aci.personal.piggybank.data.database.expense.ExpenseEntity
import aci.personal.piggybank.data.database.moneybox.MoneyboxDao
import aci.personal.piggybank.data.database.moneybox.MoneyboxEntity
import aci.personal.piggybank.data.database.user.UserDao
import aci.personal.piggybank.data.database.user.UserEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class,
        ExpenseEntity::class,
        MoneyboxEntity::class
    ],
    version = 1
)
abstract class PiggyBankDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getExpenseDao(): ExpenseDao
    abstract fun getMoneyboxDao(): MoneyboxDao
}
