package com.cardatabase.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface OwnerRepository extends JpaRepository<Owner, Long>{

}