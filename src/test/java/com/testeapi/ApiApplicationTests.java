package com.testeapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testeapi.domain.Employee;
import com.testeapi.domain.Position;
import com.testeapi.domain.Status;
import com.testeapi.services.EmployeeService;
import com.testeapi.services.PositionService;

@AutoConfigureMockMvc
@SpringBootTest
public class ApiApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private PositionService positionService;
    
    @Test
    void contextLoads() {
    }
    
    /*
     * Teste de chamada de endpoint para inserção de um Employee
     */
    @Test
    void case1() throws Exception {
    	
    	LocalDate birthDate = LocalDate.parse("1990-08-16");
    	Employee emp = new Employee(null, "Sarah Jordan", birthDate, "Stone Av. 256", 
    					new Status(1L,null), 
    					new Position(2L,null));
    	
    	mockMvc.perform(post("/employee")
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(emp)))
    	        .andExpect(status().isCreated());
    	
    	emp = employeeService.findById(1L);
    	
        Assertions.assertEquals(emp.getName(), "Sarah Jordan");
        Assertions.assertEquals(emp.getStatus().getDescription(), "Contractor");
    }
    
    /*
     * Teste de chamada de endpoint para inserção de uma Position
     */
    @Test
    void case2() throws Exception {
    	
    	Position pos = new Position(null, "Tester");
    	
    	mockMvc.perform(post("/position")
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(pos)))
    	        .andExpect(status().isCreated());
    	
    	pos = positionService.findById(6L);
    	
        Assertions.assertEquals(pos.getDescription(), "Tester");
    }
    
    /*
     * Teste de chamada de endpoint para deleção de uma Position
     */
    @Test
    void case3() throws Exception {
    	
    	Position pos = new Position(4L, null);
    	
    	mockMvc.perform(delete("/position/"+pos.getPositionId())
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(pos)))
    	        .andExpect(status().isNoContent());
    	
    }

}
