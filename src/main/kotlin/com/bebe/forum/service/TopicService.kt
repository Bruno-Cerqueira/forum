package com.bebe.forum.service

import com.bebe.forum.model.Course
import com.bebe.forum.model.Topic
import com.bebe.forum.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(private var topics: List<Topic>) {

    init {
        val topic = Topic(
            id = 1,
            title = "Kotlin",
            message = "Kotlin coroutines",
            course = Course(1, "Advanced Kotlin", "CS"),
            author = User(1, "John", "john@email.com")
        )

        val topic2 = Topic(
            id = 2,
            title = "Kotlin2",
            message = "Kotlin coroutines2",
            course = Course(1, "Advanced Kotlin", "CS"),
            author = User(1, "John", "john@email.com")
        )

        val topic3 = Topic(
            id = 3,
            title = "Kotlin3",
            message = "Kotlin coroutines3",
            course = Course(1, "Advanced Kotlin", "CS"),
            author = User(1, "John", "john@email.com")
        )

        topics = Arrays.asList(topic, topic2, topic3)
    }

    fun listData (): List<Topic> {
        return topics
    }


    fun getById(id: Long): Topic? {
        return topics.firstOrNull() { it.id == id }
    }
}