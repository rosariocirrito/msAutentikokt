package it.rcirrito.common.security.api.hs.pl

import it.rcirrito.common.security.domain.authority.AuthoritySecur
import it.rcirrito.common.security.domain.user.UserSecur
import java.util.*

class UserDTO(user: UserSecur) {
    // ------------------- campi db ---------------------------------------
    val idusers: Int
    val email: String
    val enabled: Boolean
    val username: String
    var password: String
    val pfID: Int
    val roles: MutableList<String> = ArrayList<String>()

    init {
        idusers = user.idusers!!
        email = user.email!!
        enabled = user.enabled!!
        username = user.username!!
        password = user.password!!
        pfID = user.pfID!!
        val listAuths: List<AuthoritySecur> = user.authorities
        for (auth in listAuths) {
            roles.add(auth.authorityType!!.authority!!)
        }
    }
} // ----------------


