package com.fiap.ponto.commons.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.ponto.controller.dto.JwtDto;

public class JwtDecode {
    public static JwtDto getDataFromJWT(String jwt) {
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
            String[] parts = jwt.split("\\.");
            String payloadJson = new String(decoder.decode(parts[1]));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payloadJson);
            return JwtDto.builder()
                    .cpf(jsonNode.get("cpf").asText())
                    .email(jsonNode.get("email").asText())
                    .telefone(jsonNode.get("telefone").asText())
                    .nome(jsonNode.get("nome").asText())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
