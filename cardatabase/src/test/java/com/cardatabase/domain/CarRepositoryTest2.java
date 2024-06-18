package com.cardatabase.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CarRepositoryTest2 {
	@Autowired
	CarRepository cRepository;

	@Test
	void brandTest() {
		for(Car car:cRepository.findByBrand("Toyota")) {
			log.info(car.toString());
		}
	}

}