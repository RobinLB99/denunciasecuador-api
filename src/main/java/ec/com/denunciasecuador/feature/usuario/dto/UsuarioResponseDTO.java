package ec.com.denunciasecuador.feature.usuario.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponseDTO {

	private String firstName;
	private String surnames;
	private String username;

}
