package com.bebe.forum.controller

import com.bebe.forum.dto.TopicView
import com.bebe.forum.service.TopicService
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


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
            mockMvc.get("/topics/{id}", 1){
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
