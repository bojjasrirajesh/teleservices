package com.orange.teleservice.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.teleservice.entity.Slots;

@Repository
public interface SlotRepository extends JpaRepository<Slots,Long>{
	List<Slots> findByUserIdAndEventId(Long userId, Long eventId);	
	List<Slots> findByEventIdAndRoleName(Long eventId, String string);
	List<Slots> findByRefIdAndUserId(Integer refId, Long userId);
	List<Slots> findByUserId(Long userId);
	List<Slots> findByUserIdAndRefIdAndStartTimeLessThanAndEndTimeGreaterThan(Long userId, Integer refId, LocalTime fromTime, LocalTime fromTimeAlias);
}
