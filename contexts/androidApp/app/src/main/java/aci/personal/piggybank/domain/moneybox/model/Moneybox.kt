package aci.personal.piggybank.domain.moneybox.model

import aci.personal.piggybank.data.database.moneybox.MoneyboxEntity
import aci.personal.piggybank.data.model.moneybox.MoneyboxModel
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxFunds
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxName
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxSalary
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxSaved
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxSpent
import aci.personal.piggybank.domain.moneybox.model.valueObjects.MoneyboxYear
import java.time.LocalDateTime
import java.time.Month
import java.util.UUID

data class Moneybox(
    val id: UUID,
    val name: MoneyboxName,
    val month: Month,
    val year: MoneyboxYear,
    val funds: MoneyboxFunds,
    val salary: MoneyboxSalary,
    val saved: MoneyboxSaved,
    val spent: MoneyboxSpent
) {
    companion object {
        fun from(
            id: String,
            name: String,
            month: Int,
            year: Int,
            funds: Double,
            salary: Double,
            saved: Double,
            spent: Double
        ) = Moneybox(
            id = UUID.fromString(id),
            name = MoneyboxName(name),
            month = Month.of(month),
            year = MoneyboxYear(year),
            funds = MoneyboxFunds(funds),
            salary = MoneyboxSalary(salary),
            saved = MoneyboxSaved(saved),
            spent = MoneyboxSpent(spent)
        )

        fun default(): Moneybox {
            val now = LocalDateTime.now()

            return Moneybox(
                id = UUID.randomUUID(),
                name = MoneyboxName("Your Piggy Bank"),
                month = now.month,
                year = MoneyboxYear(now.year),
                funds = MoneyboxFunds(0.0),
                salary = MoneyboxSalary(50.0),
                saved = MoneyboxSaved(0.0),
                spent = MoneyboxSpent(0.0)
            )
        }
    }
}

fun MoneyboxEntity.toDomain() =
    Moneybox.from(
        id = id,
        name = name,
        month = month,
        year = year,
        funds = funds,
        salary = salary,
        saved = saved,
        spent = spent
    )

fun MoneyboxModel.toDomain() =
    Moneybox.from(
        id = id,
        name = name,
        month = month,
        year = year,
        funds = funds,
        salary = salary,
        saved = saved,
        spent = spent
    )
