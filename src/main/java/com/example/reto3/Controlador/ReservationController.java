package com.example.reto3.Controlador;

import com.example.reto3.Modelo.Reservation;
import com.example.reto3.Repositorio.countClient;
import com.example.reto3.Servicio.ReservationService;
import com.example.reto3.Servicio.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*")

public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id){
        return reservationService.getReservation(id);
    }

    //RETO 5
    @GetMapping("/report-clients")
    public List<countClient> getReservationReportClient(){
        return reservationService.getTopClients();
    }

    @GetMapping("/report-dates/{startDate}/{endDate}")
    public List<Reservation> getReservationReportDates(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
        return reservationService.getReservationByDate(startDate, endDate);
    }

    @GetMapping("/report-status")
    public Status getReservationStatusReport(){
        return reservationService.getReservationStatusReport();
    }

    @DeleteMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation){
        return reservationService.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean delete(@PathVariable("id") int reservationId){
        return reservationService.delete(reservationId);
    }

}
