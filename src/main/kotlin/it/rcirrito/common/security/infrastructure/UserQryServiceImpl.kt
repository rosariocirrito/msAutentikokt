package it.rcirrito.common.security.infrastructure

import it.rcirrito.common.security.domain.user.UserQryService
import it.rcirrito.common.security.domain.user.UserRepository
import it.rcirrito.common.security.domain.user.UserSecur
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service("userQryService")
@Repository
@Transactional
class UserQryServiceImpl : UserQryService {
    private val log = LogFactory.getLog(UserQryServiceImpl::class.java)

    //
    @Autowired
    private lateinit var repo: UserRepository

    @Transactional(readOnly = true)
    override fun findById(id: Int): UserSecur {
        return repo.getOne(id)
    }

    @Transactional(readOnly = true)
    override fun findUserByUsername(username: String): UserSecur {
        return repo.findByUsername(username)!!
    }

    override fun findNameOfLoggedUser(): String {
        return SecurityContextHolder.getContext().authentication.name
    }
} // ------------------------------------------------------------

