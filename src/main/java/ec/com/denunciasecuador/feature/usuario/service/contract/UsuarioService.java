package ec.com.denunciasecuador.feature.usuario.service.contract;

import org.springframework.data.domain.Page;

import ec.com.denunciasecuador.feature.usuario.dto.UsuarioRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioResponseDTO;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;

public interface UsuarioService {

	public Usuario guardarUsuario(UsuarioRequestDTO usuarioRequestDTO);

	public void eliminarUsuarioPorId(Long id);

	public void eliminarUsuario(Usuario usuario);

	public UsuarioResponseDTO buscarUsuarioPorId(Long id);

	public UsuarioResponseDTO buscarUsuarioPorNumeroIdentidad(String numeroIdentidad);

	public Page<Usuario> obtenerUsuariosPaginados(int numeroPagina, int tamanioPagina);

}
