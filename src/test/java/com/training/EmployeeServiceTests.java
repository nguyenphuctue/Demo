package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.dto.EmployeeDto;
import com.training.service.EmployeeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceTests {
	@Autowired
	private EmployeeService employeeService;

	@Test
	@Order(1)
	void testAddEmployee() {
		EmployeeDto employee = new EmployeeDto();
		employee.setEmployeeId("10");
		employee.setEmployeeName("Nguyen Van Kim");
		employee.setEmail("kimnv@gmail.com");
		employee.setGender(0);
		employee.setPhone("0862212934");
		employee.setPosition("DEV");
		employeeService.save(employee);
		Optional<EmployeeDto> findById = employeeService.findById("10");
		assertTrue(findById.isPresent());
		assertEquals(employee.getEmployeeId(), findById.get().getEmployeeId());
		assertEquals(employee.getEmail(), findById.get().getEmail());
		assertEquals(employee.getPhone(), findById.get().getPhone());
	}
	
	@Test
	@Order(2)
	void testUpdateEmployee() {
		Optional<EmployeeDto> employee = employeeService.findById("10");
		assertTrue(employee.isPresent());
		employee.get().setEmail("abc@gmail.com");
		employee.get().setPhone("0377760349");
		employeeService.save(employee.get());
		Optional<EmployeeDto> actual = employeeService.findById("10");
		assertTrue(actual.isPresent());
		assertEquals("abc@gmail.com", actual.get().getEmail());
		assertEquals("0377760349", actual.get().getPhone());
		
	}
	
	@Test
	@Order(3)
	void testDeleteEmployee() {
		employeeService.deleteById("10");
		Optional<EmployeeDto> actual = employeeService.findById("10");
		assertFalse(actual.isPresent());
	}

}
