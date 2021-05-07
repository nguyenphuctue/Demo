package com.training;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.model.InjectionSchedule;
import com.training.repository.VaccineRepository;
import com.training.service.InjectionScheduleService;
import com.training.utils.ConvertData;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InjectionScheduleTests {

	@Autowired
	private InjectionScheduleService injectionScheduleService;
	
	@Autowired
	private VaccineRepository vaccineRepository;

	@Test
	void testAddInjectionSchedule() {
		List<InjectionSchedule> beforeSave = injectionScheduleService.findAll();
		InjectionSchedule injectionSchedule = new InjectionSchedule();
		injectionSchedule.setStartDate(ConvertData.stringToDate("2020-3-15"));
		injectionSchedule.setEndDate(ConvertData.stringToDate("2021-5-18"));
		injectionSchedule.setDescription("Vaccine good");
		injectionSchedule.setPlace("HN");
		injectionSchedule.setDescription("kdsfksdfh");
		injectionSchedule.setVaccine1(vaccineRepository.findById("1234").get());
		injectionScheduleService.save(injectionSchedule);
		List<InjectionSchedule> afterSave = injectionScheduleService.findAll();
		assertEquals(beforeSave.size(), afterSave.size()-1);
	}

}
