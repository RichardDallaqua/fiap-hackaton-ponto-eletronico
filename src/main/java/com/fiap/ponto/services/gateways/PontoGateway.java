package com.fiap.ponto.services.gateways;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.fiap.ponto.commons.type.TipoRegistro;
import com.fiap.ponto.dataprovider.documents.PontoDocument;
import com.fiap.ponto.domain.PontoDomain;

public interface PontoGateway {
    PontoDocument save(PontoDomain pontoDomain);

    List<PontoDocument> filterPonto(String cpf);

    List<PontoDocument> filterEspelhoDePonto(String cpf, Long mes);
}
