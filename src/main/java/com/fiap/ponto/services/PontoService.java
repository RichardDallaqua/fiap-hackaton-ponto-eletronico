package com.fiap.ponto.services;

import com.fiap.ponto.domain.PontoDomain;
import com.fiap.ponto.services.dto.EspelhoPontoDTO;
import com.fiap.ponto.services.dto.PontoDTO;
import com.fiap.ponto.services.gateways.PontoGateway;
import com.fiap.ponto.services.mapper.PontoDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.ponto.commons.utils.CalculadoraHorasTrabalhadas.calcularHorasTrabalhadas;


@Service
public class PontoService {

    @Autowired
    private PontoGateway pontoGateway;

    @Autowired
    private PontoDTOMapper mapper;

    public PontoDTO registrarPonto(String cpf, String nomeFuncionario, String email) {
        PontoDomain pontoDomain = new PontoDomain(nomeFuncionario, cpf, email);
        var ponto = pontoGateway.save(pontoDomain);
        return mapper.toDto(ponto);
    }

    public List<PontoDTO> obterPonto(String cpf){
        return mapper.toDtoList(pontoGateway.filterPonto(cpf));
    }

    public EspelhoPontoDTO obterEspelhoDePonto(String cpf, Long mes){
        var listaPonto = pontoGateway.filterEspelhoDePonto(cpf, mes);
        var duration = calcularHorasTrabalhadas(listaPonto);
        return PontoDTOMapper.espelhoPontoDTO(mapper.toDtoList(listaPonto),
                duration.toHours(), duration.toMinutes());
    }
}
