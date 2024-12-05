package it.rcirrito.common.security.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserSecur, Int> {
    fun findByUsername(username: String): UserSecur?
}