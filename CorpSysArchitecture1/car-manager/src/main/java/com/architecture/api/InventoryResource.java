package com.architecture.api;

import com.architecture.model.Car;
import com.architecture.model.Manufacturer;
import com.architecture.service.InventoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    @Inject
    private InventoryService service;

    
    @GET
    @Path("/manufacturers")
    public List<Manufacturer> getManufacturers() {
        return service.getAllManufacturers();
    }

    @POST
    @Path("/manufacturers")
    public Manufacturer addManufacturer(Manufacturer m) {
        return service.createManufacturer(m);
    }
    
    @DELETE
    @Path("/manufacturers/{id}")
    public void deleteManufacturer(@PathParam("id") Integer id) {
        service.deleteManufacturer(id);
    }
    
    @PUT
    @Path("/manufacturers/{id}")
    public Manufacturer updateManufacturer(@PathParam("id") Integer id, Manufacturer m) {
        return service.updateManufacturer(id, m);
    }

    @GET
    @Path("/cars")
    public List<Car> getCars() {
        return service.getAllCars();
    }

    @POST
    @Path("/cars")
    public Car addCar(Car car, @QueryParam("mid") Integer manufacturerId) {
        return service.createCar(car, manufacturerId);
    }

   
    @DELETE
    @Path("/cars/{id}")
    public void deleteCar(@PathParam("id") Integer id) {
        service.deleteCar(id);
    }
    
    @PUT
    @Path("/cars/{id}")
    public Car updateCar(@PathParam("id") Integer id, Car car, @QueryParam("mid") Integer manufacturerId) {
        return service.updateCar(id, car, manufacturerId);
    }
}
