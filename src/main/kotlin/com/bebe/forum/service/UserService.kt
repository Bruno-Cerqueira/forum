package com.bebe.forum.service

import com.bebe.forum.model.User
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UserService (var users: List<User>) {

    init {
        val usuario = User(
            id = 1,
            name = "Ana da Silva",
            email = "ana@email.com.br"
        )
        users = Arrays.asList(usuario)
    }

    fun getById(id: Long): User? {
        return users.firstOrNull() { it.id == id }
    }


}