package com.training.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.model.News;
import com.training.repository.NewsRepository;
import com.training.service.NewsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

	private final NewsRepository newsRepository;

	@Override
	public List<News> findAll() {
		return newsRepository.findAll();
	}

	@Override
	public News save(News news) {
		return newsRepository.save(news);
	}

	@Override
	public Optional<News> findById(int id) {
		return newsRepository.findById(id);
	}

	@Override
	public void deleteById(int newsId) {
		newsRepository.deleteById(newsId);
	}

	@Override
	public Page<News> searchNewsPages(String keyword, Pageable pageable) {
		return newsRepository.searchNewsPages(keyword, pageable);
	}

	@Override
	public Page<News> findByPage(Pageable pageable) {
		List<News> news = (List<News>) newsRepository.findByPage(pageable);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<News> list;
		if (news.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, news.size());
			list = news.subList(startItem, toIndex);
		}
		Page<News> newsPage = new PageImpl<News>(list, PageRequest.of(currentPage, pageSize), news.size());
		return newsPage;
	}

	@Override
	public boolean IdAlready(String id) {
		return false;
	}



}
