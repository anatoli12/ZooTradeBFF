package com.tinqin.bff.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.restexport.StorageRestExport;
import com.tinqin.restexport.ZooStoreRestExport;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RestClientFactory {

    private final ObjectMapper objectMapper;

    @Bean
    ZooStoreRestExport getMyZooTradeExportClient() {

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestExport.class, "http://localhost:1234");
    }
    @Bean
    StorageRestExport getMyStorageRestExportClient() {

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(StorageRestExport.class, "http://localhost:8080");
    }
}
