package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.dto.VaccineDto;
import com.training.model.VaccineType;
import com.training.service.VaccineService;
import com.training.utils.ConvertData;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VaccineTests {
	
	@Autowired
	private VaccineService vaccineService;
	
	@Test
	@Order(1)
	void testAddVaccine() {
		VaccineType vaccineType = new VaccineType();
		vaccineType.setVaccineTypeId("9");
		vaccineType.setVaccineTypeName("COVAC");
		vaccineType.setVaccineTypeStatus(true);
		vaccineType.setDescription("Vaccine COVAC");
		
		VaccineDto vaccine = new VaccineDto();
		vaccine.setVaccineId("10");
		vaccine.setActive(true);
		vaccine.setVaccineName("Vaccine T");
		vaccine.setVaccineType(vaccineType);
		vaccine.setNumberOfInjection(9);
		vaccine.setTimeBeginNextInjection(ConvertData.stringToDate("2020-10-22"));
		vaccine.setTimeEndNextInjection(ConvertData.stringToDate("2020-10-29"));
		vaccine.setOrigin("Viet Nam");
		vaccineService.save(vaccine);
		Optional<VaccineDto> findById = vaccineService.findById("10");
		assertTrue(findById.isPresent());
		assertEquals(vaccine.getVaccineId(), findById.get().getVaccineId());
		assertEquals(vaccine.getVaccineName(), findById.get().getVaccineName());
		
	}
	
	@Test
	@Order(2)
	void testUpdateVaccine() {
		Optional<VaccineDto> vaccine = vaccineService.findById("10");
		assertTrue(vaccine.isPresent());
		vaccine.get().setVaccineName("Vaccine T2");
		vaccine.get().setActive(false);
		vaccine.get().setOrigin("USA");
		vaccineService.save(vaccine.get());
		Optional<VaccineDto> actual = vaccineService.findById("10");
		assertTrue(actual.isPresent());
		assertEquals("Vaccine T2", actual.get().getVaccineName());
		assertEquals(false, actual.get().isActive());
		assertEquals("USA", actual.get().getOrigin());
		
	}
	
	@Test
	@Order(3)
	void testChangeStatus() {
		vaccineService.changeStatus("10");
		Optional<VaccineDto> vaccine = vaccineService.findById("10");
		assertEquals(false, vaccine.get().isActive());		
	}
}
