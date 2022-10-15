package com.example.reto3.Repositorio;

import com.example.reto3.Interface.ClientInterface;
import com.example.reto3.Modelo.Category;
import com.example.reto3.Modelo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {
    @Autowired
    private ClientInterface clientCrudRepository;

    public List<Client> getAll(){
        return (List<Client>) clientCrudRepository.findAll();
    }

    public Optional<Client> getClient(int id){
        return clientCrudRepository.findById(id);
    }

    public Client save(Client client){
        return clientCrudRepository.save(client);
    }

    public void delete(Client client){
        clientCrudRepository.delete(client);
    }
}
