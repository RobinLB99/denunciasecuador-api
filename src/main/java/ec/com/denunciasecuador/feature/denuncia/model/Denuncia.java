package ec.com.denunciasecuador.feature.denuncia.model;

import ec.com.denunciasecuador.feature.denuncia.model.constant.TipoDenuncia;
import ec.com.denunciasecuador.feature.usuario.model.Usuario;
import jakarta.annotation.Nonnull;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Denuncias")
public class Denuncia implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Nonnull
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Nonnull
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Nonnull
    @Column(name = "event_timestamp", nullable = false)
    private LocalDateTime eventTimestamp;

    @Nonnull
    @Column(name = "city_province", nullable = false, columnDefinition = "TEXT")
    private String cityProvince;

    @Nonnull
    @Column(name = "is_private", nullable = false)
    private boolean isPrivate;

    @Nonnull
    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false, length = 20)
    private TipoDenuncia reportType;

    @Nonnull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
}
