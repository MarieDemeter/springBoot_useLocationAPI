package com.bootspring.uselocation.controller;

import com.bootspring.uselocation.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    public RestTemplate restTemplate;

    public CarController(){
    }

    @GetMapping()
    public List<Car> getAllCars() {
        List<Car> cars = this.restTemplate.getForObject("http://localhost:8080/api/cars", List.class);
        return cars;
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable int id) {
        Car car = this.restTemplate.getForObject("http://localhost:8080/api/cars/"+id, Car.class);
        return car;
    }

}
