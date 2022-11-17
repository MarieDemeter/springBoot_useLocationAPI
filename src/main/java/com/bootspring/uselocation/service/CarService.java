package com.bootspring.uselocation.service;

import com.bootspring.uselocation.exception.MissingParametersException;
import com.bootspring.uselocation.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class CarService {
    @Autowired
    public RestTemplate restTemplate;

    public List<Car> getAllCars() {
        return this.restTemplate.getForObject("http://localhost:8080/api/cars", List.class);
    }

    public Car getCar(int id) {
        return this.restTemplate.getForObject("http://localhost:8080/api/cars/"+id, Car.class);
    }

    public Car addCar(Car newCar) {
        if (!Objects.equals(newCar.getModel(), "") && !Objects.equals(newCar.getBrand(), "") && !Objects.equals(newCar.getColor(), "")) {
            return this.restTemplate.postForObject("http://localhost:8080/api/cars/", newCar, Car.class);
        } else {
            throw new MissingParametersException();
        }
    }

    public void update(int id, Car newCar) {
        if (!Objects.equals(newCar.getModel(), "") && !Objects.equals(newCar.getBrand(), "") && !Objects.equals(newCar.getColor(), "")) {
            Car carToUpdate = getCar(id);
            this.restTemplate.put("http://localhost:8080/api/cars/"+id, newCar, carToUpdate);
        } else {
            throw new MissingParametersException();
        }
    }

    public void delete(@PathVariable int id) {
        this.restTemplate.delete("http://localhost:8080/api/cars/"+id);
    }
}
