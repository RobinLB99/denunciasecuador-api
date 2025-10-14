package ec.com.denunciasecuador.feature.usuario.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

	@Nonnull
	@NotBlank(message = "{FIRSTNAME.NOTBLANK}")
	@Size(min = 5, max = 25, message = "{FIRSTNAME.SIZE}")
	private String firstName;

	@Size(min = 5, max = 25, message = "{MIDDLENAME.SIZE}")
	private String middleName;

	@Nonnull
	@NotBlank(message = "{SURNAMES.NOTBLANK}")
	@Size(min = 5, max = 85, message = "{SURNAMES.SIZE}")
	private String surnames;

	@Nonnull
	@NotBlank(message = "{IDENTITYNUMBER.NOTBLANK}")
	@Size(min = 10, max = 10, message = "{IDENTITYNUMBER.SIZE}")
	private String identityNumber;
	
	@Nonnull
	@NotNull(message = "{CREDENTIALID.NOTNULL}")
	private Long idCredencial;

}
