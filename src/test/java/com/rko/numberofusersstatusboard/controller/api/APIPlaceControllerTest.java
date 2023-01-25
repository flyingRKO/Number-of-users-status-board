package com.rko.numberofusersstatusboard.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rko.numberofusersstatusboard.constant.ErrorCode;
import com.rko.numberofusersstatusboard.constant.EventStatus;
import com.rko.numberofusersstatusboard.constant.PlaceType;
import com.rko.numberofusersstatusboard.dto.EventResponse;
import com.rko.numberofusersstatusboard.dto.PlaceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(APIPlaceController.class)
class APIPlaceControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper mapper;

    public APIPlaceControllerTest(
            @Autowired MockMvc mvc,
            @Autowired ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @DisplayName("[API][GET] 장소 리스트 조회")
    @Test
    void givenNothing_whenRequestingPlaces_thenReturnsListOfPlacesInStandardResponse() throws Exception {
        // Given



        // When & Ten
        mvc.perform(get("/api/places"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("게이트볼장"))
                .andExpect(jsonPath("$.data[0].address").value("대전시 유성구 유성대로 882"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1111-2222"))
                .andExpect(jsonPath("$.data[0].capacity").value(30))
                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][POST] 장소 생성")
    @Test
    void givenPlace_whenCreatingAPlace_thenReturnsSuccessfulStandardResponse() throws Exception {
        // Given
        PlaceRequest placeRequest = PlaceRequest.of(
                PlaceType.COMMON,
                "게이트볼장",
                "대전시 유성구 유성대로 882",
                "010-1111-2222",
                30,
                "신장개업"
        );
        // When & Ten
        mvc.perform(
                        post("/api/places")
                                .contentType(MediaType.APPLICATION_JSON)
                                .contentType(mapper.writeValueAsString(placeRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));

    }

    @DisplayName("[API][GET] 단일 장소 조회 - 장소 있는 경우, 장소 데이터를 담은 표준 API 출력")
    @Test
    void givenPlaceId_whenRequestingExistentPlace_thenReturnsPlaceInStandardResponse() throws Exception {
        // Given
        long placeId = 1L;


        // When & Ten
        mvc.perform(get("/api/places" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data[0].placeType").value(PlaceType.COMMON.name()))
                .andExpect(jsonPath("$.data[0].placeName").value("게이트볼장"))
                .andExpect(jsonPath("$.data[0].address").value("대전시 유성구 유성대로 882"))
                .andExpect(jsonPath("$.data[0].phoneNumber").value("010-1111-2222"))
                .andExpect(jsonPath("$.data[0].capacity").value(30))
                .andExpect(jsonPath("$.data[0].memo").value("신장개업"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }


    @DisplayName("[API][GET] 단일 장소 조회 - 장소 없는 경우, 빈 표준 API 출력")
    @Test
    void givenPlaceId_whenRequestingNonexistentPlace_thenReturnsEmptyInStandardResponse() throws Exception {
        // Given
        long placeId = 2L;


        // When & Ten
        mvc.perform(get("/api/places" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isEmpty())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][PUT] 장소 변경")
    @Test
    void givenPlace_whenModifyingPlace_thenReturnsSuccessfulStandardResponse() throws Exception {
        // Given
        long placeId = 1L;
        PlaceRequest placeRequest = PlaceRequest.of(
                PlaceType.COMMON,
                "게이트볼장",
                "대전시 유성구 유성대로 882",
                "010-1111-2222",
                30,
                "신장개업"
        );


        // When & Ten
        mvc.perform(put("/api/places" + placeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(placeRequest))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

    @DisplayName("[API][DELETE] 장소 삭제")
    @Test
    void givenPlace_whenDeletingPlace_thenReturnsSuccessfulStandardResponse() throws Exception {
        // Given
        long placeId = 1L;



        // When & Ten
        mvc.perform(delete("/api/places" + placeId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.OK.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.OK.getMessage()));
    }

}