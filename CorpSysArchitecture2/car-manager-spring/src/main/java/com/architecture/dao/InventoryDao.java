package com.architecture.dao;

import com.architecture.model.Car;
import com.architecture.model.Manufacturer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryDao {

    @PersistenceContext
    private EntityManager em;

    public List<Manufacturer> getAllManufacturers() {
        return em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class).getResultList();
    }

    public Manufacturer getManufacturerById(Integer id) {
        return em.find(Manufacturer.class, id);
    }

    public void saveManufacturer(Manufacturer m) {
        if (m.getId() == null) {
            em.persist(m);
        } else {
            em.merge(m);
        }
    }

    public void deleteManufacturer(Integer id) {
        Manufacturer m = em.find(Manufacturer.class, id);
        if (m != null) em.remove(m);
    }

    public List<Car> getAllCars() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public Car getCarById(Integer id) {
        return em.find(Car.class, id);
    }

    public void saveCar(Car c) {
        if (c.getId() == null) {
            em.persist(c);
        } else {
            em.merge(c);
        }
    }

    public void deleteCar(Integer id) {
        Car c = em.find(Car.class, id);
        if (c != null) em.remove(c);
    }
}
