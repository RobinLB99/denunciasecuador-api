package ec.com.denunciasecuador.feature.denuncia.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaRequestDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;
import ec.com.denunciasecuador.feature.denuncia.repository.DenunciaRepository;
import ec.com.denunciasecuador.feature.denuncia.service.contract.DenunciaService;

@Service
public class DenunciaServiceImpl implements DenunciaService {

	private DenunciaRepository denunciaRepository;

	public DenunciaServiceImpl(DenunciaServiceImpl denunciaServiceImpl) {
		this.denunciaRepository = denunciaRepository;
	}

	@Override
	public Denuncia guardarDenuncia(DenunciaRequestDTO denunciaRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarDenuncia(Denuncia denuncia) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarDenunciaPorId(Long id) {
		// TODO Auto-generated method stub

	}

	@Transactional(readOnly = true)
	@Override
	public Denuncia obtenerDenunciaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Denuncia> obtenerDenunciasPorNumeroIdentidadUsuario(String numeroIdentidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Denuncia> obtenerTodasLasDenuncias() {
		// TODO Auto-generated method stub
		return null;
	}

}
