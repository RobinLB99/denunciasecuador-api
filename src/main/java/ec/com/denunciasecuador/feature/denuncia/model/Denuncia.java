package ec.com.denunciasecuador.feature.denuncia.model;

import ec.com.denunciasecuador.feature.denuncia.model.constant.TipoDenuncia;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Denuncias")
public class Denuncia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private Long id;

	@Column(name = "title", nullable = false, length = 255)
	private String title;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "event_timestamp", nullable = false)
	private LocalDateTime eventTimestamp;

	@Column(name = "city_province", nullable = false, columnDefinition = "TEXT")
	private String cityProvince;

	@Column(name = "is_private", nullable = false)
	private boolean isPrivate;

	@Enumerated(EnumType.STRING)
	@Column(name = "report_type", nullable = false, length = 20)
	private TipoDenuncia reportType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public Denuncia(String title, String description, LocalDateTime eventTimestamp, String cityProvince,
			boolean isPrivate, TipoDenuncia reportType, Usuario usuario) {
		super();
		this.title = title;
		this.description = description;
		this.eventTimestamp = eventTimestamp;
		this.cityProvince = cityProvince;
		this.isPrivate = isPrivate;
		this.reportType = reportType;
		this.usuario = usuario;
	}

	public Denuncia() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(LocalDateTime eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public String getCityProvince() {
		return cityProvince;
	}

	public void setCityProvince(String cityProvince) {
		this.cityProvince = cityProvince;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public TipoDenuncia getReportType() {
		return reportType;
	}

	public void setReportType(TipoDenuncia reportType) {
		this.reportType = reportType;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Denuncia other = (Denuncia) obj;
		return Objects.equals(id, other.id);
	}

}
