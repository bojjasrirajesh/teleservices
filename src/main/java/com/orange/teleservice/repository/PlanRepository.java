package com.orange.teleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.teleservice.entity.Plans;

@Repository
public interface PlanRepository extends JpaRepository<Plans,Long>{
}
