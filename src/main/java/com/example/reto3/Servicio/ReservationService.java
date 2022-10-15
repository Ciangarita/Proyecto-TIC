package com.example.reto3.Servicio;


import com.example.reto3.Modelo.Category;
import com.example.reto3.Modelo.Reservation;
import com.example.reto3.Repositorio.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

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
            Optional<Reservation> reservation1 = reservationRepository.getReservation(reservation.getIdReservation());
            if (reservation1.isEmpty()){
                return reservationRepository.save(reservation);
            }else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if (reservation.getIdReservation() != null){
            Optional<Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if (!e.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    e.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null){
                    e.get().setStatus(reservation.getStatus());
                }

                return reservationRepository.save(e.get());
            }
        }
        return reservation;
    }

    public boolean delete(int id){
        Boolean i = getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);

        return i;
    }
}
