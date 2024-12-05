package it.rcirrito.common.security.api

import it.rcirrito.common.security.api.hs.SecurityQryService
import it.rcirrito.common.security.api.hs.pl.UserDTO
import it.rcirrito.common.security.domain.user.UserCmdService
import it.rcirrito.common.security.domain.user.UserRepository
import it.rcirrito.common.security.domain.user.UserSecur
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.FileNotFoundException
import java.io.IOException


@RestController
@RequestMapping(value = ["/api"])
class ReadUserController {
    @Autowired
    private lateinit var secQryServizi: SecurityQryService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userCmdServizi: UserCmdService

    private val log = LogFactory.getLog(ReadUserController::class.java)

    @RequestMapping(value = ["/findUserByUsernameAndAppl/{username}/{appl}"])
    @Throws(IOException::class)
    fun findUserByUsernameAndAppl(@PathVariable(value = "username") username: String, @PathVariable(value = "appl") appl: String): UserDTO {
        log.info("User request for username: $username at appl: $appl")
        val user: UserDTO = secQryServizi.findUserByUsernameAndAppl(username, appl)
        return if (user == null) throw FileNotFoundException("/findUserByUsername/$username") else user
    }

    @RequestMapping(value = ["/findUserByUsername/{username}"])
    @Throws(IOException::class)
    fun findUserByUsernameAndAppl(@PathVariable(value = "username") username: String): UserDTO {
        log.info("User request for username: $username ")
        val user: UserDTO = secQryServizi.findUserByUsername(username)
        return if (user == null) throw FileNotFoundException("/findUserByUsername/$username") else user
    }

    @RequestMapping(value = ["/chgPwd/{id}/{oldPwd}/{newPwd}"])
    @Throws(IOException::class)
    fun chgPwd(@PathVariable(value = "id") id: Int,
               @PathVariable(value = "oldPwd") oldPwd: String,
               @PathVariable(value = "newPwd") newPwd: String?
    ): UserDTO {
        val userSec: UserSecur = userRepository.getOne(id)
        if (userSec.password.equals(oldPwd)) {
            log.info("chgPwd oldPwd ok!: $oldPwd")
            userSec.password = newPwd
            userCmdServizi.save(userSec)
        }
        log.info("chgPwd pfID: $id")
        val user: UserDTO = secQryServizi.findById(id)
        return if (user == null) throw FileNotFoundException("/chgPwd/$id") else user
    }
}
