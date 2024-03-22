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

    public PontoDomain(String nomeFuncionario, String cpf, String email) {
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = new CPFDomain(cpf);
        this.email = email;
        this.dataHoraNow = setDataHoraNow();
        this.tipoRegistro = setTipoRegistro();
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

    private TipoRegistro setTipoRegistro(){

        if(this.dataHoraNow.toLocalTime().isBefore(LocalTime.of(12, 00))){
            tipoRegistro = TipoRegistro.ENTRADA;
        }

        if(this.dataHoraNow.toLocalTime().isBefore(LocalTime.of(13, 0))
                && this.dataHoraNow.toLocalTime().isAfter(LocalTime.of(11, 59))){
            tipoRegistro = TipoRegistro.SAIDA_ALMOCO;
        }

        if(this.dataHoraNow.toLocalTime().isBefore(LocalTime.of(14, 0))
                && this.dataHoraNow.toLocalTime().isAfter(LocalTime.of(11, 59))){
            tipoRegistro = TipoRegistro.VOLTA_ALMOCO;
        }

        if(this.dataHoraNow.toLocalTime().isAfter(LocalTime.of(14, 0))){
            tipoRegistro = TipoRegistro.SAIDA;
        }

        if(this.dataHoraNow.toLocalTime().isBefore(LocalTime.of(8, 0))
                || this.dataHoraNow.toLocalTime().isAfter(LocalTime.of(17, 00))){
            tipoRegistro = TipoRegistro.HORA_EXTRA;
        }

        return this.tipoRegistro;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public CPFDomain getCpf() {
        return cpf;
    }

}
