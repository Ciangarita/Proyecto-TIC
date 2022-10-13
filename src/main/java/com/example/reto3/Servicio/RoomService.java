package com.example.reto3.Servicio;

import com.example.reto3.Modelo.Room;
import com.example.reto3.Repositorio.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired

    private RoomRepository roomRepository;

    public List<Room> getAll(){
        return roomRepository.getAll();
    }

    public Optional<Room> getRoom(int id){
        return roomRepository.getRoom(id);
    }

    public Room save(Room room){
        if (room.getId() == null){
            return  roomRepository.save(room);
        } else{
            Optional<Room> category1 = roomRepository.getRoom(room.getId());
            if (category1.isEmpty()){
                return roomRepository.save(room);
            }else {
                return room;
            }
        }
    }
}
