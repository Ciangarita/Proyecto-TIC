package com.example.reto3.Interface;

import com.example.reto3.Modelo.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationInterface extends CrudRepository<Reservation, Integer> {
    public List<Reservation> findAllByStatus(String status);

    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date startDate, Date endDate);

    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client) DESC")
    public List<Object[]> countTotalReservationByClient();
}
