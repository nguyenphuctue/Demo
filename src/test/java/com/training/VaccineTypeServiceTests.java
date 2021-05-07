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

import com.training.dto.VaccineTypeDto;
import com.training.service.VaccineTypeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VaccineTypeServiceTests {

	@Autowired
	private VaccineTypeService vaccineTypeService;

	@Test
	@Order(1)
	void testAddVaccineType() {
		VaccineTypeDto vaccineType = new VaccineTypeDto();
		vaccineType.setVaccineTypeId("FA01");
		vaccineType.setVaccineTypeName("NanoCovac");
		vaccineType.setVaccineTypeStatus(true);
		vaccineType.setDescription("VN");
		vaccineTypeService.save(vaccineType);
		Optional<VaccineTypeDto> findById=vaccineTypeService.findById("FA01");
		assertTrue(findById.isPresent());
		assertEquals(vaccineType.getVaccineTypeId(), findById.get().getVaccineTypeId());
		assertEquals(vaccineType.getVaccineTypeName(), findById.get().getVaccineTypeName());
		assertEquals(vaccineType.getDescription(), findById.get().getDescription());
	}
	
	@Test
	@Order(2)
	void testUpdateVaccineType() {
		Optional<VaccineTypeDto> vaccineType =vaccineTypeService.findById("FA01");
		assertTrue(vaccineType.isPresent());
		vaccineType.get().setDescription("Viet Nam");
		vaccineTypeService.save(vaccineType.get());
		Optional<VaccineTypeDto> actual=vaccineTypeService.findById("FA01");
		assertTrue(actual.isPresent());
		assertEquals("Viet Nam", actual.get().getDescription());
	}
	
	@Test
	@Order(3)
	void testChangeStatus() {
		vaccineTypeService.changeStatus("FA01");
		Optional<VaccineTypeDto> vaccineType =vaccineTypeService.findById("FA01");
		assertEquals(false, vaccineType.get().isVaccineTypeStatus());
	}
	
}
