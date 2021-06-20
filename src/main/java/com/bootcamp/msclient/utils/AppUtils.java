package com.bootcamp.msclient.utils;

import com.bootcamp.msclient.dto.ClientDTO;
import com.bootcamp.msclient.entity.Client;
import org.springframework.beans.BeanUtils;


public class AppUtils {

    private AppUtils(){
    }

    public static ClientDTO entityToDTO(Client product) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(product, clientDTO);
        return clientDTO;
    }

    public static Client dtoToEntity(ClientDTO productDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(productDTO, client);
        return client;
    }


}
