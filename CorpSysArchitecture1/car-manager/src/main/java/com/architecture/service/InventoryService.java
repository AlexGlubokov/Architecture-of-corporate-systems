package com.architecture.service;

import com.architecture.model.Car;
import com.architecture.model.Manufacturer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class InventoryService {

    @PersistenceContext(unitName = "CarPU")
    private EntityManager em;

    public Manufacturer createManufacturer(Manufacturer m) {
        em.persist(m);
        return m;
    }

    public List<Manufacturer> getAllManufacturers() {
        return em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class).getResultList();
    }

    public Manufacturer updateManufacturer(Integer id, Manufacturer m) {
        Manufacturer existing = em.find(Manufacturer.class, id);
        if (existing != null) {
            existing.setName(m.getName());
            existing.setOrigin(m.getOrigin());
        }
        return existing;
    }

    public void deleteManufacturer(Integer id) {
        Manufacturer m = em.find(Manufacturer.class, id);
        if (m != null) {
            em.remove(m);
        }
    }

    public Car createCar(Car car, Integer manufacturerId) {
        Manufacturer m = em.find(Manufacturer.class, manufacturerId);
        if (m != null) {
            car.setManufacturer(m);
            em.persist(car);
        }
        return car;
    }

    public List<Car> getAllCars() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public Car updateCar(Integer id, Car carData, Integer manufacturerId) {
        Car existing = em.find(Car.class, id);
        if (existing != null) {
            
            existing.setModel(carData.getModel());
            existing.setYear(carData.getYear());
            existing.setColor(carData.getColor());
            
            if (manufacturerId != null) {
                Manufacturer m = em.find(Manufacturer.class, manufacturerId);
                if (m != null) {
                    existing.setManufacturer(m);
                }
            }
        }
        return existing;
    }


    public void deleteCar(Integer id) {
        Car c = em.find(Car.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}
