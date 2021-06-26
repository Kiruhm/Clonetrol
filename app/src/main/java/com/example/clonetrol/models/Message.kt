package com.example.clonetrol.models

import com.example.clonetrol.models.enums.OwnerEnum

data class Message(val owner: OwnerEnum, var message: String)
