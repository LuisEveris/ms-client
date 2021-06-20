package com.bootcamp.msclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Integer id;
    private String name;
    private Integer dni;
    private String address;
    private String email;
    private String phone;
    private String type;
}
