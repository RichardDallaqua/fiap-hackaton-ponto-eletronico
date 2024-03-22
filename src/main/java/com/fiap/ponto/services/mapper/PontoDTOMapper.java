package com.fiap.ponto.services.mapper;

import com.fiap.ponto.dataprovider.documents.PontoDocument;
import com.fiap.ponto.services.dto.EspelhoPontoDTO;
import com.fiap.ponto.services.dto.PontoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PontoDTOMapper {

    PontoDTO toDto(PontoDocument document);

    List<PontoDTO> toDtoList(List<PontoDocument> documents);

    static EspelhoPontoDTO espelhoPontoDTO(List<PontoDTO> pontos, long totalHoras, long totalMinutos){
        return EspelhoPontoDTO.builder()
                .pontos(pontos)
                .totalHoras(totalHoras)
                .totalMinutos(totalMinutos)
                .build();
    }
}
