package com.bootspring.uselocation.controller;

import com.bootspring.uselocation.exception.MissingParametersException;
import com.bootspring.uselocation.model.Car;
import com.bootspring.uselocation.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    public CarController(){
    }

    @GetMapping()
    public String getAllCars(Model model) {
        model.addAttribute("cars",carService.getAllCars());
        return "index";
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return carService.getCar(id);
    }

    @PostMapping()
    public Car addCar(@RequestBody Car newCar) {
        try {
            return carService.addCar(newCar);
        } catch (MissingParametersException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable int id, @RequestBody Car newCar) {
        try {
            carService.update(id,newCar);
            return getCar(id);
        } catch (MissingParametersException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public Car deleteCar(@PathVariable int id) {
        Car car = getCar(id);
        carService.delete(id);
        return car;
    }

}
