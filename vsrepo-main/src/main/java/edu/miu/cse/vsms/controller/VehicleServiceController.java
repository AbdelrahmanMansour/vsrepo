package edu.miu.cse.vsms.controller;

import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class VehicleServiceController {

    @Autowired
    private final VehicleService vehicleService;

    // Assign a service to an employee
    @PostMapping
    public ResponseEntity<VehicleServiceResponseDto> assignService(@RequestBody ServiceRequestDto request) {
        VehicleServiceResponseDto response = vehicleService.assignService(request);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
    
}
