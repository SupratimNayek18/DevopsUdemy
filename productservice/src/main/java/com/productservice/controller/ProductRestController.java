package com.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.productservice.dto.Coupon;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${couponService.url}")
	private String couponServiceUrl;
	
	@PostMapping("/products/create")
	public Product createCoupon(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceUrl+product.getCouponCode(), Coupon.class);
		if(coupon!=null) product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepository.save(product);
	}
	
	
}
