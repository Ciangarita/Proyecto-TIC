package com.example.reto3.Repositorio;

import com.example.reto3.Modelo.Client;

public class countClient {
    private Long total;
    private Client client;

    public countClient(Long total, Client client) {
        this.total = total;
        this.client = client;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
