package com.koreait.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.koreait.project.domain.Review;
import com.koreait.project.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private ReviewRepository repository;
	
	public ReviewServiceImpl(SqlSessionTemplate sqlSession) {
		repository = sqlSession.getMapper(ReviewRepository.class);
	}
	
	// 리뷰 조회
	@Override
	public Map<String, Object> findAllReview(Long artNo) {
		List<Review> reviewList = repository.selectReviewListByArtNo(artNo);
		for (Review review : reviewList) {
			// System.out.println("===" + review);			
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reviewList", reviewList);
		return map;
	}
	
	// 리뷰 등록
	@Override
	public Map<String, Object> addReview(Review review) {
		int result = repository.insertReview(review);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

}
