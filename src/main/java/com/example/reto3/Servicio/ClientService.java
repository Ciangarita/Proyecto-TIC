package com.example.reto3.Servicio;


import com.example.reto3.Modelo.Client;
import com.example.reto3.Repositorio.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClientService {
    @Autowired

    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }

    public Client save(Client client){
        if (client.getIdClient() == null){
            return  clientRepository.save(client);
        } else{
            Optional<Client> client1 = clientRepository.getClient(client.getIdClient());
            if (client1.isEmpty()){
                return clientRepository.save(client);
            }else {
                return client;
            }
        }
    }
}
