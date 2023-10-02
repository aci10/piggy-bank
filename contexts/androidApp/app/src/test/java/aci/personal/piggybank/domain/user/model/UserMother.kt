package aci.personal.piggybank.domain.user.model

import aci.personal.piggybank.domain.utils.StringFactory
import java.util.*
import kotlin.random.Random

class UserMother {
    companion object {
        fun createRandom() =
            User.from(
                id = UUID.randomUUID(),
                name = StringFactory.random(Random.nextInt(1, 50))
            )
    }
}
