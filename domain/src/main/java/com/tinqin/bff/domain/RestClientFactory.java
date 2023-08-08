package com.tinqin.bff.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.restexport.StorageRestExport;
import com.tinqin.restexport.ZooStoreRestExport;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RestClientFactory {

    @Bean
    ZooStoreRestExport getMyZooTradeExportClient() {

        ObjectMapper om = new ObjectMapper();
        om.findAndRegisterModules();

        return Feign.builder()
                .encoder(new JacksonEncoder(om))
                .decoder(new JacksonDecoder(om))
                .requestInterceptor(new MyRequestInterceptor())
                .target(ZooStoreRestExport.class, "http://localhost:1234");
    }
    @Bean
    StorageRestExport getMyStorageRestExportClient() {

        ObjectMapper om = new ObjectMapper();
        om.findAndRegisterModules();
        return Feign.builder()
                .encoder(new JacksonEncoder(om))
                .decoder(new JacksonDecoder(om))
                .requestInterceptor(new MyRequestInterceptor())
                .target(StorageRestExport.class, "http://localhost:8080");
    }
}
