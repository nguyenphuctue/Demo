package com.training.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.dto.CustomerDto;
import com.training.dto.InjectionResultRpDto;
import com.training.model.InjectionResult;
import com.training.repository.InjectionResultRepository;
import com.training.service.CustomerService;
import com.training.service.InjectionResultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InjectionResultServiceImpl implements InjectionResultService {

	private final ModelMapper mapper;
	private final InjectionResultRepository injectionResultRepository;
	private final CustomerService customerService;

	@Override
	public List<InjectionResult> findAll() {

		return injectionResultRepository.findAll();
	}

	@Override
	public Optional<InjectionResult> findByInjectionResultId(long id) {
		return injectionResultRepository.findByInjectionResultId(id);
	}

	@Override
	public InjectionResult save(InjectionResult injectionResult) {
		injectionResult.setCustomerStr();
		injectionResult.setVaccineStr();
		InjectionResult injectionResultDb = injectionResultRepository.save(injectionResult);
		Set<InjectionResult> injectionResults = new HashSet<>();
		if (injectionResult.getCustomer().getInjectionResults() != null) {
			injectionResults = injectionResult.getCustomer().getInjectionResults();
		}
		injectionResults.add(injectionResultDb);
		injectionResult.getCustomer().setInjectionResults(injectionResults);
		customerService.save(mapper.map(injectionResult.getCustomer(), CustomerDto.class));
		return injectionResultRepository.save(injectionResultDb);
	}

	@Override
	public Page<InjectionResult> findPaginated(int pageNo, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		if (keyword == null || keyword.isEmpty()) {
			return injectionResultRepository.findAll(pageable);

		}
		return injectionResultRepository.findByKeyword(keyword, pageable);
	}

	@Override
	public void deleteById(long id) {
		injectionResultRepository.deleteById(id);
	}

	@Override
	public List<InjectionResultRpDto> findAllRp() {
		return injectionResultRepository.findAll().stream()
				.map(injectionResult -> mapper.map(injectionResult, InjectionResultRpDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<InjectionResultRpDto> findByOptions(Date fromDate, Date toDate, String prevention, String vaccineTypeName) {
		return injectionResultRepository.findByOptions(fromDate, toDate, prevention, vaccineTypeName)
				.stream()
				.map(injectionResult -> mapper.map(injectionResult, InjectionResultRpDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Integer> getListYear() {
		return injectionResultRepository.getListYear();
	}

	@Override
	public List<Object[]> getlistByYear(int year) {
		return injectionResultRepository.getlistByYear(year);
	}
}
