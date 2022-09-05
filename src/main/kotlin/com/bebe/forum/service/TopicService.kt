package com.bebe.forum.service

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicView
import com.bebe.forum.mapper.TopicFormMapper
import com.bebe.forum.mapper.TopicViewMapper
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(
    private var topics: List<Topic>,
    private val topicViewMapper: TopicViewMapper,
    val topicFormMapper: TopicFormMapper
) {
    init {
        val topic = Topic(
            id = 1,
            title = "Kotlin",
            message = "Kotlin coroutines",
            course = Course(1, "Advanced Kotlin", "CS"),
            author = User(1, "John", "john@email.com")
        )

        val topic2 = Topic(
            id = 2,
            title = "Kotlin2",
            message = "Kotlin coroutines2",
            course = Course(1, "Advanced Kotlin", "CS"),
            author = User(1, "John", "john@email.com")
        )

        val topic3 = Topic(
            id = 3,
            title = "Kotlin3",
            message = "Kotlin coroutines3",
            course = Course(1, "Advanced Kotlin", "CS"),
            author = User(1, "John", "john@email.com")
        )

        topics = Arrays.asList(topic, topic2, topic3)
    }

    fun listData (): List<TopicView> {
        return topics.map { it ->
            topicViewMapper.map(it)
        }
    }

    fun getById(id: Long): TopicView? {
        val topic: Topic? = topics.firstOrNull() { it.id == id }
        return topic?.let {
            topicViewMapper.map(it)
        }
    }

    fun post(topic: NewTopicForm) {
        topic.id = topics.size.toLong() + 1
        topics = topics.plus(topicFormMapper.map(topic))
    }
}