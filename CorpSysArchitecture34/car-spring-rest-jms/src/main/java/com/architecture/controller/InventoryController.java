package com.architecture.controller;

import com.architecture.model.*;
import com.architecture.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired private InventoryService service;

    @GetMapping(value = "/manufacturers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Manufacturer> getManufacturers() {
        return service.getAllManufacturers();
    }

    @PostMapping(value = "/manufacturers", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Manufacturer addManufacturer(@RequestBody Manufacturer m) {
        return service.saveManufacturer(m);
    }

    @PutMapping(value = "/manufacturers/{id}")
    public Manufacturer updateManufacturer(@PathVariable Integer id, @RequestBody Manufacturer m) {
        m.setId(id);
        return service.saveManufacturer(m);
    }

    @DeleteMapping(value = "/manufacturers/{id}")
    public void deleteManufacturer(@PathVariable Integer id) {
        service.deleteManufacturer(id);
    }

    @GetMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @PostMapping(value = "/cars")
    public Car addCar(@RequestBody Car car, @RequestParam("mid") Integer mid) {
        return service.saveCar(car, mid);
    }
    
    @PutMapping(value = "/cars/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Car updateCar(@PathVariable Integer id, @RequestBody Car car, @RequestParam(value = "mid", required = false) Integer mid) {
        car.setId(id);
        return service.saveCar(car, mid);
    }
    
    @DeleteMapping(value = "/cars/{id}")
    public void deleteCar(@PathVariable Integer id) {
        service.deleteCar(id);
    }
}
