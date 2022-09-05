package com.bebe.forum.mapper

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.model.Topic
import com.bebe.forum.service.CourseService
import com.bebe.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService
) : Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        return Topic(
            id = t.id,
            title = t.title,
            message = t.message,
            course = courseService.getById(1),
            author = userService.getById(1),
        )
    }
}