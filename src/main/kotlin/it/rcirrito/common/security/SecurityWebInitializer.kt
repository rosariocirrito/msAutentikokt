package it.rcirrito.common.security

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer


// serve per registrare lo springSecurityFilterChain per ogni URl vedi Spring Security Reference pag.17
class SecurityWebInitializer : AbstractSecurityWebApplicationInitializer()
