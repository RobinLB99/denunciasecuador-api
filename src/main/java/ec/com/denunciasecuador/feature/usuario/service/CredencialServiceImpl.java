package ec.com.denunciasecuador.feature.usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ec.com.denunciasecuador.common.exception.entitynotfound.CredencialNotFoundException;
import ec.com.denunciasecuador.common.util.PasswordEncryptor;
import ec.com.denunciasecuador.feature.usuario.dto.CredencialRequestDTO;
import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.repository.CredencialRepository;
import ec.com.denunciasecuador.feature.usuario.service.contract.CredencialService;

@Service
public class CredencialServiceImpl implements CredencialService {

	private CredencialRepository credencialRepository;

	private CredencialServiceImpl(CredencialRepository credencialRepository) {
		this.credencialRepository = credencialRepository;
	}

	@Override
	public Credencial guardarCredencial(CredencialRequestDTO credencialRequestDTO) {
		Credencial credencial = new Credencial();
		credencial.setUsername(credencialRequestDTO.getUsername());
		// Encripta la contraseña y la inyecta en el objeto Credencial
		credencial.setPassword(PasswordEncryptor.encryptPassword(credencialRequestDTO.getPasswordString()));
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

	@Override
	public Credencial buscarCredencialPorId(Long id) {
		return credencialRepository.findById(id).orElseThrow(
				() -> new CredencialNotFoundException("No se encontro la credencial el número de id: " + id));
	}

	@Override
	public Credencial buscarCredencialPorUsername(String username) {
		return credencialRepository.findCredencialByUsername(username)
				.orElseThrow(() -> new CredencialNotFoundException(
						"No se encontro la credencial con el nombre de usuario: " + username));
	}

	@Override
	public Page<Credencial> obtenerCredencialesPaginadas(int numeroPagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(numeroPagina, tamanioPagina, Sort.by("username").ascending());
		return credencialRepository.findAll(pageable);
	}

}
