package com.tinqin.bff.domain;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.uri(template
                .path()
                .replace("%3A", ":")
                .replace("%3F", "?")
                .replace("%3D", "="));
    }
}