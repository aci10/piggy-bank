package aci.personal.piggybank.di

import aci.personal.piggybank.data.database.PiggyBankDatabase
import aci.personal.piggybank.domain.user.model.User
import aci.personal.piggybank.domain.user.usecases.GetLastUserLogedUseCase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.runBlocking

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PIGGY_BANK_DATABASE_NAME = "piggy_bank_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PiggyBankDatabase::class.java,
            PIGGY_BANK_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideExpenseDao(db: PiggyBankDatabase) = db.getExpenseDao()

    @Singleton
    @Provides
    fun provideUserDao(db: PiggyBankDatabase) = db.getUserDao()

    @Singleton
    @Provides
    @Named("app_user")
    fun provideAppUser(getLastUserLogedUseCase: GetLastUserLogedUseCase): User =
        runBlocking { getLastUserLogedUseCase() }

    @Singleton
    @Provides
    fun provideMoneyboxDao(db: PiggyBankDatabase) = db.getMoneyboxDao()
}
