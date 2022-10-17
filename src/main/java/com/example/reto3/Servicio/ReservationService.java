package com.example.reto3.Servicio;


import com.example.reto3.Modelo.Reservation;
import com.example.reto3.Repositorio.ReservationRepository;
import com.example.reto3.Repositorio.countClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    //Reto 5

    public Status getReservationStatusReport(){
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");

        return new Status(completed.size(), cancelled.size());
    }

    public List<Reservation> getReservationByDate(String start, String end){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate = new Date();

        try {
            startDate = parser.parse(start);
            endDate = parser.parse(end);
        }catch (ParseException e){
            e.printStackTrace();
        }

        if (startDate.before(endDate)){
            return reservationRepository.getReservationByDate(startDate, endDate);
        }else{
            return new ArrayList<>();
        }
    }

    public List<countClient> getTopClients(){
        return reservationRepository.getTopClient();
    }

}
