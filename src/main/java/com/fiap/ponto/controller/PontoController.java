package com.fiap.ponto.controller;

import com.fiap.ponto.services.PontoService;

import com.fiap.ponto.services.dto.EspelhoPontoDTO;
import com.fiap.ponto.services.dto.PontoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @PostMapping
    public ResponseEntity<PontoDTO> registrarPonto(@RequestParam("cpf") String cpf,
                                                   @RequestParam("nome") String nome,
                                                   @RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoService.registrarPonto(cpf, nome, email));
    }

    @GetMapping
    public ResponseEntity<List<PontoDTO>> obterPonto(@RequestParam("cpf") String cpf){
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoService.obterPonto(cpf));
    }

    @GetMapping("/espelho")
    public ResponseEntity<EspelhoPontoDTO> espelhoPonto(@RequestParam("cpf") String cpf,
                                                        @RequestParam("mes") Long mes){
        return ResponseEntity.status(HttpStatus.CREATED).body(pontoService.obterEspelhoDePonto(cpf, mes));
    }
}
