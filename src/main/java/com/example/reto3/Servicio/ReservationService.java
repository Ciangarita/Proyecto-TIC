package com.example.reto3.Servicio;


import com.example.reto3.Modelo.Reservation;
import com.example.reto3.Repositorio.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReservationService {
    @Autowired

    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation){
        if (reservation.getIdReservation() == null){
            return  reservationRepository.save(reservation);
        } else{
            Optional<Reservation> category1 = reservationRepository.getReservation(reservation.getIdReservation());
            if (category1.isEmpty()){
                return reservationRepository.save(reservation);
            }else {
                return reservation;
            }
        }
    }
}
