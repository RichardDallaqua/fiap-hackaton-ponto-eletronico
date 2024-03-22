package com.fiap.ponto.commons.utils;

import com.fiap.ponto.commons.type.TipoRegistro;
import com.fiap.ponto.dataprovider.documents.PontoDocument;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CalculadoraHorasTrabalhadas {

    public static Duration calcularHorasTrabalhadas(List<PontoDocument> registros) {
        LocalDateTime entrada = null;
        LocalDateTime saidaAlmoco = null;
        LocalDateTime voltaAlmoco = null;
        LocalDateTime saida = null;

        Duration horasTrabalhadas = Duration.ZERO;

        for (PontoDocument ponto : registros) {
            switch (ponto.getTipoRegistro()) {
                case ENTRADA:
                    entrada = ponto.getDataHora();
                    break;
                case SAIDA_ALMOCO:
                    saidaAlmoco = ponto.getDataHora();
                    break;
                case VOLTA_ALMOCO:
                    voltaAlmoco = ponto.getDataHora();
                    break;
                case SAIDA:
                    saida = ponto.getDataHora();
                    break;
                default:
                    // Não faz nada para outros tipos de registro
                    break;
            }
        }

        if (entrada != null && saidaAlmoco != null && voltaAlmoco != null && saida != null) {
            Duration horasManha = Duration.between(entrada, saidaAlmoco);
            Duration horasTarde = Duration.between(voltaAlmoco, saida);
            horasTrabalhadas = horasManha.plus(horasTarde);
        }

        return horasTrabalhadas;
    }


}
