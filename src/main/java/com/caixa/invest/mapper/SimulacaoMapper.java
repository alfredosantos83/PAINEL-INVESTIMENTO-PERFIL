package com.caixa.invest.mapper;

import com.caixa.invest.domain.Simulacao;
import java.util.HashMap;
import java.util.Map;

public class SimulacaoMapper {
    public static Map<String, Object> toEnvelope(Simulacao s) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", s.getId());
        map.put("clienteId", s.getClienteId());
        map.put("produto", s.getProduto().getNome());
        map.put("valorInvestido", s.getValorInvestido());
        map.put("valorFinal", s.getValorFinal());
        map.put("prazoMeses", s.getPrazoMeses());
        map.put("dataSimulacao", s.getDataSimulacao().toString());
        return map;
    }
}
