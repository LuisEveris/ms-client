package com.bootcamp.msclient.repository;

import com.bootcamp.msclient.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<Client, Integer> {}
