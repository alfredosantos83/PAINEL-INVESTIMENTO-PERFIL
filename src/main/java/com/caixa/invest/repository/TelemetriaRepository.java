package com.caixa.invest.repository;

import com.caixa.invest.domain.Telemetria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelemetriaRepository implements PanacheRepository<Telemetria> {
}
