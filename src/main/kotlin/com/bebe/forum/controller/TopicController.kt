package com.bebe.forum.controller

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicView
import com.bebe.forum.dto.UpdateTopicForm
import com.bebe.forum.service.TopicService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    fun index(@RequestParam(required = false) courseName: String?, @PageableDefault(size = 2) pagination: Pageable) : Page<TopicView> {
        return topicService.listData(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long) : TopicView? {
        return topicService.getById(id)
    }

    @PostMapping
    @Transactional
    fun post(@RequestBody @Valid topic: NewTopicForm, uriBuilder: UriComponentsBuilder) : ResponseEntity<TopicView> {
        val topicView = topicService.post(topic)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)

    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid topic: UpdateTopicForm): ResponseEntity<TopicView> {
        val topicView = topicService.update(topic)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        topicService.remove(id)
    }
}