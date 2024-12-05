package it.rcirrito.common.security.infrastructure

import it.rcirrito.common.security.domain.user.UserCmdService
import it.rcirrito.common.security.domain.user.UserRepository
import it.rcirrito.common.security.domain.user.UserSecur
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.logging.Level
import java.util.logging.Logger

@Service("userCmdService")
@Repository
@Transactional
class UserCmdServiceImpl : UserCmdService {
    private val log = LogFactory.getLog(UserCmdServiceImpl::class.java)

    // vedi Spring Data Pro Spring 3 p380 al posto di em uso il Repository
    @Autowired
    private lateinit var repo: UserRepository

    @Transactional(readOnly = true)
    fun findById(id: Int): UserSecur {
        return repo.getOne(id)
    }

    override fun save(arg0: UserSecur): UserSecur {
        if (arg0.idusers == null) { // Insert
            log.info("Inserting new User")
        } else {                       // Update
            log.info("Updating existing User")
        }
        log.info("azione saved with id: " + arg0.idusers)
        return repo.save(arg0)
    }

    override fun changePwd(user: UserSecur, password: String) {
        //log.info("UserServiceImpl.changePwd() username= "+userName+" pwd= "+password);
        var hash = ""
        var sha256: MessageDigest? = null
        try {
            //sha256 = MessageDigest.getInstance("SHA-256");
            sha256 = MessageDigest.getInstance("SHA-1")
        } catch (ex: NoSuchAlgorithmException) {
            Logger.getLogger(UserCmdServiceImpl::class.java.name).log(Level.SEVERE, null, ex)
        }
        //
        val bytes = sha256!!.digest(password.toByteArray())
        for (b in bytes) hash += String.format("%02x", b)
        println(hash)
        user.password = hash
        save(user)
    }

    override fun resetPwd(user: UserSecur) {
        changePwd(user, user.username!!.toLowerCase().trim())
    }
} // ------------------------------------------------------

