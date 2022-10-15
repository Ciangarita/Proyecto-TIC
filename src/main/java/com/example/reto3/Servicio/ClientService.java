package com.example.reto3.Servicio;


import com.example.reto3.Modelo.Category;
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

    public Client update(Client client){
        if (client.getIdClient() != null){
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if (!e.isEmpty()) {
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                if (client.getPassword() != null){
                    e.get().setPassword(client.getPassword());
                }
                if (client.getEmail() != null){
                    e.get().setEmail(client.getEmail());
                }
                return clientRepository.save(e.get());
            }
        }
        return client;
    }

    public boolean delete(int id){
        Boolean i = getClient(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);

        return i;
    }
}
