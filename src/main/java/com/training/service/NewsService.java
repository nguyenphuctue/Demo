package com.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.training.model.News;

public interface NewsService {
	List<News> findAll();

	News save(News news);

	Optional<News> findById(int title);

	void deleteById(int id);

	Page<News> searchNewsPages(String keyword, Pageable pageable);

	Page<News> findByPage(Pageable pageable);

	boolean IdAlready(String id);

}
