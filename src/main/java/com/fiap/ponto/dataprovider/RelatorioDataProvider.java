package com.fiap.ponto.dataprovider;

import com.fiap.ponto.dataprovider.documents.PontoDocument;
import com.fiap.ponto.services.dto.PontoDetails;
import com.fiap.ponto.services.dto.PontoInfoDTO;
import com.fiap.ponto.services.gateways.RelatorioGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RelatorioDataProvider implements RelatorioGateway {

    @Value("${gateways.url}")
    private String url;

    @Override
    public void gerarRelatorioEnviaEmail(List<PontoDocument> listaPonto, Duration horasTrabalhadas, String email) {
        log.info("<<< Gerando dados de relatorio para envio por email >>>");
        List<PontoDetails> detailsList = new ArrayList<>();

        for (PontoDocument document : listaPonto){
            var details = PontoDetails.builder().nomeFuncionario(document.getNomeFuncionario()).cpf(document.getCpf())
                    .tipoRegistro(document.getTipoRegistro().name()).dataHora(document.getDataHora().toString()).build();
            detailsList.add(details);
        }

        var pontoInfo = PontoInfoDTO.builder().pontos(detailsList).totalHoras(horasTrabalhadas.toHours()).totalMinutos(horasTrabalhadas.toMinutes()).build();

        WebClient webClient = WebClient.create();
        webClient.post().uri(url.concat("/api/v1/relatorio/sendEmail")).body(BodyInserters.fromValue(pontoInfo)).retrieve().bodyToMono(Void.class).subscribe();
        log.info("<<< Email enviado >>>");
    }
}
