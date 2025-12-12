package com.caixa.invest;

import com.caixa.invest.domain.Product;
import com.caixa.invest.domain.Product.NivelRisco;
import com.caixa.invest.domain.Product.TipoProduto;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;

@Singleton
public class DataLoader {
    @Transactional
    void onStart(@Observes StartupEvent ev) {
        if (Product.count() == 0) {
            Product cdb = Product.builder()
                .nome("CDB Caixa 2026")
                .tipo(TipoProduto.CDB)
                .rentabilidade(new BigDecimal("0.12"))
                .risco(NivelRisco.BAIXO)
                .prazoMinimoMeses(6)
                .prazoMaximoMeses(36)
                .valorMinimo(new BigDecimal("1000.00"))
                .valorMaximo(new BigDecimal("50000.00"))
                .ativo(true)
                .liquidezDias(30)
                .descricao("CDB Caixa 2026 - Renda Fixa")
                .build();
            cdb.persist();

            Product fundo = Product.builder()
                .nome("Fundo XPTO")
                .tipo(TipoProduto.FUNDO)
                .rentabilidade(new BigDecimal("0.18"))
                .risco(NivelRisco.ALTO)
                .prazoMinimoMeses(3)
                .prazoMaximoMeses(24)
                .valorMinimo(new BigDecimal("500.00"))
                .valorMaximo(new BigDecimal("20000.00"))
                .ativo(true)
                .liquidezDias(15)
                .descricao("Fundo XPTO - Multimercado")
                .build();
            fundo.persist();
        }
    }
}
