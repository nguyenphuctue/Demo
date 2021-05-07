package com.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.model.News;
@Repository
public interface NewsRepository extends JpaRepository<News, Integer>{
	Optional<News> findByContent(String content);
	Optional<News> findByNewsId(int newsId);
	
	@Query("SELECT n FROM News n WHERE"
			+ " n.active = 0"
//			+ " AND n.newsId = ?1"
			+ " AND n.content LIKE %?1%"
			)
	Page<News> searchNewsPages(String keyword, Pageable pageable);
	

	@Query("SELECT n FROM News n WHERE" + " n.active = 0")
	List<News> findByPage(Pageable pageable);
	 
	
//	Optional<News> findByNewsActive(int activeStatus);
		
	
	
}
