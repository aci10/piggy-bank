package aci.personal.piggybank.domain.user.model

import aci.personal.piggybank.data.database.user.UserEntity
import aci.personal.piggybank.domain.user.model.valueObjects.UserName
import java.util.UUID

data class User(
    val id: UUID,
    val name: UserName,
    val isFake: Boolean
) {
    companion object {
        fun from(
            id: UUID,
            name: String
        ) = User(
            id,
            UserName(name),
            isFake = false
        )

        fun createRandom() =
            User(
                id = UUID.randomUUID(),
                name = UserName("fakeUser"),
                isFake = true
            )
    }
}

fun UserEntity.toDomain() =
    User(
        id = UUID.fromString(id),
        name = UserName(name),
        isFake = false
    )
