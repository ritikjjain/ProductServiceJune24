package com.ecom.productservicejune24.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
// in this Configuration Class only Bean methods will be present
public class ApplicationConfig {
    @Bean
    // only 1 object will be created
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
