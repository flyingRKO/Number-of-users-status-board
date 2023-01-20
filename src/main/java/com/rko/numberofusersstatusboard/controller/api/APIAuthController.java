package com.rko.numberofusersstatusboard.controller.api;

import com.rko.numberofusersstatusboard.dto.APIDataResponse;
import com.rko.numberofusersstatusboard.dto.AdminRequest;
import com.rko.numberofusersstatusboard.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class APIAuthController {

    @PostMapping("/sign-up")
    public APIDataResponse<String> signUp(@RequestBody AdminRequest adminRequest) {
        return APIDataResponse.empty();
    }

    @PostMapping("/login")
    public APIDataResponse<String> login(@RequestBody LoginRequest loginRequest) {
        return APIDataResponse.empty();
    }
}
