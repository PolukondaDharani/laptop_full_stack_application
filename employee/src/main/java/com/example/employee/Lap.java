package com.example.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dharanidetails")
public class Lap {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	public Lap(String name, int price, String brand, int storage, int ram, String processor) {
		super();
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.storage = storage;
		this.ram = ram;
		this.processor = processor;
	}
	
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lap() {
        // Default constructor
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	private int price;
	private String brand;
	private int storage;
	private int ram;
	private String processor;
	@Override
	public String toString() {
		return "laptop [name=" + name + ", price=" + price + ", brand=" + brand + ", storage=" + storage + ", ram="
				+ ram + ", processor=" + processor + "]";
	}
}