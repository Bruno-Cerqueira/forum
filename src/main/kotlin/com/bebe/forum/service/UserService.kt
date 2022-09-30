package com.bebe.forum.service

import com.bebe.forum.model.User
import com.bebe.forum.repository.UserRespository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UserService (private val userRepository: UserRespository) {

    fun getById(id: Long): User? {
        return userRepository.getOne(id)
    }


}