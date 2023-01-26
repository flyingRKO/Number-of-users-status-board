package com.rko.numberofusersstatusboard.repository;

import com.rko.numberofusersstatusboard.constant.EventStatus;
import com.rko.numberofusersstatusboard.dto.EventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//TODO: 인스턴스 생성 편의를 위해 임시로 디폴트 사용. repository layer 구현이 완성되면 삭제할 것
public interface EventRepository {

    default List<EventDTO> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) { return List.of();}
    default Optional<EventDTO> findEvents(Long eventId) { return Optional.empty();}
    default boolean insertEvent(EventDTO eventDTO) { return false;}
    default boolean updateEvent(Long eventId, EventDTO eventDTO) { return false;}
    default boolean deleteEvent(Long eventId) { return false;}

}
