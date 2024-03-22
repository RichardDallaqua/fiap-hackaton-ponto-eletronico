package com.fiap.ponto.commons.utils;

import com.fiap.ponto.commons.type.TipoRegistro;
import com.fiap.ponto.dataprovider.documents.PontoDocument;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculadoraHorasTrabalhadas {

    private static final Map<TipoRegistro, TipoRegistro> PAR_TIPO_REGISTRO = new HashMap<>();

    static {
        PAR_TIPO_REGISTRO.put(TipoRegistro.ENTRADA, TipoRegistro.SAIDA);
        PAR_TIPO_REGISTRO.put(TipoRegistro.SAIDA_ALMOCO, TipoRegistro.VOLTA_ALMOCO);
        // Adicione outros pares de tipos de registro conforme necessário
    }

    public static Duration calcularHorasTrabalhadas(List<PontoDocument> registros) {
        Duration totalHorasTrabalhadas = Duration.ZERO;
        Map<TipoRegistro, PontoDocument> registrosPendentes = new HashMap<>();

        for (PontoDocument ponto : registros) {
            TipoRegistro tipoRegistro = ponto.getTipoRegistro();
            if (PAR_TIPO_REGISTRO.containsKey(tipoRegistro)) {
                TipoRegistro tipoCorrespondente = PAR_TIPO_REGISTRO.get(tipoRegistro);
                PontoDocument pontoCorrespondente = registrosPendentes.get(tipoCorrespondente);
                if (pontoCorrespondente != null) {
                    Duration diferenca = Duration.between(pontoCorrespondente.getDataHora(), ponto.getDataHora());
                    totalHorasTrabalhadas = totalHorasTrabalhadas.plus(diferenca);
                    registrosPendentes.remove(tipoCorrespondente);
                } else {
                    registrosPendentes.put(tipoRegistro, ponto);
                }
            } else {
                registrosPendentes.put(tipoRegistro, ponto);
            }
        }

        return totalHorasTrabalhadas;
    }
}
