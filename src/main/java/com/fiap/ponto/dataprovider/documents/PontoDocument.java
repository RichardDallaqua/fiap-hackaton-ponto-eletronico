package com.fiap.ponto.dataprovider.documents;

import java.time.LocalDateTime;

import com.fiap.ponto.commons.type.TipoRegistro;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "ponto")
public class PontoDocument {

    private String nomeFuncionario;
    private String cpf;
    private String email;
    private LocalDateTime dataHora;
    private TipoRegistro tipoRegistro;


}
