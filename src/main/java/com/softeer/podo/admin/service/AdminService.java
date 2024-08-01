package com.softeer.podo.admin.service;


import com.softeer.podo.admin.model.dto.EventListResponseDto;
import com.softeer.podo.admin.model.dto.mapper.AdminMapper;
import com.softeer.podo.admin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
	private final EventRepository eventRepository;

	private final AdminMapper adminMapper;

	@Transactional
	public EventListResponseDto getEventList() {
		return adminMapper.eventListToEventListResponseDto(eventRepository.findAll());
	}


}
