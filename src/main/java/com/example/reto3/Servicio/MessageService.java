package com.example.reto3.Servicio;

import com.example.reto3.Modelo.Category;
import com.example.reto3.Modelo.Message;
import com.example.reto3.Repositorio.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MessageService {
    @Autowired

    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message message){
        if (message.getIdMessage() == null){
            return  messageRepository.save(message);
        } else{
            Optional<Message> message1 = messageRepository.getMessage(message.getIdMessage());
            if (message1.isEmpty()){
                return messageRepository.save(message);
            }else {
                return message;
            }
        }
    }

    public Message update(Message message){
        if (message.getIdMessage() != null){
            Optional<Message> e = messageRepository.getMessage(message.getIdMessage());
            if (!e.isEmpty()) {
                if (message.getMessageText() != null) {
                    e.get().setMessageText(message.getMessageText());
                }
                return messageRepository.save(e.get());
            }
        }
        return message;
    }

    public boolean delete(int id){
        Boolean i = getMessage(id).map(message -> {
            messageRepository.delete(message);
            return true;
        }).orElse(false);

        return i;
    }
}
