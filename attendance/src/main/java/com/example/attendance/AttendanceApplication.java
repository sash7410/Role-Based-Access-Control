package com.example.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableJpaAuditing
public class AttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate(getClientHttpRequestFactory());
    }

    public ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 1000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        return clientHttpRequestFactory;
    }

}
