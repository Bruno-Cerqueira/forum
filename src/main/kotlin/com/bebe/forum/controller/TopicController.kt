package com.bebe.forum.controller

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicView
import com.bebe.forum.dto.UpdateTopicForm
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.User
import com.bebe.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*
import javax.validation.Valid

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
    fun post(@RequestBody @Valid topic: NewTopicForm, uriBuilder: UriComponentsBuilder) : ResponseEntity<TopicView> {
        val topicView = topicService.post(topic)
        val uri = uriBuilder.path("/topcis/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)

    }

    @PutMapping
    fun update(@RequestBody @Valid topic: UpdateTopicForm): ResponseEntity<TopicView> {
        val topicView = topicService.update(topic)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        topicService.remove(id)
    }
}