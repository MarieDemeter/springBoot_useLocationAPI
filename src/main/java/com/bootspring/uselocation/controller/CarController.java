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
        model.addAttribute("cars", this.carService.getAllCars());
        model.addAttribute("car",new Car());
        return "index";
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return this.carService.getCar(id);
    }

    @PostMapping()
    public String addCar(@ModelAttribute Car newCar, Model model) {
        try {
            this.carService.addCar(newCar);
            return getAllCars(model);
        } catch (MissingParametersException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable int id, @RequestBody Car newCar) {
        try {
            this.carService.update(id,newCar);
            return getCar(id);
        } catch (MissingParametersException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public Car deleteCar(@PathVariable int id) {
        Car car = getCar(id);
        this.carService.delete(id);
        return car;
    }

}
