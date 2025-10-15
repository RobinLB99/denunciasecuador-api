package ec.com.denunciasecuador.feature.registro.service.contract;

import ec.com.denunciasecuador.feature.registro.dto.RegistroRequestDTO;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;

public interface RegistroService {

	public Usuario registrarNuevoUsuario(RegistroRequestDTO registroDTO);
	
}
