package org.wallpaperwizardapp.wallpaperwizard.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configure a RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    /**
     * RestTemplate is a class in Spring Framework that simplifies the process of making HTTP requests to remote
     * RESTful services. It provides a high-level API for interacting with RESTful services by abstracting away the
     * complexities of handling HTTP connections, requests, and responses.
     *
     * @param restTemplateBuilder helper class in Spring Framework used to create and configure instances of RestTemplate
     * @return RestTemplate instance
     */
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}