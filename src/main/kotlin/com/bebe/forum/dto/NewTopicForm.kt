package com.bebe.forum.dto

data class NewTopicForm(
    var id: Long?,
    val title: String,
    val message: String,
    val idCourse: Long,
    val idUser: Long
)
