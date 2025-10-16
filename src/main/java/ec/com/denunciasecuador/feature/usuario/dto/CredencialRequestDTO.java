package ec.com.denunciasecuador.feature.usuario.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para la solicitud de credenciales.
 * <p>
 * Este record se utiliza para encapsular los datos de nombre de usuario y
 * contraseña que se reciben en las solicitudes de creación o actualización de
 * credenciales. Incluye anotaciones de validación para asegurar la integridad
 * de los datos.
 * </p>
 *
 * @param username El nombre de usuario. Debe ser no nulo, no vacío y tener una
 *                 longitud entre 5 y 25 caracteres.
 * @param password La contraseña. Debe ser no nula, no vacía y tener una
 *                 longitud entre 8 y 16 caracteres.
 * @author Robin Lugo
 * @version 1.0
 */
public record CredencialRequestDTO(
		@Nonnull
		@NotBlank(message = "{USERNAME.NOTBLANK}")
		@Size(min = 5, max = 25, message = "{USERNAME.SIZE}")
		String username,
		
		@Nonnull
		@NotBlank(message = "{PASSWORD.NOTBLANK}")
		@Size(min = 8, max = 16, message = "{PASSWORD.SIZE}")
		String password
) {}
