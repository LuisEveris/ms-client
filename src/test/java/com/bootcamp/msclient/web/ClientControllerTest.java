package com.bootcamp.msclient.web;

import com.bootcamp.msclient.dto.ClientDTO;
import com.bootcamp.msclient.repository.ClientRepository;
import com.bootcamp.msclient.service.ClientService;
import com.bootcamp.msclient.utils.AppUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ClientController.class)
@Import(ClientService.class)
class ClientControllerTest {

    @MockBean
    ClientRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetClients() {
        ClientDTO clientDTO = new ClientDTO(1, "name1", 73866786, "pasivo", "su casa", "email@email.com", 97865412);
        Mockito.when(repository.findAll())
                .thenReturn(Flux.just(clientDTO).map(AppUtils::dtoToEntity));

        webTestClient.get()
                .uri("/clients")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetClient() {
        ClientDTO clientDTO = new ClientDTO(1, "name1", 73866786, "pasivo", "su casa", "email@email.com", 97865412);
        Mockito.when(repository.findById(clientDTO.getId()))
                .thenReturn(Mono.just(clientDTO).map(AppUtils::dtoToEntity));

        webTestClient.get()
                .uri("/clients/{id}", clientDTO.getId())
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk();
    }
}