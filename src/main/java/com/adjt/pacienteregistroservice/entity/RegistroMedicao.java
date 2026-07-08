package com.adjt.pacienteregistroservice.entity;

import com.adjt.pacienteregistroservice.dto.generated.model.RegistroRequest.OrigemRegistroEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "registro_medicao")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistroMedicao {

    @Id
    private UUID id;

    @Column(name = "cpf_paciente", nullable = false)
    private String cpfPaciente;

    @Column(name = "id_evento", nullable = false)
    private UUID idEvento;

    @Column(name = "valor_medicao", nullable = false)
    private Float valorMedicao;

    @Column(name = "data_hora", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime dataHora;

    @Column(name = "origem_registro", nullable = false)
    private OrigemRegistroEnum origemRegistro; // todo: ajustar para persistir valor string e não posição

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RegistroMedicao that = (RegistroMedicao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}