package com.bebe.forum.mapper

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.model.Topic
import com.bebe.forum.service.ClientService
import com.bebe.forum.service.CourseService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val clientService: ClientService
) : Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        return Topic(
            id = null,
            title = t.title,
            message = t.message,
            course = courseService.getById(1),
            author = clientService.getById(1),
        )
    }
}