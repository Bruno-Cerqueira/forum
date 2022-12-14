package com.bebe.forum.service

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicByCategoryDTO
import com.bebe.forum.dto.TopicView
import com.bebe.forum.dto.UpdateTopicForm
import com.bebe.forum.exception.NotFoundException
import com.bebe.forum.mapper.TopicFormMapper
import com.bebe.forum.mapper.TopicViewMapper
import com.bebe.forum.model.Topic
import com.bebe.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic not found"
) {

    fun listData (courseName: String?, pagination: Pageable): Page<TopicView> {
        val topics = if(courseName == null) {
            repository.findAll(pagination)
        } else {
            repository.findByCourseName(courseName, pagination)
        }
        return topics.map { it ->
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
        topic.title = form.title
        topic.message = form.message
        return topicViewMapper.map(topic)
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }

    fun report(): List<TopicByCategoryDTO> {
        return repository.report()
    }
}