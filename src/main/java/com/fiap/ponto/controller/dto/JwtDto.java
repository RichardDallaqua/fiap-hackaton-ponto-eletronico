package com.fiap.ponto.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class JwtDto {
    @NonNull
    String cpf;
    @NonNull
    String email;
    @NonNull
    String telefone;
    @NonNull
    String nome;
}
