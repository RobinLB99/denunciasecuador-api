package ec.com.denunciasecuador.feature.usuario.service;

import ec.com.denunciasecuador.common.exception.entitynotfound.UsuarioNotFoundException;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.UsuarioResponseDTO;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import ec.com.denunciasecuador.feature.usuario.repository.UsuarioRepository;
import ec.com.denunciasecuador.feature.usuario.service.contract.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import ec.com.denunciasecuador.feature.usuario.repository.CredencialRepository;

/**
 * Implementación de la interfaz {@link UsuarioService}.
 * <p>
 * Esta clase proporciona la lógica de negocio para la gestión de usuarios,
 * interactuando con {@link UsuarioRepository} para el acceso a datos.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     *
     * @param usuarioRepository El repositorio de usuarios a inyectar.
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Mapea un {@link UsuarioRequestDTO} a una entidad {@link Usuario}.
     * <p>
     * Este método toma un DTO de solicitud de usuario y lo convierte en un objeto
     * {@code Usuario}, transfiriendo los datos relevantes.
     * </p>
     *
     * @param usuarioDTO El DTO de solicitud de usuario.
     * @return Una entidad {@link Usuario} con los datos del DTO.
     */
    public Usuario mapperDTOUsuario(UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setFirstName(usuarioDTO.firstName());
        usuario.setMiddleName(usuarioDTO.middleName());
        usuario.setSurnames(usuarioDTO.surnames());
        usuario.setIdentityNumber(usuarioDTO.identityNumber());
        return usuario;
    }

    /**
     * Crea un DTO de respuesta de usuario a partir de un objeto Usuario.
     * <p>
     * Este método toma un objeto {@link Usuario} y lo convierte en un
     * {@link UsuarioResponseDTO}, extrayendo los campos necesarios para la respuesta.
     * </p>
     *
     * @param usuario El objeto {@link Usuario} a convertir.
     * @return Un {@link UsuarioResponseDTO} con los datos del usuario.
     */
    public UsuarioResponseDTO crearUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getFirstName(),
            usuario.getSurnames(),
            usuario.getCredential().getUsername()
        );
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository
            .findById(id)
            .orElseThrow(() ->
                new UsuarioNotFoundException(
                    "No se encontro el usuario con el id: " + id
                )
            );
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorNumeroIdentidad(String numeroIdentidad) {
        return usuarioRepository
            .findUsuarioByIdentityNumber(numeroIdentidad)
            .orElseThrow(() ->
                new UsuarioNotFoundException(
                    "No se encontro el usuario con el número de identidad: " +
                        numeroIdentidad
                )
            );
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional(readOnly = true)
    public Page<Usuario> obtenerUsuariosPaginados(
        int numeroPagina,
        int tamanioPagina
    ) {
        Pageable pageable = PageRequest.of(
            numeroPagina,
            tamanioPagina,
            Sort.by("surnames").ascending()
        );
        return usuarioRepository.findAll(pageable);
    }
}
