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
import ec.com.denunciasecuador.common.exception.entitynotfound.CredencialNotFoundException;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.repository.CredencialRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final CredencialRepository credencialRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, CredencialRepository credencialRepository) {
		this.usuarioRepository = usuarioRepository;
		this.credencialRepository = credencialRepository;
	}

	@Override
	public Usuario guardarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
		Credencial credencial = credencialRepository.findById(usuarioRequestDTO.getIdCredencial())
				.orElseThrow(() -> new CredencialNotFoundException(
						"No se encontró la credencial con el id: " + usuarioRequestDTO.getIdCredencial()));

		Usuario usuario = new Usuario();
		usuario.setFirstName(usuarioRequestDTO.getFirstName());
		usuario.setMiddleName(usuarioRequestDTO.getMiddleName());
		usuario.setSurnames(usuarioRequestDTO.getSurnames());
		usuario.setIdentityNumber(usuarioRequestDTO.getIdentityNumber());
		usuario.setCredential(credencial);

		return usuarioRepository.save(usuario);
	}

	@Override
	public void eliminarUsuarioPorId(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el id: " + id));
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario buscarUsuarioPorNumeroIdentidad(String numeroIdentidad) {
		return usuarioRepository.findUsuarioByIdentityNumber(numeroIdentidad)
				.orElseThrow(() -> new UsuarioNotFoundException(
						"No se encontro el usuario con el número de identidad: " + numeroIdentidad));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Usuario> obtenerUsuariosPaginados(int numeroPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina, Sort.by("surnames").ascending());
		return usuarioRepository.findAll(pageable);
	}

}
