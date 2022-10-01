package com.bebe.forum.service

import com.bebe.forum.model.Client
import com.bebe.forum.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService (private val clientRepository: ClientRepository) {

    fun getById(id: Long): Client? {
        return clientRepository.getReferenceById(id)
    }
}