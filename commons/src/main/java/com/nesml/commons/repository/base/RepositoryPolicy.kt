package com.nesml.commons.repository.base

interface RepositoryPolicy<Info> {
    suspend fun shouldGoRemote(info: Info): Boolean = false
}