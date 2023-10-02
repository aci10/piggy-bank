package aci.personal.piggybank.ui.composables.screens.moneboxes.viewmodel

import aci.personal.piggybank.domain.moneybox.model.Moneybox
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxYear
import aci.personal.piggybank.domain.moneybox.usecases.GetMoneyboxesUseCase
import aci.personal.piggybank.domain.moneybox.usecases.InsertMoneyboxesUseCase
import aci.personal.piggybank.domain.user.model.User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MoneyboxViewModel @Inject constructor(
    @Named("app_user") private val appUser: User,
    private val getMoneyboxesUseCase: GetMoneyboxesUseCase,
    private val insertMoneyboxesUseCase: InsertMoneyboxesUseCase
) : ViewModel() {
    private val _moneyboxes = MutableStateFlow<List<Moneybox>>(listOf())
    val moneyboxes = _moneyboxes.asStateFlow()

    init {
        viewModelScope.launch {
            val now = LocalDateTime.now()
            val month = now.month
            val year = MoneyboxYear(now.year)
            val result = getMoneyboxesUseCase(
                month = month,
                year = year,
                userId = appUser.id
            )

            if (result.isNotEmpty()) {
                _moneyboxes.value = result
            } else {
                val moneybox = Moneybox.default()

                insertMoneyboxesUseCase(moneyboxes = listOf(moneybox))
                _moneyboxes.value = listOf(moneybox)
            }
        }
    }
}
