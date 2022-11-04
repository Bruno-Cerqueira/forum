package com.bebe.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class NewTopicForm(
    var id: Long?,
    @field:NotEmpty @field:Size(min = 5, max = 100) val title: String?,
    @field:NotEmpty val message: String,
    @field:NotNull val idCourse: Long,
    @field:NotNull val idClient: Long
)
