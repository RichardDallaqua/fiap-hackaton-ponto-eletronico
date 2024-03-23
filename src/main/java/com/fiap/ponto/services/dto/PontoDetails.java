package com.fiap.ponto.services.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class PontoDetails {

    private String nomeFuncionario;
    private String cpf;
    private String dataHora;
    private String tipoRegistro;

}
