package edu.miu.cse.vsms;

import edu.miu.cse.vsms.controller.EmployeeController;
import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.service.EmployeeService;
import org.junit.jupiter.api.Test;
;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class VsmsApplicationTests {

    private MockMvc mockMvc;
     @Mock
    EmployeeService employeeService;

     @InjectMocks
    EmployeeController employeeController;

    @Test
    void contextLoads() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeRequestDto employee = new EmployeeRequestDto("testName", "testEmaol", "testPhone", LocalDate.of(2024, 12, 12));

        when(employeeService.addEmployee(any(EmployeeRequestDto.class))).thenReturn(employee);

        mockMvc.perform(post("employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{name: 'terstname', "email" : 'testemail',phone :' testphone'"}")
        )


    }

}
