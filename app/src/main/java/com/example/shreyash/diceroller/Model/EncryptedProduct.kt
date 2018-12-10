package com.example.shreyash.diceroller.Model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index


@Entity
data class EncryptedProduct(@Id var id: Long = 0,
                   @Index var Name: String = "",
                   @Index var Age: String = "",
                   @Index var Email: String = ""
)