package com.bebe.forum.repository

import com.bebe.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRespository: JpaRepository<User, Long> {
}