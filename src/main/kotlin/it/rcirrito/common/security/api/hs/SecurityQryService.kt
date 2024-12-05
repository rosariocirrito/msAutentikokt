package it.rcirrito.common.security.api.hs

import it.rcirrito.common.security.api.hs.pl.UserDTO

interface SecurityQryService {
    fun findById(id: Int): UserDTO
    fun findUserByUsernameAndAppl(username: String, appl: String): UserDTO
    fun findUserByUsername(username: String): UserDTO
}