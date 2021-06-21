package com.bootcamp.msclient.service;

import com.bootcamp.msclient.dto.ClientDTO;
import com.bootcamp.msclient.repository.ClientRepository;
import com.bootcamp.msclient.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ClientService {
    @Autowired
    ClientRepository repository;


    public Flux<ClientDTO> getAllClients() {
        log.debug("getAll clients | method from ClientService");
        return repository.findAll().map(AppUtils::entityToDTO);
    }

    public Mono<ClientDTO> getClientById(Integer id) {
        log.debug("get 1 client by id | method from ClientService {}", id);
        return repository.findById(id)
                .map(AppUtils::entityToDTO);
    }

    public Mono<ClientDTO> saveClient(Mono<ClientDTO> clientDTOMono) {
        log.debug("save a client| method from ClientService : {}", clientDTOMono);
        return clientDTOMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDTO);
    }

    public Mono<ClientDTO> updateClient(Integer id, Mono<ClientDTO> clientDTOMono) {
        log.debug("update a client by ID| method from ClientService the id is : {} and the ClientDTO is : {}", id, clientDTOMono);
        return repository.findById(id)
                .flatMap(p -> clientDTOMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDTO);
    }

    public Mono<Void> deleteProduct(Integer id) {
        log.debug("delete a client| method from ClientService the id is : {}", id);
        return repository.deleteById(id);
    }

}
