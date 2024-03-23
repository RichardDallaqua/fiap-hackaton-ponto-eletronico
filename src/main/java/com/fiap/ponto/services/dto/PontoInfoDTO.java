package com.fiap.ponto.services.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PontoInfoDTO {

    private List<PontoDetails> pontos;
    private Long totalHoras;
    private Long totalMinutos;
}
