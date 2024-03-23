package com.fiap.ponto.services.gateways;

import com.fiap.ponto.dataprovider.documents.PontoDocument;

import java.time.Duration;
import java.util.List;

public interface RelatorioGateway {

    void gerarRelatorioEnviaEmail(List<PontoDocument> listaPonto, Duration horasTrabalhadas, String email);
}
