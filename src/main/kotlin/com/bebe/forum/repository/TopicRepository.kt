package com.bebe.forum.repository

import com.bebe.forum.dto.TopicByCategoryDTO
import com.bebe.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByCourseName(courseName: String, pagination: Pageable): Page<Topic>

    @Query("SELECT new com.bebe.forum.dto.TopicByCategoryDTO(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicByCategoryDTO>
}