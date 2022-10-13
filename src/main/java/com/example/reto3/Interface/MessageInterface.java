package com.example.reto3.Interface;

import com.example.reto3.Modelo.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageInterface extends CrudRepository<Message, Integer> {
}
