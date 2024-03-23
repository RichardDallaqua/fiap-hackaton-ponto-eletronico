package com.fiap.ponto.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fiap.ponto.commons.type.TipoRegistro;
import com.fiap.ponto.commons.utils.JwtDecode;
import com.fiap.ponto.controller.dto.JwtDto;
import com.fiap.ponto.services.PontoService;

import com.fiap.ponto.services.dto.EspelhoPontoDTO;
import com.fiap.ponto.services.dto.PontoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping
    public ResponseEntity<PontoDTO> registrarPonto(@RequestHeader("Authorization") String token) {
        JwtDto jwtDto = JwtDecode.getDataFromJWT(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoService.registrarPonto(jwtDto.getCpf(), jwtDto.getNome(), jwtDto.getEmail(), null));
    }

    @GetMapping
    public ResponseEntity<List<PontoDTO>> obterPonto(@RequestHeader("Authorization") String token){
        JwtDto jwtDto = JwtDecode.getDataFromJWT(token);
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.obterPonto(jwtDto.getCpf()));
    }

    @GetMapping("/espelho")
    public ResponseEntity<EspelhoPontoDTO> espelhoPonto(@RequestHeader("Authorization") String token,
                                                        @RequestParam("mes") Long mes){
        JwtDto jwtDto = JwtDecode.getDataFromJWT(token);
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.obterEspelhoDePonto(jwtDto.getCpf(), mes));
    }

    @PostMapping("/relatorio/sendEmail")
    public ResponseEntity<EspelhoPontoDTO> enviarRelatorioEmail(@RequestHeader("Authorization") String token, @RequestParam("mes") Long mes){
        JwtDto jwtDto = JwtDecode.getDataFromJWT(token);
        pontoService.gerarRelatorioEnvioEmail(jwtDto.getCpf(), mes, jwtDto.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
