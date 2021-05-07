package com.training;

import com.training.dto.CustomerDto;
import com.training.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RequiredArgsConstructor
public class CustomerServiceTests {

    private final CustomerService customerService;

    @Test
    @Order(1)
    void testSave() {
        CustomerDto customer = new CustomerDto();
        customer.setFullName("Nguyen Anh Duc");
        customer.setDateOfBirth(new Date("1999/01/20"));
        customer.setGender(true);
        customer.setIdentityCard("696969");
        customer.setAddress("hanoi");
        customer.setUserName("anhduc99tn");
        customer.setPassword("123456");
        customer.setEmail("ducna99tn@gmail.com");
        customer.setPhone("0865269919");
        customerService.save(customer);
        Optional<CustomerDto> customerDb = customerService.findByUserName("anhduc99tn");
        assertTrue(customerDb.isPresent());
    }
    @Test
    @Order(2)
    void testUpdate() {
        Optional<CustomerDto> customer = customerService.findByUserName("anhduc99tn");
        customer.get().setIdentityCard("686868");
        customerService.save(customer.get());
        Optional<CustomerDto> customerDb = customerService.findByUserName("anhduc99tn");
        assertTrue(customerDb.isPresent());
        assertEquals("686868", customerDb.get().getIdentityCard());
    }
    @Test
    @Order(3)
    void testDeleteEmployee() {
        customerService.deleteById(10);
        Optional<CustomerDto> actual = customerService.findById(10);
        assertFalse(actual.isPresent());
    }
}
