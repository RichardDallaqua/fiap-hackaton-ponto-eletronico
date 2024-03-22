package com.fiap.ponto.services.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.ponto.commons.type.TipoRegistro;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PontoDTO {

    private String nomeFuncionario;
    private String cpf;
    private String email;
    private LocalDateTime dataHora;
    private TipoRegistro tipoRegistro;

    public String getEmail() {
        return email;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDateTime getDataHora() {
        return dataHora.plusHours(3l);
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

}
