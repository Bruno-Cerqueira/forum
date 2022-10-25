package com.bebe.forum.dto

import com.bebe.forum.model.TopicStatus
import java.time.LocalDateTime

class TopicView (
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val creationDate: LocalDateTime
) {
    override fun toString(): String {
        return "{\"id\":$id,\"title\":\"$title\",\"message\":\"$message\",\"status\":\"$status\",\"creationDate\":\"$creationDate\"}"
    }
}
