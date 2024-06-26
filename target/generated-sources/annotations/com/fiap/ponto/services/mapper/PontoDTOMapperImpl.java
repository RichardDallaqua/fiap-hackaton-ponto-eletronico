package com.fiap.ponto.services.mapper;

import com.fiap.ponto.dataprovider.documents.PontoDocument;
import com.fiap.ponto.services.dto.PontoDTO;
import com.fiap.ponto.services.dto.PontoDTO.PontoDTOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-23T16:56:36-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Microsoft)"
)
@Component
public class PontoDTOMapperImpl implements PontoDTOMapper {

    @Override
    public PontoDTO toDto(PontoDocument document) {
        if ( document == null ) {
            return null;
        }

        PontoDTOBuilder pontoDTO = PontoDTO.builder();

        pontoDTO.nomeFuncionario( document.getNomeFuncionario() );
        pontoDTO.cpf( document.getCpf() );
        pontoDTO.email( document.getEmail() );
        pontoDTO.dataHora( document.getDataHora() );
        pontoDTO.tipoRegistro( document.getTipoRegistro() );

        return pontoDTO.build();
    }

    @Override
    public List<PontoDTO> toDtoList(List<PontoDocument> documents) {
        if ( documents == null ) {
            return null;
        }

        List<PontoDTO> list = new ArrayList<PontoDTO>( documents.size() );
        for ( PontoDocument pontoDocument : documents ) {
            list.add( toDto( pontoDocument ) );
        }

        return list;
    }
}
