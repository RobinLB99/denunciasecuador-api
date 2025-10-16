package ec.com.denunciasecuador.feature.registro.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.denunciasecuador.common.util.PasswordEncryptor;
import ec.com.denunciasecuador.feature.registro.dto.RegistroRequestDTO;
import ec.com.denunciasecuador.feature.registro.dto.RegistroResponseDTO;
import ec.com.denunciasecuador.feature.registro.service.contract.RegistroService;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import ec.com.denunciasecuador.feature.usuario.service.CredencialServiceImpl;
import ec.com.denunciasecuador.feature.usuario.service.UsuarioServiceImpl;

@Service
public class RegistroServiceImpl implements RegistroService {

	private final CredencialServiceImpl credencialServiceImpl;
	private final UsuarioServiceImpl usuarioServiceImpl;

	public RegistroServiceImpl(CredencialServiceImpl credencialServiceImpl, UsuarioServiceImpl usuarioServiceImpl) {
		this.credencialServiceImpl = credencialServiceImpl;
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	@Override
	@Transactional
	public Usuario registrarNuevoUsuario(RegistroRequestDTO registroDTO) {
		CredencialRequestDTO credencial = new CredencialRequestDTO();
		credencial.setUsername(registroDTO.username());
		credencial.setPassword(PasswordEncryptor.encryptPassword(registroDTO.password()));

		Credencial newCredencial = credencialServiceImpl.guardarCredencial(credencial);

		Usuario usuario = new Usuario();
		usuario.setFirstName(registroDTO.firstName());
		usuario.setMiddleName(registroDTO.middleName());
		usuario.setSurnames(registroDTO.surnames());
		usuario.setIdentityNumber(registroDTO.identityNumber());
		usuario.setCredential(newCredencial);

		return usuarioServiceImpl.guardarUsuario(usuario);
	}

	public RegistroResponseDTO usuarioMapearRegistro(Usuario usuario) {		
		return new RegistroResponseDTO(
				usuario.getFirstName(), 
				usuario.getSurnames(), 
				usuario.getIdentityNumber(), 
				usuario.getCredential().getUsername());
	}

}
