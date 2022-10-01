package com.bebe.forum.service

import com.bebe.forum.model.Course
import com.bebe.forum.repository.CourseRespository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CourseService(private val repository: CourseRespository) {
    fun getById(id: Long): Course? {
        return repository.getReferenceById(id)
    }
}