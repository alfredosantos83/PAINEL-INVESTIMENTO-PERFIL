package com.caixa.invest.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetria")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Telemetria extends PanacheEntity {
    @Column(nullable = false)
    private String servico;

    @Column(nullable = false)
    private Integer quantidadeChamadas;

    @Column(nullable = false)
    private Long somaTempoRespostaMs;

    @Column(nullable = false)
    private LocalDateTime periodoInicio;

    @Column(nullable = false)
    private LocalDateTime periodoFim;

    public double getMediaTempoRespostaMs() {
        return quantidadeChamadas == 0 ? 0 : (double) somaTempoRespostaMs / quantidadeChamadas;
    }
}
