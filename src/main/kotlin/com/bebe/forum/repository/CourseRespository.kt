package com.bebe.forum.repository

import com.bebe.forum.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRespository: JpaRepository<Course, Long> {
}