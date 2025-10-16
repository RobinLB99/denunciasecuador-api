package ec.com.denunciasecuador.feature.usuario.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa la entidad de las credenciales de un usuario en el sistema.
 * <p>
 * Esta clase se mapea a la tabla "Credenciales" en la base de datos y contiene
 * el nombre de usuario y la contraseña (hash) para el acceso al sistema.
 * </p>
 * 
 * @author Robin Lugo
 * @version 1.0
 */
@Entity
@Table(name = "Credenciales")
public class Credencial implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador único de la credencial.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credentials_id")
	private Long id;

	/**
	 * Nombre de usuario único para el inicio de sesión.
	 */
	@Column(name = "username", nullable = false, unique = true, length = 25)
	private String username;

	/**
	 * Contraseña del usuario (almacenada como hash).
	 */
	@Column(name = "password", nullable = false, length = 255)
	private String password;

	/**
	 * Constructor por defecto de la clase Credencial.
	 */
	public Credencial() {
		super();
	}

	/**
	 * Constructor parametrizado para crear una instancia de Credencial.
	 *
	 * @param username El nombre de usuario.
	 * @param password La contraseña (hash) del usuario.
	 */
	public Credencial(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Obtiene el nombre de usuario.
	 *
	 * @return El nombre de usuario.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Establece el nombre de usuario.
	 *
	 * @param username El nombre de usuario a establecer.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Obtiene la contraseña (hash) del usuario.
	 *
	 * @return La contraseña del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña (hash) del usuario.
	 *
	 * @param password La contraseña a establecer.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene el identificador único de la credencial.
	 *
	 * @return El ID de la credencial.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Genera un código hash para el objeto Credencial.
	 * <p>
	 * La implementación utiliza el campo {@code id}.
	 * </p>
	 * 
	 * @return El código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Compara este objeto Credencial con otro objeto para determinar si son
	 * iguales.
	 * <p>
	 * Dos objetos Credencial se consideran iguales si tienen el mismo {@code id}.
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
		Credencial other = (Credencial) obj;
		return Objects.equals(id, other.id);
	}

}
