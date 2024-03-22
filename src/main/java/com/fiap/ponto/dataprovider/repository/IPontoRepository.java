package com.fiap.ponto.dataprovider.repository;

import com.fiap.ponto.dataprovider.documents.PontoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IPontoRepository extends MongoRepository<PontoDocument, UUID> {

}
