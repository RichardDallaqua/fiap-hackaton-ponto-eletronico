package com.fiap.ponto.services;

import com.fiap.ponto.commons.type.TipoRegistro;
import com.fiap.ponto.domain.PontoDomain;
import com.fiap.ponto.services.dto.EspelhoPontoDTO;
import com.fiap.ponto.services.dto.PontoDTO;
import com.fiap.ponto.services.gateways.PontoGateway;
import com.fiap.ponto.services.mapper.PontoDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.ponto.commons.utils.CalculadoraHorasTrabalhadas.calcularHorasTrabalhadas;

@Slf4j
@Service
public class PontoService {

    @Autowired
    private PontoGateway pontoGateway;

    @Autowired
    private PontoDTOMapper mapper;

    public PontoDTO registrarPonto(String cpf, String nomeFuncionario, String email, TipoRegistro tipoRegistro) {
        log.info("<<< Registrando ponto de funcionario >>>");
        PontoDomain pontoDomain = new PontoDomain(nomeFuncionario, cpf, email, tipoRegistro);
        var ponto = pontoGateway.save(pontoDomain);
        log.info("<<< Ponto registrado com sucesso >>>");
        return mapper.toDto(ponto);
    }

    public List<PontoDTO> obterPonto(String cpf){
        return mapper.toDtoList(pontoGateway.filterPonto(cpf));
    }

    public EspelhoPontoDTO obterEspelhoDePonto(String cpf, Long mes){
        log.info("<<< Gerando espelho de ponto de funcionario >>>");
        var listaPonto = pontoGateway.filterEspelhoDePonto(cpf, mes);
        var duration = calcularHorasTrabalhadas(listaPonto);
        return PontoDTOMapper.espelhoPontoDTO(mapper.toDtoList(listaPonto),
                duration.toHours(), duration.toMinutes());
    }
}
