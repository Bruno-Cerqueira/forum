package com.bebe.forum.service

import com.bebe.forum.dto.TopicView
import com.bebe.forum.exception.NotFoundException
import com.bebe.forum.mapper.TopicFormMapper
import com.bebe.forum.mapper.TopicViewMapper
import com.bebe.forum.model.Client
import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.TopicStatus
import com.bebe.forum.repository.TopicRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean
import java.lang.Exception
import java.time.LocalDateTime
import java.util.Optional

class TopicServiceTest {
    @MockBean
    private val mockTopicRepository: TopicRepository = Mockito.mock(TopicRepository::class.java)
    private val notFoundMessage: String = "Topic not found"
    private val topicViewMapper: TopicViewMapper = Mockito.mock(TopicViewMapper::class.java)
    private val topicFormMapper: TopicFormMapper = Mockito.mock(TopicFormMapper::class.java)

    val topicService = TopicService(mockTopicRepository, topicViewMapper, topicFormMapper, notFoundMessage)
    val topicView = TopicView(1, "General", "Message", TopicStatus.NOT_ANSWERED, LocalDateTime.now())
    private val course = Course(1, "Admin", "Adm")
    private val author = Client(1, "cliente", "Cliente@gmail.com")
    val topic = Topic(1, "General", "Message", course = course, author = author)

    @Nested
    @DisplayName("getById")
    inner class getTopicById {
        var serviceResponse: TopicView? = null

        @Nested
        inner class Success {
            @BeforeEach
            fun `setup`() {
                Mockito.`when`(mockTopicRepository.findById(1)).thenReturn(Optional.of(topic))
                Mockito.`when`(topicViewMapper.map(topic as Topic)).thenReturn(topicView)
                serviceResponse = topicService.getById(1)
            }


            @Test
            fun `return topic by id`() {
                Assertions.assertEquals(serviceResponse, topicView)
            }

            @Test
            fun getTopicFromRepository() {
                Mockito.verify(mockTopicRepository, Mockito.times(1)).findById(1)
            }
        }

        @Nested
        inner class NotFoundData {

            @BeforeEach
            fun`setup`() {
                Mockito.`when`(mockTopicRepository.findById(1)).thenReturn(Optional.empty())
            }


            @Test
            fun `test orElseThrow`() {
                val exception = Assertions.assertThrows(Exception::class.java) {
                    topicService.getById(1L)
                }
                Assertions.assertEquals(notFoundMessage, exception.message)
            }
        }
    }
}