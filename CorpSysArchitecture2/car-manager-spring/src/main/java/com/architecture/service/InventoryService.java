package com.architecture.service;

import com.architecture.dao.InventoryDao;
import com.architecture.model.Car;
import com.architecture.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryDao dao;

    public List<Manufacturer> getAllManufacturers() {
        return dao.getAllManufacturers();
    }

    public Manufacturer saveManufacturer(Manufacturer m) {
        dao.saveManufacturer(m);
        return m;
    }

    public Manufacturer updateManufacturer(Integer id, Manufacturer m) {
        Manufacturer existing = dao.getManufacturerById(id);
        if (existing != null) {
            existing.setName(m.getName());
            existing.setOrigin(m.getOrigin());
            dao.saveManufacturer(existing);
        }
        return existing;
    }

    public void deleteManufacturer(Integer id) {
        dao.deleteManufacturer(id);
    }

    public List<Car> getAllCars() {
        return dao.getAllCars();
    }

    public Car createCar(Car car, Integer manufacturerId) {
        Manufacturer m = dao.getManufacturerById(manufacturerId);
        if (m != null) {
            car.setManufacturer(m);
            dao.saveCar(car);
        }
        return car;
    }

    public Car updateCar(Integer id, Car carData, Integer manufacturerId) {
        Car existing = dao.getCarById(id);
        if (existing != null) {
            existing.setModel(carData.getModel());
            existing.setYear(carData.getYear());
            existing.setColor(carData.getColor());
            
            if (manufacturerId != null) {
                Manufacturer m = dao.getManufacturerById(manufacturerId);
                if (m != null) {
                    existing.setManufacturer(m);
                }
            }
            dao.saveCar(existing);
        }
        return existing;
    }

    public void deleteCar(Integer id) {
        dao.deleteCar(id);
    }
}
