package com.bebe.forum.controller

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicByCategoryDTO
import com.bebe.forum.dto.TopicView
import com.bebe.forum.dto.UpdateTopicForm
import com.bebe.forum.service.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    @Cacheable("TopicsList")
    fun index(@RequestParam(required = false) courseName: String?, @PageableDefault(size = 2, sort = ["id"], direction = Sort.Direction.DESC) pagination: Pageable) : Page<TopicView> {
        return topicService.listData(courseName, pagination)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long) : ResponseEntity<TopicView?> {
        val topic = topicService.getById(id)
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(topic)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["TopicsList"], allEntries = true)
    fun post(@RequestBody @Valid topic: NewTopicForm, uriBuilder: UriComponentsBuilder) : ResponseEntity<TopicView> {
        val topicView = topicService.post(topic)
        val uri = uriBuilder.path("/topics/${topicView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicView)

    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["TopicsList"], allEntries = true)
    fun update(@RequestBody @Valid topic: UpdateTopicForm): ResponseEntity<TopicView> {
        val topicView = topicService.update(topic)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["TopicsList"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        topicService.remove(id)
    }

    @GetMapping("/report")
    fun report(): List<TopicByCategoryDTO> {
        return topicService.report()
    }
}