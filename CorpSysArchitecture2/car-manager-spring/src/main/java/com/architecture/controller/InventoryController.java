package com.architecture.controller;

import com.architecture.model.Car;
import com.architecture.model.Manufacturer;
import com.architecture.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    
    @GetMapping("/manufacturers")
    public List<Manufacturer> getManufacturers() {
        return service.getAllManufacturers();
    }

    @PostMapping("/manufacturers")
    public Manufacturer addManufacturer(@RequestBody Manufacturer m) {
        return service.saveManufacturer(m);
    }

    @PutMapping("/manufacturers/{id}")
    public Manufacturer updateManufacturer(@PathVariable Integer id, @RequestBody Manufacturer m) {
        return service.updateManufacturer(id, m);
    }

    @DeleteMapping("/manufacturers/{id}")
    public void deleteManufacturer(@PathVariable Integer id) {
        service.deleteManufacturer(id);
    }

    
    @GetMapping("/cars")
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car, @RequestParam("mid") Integer manufacturerId) {
        return service.createCar(car, manufacturerId);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable Integer id, @RequestBody Car car, @RequestParam("mid") Integer manufacturerId) {
        return service.updateCar(id, car, manufacturerId);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Integer id) {
        service.deleteCar(id);
    }
}
