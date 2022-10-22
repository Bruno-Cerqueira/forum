package com.bebe.forum.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@WebMvcTest(HelloController::class)
class HelloControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc;

    @Test
    fun whenValidInput_thenReturns200() {
        mockMvc.get("/hello").andExpect {
            status { isOk() }
            content { contentType(org.springframework.http.MediaType.valueOf("text/plain;charset=UTF-8")) }
            content { "Hello" }
        }
    }
}
