package ec.com.denunciasecuador.feature.usuario.dto;

import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CredencialResponseDTO {

	private Long id;
	private String username;

	public CredencialResponseDTO(Credencial credencial) {
		this.id = credencial.getId();
		this.username = credencial.getUsername();
	}

}
