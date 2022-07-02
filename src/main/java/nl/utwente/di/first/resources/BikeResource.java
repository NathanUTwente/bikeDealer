package nl.utwente.di.first.resources;

import nl.utwente.di.first.dao.BikeDao;
import nl.utwente.di.first.model.Bike;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

public class BikeResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;
    String id;

    public BikeResource(UriInfo uriInfo, Request request, String id){
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application Integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Produces ({MediaType.TEXT_PLAIN})
    public Bike getBikeDetails(){
        String details = "";
        Bike bike = BikeDao.instance.getModel().get(id);
        if (bike == null){
            throw new RuntimeException("Get: Bike with " + id + " not found");
        }
//        if (bike.getColour() != null){
//            details += "\nColour = " + bike.getColour();
//        }
//        if (bike.getOwnerName() != null){
//            details += "\nOwner Name = " + bike.getOwnerName();
//        }
//        if (bike.getGender() != null){
//            details += "\nGender = " + bike.getGender();
//        }
        return bike;
    }

    // for the browser
    @GET
    @Produces({MediaType.TEXT_HTML})
    public Bike getBikeDetailsHTML(){
        Bike bike = BikeDao.instance.getModel().get(id);
        if (bike == null){
            throw new RuntimeException("Get: Bike with " + id + " not found");
        }
        return bike;
    }

    // Create Bike
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putBike(JAXBElement<Bike> todo) {
        Bike c = todo.getValue();
        return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Bike bike) {
        Response res;
        if(BikeDao.instance.getModel().containsKey(bike.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        BikeDao.instance.getModel().put(bike.getId(), bike);
        return res;
    }




}
