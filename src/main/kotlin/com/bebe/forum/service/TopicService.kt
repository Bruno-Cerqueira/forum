package com.bebe.forum.service

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicView
import com.bebe.forum.dto.UpdateTopicForm
import com.bebe.forum.exception.NotFoundException
import com.bebe.forum.mapper.TopicFormMapper
import com.bebe.forum.mapper.TopicViewMapper
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.User
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(
    private var topics: List<Topic>,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found"
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

    fun post(topicForm: NewTopicForm): TopicView {
        topicForm.id = topics.size.toLong() + 1
        val topic = topicFormMapper.map(topicForm)
        topics = topics.plus(topic)
        return topicViewMapper.map(topic)

    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic: Topic = topics.first() { it.id == form.id }
        val newTopic: Topic = Topic(
            id = form.id,
            title = form.title,
            message = form.message,
            author = topic.author,
            course = topic.course,
            answers = topic.answers,
            status = topic.status,
            creationDate = topic.creationDate
        )!!
        topics = topics.minus(topic).plus(newTopic as Topic)
        return topicViewMapper.map(newTopic)
    }

    fun remove(id: Long) {
        val topic: Topic = topics.firstOrNull(){ it.id == id } ?: throw NotFoundException(notFoundMessage)
        topics = topics.minus(topic)
    }
}