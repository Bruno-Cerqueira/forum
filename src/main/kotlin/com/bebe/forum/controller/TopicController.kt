package com.bebe.forum.controller

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicView
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.User
import com.bebe.forum.service.TopicService
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    fun index() : List<TopicView> {
        return topicService.listData()
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long) : TopicView? {
        return topicService.getById(id)
    }

    @PostMapping
    fun post(@RequestBody topic: NewTopicForm) {
        try {
            topicService.post(topic)
        } catch (e: HttpMessageNotReadableException){
            e.printStackTrace()
            println("ClassCastException foi pegada")
        }
    }
}