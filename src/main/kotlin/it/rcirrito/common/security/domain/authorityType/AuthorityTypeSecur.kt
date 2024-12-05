package it.rcirrito.common.security.domain.authorityType

import javax.persistence.*

/**
 * The persistent class for the authority_type database table.
 *
 */
@Entity
@Table(name = "authority_type")
class AuthorityTypeSecur  //@OneToMany(mappedBy = "authorityType",fetch = FetchType.EAGER)
//private List<AuthoritySecur> authorities = new ArrayList<AuthoritySecur>();
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idauthority_type")
    var idauthorityType: Int? = null
    var authority: String? = null
    var descrizione: String? = null
    var application: String? = null

    override fun equals(o: Any?): Boolean {
        var check = false
        if (o is AuthorityTypeSecur) {
            if (o.idauthorityType == idauthorityType) check = true
        }
        return check
    }

    override fun hashCode(): Int {
        var hash = 7
        hash = 41 * hash + if (idauthorityType != null) idauthorityType.hashCode() else 0
        return hash
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}

