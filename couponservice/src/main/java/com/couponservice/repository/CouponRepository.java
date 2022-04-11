package com.couponservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.couponservice.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon getByCode(String code);

}
