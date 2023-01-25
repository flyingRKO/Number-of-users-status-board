package com.rko.numberofusersstatusboard.controller;

import com.rko.numberofusersstatusboard.constant.EventStatus;
import com.rko.numberofusersstatusboard.constant.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    private final MockMvc mvc;

    public AdminControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 어드민 페이지 - 장소 리스트 뷰")
    @Test
    void givenQueryParams_whenRequestingAdminPlacesPage_thenReturnsAdminPlacesPage() throws Exception {
        // Given


        // When & Ten
        mvc.perform(
                get("admin/places")
                        .queryParam("placeType", PlaceType.SPORTS.name())
                        .queryParam("placeName", "게이트볼장")
                        .queryParam("address", "대전시 유성구 유성대로 882")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/places"));
    }

    @DisplayName("[view][GET] 어드민 페이지 - 장소 세부 정보 뷰")
    @Test
    void givenPlaceId_whenRequestingAdminPlaceDetailPage_thenReturnsAdminPlaceDetailPage() throws Exception {
        // Given
        long placeId = 1L;

        // When & Ten
        mvc.perform(get("/admin/places/" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/place-detail"));
    }

    @DisplayName("[view][GET] 어드민 페이지 - 이벤트 리스트 뷰")
    @Test
    void givenQueryParams_whenREquestingAdminEventsPage_thenReturnsAdminEventsPage() throws Exception {
        // Given


        // When & Ten
        mvc.perform(
                get("/admin/events")
                        .contentType(MediaType.TEXT_HTML)
                        .queryParam("placeId","1")
                        .queryParam("placeName", "게이트볼장")
                        .queryParam("eventName", "오후 운동")
                        .queryParam("eventStatus", EventStatus.OPENED.name())
                        .queryParam("eventStartDatetime", LocalDateTime.now().minusDays(1).toString())
                        .queryParam("eventEndDatetime", LocalDateTime.now().toString())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/events"));
    }

    void givenEventId_whenRequestingAdminEventDetailPage_thenReturnsAdminEventDetailPage() throws Exception {
        // Given
        long eventId = 1L;

        // When & Ten
        mvc.perform(get("/admin/events/" + eventId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("admin/event-detail"));
    }
}