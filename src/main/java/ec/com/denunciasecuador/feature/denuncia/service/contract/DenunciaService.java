package ec.com.denunciasecuador.feature.denuncia.service.contract;

import org.springframework.data.domain.Page;

import ec.com.denunciasecuador.feature.denuncia.dto.DenunciaRequestDTO;
import ec.com.denunciasecuador.feature.denuncia.model.Denuncia;

public interface DenunciaService {

	public Denuncia guardarDenuncia(DenunciaRequestDTO denunciaRequestDTO);

	public void eliminarDenuncia(Denuncia denuncia);

	public void eliminarDenunciaPorId(Long id);

	public Denuncia obtenerDenunciaPorId(Long id);

	public Page<Denuncia> obtenerDenunciasPorNumeroIdentidadUsuario(String numeroIdentidad, int pagina, int tamanioPagina);

	public Page<Denuncia> obtenerTodasLasDenuncias(int pagina, int tamanioPagina);

}
