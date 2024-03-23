package com.fiap.ponto.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fiap.ponto.commons.type.TipoRegistro;

public class PontoDomain {

    private String nomeFuncionario;
    private CPFDomain cpf;
    private String email;
    private LocalDateTime dataHoraNow;
    private TipoRegistro tipoRegistro;

    public PontoDomain(String nomeFuncionario, String cpf, String email, TipoRegistro tipoRegistro) {
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = new CPFDomain(cpf);
        this.email = email;
        this.dataHoraNow = setDataHoraNow();
        this.tipoRegistro = tipoRegistro;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataHoraNow() {
        return dataHoraNow;
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    private LocalDateTime setDataHoraNow() {
        return LocalDateTime.now();
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public CPFDomain getCpf() {
        return cpf;
    }

    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
