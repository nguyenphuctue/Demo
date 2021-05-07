package com.training.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.model.News;
import com.training.service.NewsService;

import lombok.RequiredArgsConstructor;

import static com.training.utils.Constant.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("news")
public class NewsController {
	private final NewsService newsService;

	@GetMapping(value = "/listNews")
	public String listNews(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam(value = "keyword", required = false) String keyword) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		String mess = null;
		Page<News> newsPage;
		List<Integer> pagesizes = Arrays.asList(5, 10, 50, 100);
		if (keyword == null) {
			newsPage = newsService.findByPage(PageRequest.of(currentPage - 1, pageSize));
		} else {
			newsPage = newsService.searchNewsPages(keyword, PageRequest.of(currentPage - 1, pageSize));
		}
		if (newsPage.isEmpty()) {
			mess = "Data not found!";
		}
		model.addAttribute("messs", mess);
		model.addAttribute("newsPage", newsPage);
		int totalPages = newsPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
			model.addAttribute("keyword", keyword);
			model.addAttribute("pagesizes", pagesizes);
			model.addAttribute("psize", pageSize);
		}
		return NEWS_LIST_NEWS;
	}

	@GetMapping(value = "/addNews")
	public String addNews(Model model) {
		News news = new News();
		model.addAttribute("news", news);
		return NEWS_ADD_NEWS;
	}

	@PostMapping(value = "/addNews")
	public String saveNews(Model model, @ModelAttribute("news") @Valid News newsAttr, BindingResult result,
			MultipartFile multipartFile, RedirectAttributes redirectAttributes)
			throws IOException {
		if (result.hasErrors()) {
			return NEWS_ADD_NEWS;
		}
		News news = new News();
		Optional<News> optionalNews = newsService.findById(newsAttr.getNewsId());
		if (!optionalNews.isPresent()) {
			news = newsAttr;
			news.setActive(0);
//			news.setNewsUUID(new UUID(0, 0));
		} else {
			news = optionalNews.get();
			news.setContent(newsAttr.getContent());
			news.setPreview(newsAttr.getPreview());
			news.setTitle(newsAttr.getTitle());
//			news.setNewType(newsAttr.getNewType());
			news.setPostDate(newsAttr.getPostDate());
			
//			news.setNewsUUID(newsAttr.getNewsUUID());
		}

		newsService.save(news);
		return NEWS_REDIRECT_LIST_NEWS;
	}

	@GetMapping(value = "/updateNews")
	public String showUpdateNews(Model model, @RequestParam int id) {
		model.addAttribute("news", newsService.findById(id));
		model.addAttribute("ava", newsService.findById(id).get());
		return NEWS_ADD_NEWS;
	}

//	    private  byte[] toByteArray(InputStream in) throws IOException {
//	        ByteArrayOutputStream os = new ByteArrayOutputStream();
//	        byte[] buffer = new byte[1024];
//	        int len;
//	        while ((len = in.read(buffer)) != -1) {
//	            os.write(buffer, 0, len);
//	        }
//	        return os.toByteArray();
//	    }
	@GetMapping(value = "/deleteNews")
	public String deleteNews(Model model, @RequestParam("id") int id, RedirectAttributes redirectAttributes) {
//		newsService.deleteById(id);
		News news = newsService.findById(id).get();
		news.setActive(1);
		newsService.save(news);
		return NEWS_REDIRECT_LIST_NEWS;
	}

	@PostMapping(value = "/deleteListNews")
	public String deleteListNews(Model model, @RequestParam("idChecked") List<Integer> ids,
			RedirectAttributes redirectAttributes) {
		for (int id : ids) {

			News news = newsService.findById(id).get();
			news.setActive(1);
			newsService.save(news);
		}
		return NEWS_REDIRECT_LIST_NEWS;
	}
}
