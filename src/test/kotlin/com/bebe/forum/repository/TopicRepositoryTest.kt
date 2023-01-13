package com.bebe.forum.repository

import com.bebe.forum.dto.TopicByCategoryDTO
import com.bebe.forum.model.Client
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class TopicRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var topicRepository: TopicRepository

    private val course = Course(name = "Admin", category =  "Adm")
    private val author = Client(name = "cliente", email = "Cliente@gmail.com")
    val topic = Topic(title = "General", message =  "Message", course = course, author = author)

    @Test
    fun WhenFindByIdOrNull_thenReturnBankAccount() {
        entityManager.persist(course)
        entityManager.persist(author)
        entityManager.persist(topic)
        entityManager.flush()
        val topicFound = topicRepository.findByIdOrNull(topic.id!!)
        Assertions.assertThat(topicFound == topic)
    }

    @Test
    fun Whenreport_thenReturnNumberOfTopicsByCategory() {
        entityManager.flush()
        val report = topicRepository.report()
        val expectedReport : List<TopicByCategoryDTO> = listOf(TopicByCategoryDTO(category = "Adm", quantity = 1))

        Assertions.assertThat(report == expectedReport)
    }
}