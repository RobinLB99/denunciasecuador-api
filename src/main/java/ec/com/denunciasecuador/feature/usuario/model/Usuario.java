package ec.com.denunciasecuador.feature.usuario.model;

import java.io.Serializable;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Long id;

	@Nonnull
	@NotEmpty(message = "El campo 'Primer nombre' no puede estar vacío.")
	@NotNull(message = "El campo 'Primer nombre' es obligatorio.")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "middle_name", length = 50)
	private String middleName;

	@Nonnull
	@NotEmpty(message = "El campo 'Apellidos' no puede estar vacío.")
	@NotNull(message = "El campo 'Apellidos' es obligatorio.")
	@Column(name = "surnames", nullable = false, length = 100)
	private String surnames;
	
	@Nonnull
	@NotEmpty(message = "El campo 'Número de identidad' no puede estar vacío.")
	@NotNull(message = "El campo 'Número de identidad' es obligatorio.")
	@Column(name = "identity_number", nullable = false, length = 10)
	private String identityNumber;

	@Nonnull
	@NotNull(message = "El campo 'Credencial' es obligatorio.")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credential_id", referencedColumnName = "credentials_id", nullable = false)
	private Credencial credential;

}
