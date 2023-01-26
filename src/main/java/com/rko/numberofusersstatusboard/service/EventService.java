package com.rko.numberofusersstatusboard.service;

import com.rko.numberofusersstatusboard.constant.ErrorCode;
import com.rko.numberofusersstatusboard.constant.EventStatus;
import com.rko.numberofusersstatusboard.dto.EventDTO;
import com.rko.numberofusersstatusboard.exception.GeneralException;
import com.rko.numberofusersstatusboard.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        try {
            return eventRepository.findEvents(
                    placeId,
                    eventName,
                    eventStatus,
                    eventStartDatetime,
                    eventEndDatetime
            );
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }

    }

    public Optional<EventDTO> getEvent(Long eventId) {
        try {
            return eventRepository.findEvents(eventId);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }

    }

    public boolean createEvent(EventDTO eventDTO) {
        return eventRepository.insertEvent(eventDTO);
    }

    public boolean modifyEvent(Long eventId, EventDTO eventDTO) {
       try {
           return eventRepository.updateEvent(eventId, eventDTO);
       } catch (Exception e) {
           throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
       }
    }

    public boolean removeEvent(Long eventId){
        try {
            return eventRepository.deleteEvent(eventId);
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }
}
