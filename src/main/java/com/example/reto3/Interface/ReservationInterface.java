package com.example.reto3.Interface;

import com.example.reto3.Modelo.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationInterface extends CrudRepository<Reservation, Integer> {
}
