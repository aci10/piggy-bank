package aci.personal.piggybank.domain.utils

import kotlin.random.Random

class StringFactory {
    companion object {
        fun random(length: Int): String {
            val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            return (1..length)
                .map { charset[Random.nextInt(0, charset.length)] }
                .joinToString("")
        }
    }
}
