package com.bebe.forum.service

import com.bebe.forum.model.Course
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CourseService(var courses: List<Course>) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programacao"
        )
        courses = Arrays.asList(course)
    }

    fun getById(id: Long): Course? {
        return courses.firstOrNull { it -> it.id == id }
    }


}