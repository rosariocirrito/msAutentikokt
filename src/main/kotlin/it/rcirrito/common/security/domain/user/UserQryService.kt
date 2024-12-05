package it.rcirrito.common.security.domain.user

interface UserQryService {
    fun findById(id: Int): UserSecur
    fun findUserByUsername(username: String): UserSecur
    fun findNameOfLoggedUser(): String
}