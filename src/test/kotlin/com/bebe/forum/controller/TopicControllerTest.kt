package com.bebe.forum.controller

import com.bebe.forum.dto.TopicView
import com.bebe.forum.model.*
import com.bebe.forum.service.TopicService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.util.Assert
import java.time.LocalDateTime


@WebMvcTest(TopicController::class)
class TopicControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc;

    @MockBean
    private lateinit var topicService: TopicService

    @Nested
    inner class Show {
        @Test
        fun whenValidInput_thenReturns200() {
            val topicView = TopicView(1, "General", "Message", TopicStatus.NOT_ANSWERED, LocalDateTime.now())
            Mockito.`when`(topicService.getById(1)).thenReturn(topicView)
            val result = mockMvc.get("/topics/{id}", 1){
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }.andReturn()

            val getTopicResponse = result.response.getContentAsString()
            println(getTopicResponse)
            Assertions.assertEquals(getTopicResponse, topicView)
        }
    }
    @Nested
    inner class Index {
        @Test
        fun whenReturns200() {
            mockMvc.get("/topics"){
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                content { "Hello" }
            }
        }
    }

}
