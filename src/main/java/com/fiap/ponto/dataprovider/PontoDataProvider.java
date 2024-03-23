package com.fiap.ponto.dataprovider;

import com.fiap.ponto.commons.type.TipoRegistro;
import com.fiap.ponto.dataprovider.documents.PontoDocument;
import com.fiap.ponto.dataprovider.mapper.PontoDocumentMapper;
import com.fiap.ponto.dataprovider.repository.IPontoRepository;
import com.fiap.ponto.domain.PontoDomain;
import com.fiap.ponto.services.gateways.PontoGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;


@Component
@AllArgsConstructor
public class PontoDataProvider implements PontoGateway {

    private IPontoRepository repository;

    private PontoDocumentMapper mapper;

    private final MongoOperations mongoOperations;

    @Override
    public PontoDocument save(PontoDomain pontoDomain) {
        PontoDocument document = mongoOperations.findOne(criarFiltroOrderBy(pontoDomain.getCpf().getNumero()), PontoDocument.class);
        if(document == null){
            pontoDomain.setTipoRegistro(TipoRegistro.ENTRADA);
        }else{
            pontoDomain.setTipoRegistro(TipoRegistro.getNextValue(document.getTipoRegistro()));
        }
        return repository.save(mapper.toDocument(pontoDomain));
    }

    public List<PontoDocument> filterPonto(String cpf){
        return mongoOperations.find(criarFiltroVisualizarPonto(cpf), PontoDocument.class);
    }

    public List<PontoDocument> filterEspelhoDePonto(String cpf, Long mes){
        return mongoOperations.find(criarFiltroEspelhoDePonto(cpf, mes), PontoDocument.class);
    }

    private Query criarFiltroVisualizarPonto(String cpf){
        LocalDateTime dataInicio = LocalDate.now().atStartOfDay();
        LocalDateTime dataFim = LocalDate.now().plusDays(1).atStartOfDay();
        Query query = new Query(Criteria.where("cpf").is(cpf)
                .and("dataHora").gte(dataInicio).lt(dataFim));
        return query;
    }

    private Query criarFiltroOrderBy(String cpf){
        LocalDateTime dataInicio = LocalDate.now().atStartOfDay();
        LocalDateTime dataFim = LocalDate.now().plusDays(1).atStartOfDay();
        Query query = new Query(Criteria.where("cpf").is(cpf)
                .and("dataHora").gte(dataInicio).lt(dataFim));
        query.with(Sort.by(Sort.Direction.DESC, "dataHora"));
        return query;
    }

    private Query criarFiltroEspelhoDePonto(String cpf, Long mes){
        YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), mes.intValue());
        LocalDateTime primeiroDiaDoMes = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime ultimoDiaDoMes = yearMonth.atEndOfMonth().atTime(23, 59, 59);

        Criteria criteria = Criteria.where("cpf").is(cpf)
                .and("dataHora").gte(primeiroDiaDoMes).lte(ultimoDiaDoMes);
        return new Query(criteria);

    }
}
