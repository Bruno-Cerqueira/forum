package com.bebe.forum.repository

import com.bebe.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByCourseName(courseName: String, pagination: Pageable): Page<Topic>
}