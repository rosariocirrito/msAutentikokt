package it.rcirrito.common.security.domain.user

interface UserCmdService {
    fun save(arg0: UserSecur): UserSecur
    fun changePwd(user: UserSecur, pwd: String)
    fun resetPwd(user: UserSecur)
}