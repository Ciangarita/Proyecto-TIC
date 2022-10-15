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
            Optional<Room> room1 = roomRepository.getRoom(room.getId());
            if (room1.isEmpty()){
                return roomRepository.save(room);
            }else {
                return room;
            }
        }
    }

    public Room update(Room room){
        if (room.getId() != null){
            Optional<Room> e = roomRepository.getRoom(room.getId());
            if (!e.isEmpty()) {
                if (room.getName() != null) {
                    e.get().setName(room.getName());
                }
                if (room.getHotel() != null){
                    e.get().setHotel(room.getHotel());
                }
                if (room.getStars() != null){
                    e.get().setStars(room.getStars());
                }
                if (room.getDescription() != null) {
                    e.get().setDescription(room.getDescription());
                }
                if (room.getCategory() != null){
                    e.get().setCategory(room.getCategory());
                }

                return roomRepository.save(e.get());
            }
        }
        return room;
    }
    public boolean delete(int id){
        Boolean i = getRoom(id).map(room -> {
            roomRepository.delete(room);
            return true;
        }).orElse(false);

        return i;
    }
}
