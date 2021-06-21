package com.bootcamp.msclient.repository;

import com.bootcamp.msclient.entity.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Integer> {
}
