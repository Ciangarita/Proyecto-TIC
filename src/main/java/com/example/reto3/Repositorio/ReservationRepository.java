package com.example.reto3.Repositorio;

import com.example.reto3.Interface.ReservationInterface;
import com.example.reto3.Modelo.Client;
import com.example.reto3.Modelo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationInterface reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }

    //RETO 5

    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> getReservationByDate(Date startDate, Date endDate){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(startDate, endDate);
    }

    public List<countClient> getTopClient(){
        List<countClient> res = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        report.forEach(reports->{
            res.add(new countClient((long)reports[1], (Client)reports[0]));
        });

        return res;
    }
}
