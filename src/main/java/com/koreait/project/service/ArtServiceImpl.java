package com.koreait.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.koreait.project.domain.ArtWork;
import com.koreait.project.domain.Author;
import com.koreait.project.repository.ArtRepository;
import com.koreait.project.util.PageUtils;

@Service
public class ArtServiceImpl implements ArtService {

	private ArtRepository repository;
	public ArtServiceImpl(SqlSessionTemplate sqlSession) {
		repository = sqlSession.getMapper(ArtRepository.class);
	}
	@Override
	public Map<String, Object> FindAllartList() {
		List<ArtWork> list = repository.artList();
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		return map;
	}
	@Override
	public Map<String, Object> findArtList(Map<String, Object> m) {
		int totalRecord = repository.artTotalCount(m);
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, (int)m.get("page"));
		m.put("beginRecord", pageUtils.getBeginRecord());
		m.put("endRecord", pageUtils.getEndRecord());
		List<ArtWork> list = repository.findArtList(m);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageUtils", pageUtils);
		return map;
	}
	
	@Override
	public Map<String, Object> autoComplete(String query) {
	
		List<Author> list = repository.autoComplete(query);
		List<ArtWork> list2 = repository.autoComplete2(query);
		System.out.println(list);
		System.out.println(list2);
		Map<String, Object> result = new HashMap<String, Object>();
		if(list.size() == 0 && list2.size() == 0) {
			result.put("list", null);
			result.put("list2", null);
		}else {
			result.put("list", list);
			result.put("list2", list2);
		}
		return result;
	}
	
	@Override
	public Map<String, Object> searchList(Map<String, Object> m2) {
		int totalRecord = repository.artTotalCount(m2);
		PageUtils pageUtils = new PageUtils();
		pageUtils.setPageEntity(totalRecord, (int)m2.get("page"));
		m2.put("beginRecord", pageUtils.getBeginRecord());
		m2.put("endRecord", pageUtils.getEndRecord());
		List<ArtWork> list = repository.searchArtList(m2);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageUtils", pageUtils);
		return map;
	}
		
	@Override
	public Map<String, Object> selectArtByNo(Long artNo) {		
		ArtWork artwork = repository.selectArtByNo(artNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("artwork", artwork);
		return map;
	}
	
}
