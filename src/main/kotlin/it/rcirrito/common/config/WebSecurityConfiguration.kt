package it.rcirrito.common.config

import org.apache.commons.logging.Log

import org.apache.commons.logging.LogFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import org.springframework.security.crypto.password.PasswordEncoder

import org.springframework.security.authentication.encoding.ShaPasswordEncoder


@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {
    /*
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService; // per l'implementazione vedi infrastructure
	*/
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder?) {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //.p  .passwordEncoder());
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return object : PasswordEncoder {
            private val shap: ShaPasswordEncoder = ShaPasswordEncoder(1)
            override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
                return if (encodedPassword == null || encodedPassword.length == 0) {
                    false
                } else shap.isPasswordValid(encodedPassword, rawPassword.toString(), null)
            }

            override fun encode(rawPassword: CharSequence): String {
                return shap.encodePassword(rawPassword.toString(), null)
            }
        }
    }

    companion object {
        private val log = LogFactory.getLog(WebSecurityConfiguration::class.java)
    }
}
