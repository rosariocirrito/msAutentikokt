package it.rcirrito.common.security.domain.authority

import it.rcirrito.common.security.domain.authorityType.AuthorityTypeSecur
import it.rcirrito.common.security.domain.user.UserSecur
import javax.persistence.*

@Entity
@Table(name = "authorities")
class AuthoritySecur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Int? = null

    //bi-directional many-to-one association to User
    //@ManyToOne(fetch=FetchType.EAGER) 2015/07/20
    @ManyToOne
    @JoinColumn(name = "idusers")
    var user: UserSecur? = null

    //bi-directional many-to-one association to AuthorityType
    // @ManyToOne(fetch=FetchType.EAGER) 2015/07/20
    @ManyToOne
    @JoinColumn(name = "idauthority_type")
    var authorityType: AuthorityTypeSecur? = null

    @Transient
    var idauthority_type = 0

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (id == null) 0 else id.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as AuthoritySecur
        if (id == null) {
            if (other.id != null) return false
        } else if (id != other.id) return false
        return true
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
