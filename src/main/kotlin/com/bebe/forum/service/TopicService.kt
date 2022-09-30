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
import com.bebe.forum.repository.TopicRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found"
) {

    fun listData (): List<TopicView> {
        return repository.findAll().map { it ->
            topicViewMapper.map(it)
        }
    }

    fun getById(id: Long): TopicView? {
        val topic: Topic = repository.findById(id).orElseThrow{NotFoundException(notFoundMessage)}
        return topicViewMapper.map(topic)
    }

    fun post(topicForm: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(topicForm)
        repository.save(topic)
        return topicViewMapper.map(topic)

    }

    fun update(form: UpdateTopicForm): TopicView {
        val topic: Topic = repository.findById(form.id).orElseThrow{NotFoundException(notFoundMessage)}
        val newTopic: Topic = Topic(
            id = form.id,
            title = form.title,
            message = form.message,
            author = topic.author,
            course = topic.course,
            answers = topic.answers,
            status = topic.status,
            creationDate = topic.creationDate
        )
        topic.title = form.title
        topic.message = form.message
        return topicViewMapper.map(topic)
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }
}