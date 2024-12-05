package it.rcirrito.common.security.api.hs

import it.rcirrito.common.security.api.hs.pl.UserDTO
import it.rcirrito.common.security.domain.authority.AuthoritySecur
import it.rcirrito.common.security.domain.user.UserQryService
import it.rcirrito.common.security.domain.user.UserSecur
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("securityQryService")
class SecurityQryServiceImpl : SecurityQryService {
    private val log = LogFactory.getLog(SecurityQryServiceImpl::class.java)

    // vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
    @Autowired
    private lateinit var userServizi: UserQryService

    override fun findById(id: Int): UserDTO {
        val user: UserSecur = userServizi.findById(id)!!
        return UserDTO(user)
    }

    override fun findUserByUsernameAndAppl(username: String, appl: String): UserDTO {
        val user: UserSecur = userServizi.findUserByUsername(username)
        val validAuthorities: MutableList<AuthoritySecur> = ArrayList<AuthoritySecur>()
        for (aut in user.authorities) {
            if (aut.authorityType!!.application.equals(appl)) {
                validAuthorities.add(aut)
            }
        }
        user.authorities = validAuthorities
        return UserDTO(user)
    }

    override fun findUserByUsername(username: String): UserDTO {
        val user: UserSecur = userServizi.findUserByUsername(username)
        val validAuthorities: MutableList<AuthoritySecur> = ArrayList<AuthoritySecur>()
        for (aut in user.authorities) {
            //if (aut.authorityType!!.application.equals(appl)) {
                validAuthorities.add(aut)
            //}
        }
        user.authorities = validAuthorities
        return UserDTO(user)
    }
}