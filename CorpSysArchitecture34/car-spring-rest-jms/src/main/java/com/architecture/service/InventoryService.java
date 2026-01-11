package com.architecture.service;

import com.architecture.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class InventoryService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JmsTemplate jmsTemplate;

    private void sendAudit(String action, String entity, Integer id, String details) {
        jmsTemplate.convertAndSend(new AuditMessage(action, entity, id, details)); //посылка сообщения JMS объекту
    }

    public List<Manufacturer> getAllManufacturers() {
        return em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class).getResultList();
    }

    public Manufacturer getManufacturer(Integer id) {
        return em.find(Manufacturer.class, id);
    }

    public Manufacturer saveManufacturer(Manufacturer m) {
        boolean isNew = (m.getId() == null);
        if (isNew) em.persist(m);
        else em.merge(m);
        
        sendAudit(isNew ? "INSERT" : "UPDATE", "Manufacturer", m.getId(), "Name: " + m.getName()); //изменение статуса
        return m;
    }

    public void deleteManufacturer(Integer id) {
        Manufacturer m = em.find(Manufacturer.class, id);
        if (m != null) {
            em.remove(m);
            sendAudit("DELETE", "Manufacturer", id, "Name: " + m.getName()); //изменение статуса
        }
    }

    public List<Car> getAllCars() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public Car saveCar(Car c, Integer manufId) {
        boolean isNew = (c.getId() == null);
        if (manufId != null) c.setManufacturer(em.find(Manufacturer.class, manufId));
        
        if (isNew) em.persist(c);
        else em.merge(c);

        sendAudit(isNew ? "INSERT" : "UPDATE", "Car", c.getId(), "Model: " + c.getModel()); //изменение статуса
        return c;
    }

    public void deleteCar(Integer id) {
        Car c = em.find(Car.class, id);
        if (c != null) {
            em.remove(c);
            sendAudit("DELETE", "Car", id, "Model: " + c.getModel()); //изменение статуса
        }
    }
}
