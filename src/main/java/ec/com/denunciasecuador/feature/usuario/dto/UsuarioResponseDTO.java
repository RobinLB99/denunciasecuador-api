package ec.com.denunciasecuador.feature.usuario.dto;

import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class UsuarioResponseDTO {

	private String firstName;
	private String surnames;
	private String username;

	public UsuarioResponseDTO(Usuario usuario) {
		this.firstName = usuario.getFirstName();
		this.surnames = usuario.getSurnames();
		this.username = usuario.getCredential().getUsername();
	}

}
