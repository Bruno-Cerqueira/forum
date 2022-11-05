package com.bebe.forum.controller

import com.bebe.forum.dto.NewTopicForm
import com.bebe.forum.dto.TopicView
import com.bebe.forum.model.*
import com.bebe.forum.service.TopicService
import org.junit.jupiter.api.*
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.*
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.springframework.test.web.servlet.post


@WebMvcTest(TopicController::class)
class TopicControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc;

    @MockBean
    private lateinit var topicService: TopicService
    val topicView = TopicView(1, "General", "Message", TopicStatus.NOT_ANSWERED, LocalDateTime.now())
    lateinit var result : ResultActionsDsl

    @Nested
    inner class Show {
        @BeforeEach
        fun setup() {
            Mockito.`when`(topicService.getById(1)).thenReturn(topicView)
            result = mockMvc.get("/topics/{id}", 1){
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }
        }

        // 1. Verifying HTTP Request Matching
        @Test
        fun whenValidReturns200() {
            result.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }

        // 4. Verifying Business Logic Calls
        @Test
        fun whenValidInput_thenMapsToBusinessModel() {
            verify(topicService, times(1)).getById(1)
        }

        // 5. Verifying Output Serialization
        @Test
        fun whenValidInput_thenReturnsUserResource() {
            val getTopicResponse = result.andReturn().response.contentAsString
            Assertions.assertEquals(getTopicResponse, topicView.toString())
        }
    }
    @Nested
    inner class Index {
        var pageable: Pageable = PageRequest.of(0,2, Sort.Direction.DESC, "id")
        var pagedResponse: Page<TopicView?> = PageImpl<TopicView?>(listOf(topicView))
        @BeforeEach
        fun setup() {
            Mockito.`when`(topicService.listData(null, pageable)).thenReturn(pagedResponse as Page<TopicView>)
            result = mockMvc.get("/topics") {
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }
        }

        // 1. Verifying HTTP Request Matching
        @Test
        fun whenValidReturns200() {
            result.andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }

        // 4. Verifying Business Logic Calls
        @Test
        fun whenValidInput_thenMapsToBusinessModel() {
            verify(topicService, times(1)).listData(null, pageable)
        }

        // 5. Verifying Output Serialization
        @Test
        fun whenValidInput_thenReturnsUserResource() {
            val getTopicResponse = result.andReturn().response.contentAsString
            assertThat(getTopicResponse).contains(topicView.toString())
        }
    }

    @Nested
    inner class Post {
        val payload = mapOf("title" to "Title", "message" to "message")
        val topicForm = NewTopicForm(null, "Title", "message")
        val topicView = TopicView(1, "Title", "message", TopicStatus.NOT_ANSWERED, LocalDateTime.now())

        @BeforeEach
        fun setup() {
            Mockito.`when`(topicService.post(topicForm)).thenReturn(topicView)
            result = mockMvc.post("/topics") {
                // 2. Verifying Input Deserialization
                content = JSONObject(payload).toString()
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }
        }

        // 1. Verifying HTTP Request Matching
        @Test
        fun whenValidReturns201() {
            result.andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
        }

        // 4. Verifying Business Logic Calls
        @Test
        fun whenValidInput_thenMapsToBusinessModel() {
            verify(topicService, times(1)).post(topicForm)
        }

        // 5. Verifying Output Serialization
        @Test
        fun whenValidInput_thenReturnsUserResource() {
            val getTopicResponse = result.andReturn().response.contentAsString
            assertThat(getTopicResponse).contains(topicView.toString())
        }
    }

    // 6. Verifying Exception Handling
    // 3. Verifying Input Validation
    @Nested
    inner class Validation {



        @Test
        fun whenNullTitle_thenReturns400() {
            val payloadWithError = mapOf("id" to 1, "title" to null, "message" to "message")
            result = mockMvc.post("/topics") {
                content = JSONObject(payloadWithError).toString()
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }

            val badResponse = result.andReturn().response.contentAsString
            assertThat(badResponse).contains("title=must not be empty")
        }

        @Test
        fun whenNullMessage_thenReturns400() {
            val payloadWithError = mapOf("id" to 1, "title" to "title", "message" to null)
            result = mockMvc.post("/topics") {
                content = JSONObject(payloadWithError).toString()
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }

            val badResponse = result.andReturn().response.contentAsString
            assertThat(badResponse).contains("message=must not be empty")
        }
    }
}

