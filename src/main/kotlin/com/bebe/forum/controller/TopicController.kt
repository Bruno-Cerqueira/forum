package com.bebe.forum.controller

import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.User
import com.bebe.forum.service.TopicService
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
    fun index() : List<Topic> {
        return topicService.listData()
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long) : Topic? {
        return topicService.getById(id)
    }

    @PostMapping
    fun post(@RequestBody topic: Topic) {
        topicService.post(topic)
    }
}