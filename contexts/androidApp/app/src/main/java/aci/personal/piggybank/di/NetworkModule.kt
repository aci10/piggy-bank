package aci.personal.piggybank.di

import aci.personal.piggybank.data.network.expense.ExpenseApiClient
import aci.personal.piggybank.data.network.moneybox.MoneyboxApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/place/details/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideExpenseApiClient(retrofit: Retrofit): ExpenseApiClient {
        return retrofit.create(ExpenseApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideMoneyboxApiClient(retrofit: Retrofit): MoneyboxApiClient {
        return retrofit.create(MoneyboxApiClient::class.java)
    }
}
