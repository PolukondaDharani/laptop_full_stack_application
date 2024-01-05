package com.example.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import Models.Laptop;


@Repository
public interface LaptopRepository extends JpaRepository<Lap, Long> {
	
	Lap findByName(String name);
	Lap findByPrice(int price);
	List<Lap> findBybrand(String brand);
}

