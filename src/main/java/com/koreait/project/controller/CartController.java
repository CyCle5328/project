package com.koreait.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.project.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping(value = "api/cart", produces = "application/json; charset=UTF-8")
	public Map<String, Object> addCart(@RequestBody Map<String, Object> map){
		return cartService.addCart(map);
	}
	
	@GetMapping(value = "api/cart/{userNo}", produces = "application/json; charset=UTF-8")
	public Map<String, Object> findCartList(@PathVariable("userNo") Long userNo){
		return cartService.findCartList(userNo);
	}
	
	@DeleteMapping(value = "api/cart/{cartNo}", produces = "application/json; charset=UTF-8")
	public Map<String, Object> deleteCart(@PathVariable("cartNo") List<Long> strCartNo){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cartNo", strCartNo);
			return cartService.removeCart(map);
	}
}
