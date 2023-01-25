package com.rko.numberofusersstatusboard.dto;

import com.rko.numberofusersstatusboard.constant.PlaceType;

import java.time.LocalDateTime;

public record PlaceDTO(
        PlaceType placeType,
        String placeName,
        String address,
        String phoneNumber,
        Integer capacity,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static PlaceDTO of(
            PlaceType placeType,
            String placeName,
            String address,
            String phoneNumber,
            Integer capacity,
            String memo,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        return new PlaceDTO(
                placeType,
                placeName,
                address,
                phoneNumber,
                capacity,
                memo,
                createdAt,
                modifiedAt
        );
    }
}
