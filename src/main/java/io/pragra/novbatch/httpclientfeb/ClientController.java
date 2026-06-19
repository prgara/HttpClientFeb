package io.pragra.novbatch.httpclientfeb;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ProductOrder getOrders(@PathVariable Integer id){
       return service.getOrder(id);
    }


    @GetMapping("/github/{userId}")
    public GitHubUser getUser(@PathVariable String userId){
        return service.getUser(userId);
    }






}
