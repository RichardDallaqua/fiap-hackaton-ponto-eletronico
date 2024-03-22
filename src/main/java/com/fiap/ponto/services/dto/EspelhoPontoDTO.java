package com.fiap.ponto.services.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EspelhoPontoDTO {

    List<PontoDTO> pontos;
    long totalHoras;
    long totalMinutos;

}
