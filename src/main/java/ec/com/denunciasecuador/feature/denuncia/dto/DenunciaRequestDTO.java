package ec.com.denunciasecuador.feature.denuncia.dto;

import java.time.LocalDateTime;
import ec.com.denunciasecuador.feature.denuncia.model.constant.TipoDenuncia;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DenunciaRequestDTO(
	
	@Nonnull
	@NotBlank(message = "{TITLE.NOTBLANK}")
	@Size(min = 5, message = "{TITLE.SIZE}")
	String title,
	
	@Nonnull
	@NotNull(message = "{DESCRIPTION.NOTNULL}")
	String description,
	
	@Nonnull
	@NotNull(message = "{EVENTTIMESTAMP.NOTNULL}")
	LocalDateTime eventTimestamp,
	
	@Nonnull
	@NotBlank(message = "{CITYPROVINCE.NOTBLANK}")
	String cityProvince,
	
	@Nonnull
	@NotNull(message = "{ISPRIVATE.NOTNUL}")
	boolean isPrivate,
	
	@Nonnull
	@NotNull(message = "{REPORTTYPE.NOTNULL}")
	@Enumerated(EnumType.STRING)
	TipoDenuncia reportType,
	
	@Nonnull
	@NotNull(message = "{USERID.NOTNULL}")
	Long usuario_id
) {}
