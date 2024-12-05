package it.rcirrito.common.security.domain.user

import it.rcirrito.common.security.domain.authority.AuthoritySecur
import it.rcirrito.common.security.domain.authorityType.AuthorityTypeSecur
import java.util.*
import javax.persistence.*

//import it.sicilia.regione.gekoddd.security.acl.model.PersonaFisicaSecur;
@Entity
@Table(name = "users")
class UserSecur  //transient private PersonaFisicaSecur persona;
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var idusers: Int? = null
    var email: String? = null
    var enabled: Boolean? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date")
    var expiryDate: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date")
    var insertDate: Date? = null
    var password: String? = null

    @Transient
    var password2: String? = null

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "renewal_date")
    var renewalDate: Date? = null
    var username: String? = null

    /*
	public PersonaFisicaSecur getPersona() {
		return this.persona;
	}

	public void setPersona(PersonaFisicaSecur persona) {
		this.persona = persona;
	}
	*/
    @Column(name = "persona_idpersona")
    var pfID: Int? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var authorities: List<AuthoritySecur> = ArrayList<AuthoritySecur>()

    val authorityTypes: List<Any>
        get() {
            val lista: MutableList<AuthorityTypeSecur> = ArrayList<AuthorityTypeSecur>()
            for (auth in authorities) {
                lista.add(auth.authorityType!!)
            }
            return lista
        }

    override fun equals(o: Any?): Boolean {
        var check = false
        if (o is UserSecur) {
            if (o.idusers == idusers) check = true
        }
        return check
    }

    override fun hashCode(): Int {
        var hash = 5
        hash = 41 * hash + if (idusers != null) idusers.hashCode() else 0
        return hash
    }

    override fun toString(): String {
        return ("User [idusers=" + idusers + ", username=" + username
                + ", pfID=" + pfID + "]")
    }

    companion object {
        private const val serialVersionUID = 1L
    }
} // -------------------------------------------------------------


