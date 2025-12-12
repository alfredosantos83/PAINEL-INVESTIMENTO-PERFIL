package com.caixa.invest.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulacoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Simulacao extends PanacheEntity {
    @Column(nullable = false)
    private Long clienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Product produto;

    @Column(nullable = false)
    private BigDecimal valorInvestido;

    @Column(nullable = false)
    private BigDecimal valorFinal;

    @Column(nullable = false)
    private Integer prazoMeses;

    @Column(nullable = false)
    private LocalDateTime dataSimulacao;

    public Long getId() {
        return this.id;
    }
}
