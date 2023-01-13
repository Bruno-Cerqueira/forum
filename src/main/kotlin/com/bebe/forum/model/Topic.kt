package com.bebe.forum.model

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotNull
    var title: String,

    var message: String,

    val creationDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val course: Course?,

    @ManyToOne
    val author: Client?,

    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus = TopicStatus.NOT_ANSWERED,

    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = ArrayList()
)
