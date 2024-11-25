package edu.miu.cse.vsms.controller;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    // Add a new employee
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto request) {
        EmployeeResponseDto respose = employeeService.addEmployee(request);
        if(respose != null){
            return  ResponseEntity.ok(respose);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> response = employeeService.getAllEmployees();
        if(response != null){
            return  ResponseEntity.ok(response);
        }
         return  ResponseEntity.notFound().build();
    }

    // Get a specific employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDto employee = employeeService.getEmployeeById(id);
        if(employee != null){
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    // Update partially an existing employee
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> partiallyUpdateEmployee(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        EmployeeResponseDto response = employeeService.partiallyUpdateEmployee(id, updates);
        if(response != null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
