package ec.com.denunciasecuador.feature.denuncia.dto;

import java.time.LocalDateTime;

public record DenunciaResponseDTO(
	Long report_id,
	String title,
	String description,
	LocalDateTime eventTimestamp,
	String cityProvince,
	boolean isPrivate,
	String reportType,
	String user_name
) {}
