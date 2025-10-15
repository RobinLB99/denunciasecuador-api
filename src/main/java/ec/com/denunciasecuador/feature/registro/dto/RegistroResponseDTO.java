package ec.com.denunciasecuador.feature.registro.dto;

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
public class RegistroResponseDTO {

	private String firstName;
	private String surnames;
	private String identityNumber;
	private String username;
	
}
