package com.rko.numberofusersstatusboard.controller;

import com.rko.numberofusersstatusboard.constant.EventStatus;
import com.rko.numberofusersstatusboard.constant.PlaceType;
import com.rko.numberofusersstatusboard.dto.EventDTO;
import com.rko.numberofusersstatusboard.dto.PlaceDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/places")
    public ModelAndView adminPlaces(
            PlaceType placeType,
            String placeNamem,
            String address
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeType", placeType);
        map.put("placeName", placeType);
        map.put("address", address);

        return new ModelAndView("admin/places", map);
    }

    @GetMapping("/places/{placeId}")
    public ModelAndView adminPlaceDetail(@PathVariable Long placeId) {
        Map<String,Object> map = new HashMap<>();
        map.put("place", PlaceDTO.of(
                PlaceType.COMMON,
                "게이트볼장",
                "대전시 유성구 유성대로 882",
                "010-2222-3333",
                15,
                "신장개업 ",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        return new ModelAndView("admin/places-detail", map);
    }

    @GetMapping("/events")
    public ModelAndView adminEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDatetime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDatetime
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("placeName", "place-"+placeId);
        map.put("eventName", eventName);
        map.put("eventStatus", eventStatus);
        map.put("eventStartDatetime", eventStartDatetime);
        map.put("eventEndDatetime", eventEndDatetime);

        return new ModelAndView("/admin/events", map);
    }

    @GetMapping("/places/{eventId}")
    public ModelAndView adminEventDetail(@PathVariable Long eventId) {
        Map<String, Object> map = new HashMap<>();
        map.put("event", EventDTO.of(
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2023, 1, 1, 13,0,0),
                LocalDateTime.of(2023, 1, 1, 16,0,0),
                0,
                30,
                "마스크 꼭 착용하세요",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));

        return new ModelAndView("admin/event-detail", map);
    }
}
