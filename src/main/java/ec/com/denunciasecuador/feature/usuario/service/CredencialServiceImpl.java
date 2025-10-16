package ec.com.denunciasecuador.feature.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.denunciasecuador.common.exception.entitynotfound.CredencialNotFoundException;
import ec.com.denunciasecuador.common.util.PasswordEncryptor;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialResponseDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.repository.CredencialRepository;
import ec.com.denunciasecuador.feature.usuario.service.contract.CredencialService;

@Service
public class CredencialServiceImpl implements CredencialService {

	private CredencialRepository credencialRepository;

	public CredencialServiceImpl(CredencialRepository credencialRepository) {
		this.credencialRepository = credencialRepository;
	}

	@Override
	@Transactional
	public Credencial guardarCredencial(CredencialRequestDTO credencialRequestDTO) {
		Credencial credencial = new Credencial();
		credencial.setUsername(credencialRequestDTO.username());
		// Encripta la contraseña y la inyecta en el objeto Credencial
		credencial.setPassword(PasswordEncryptor.encryptPassword(credencialRequestDTO.password()));
		return credencialRepository.save(credencial);
	}

	@Override
	@Transactional
	public void eliminarCredencialPorId(Long id) {
		credencialRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void eliminarCredencial(Credencial credencial) {
		credencialRepository.delete(credencial);
	}

	@Override
	@Transactional(readOnly = true)
	public Credencial buscarCredencialPorId(Long id) {
		return credencialRepository.findById(id).orElseThrow(
				() -> new CredencialNotFoundException("No se encontro la credencial el número de id: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public Credencial buscarCredencialPorUsername(String username) {
		return credencialRepository.findCredencialByUsername(username)
				.orElseThrow(() -> new CredencialNotFoundException(
						"No se encontro la credencial con el nombre de usuario: " + username));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Credencial> obtenerCredencialesPaginadas(int numeroPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina, Sort.by("username").ascending());
		return credencialRepository.findAll(pageable);
	}
	
	public CredencialResponseDTO crearCResponseDTO(Credencial credencial) {
		return new CredencialResponseDTO(
				credencial.getId(),
				credencial.getUsername());
	}

}