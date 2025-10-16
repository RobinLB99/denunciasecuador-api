package ec.com.denunciasecuador.feature.usuario.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
