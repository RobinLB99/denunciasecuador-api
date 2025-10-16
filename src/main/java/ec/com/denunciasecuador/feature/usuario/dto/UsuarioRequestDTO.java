package ec.com.denunciasecuador.feature.usuario.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
