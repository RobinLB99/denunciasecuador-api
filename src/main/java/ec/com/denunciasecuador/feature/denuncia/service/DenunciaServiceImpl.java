package ec.com.denunciasecuador.feature.denuncia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.denunciasecuador.common.exception.entitynotfound.DenunciaNotFoundException;
import ec.com.denunciasecuador.common.exception.entitynotfound.UsuarioNotFoundException;
import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaRequestDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;
import ec.com.denunciasecuador.feature.denuncia.repository.DenunciaRepository;
import ec.com.denunciasecuador.feature.denuncia.service.contract.DenunciaService;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import ec.com.denunciasecuador.feature.usuario.repository.UsuarioRepository;

@Service
public class DenunciaServiceImpl implements DenunciaService {

	private DenunciaRepository denunciaRepository;
	private UsuarioRepository usuarioRepository;

	public DenunciaServiceImpl(DenunciaRepository denunciaRepository, UsuarioRepository usuarioRepository) {
		this.denunciaRepository = denunciaRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional
	public Denuncia guardarDenuncia(DenunciaRequestDTO denunciaRequestDTO) {
		Usuario usuario = usuarioRepository.findById(denunciaRequestDTO.getUsuario_id())
				.orElseThrow(() -> new UsuarioNotFoundException("No se encontro el usuario con el ID '"
						+ denunciaRequestDTO.getUsuario_id() + "', por lo tanto, la denuncia no se guarda."));
		return denunciaRepository.save(crearObjetoDenuncia(denunciaRequestDTO, usuario));
	}

	@Override
	@Transactional
	public void eliminarDenuncia(Denuncia denuncia) {
		denunciaRepository.delete(denuncia);
	}

	@Override
	@Transactional
	public void eliminarDenunciaPorId(Long id) {
		if (!denunciaRepository.existsById(id))
			throw new DenunciaNotFoundException("La denuncia con ID " + id + " no existe.");
		denunciaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Denuncia obtenerDenunciaPorId(Long id) {
		return denunciaRepository.findById(id)
				.orElseThrow(() -> new DenunciaNotFoundException("No se encontro la denuncia con el ID: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Denuncia> obtenerDenunciasPorNumeroIdentidadUsuario(String numeroIdentidad, int pagina,
			int tamanioPagina) {
		Pageable pageable = PageRequest.of(pagina, tamanioPagina, Sort.by("title"));
		return denunciaRepository.findDenunciasByIdentityNumberUsuario(numeroIdentidad, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Denuncia> obtenerTodasLasDenuncias(int pagina, int tamanioPagina) {
		Pageable pageable = PageRequest.of(pagina, tamanioPagina, Sort.by("title"));
		return denunciaRepository.findAll(pageable);
	}

	private final Denuncia crearObjetoDenuncia(DenunciaRequestDTO denunciaRequestDTO, Usuario usuario) {
		Denuncia denuncia = new Denuncia();
		denuncia.setTitle(denunciaRequestDTO.getTitle());
		denuncia.setDescription(denunciaRequestDTO.getDescription());
		denuncia.setReportType(denunciaRequestDTO.getReportType());
		denuncia.setEventTimestamp(denunciaRequestDTO.getEventTimestamp());
		denuncia.setCityProvince(denunciaRequestDTO.getCityProvince());
		denuncia.setPrivate(denunciaRequestDTO.isPrivate());
		denuncia.setUsuario(usuario);
		return denuncia;
	}

}
