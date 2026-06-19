package io.pragra.novbatch.httpclientfeb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Service
public class ClientService {

    @Autowired
    RestTemplate restTemplate;

    String url = "http://localhost:8080/getOrder/";

    public ProductOrder getOrder(Integer id) {
        ResponseEntity<ProductOrder> forEntity = restTemplate.getForEntity(url + id, ProductOrder.class);
        return forEntity.getBody();
    }


    public GitHubUser getUser(String userId) {
        WebClient client = WebClient.builder().build();
       return client.get().
                uri("https://api.github.com/users/"+userId)
                .retrieve().bodyToMono(GitHubUser.class).
               timeout(Duration.ofMinutes(1)).block();

    }
}


/*
Flux -> Many

Mono -> One


 */

