package com.a502.backend.application.controller;



import com.a502.backend.global.response.ApiResponse;
import com.a502.backend.global.response.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.a502.backend.global.response.ResponseCode.API_SUCCESS_DOMAIN_METHOD;


@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @GetMapping()
    public ResponseEntity<ApiResponse<String>> hello(){
        String response = "response";
        return ResponseEntity.ok(new ApiResponse<>(API_SUCCESS_DOMAIN_METHOD, response));
    }
}
