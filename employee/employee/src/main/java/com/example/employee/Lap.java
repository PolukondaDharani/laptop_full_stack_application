package com.example.employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;



@Entity
@Table(name = "dharanidetails")
public class Lap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is required", groups = {Default.class})
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price should be a non-negative value")
    @Max(value = 9999, message = "Price should not exceed 9999")
    private Integer price;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Storage is required")
    private Integer storage;

    @NotNull(message = "RAM is required")
    private Integer ram;

    @NotBlank(message = "Processor is required")
    private String processor;

    // Constructors

    public Lap() {
        // Default constructor
    }

    public Lap(String name, Integer price, String brand, Integer storage, Integer ram, String processor) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.storage = storage;
        this.ram = ram;
        this.processor = processor;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    // Other methods

    @Override
    public String toString() {
        return "Lap [name=" + name + ", price=" + price + ", brand=" + brand + ", storage=" + storage + ", ram=" + ram
                + ", processor=" + processor + "]";
    }
}
