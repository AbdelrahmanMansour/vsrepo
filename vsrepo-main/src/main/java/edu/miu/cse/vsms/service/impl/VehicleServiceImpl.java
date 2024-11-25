package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.configuration.ModelMapperConfig;
import edu.miu.cse.vsms.dto.request.ServiceRequestDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.model.VService;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.repository.VehicleServiceRepository;
import edu.miu.cse.vsms.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private final VehicleServiceRepository vehicleServiceRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    ModelMapperConfig mapper;

    @Override
    public VehicleServiceResponseDto assignService(ServiceRequestDto request) {
       Employee emp = employeeRepository.findById(request.employeeId()).get();
       VService service;
       if (emp == null) {
           throw new ResourceNotFoundException("Employee not found");
       } else {
          VService vService = new VService(request.serviceName(), 100d, request.vehicleType(), emp );
            service = vehicleServiceRepository.save(vService);
       }
        return mapper.modelMaper().map(service, VehicleServiceResponseDto.class);
    }
}
