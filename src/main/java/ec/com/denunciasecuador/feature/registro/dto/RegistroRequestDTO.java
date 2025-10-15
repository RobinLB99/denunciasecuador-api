package ec.com.denunciasecuador.feature.registro.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequestDTO {
	
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
	@NotBlank(message = "{USERNAME.NOTBLANK}")
	@Size(min = 5, max = 25, message = "{USERNAME.SIZE}")
	private String username;
	
	@Nonnull
	@NotBlank(message = "{PASSWORD.NOTBLANK}")
	@Size(min = 8, max = 16, message = "{PASSWORD.SIZE}")
	private String password;

}
