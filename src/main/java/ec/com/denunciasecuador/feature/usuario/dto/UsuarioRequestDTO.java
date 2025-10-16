package ec.com.denunciasecuador.feature.usuario.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para la solicitud de creación o actualización de un usuario.
 * <p>
 * Este record se utiliza para encapsular los datos de un usuario que se reciben
 * en las solicitudes. Incluye anotaciones de validación para asegurar la
 * integridad de los datos antes de su procesamiento.
 * </p>
 *
 * @param firstName      El primer nombre del usuario. Debe ser no nulo, no vacío y tener una
 *                       longitud entre 5 y 25 caracteres.
 * @param middleName     El segundo nombre del usuario. Opcional, pero si se proporciona, debe
 *                       tener una longitud entre 5 y 25 caracteres.
 * @param surnames       Los apellidos del usuario. Debe ser no nulo, no vacío y tener una
 *                       longitud entre 5 y 85 caracteres.
 * @param identityNumber El número de identificación del usuario. Debe ser no nulo, no vacío y
 *                       tener exactamente 10 caracteres.
 * @param idCredencial   El ID de la credencial asociada al usuario. Debe ser no nulo.
 * @author Robin Lugo
 * @version 1.0
 */
public record UsuarioRequestDTO(
		@Nonnull
		@NotBlank(message = "{FIRSTNAME.NOTBLANK}")
		@Size(min = 5, max = 25, message = "{FIRSTNAME.SIZE}")
		String firstName,

		@Size(min = 5, max = 25, message = "{MIDDLENAME.SIZE}")
		String middleName,

		@Nonnull
		@NotBlank(message = "{SURNAMES.NOTBLANK}")
		@Size(min = 5, max = 85, message = "{SURNAMES.SIZE}")
		String surnames,

		@Nonnull
		@NotBlank(message = "{IDENTITYNUMBER.NOTBLANK}")
		@Size(min = 10, max = 10, message = "{IDENTITYNUMBER.SIZE}")
		String identityNumber,

		@Nonnull
		@NotNull(message = "{CREDENTIALID.NOTNULL}")
		Long idCredencial
) {}
