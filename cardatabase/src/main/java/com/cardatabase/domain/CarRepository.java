package com.cardatabase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>{
	//브랜드로 자동차를 검색
	List<Car> findByBrand(String brand);
}