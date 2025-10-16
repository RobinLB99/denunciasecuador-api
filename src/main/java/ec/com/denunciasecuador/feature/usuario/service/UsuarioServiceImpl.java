package ec.com.denunciasecuador.feature.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.denunciasecuador.common.exception.entitynotfound.UsuarioNotFoundException;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioRequestDTO;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import ec.com.denunciasecuador.feature.usuario.repository.UsuarioRepository;
import ec.com.denunciasecuador.feature.usuario.service.contract.UsuarioService;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioResponseDTO;
//import ec.com.denunciasecuador.feature.usuario.repository.CredencialRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
//	private final CredencialRepository credencialRepository;

	public UsuarioServiceImpl(
			UsuarioRepository usuarioRepository
//			, CredencialRepository credencialRepository
			) {
		this.usuarioRepository = usuarioRepository;
//		this.credencialRepository = credencialRepository;
	}

	public Usuario mapperDTOUsuario(UsuarioRequestDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setFirstName(usuarioDTO.firstName());
		usuario.setMiddleName(usuarioDTO.middleName());
		usuario.setSurnames(usuarioDTO.surnames());
		usuario.setIdentityNumber(usuarioDTO.identityNumber());
		return usuario;
	}
	
	public UsuarioResponseDTO crearUsuarioResponseDTO(Usuario usuario) {
		return new UsuarioResponseDTO(
				usuario.getFirstName(),
				usuario.getSurnames(),
				usuario.getCredential().getUsername());
	}

	@Override
	@Transactional
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void eliminarUsuarioPorId(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void eliminarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el id: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPorNumeroIdentidad(String numeroIdentidad) {
		return usuarioRepository.findUsuarioByIdentityNumber(numeroIdentidad)
				.orElseThrow(() -> new UsuarioNotFoundException(
						"No se encontro el usuario con el número de identidad: " + numeroIdentidad));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> obtenerUsuariosPaginados(int numeroPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina, Sort.by("surnames").ascending());
		return usuarioRepository.findAll(pageable);
	}

}
