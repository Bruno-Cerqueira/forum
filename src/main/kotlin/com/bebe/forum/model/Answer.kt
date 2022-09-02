package com.bebe.forum.model

import java.time.LocalDateTime

data class Answer(
    val id: Long?,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic,
    val solution: Boolean,
)
