package ec.com.denunciasecuador.feature.usuario.service;

import ec.com.denunciasecuador.common.exception.entitynotfound.CredencialNotFoundException;
import ec.com.denunciasecuador.common.util.PasswordEncryptor;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialResponseDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.repository.CredencialRepository;
import ec.com.denunciasecuador.feature.usuario.service.contract.CredencialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de la interfaz {@link CredencialService}.
 * <p>
 * Esta clase proporciona la lógica de negocio para la gestión de credenciales,
 * interactuando con {@link CredencialRepository} para el acceso a datos y
 * utilizando {@link PasswordEncryptor} para el cifrado de contraseñas.
 * </p>
 *
 * @author Robin Lugo
 * @version 1.0
 */
@Service
public class CredencialServiceImpl implements CredencialService {

    private CredencialRepository credencialRepository;

    /**
     * Constructor que inyecta el repositorio de credenciales.
     *
     * @param credencialRepository El repositorio de credenciales a inyectar.
     */
    public CredencialServiceImpl(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional
    public Credencial guardarCredencial(
        CredencialRequestDTO credencialRequestDTO
    ) {
        Credencial credencial = new Credencial();
        credencial.setUsername(credencialRequestDTO.username());
        // Encripta la contraseña y la inyecta en el objeto Credencial
        credencial.setPassword(
            PasswordEncryptor.encryptPassword(credencialRequestDTO.password())
        );
        return credencialRepository.save(credencial);
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional
    public void eliminarCredencialPorId(Long id) {
        credencialRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional
    public void eliminarCredencial(Credencial credencial) {
        credencialRepository.delete(credencial);
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional(readOnly = true)
    public Credencial buscarCredencialPorId(Long id) {
        return credencialRepository
            .findById(id)
            .orElseThrow(() ->
                new CredencialNotFoundException(
                    "No se encontro la credencial el número de id: " + id
                )
            );
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional(readOnly = true)
    public Credencial buscarCredencialPorUsername(String username) {
        return credencialRepository
            .findCredencialByUsername(username)
            .orElseThrow(() ->
                new CredencialNotFoundException(
                    "No se encontro la credencial con el nombre de usuario: " +
                        username
                )
            );
    }

    /**
     * {@inheritDoc}
     */ @Override
    @Transactional(readOnly = true)
    public Page<Credencial> obtenerCredencialesPaginadas(
        int numeroPagina,
        int tamanioPagina
    ) {
        Pageable pageable = PageRequest.of(
            numeroPagina,
            tamanioPagina,
            Sort.by("username").ascending()
        );
        return credencialRepository.findAll(pageable);
    }

    /**
     * Crea un DTO de respuesta de credencial a partir de un objeto Credencial.
     *
     * @param credencial El objeto {@link Credencial} a convertir.
     * @return Un {@link CredencialResponseDTO} con los datos de la credencial.
     */
    public CredencialResponseDTO crearCResponseDTO(Credencial credencial) {
        return new CredencialResponseDTO(
            credencial.getId(),
            credencial.getUsername()
        );
    }
}