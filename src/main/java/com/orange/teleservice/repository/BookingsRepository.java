package com.orange.teleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.teleservice.entity.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Long>{
}
