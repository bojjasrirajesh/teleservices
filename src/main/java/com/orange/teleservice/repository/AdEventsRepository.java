package com.orange.teleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.teleservice.entity.AdEvents;

@Repository
public interface AdEventsRepository extends JpaRepository<AdEvents,Long>{
}
