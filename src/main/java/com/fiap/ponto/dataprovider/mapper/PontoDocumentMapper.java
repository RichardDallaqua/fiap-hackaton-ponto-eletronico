package com.fiap.ponto.dataprovider.mapper;

import com.fiap.ponto.dataprovider.documents.PontoDocument;
import com.fiap.ponto.domain.PontoDomain;
import com.fiap.ponto.services.dto.EspelhoPontoDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PontoDocumentMapper {

    public PontoDocument toDocument(PontoDomain domain){
        return PontoDocument.builder()
                .nomeFuncionario(domain.getNomeFuncionario())
                .cpf(domain.getCpf().getNumero())
                .email(domain.getEmail())
                .dataHora(domain.getDataHoraNow().minusHours(3l))
                .tipoRegistro(domain.getTipoRegistro())
                .build();
    }
}
