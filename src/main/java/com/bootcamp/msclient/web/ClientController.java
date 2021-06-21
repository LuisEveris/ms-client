package com.bootcamp.msclient.web;

import com.bootcamp.msclient.dto.ClientDTO;
import com.bootcamp.msclient.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping("/")
    public Flux<ClientDTO> getAllClients() {
        log.info("getting all clients");
        return service.getAllClients()
                .switchIfEmpty(Mono.error(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There is not data yet, please register some clients.")));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClientDTO>> getClient(@PathVariable Integer id) {
        log.info("getting a client by id {}", id);
        return service.getClientById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "The Client with ID " + id + " not found.")));
    }

    @PostMapping
    public Mono<ResponseEntity<ClientDTO>> saveClient(@RequestBody Mono<ClientDTO> clientDTOMono) {
        log.info("saving a client | ClientDTO : {}", clientDTOMono);
        return service.saveClient(clientDTOMono)
                .map(ResponseEntity::ok);
    }

    @PutMapping("update/{id}")
    public Mono<ResponseEntity<ClientDTO>> updateProduct(@PathVariable Integer id,
                                                          @RequestBody Mono<ClientDTO> clientDTOMono) {
        log.info("updating an existing client | id : {}, client : {}", id, clientDTOMono);
        return service.updateClient(id, clientDTOMono)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable Integer id) {
        log.info("deleting a new product {}", id);
        return service.deleteProduct(id)
                .map(ResponseEntity::ok);
    }
}
