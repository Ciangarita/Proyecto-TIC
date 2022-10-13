package com.example.reto3.Repositorio;

import com.example.reto3.Interface.RoomInterface;
import com.example.reto3.Modelo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepository {
    @Autowired
    private RoomInterface roomCrudRepository;

    public List<Room> getAll(){
        return (List<Room>) roomCrudRepository.findAll();
    }

    public Optional<Room> getRoom(int id){
        return roomCrudRepository.findById(id);
    }

    public Room save(Room room){
        return roomCrudRepository.save(room);
    }
}
