package ec.com.denunciasecuador.feature.usuario.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Representa la entidad de un usuario en el sistema.
 * <p>
 * Esta clase se mapea a la tabla "Usuarios" en la base de datos y contiene
 * información personal del usuario, así como una relación con sus credenciales
 * de acceso.
 * </p>
 * 
 * @author Robin Lugo
 * @version 1.0
 */
@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador único del usuario.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Long id;

	/**
	 * Primer nombre del usuario.
	 */
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	/**
	 * Segundo nombre del usuario (opcional).
	 */
	@Column(name = "middle_name", length = 50)
	private String middleName;

	/**
	 * Apellidos del usuario.
	 */
	@Column(name = "surnames", nullable = false, length = 100)
	private String surnames;

	/**
	 * Número de identificación (cédula) del usuario. Debe ser único.
	 */
	@Column(name = "identity_number", unique = true, nullable = false, length = 10)
	private String identityNumber;

	/**
	 * Credenciales de acceso asociadas a este usuario.
	 * <p>
	 * Establece una relación uno a uno con la entidad {@link Credencial}.
	 * </p>
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id", referencedColumnName = "credentials_id", nullable = false)
	private Credencial credential;

	/**
	 * Constructor por defecto de la clase Usuario.
	 */
	public Usuario() {
		super();
	}

	/**
	 * Constructor parametrizado para crear una instancia de Usuario.
	 *
	 * @param firstName      El primer nombre del usuario.
	 * @param middleName     El segundo nombre del usuario.
	 * @param surnames       Los apellidos del usuario.
	 * @param identityNumber El número de identificación del usuario.
	 * @param credential     Las credenciales asociadas al usuario.
	 */
	public Usuario(String firstName, String middleName, String surnames, String identityNumber, Credencial credential) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.surnames = surnames;
		this.identityNumber = identityNumber;
		this.credential = credential;
	}

	/**
	 * Obtiene el primer nombre del usuario.
	 *
	 * @return El primer nombre del usuario.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Establece el primer nombre del usuario.
	 *
	 * @param firstName El primer nombre a establecer.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Obtiene el segundo nombre del usuario.
	 *
	 * @return El segundo nombre del usuario.
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Establece el segundo nombre del usuario.
	 *
	 * @param middleName El segundo nombre a establecer.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Obtiene los apellidos del usuario.
	 *
	 * @return Los apellidos del usuario.
	 */
	public String getSurnames() {
		return surnames;
	}

	/**
	 * Establece los apellidos del usuario.
	 *
	 * @param surnames Los apellidos a establecer.
	 */
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	/**
	 * Obtiene el número de identificación del usuario.
	 *
	 * @return El número de identificación del usuario.
	 */
	public String getIdentityNumber() {
		return identityNumber;
	}

	/**
	 * Establece el número de identificación del usuario.
	 *
	 * @param identityNumber El número de identificación a establecer.
	 */
	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	/**
	 * Obtiene las credenciales asociadas al usuario.
	 *
	 * @return Las credenciales del usuario.
	 */
	public Credencial getCredential() {
		return credential;
	}

	/**
	 * Establece las credenciales asociadas al usuario.
	 *
	 * @param credential Las credenciales a establecer.
	 */
	public void setCredential(Credencial credential) {
		this.credential = credential;
	}

	/**
	 * Obtiene el identificador único del usuario.
	 *
	 * @return El ID del usuario.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Genera un código hash para el objeto Usuario.
	 * <p>
	 * La implementación utiliza los campos {@code id} y {@code identityNumber}.
	 * </p>
	 * 
	 * @return El código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, identityNumber);
	}

	/**
	 * Compara este objeto Usuario con otro objeto para determinar si son iguales.
	 * <p>
	 * Dos objetos Usuario se consideran iguales si tienen el mismo {@code id} y
	 * {@code identityNumber}.
	 * </p>
	 * 
	 * @param obj El objeto a comparar.
	 * @return {@code true} si los objetos son iguales, {@code false} en caso
	 *         contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) && Objects.equals(identityNumber, other.identityNumber);
	}

}
