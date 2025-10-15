package ec.com.denunciasecuador.feature.usuario.service.contract;

import org.springframework.data.domain.Page;

import ec.com.denunciasecuador.feature.usuario.model.Usuario;

public interface UsuarioService {

	public Usuario guardarUsuario(Usuario usuario);

	public void eliminarUsuarioPorId(Long id);

	public void eliminarUsuario(Usuario usuario);

	public Usuario buscarUsuarioPorId(Long id);

	public Usuario buscarUsuarioPorNumeroIdentidad(String numeroIdentidad);

	public Page<Usuario> obtenerUsuariosPaginados(int numeroPagina, int tamanioPagina);

}
