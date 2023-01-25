package com.rko.numberofusersstatusboard.controller.api;

import com.rko.numberofusersstatusboard.constant.PlaceType;
import com.rko.numberofusersstatusboard.dto.APIDataResponse;
import com.rko.numberofusersstatusboard.dto.PlaceRequest;
import com.rko.numberofusersstatusboard.dto.PlaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class APIPlaceController {

    @GetMapping("/places")
    public APIDataResponse<List<PlaceResponse>> getPlaces() {

        return APIDataResponse.of(List.of(PlaceResponse.of(
                PlaceType.COMMON,
                "게이트볼장",
                "대전시 유성구 유성대로 882",
                "010-1111-2222",
                30,
                "신장개업"
        )));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping ("/places")
    public APIDataResponse<Void> createPlace(@RequestBody PlaceRequest placeRequest) {
        return APIDataResponse.empty();

    }

    @GetMapping("/places/{placeId}")
    public APIDataResponse<PlaceResponse> getPlace(@PathVariable Long placeId){
        if (placeId.equals(2L)){
            return APIDataResponse.empty();
        }
        return APIDataResponse.of(PlaceResponse.of(
                PlaceType.COMMON,
                "게이트볼장",
                "대전시 유성구 유성대로 882",
                "010-1111-2222",
                30,
                "신장개업"
        ));
    }

    @PutMapping("/places/{placeId}")
    public APIDataResponse<Void> modifyPlace(
            @PathVariable Long placeId,
            @RequestBody PlaceRequest placeRequest
    ) {
        return APIDataResponse.empty();
    }

    @DeleteMapping("/places/{placeId}")
    public APIDataResponse<Void> removePlace(@PathVariable Long placeId) {
        return APIDataResponse.empty();
    }
}
