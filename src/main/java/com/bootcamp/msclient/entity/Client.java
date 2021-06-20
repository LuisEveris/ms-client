package com.bootcamp.msclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("clients")
public class Client {
    @Id
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String type;
//    private List<String> counts;
}
