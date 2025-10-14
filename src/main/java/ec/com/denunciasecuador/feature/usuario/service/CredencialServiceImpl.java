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
	public Credencial guardarCredencial(CredencialRequestDTO credencialRequestDTO) {
		Credencial credencial = new Credencial();
		credencial.setUsername(credencialRequestDTO.getUsername());
		// Encripta la contraseña y la inyecta en el objeto Credencial
		credencial.setPassword(PasswordEncryptor.encryptPassword(credencialRequestDTO.getPassword()));
		return credencialRepository.save(credencial);
	}

	@Override
	public void eliminarCredencialPorId(Long id) {
		credencialRepository.deleteById(id);
	}

	@Override
	public void eliminarCredencial(Credencial credencial) {
		credencialRepository.delete(credencial);
	}

	@Transactional(readOnly = true)
	@Override
	public Credencial buscarCredencialPorId(Long id) {
		return credencialRepository.findById(id).orElseThrow(
				() -> new CredencialNotFoundException("No se encontro la credencial el número de id: " + id));
	}

	@Transactional(readOnly = true)
	@Override
	public Credencial buscarCredencialPorUsername(String username) {
		return credencialRepository.findCredencialByUsername(username)
				.orElseThrow(() -> new CredencialNotFoundException(
						"No se encontro la credencial con el nombre de usuario: " + username));
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Credencial> obtenerCredencialesPaginadas(int numeroPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina, Sort.by("username").ascending());
		return credencialRepository.findAll(pageable);
	}

}