package com.orange.teleservice.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orange.teleservice.dto.SlotRequestDto;
import com.orange.teleservice.dto.SlotResponseDto;

@Service
public interface SlotsService {
	List<SlotResponseDto> getAllAvailableSlotsByUserIdAndEventIdAndRole(Long userId, Long eventId, String role);
	SlotResponseDto createSlotsForAdmin(SlotRequestDto slotRequestDto) throws NoSuchAlgorithmException;
    SlotResponseDto createSlotsForSales(SlotRequestDto slotRequestDto);
	List<SlotResponseDto> getAllAvailableSalesSlots(Long userId);
}
