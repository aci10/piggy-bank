package aci.personal.piggybank.data.database.user

import aci.personal.piggybank.domain.user.model.User
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("last_session_date") val lastSessionDate: Long
)

fun User.toDatabase() =
    UserEntity(
        id = id.toString(),
        name = name.value,
        lastSessionDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    )
