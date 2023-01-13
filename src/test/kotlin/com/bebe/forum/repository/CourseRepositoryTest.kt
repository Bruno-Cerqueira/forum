package com.bebe.forum.repository

import com.bebe.forum.model.Client
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@DataJpaTest
class CourseRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var courseRepository: CourseRespository

    @Test
    fun WhenFindById_thenReturnCourse() {
        val course = Course(name = "Admin", category = "Adm")
        entityManager.persist(course)
        entityManager.flush()

        val courseFound = courseRepository.findById(1L)
        Assertions.assertThat(courseFound.get().name == "Admin")
    }
}