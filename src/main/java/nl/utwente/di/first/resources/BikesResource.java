package nl.utwente.di.first.resources;

import nl.utwente.di.first.dao.BikeDao;
import nl.utwente.di.first.model.Bike;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Will map the resource to the URL todos
@Path("/bikes")
public class BikesResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;


    // Return the list of todos to the user in the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Bike> getBikesBrowser() {
        List<Bike> bikes = new ArrayList<Bike>();
        bikes.addAll(BikeDao.instance.getModel().values());
        return bikes;
    }

    // Return the list of todos for applications
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bike> getBikes() {
        List<Bike> bikes = new ArrayList<Bike>();
        bikes.addAll(BikeDao.instance.getModel().values());
        return bikes;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createBike(@FormParam("id") String id,
                        @FormParam("ownerName") String ownerName,
                        @FormParam("colour") String colour,
                        @FormParam("gender") String gender,
                        @Context HttpServletResponse servletResponse) throws IOException {
        Bike bike = new Bike(id,ownerName, colour, gender);
        BikeDao.instance.getModel().put(id, bike);

        servletResponse.sendRedirect("../create_bike.html");
    }

    // Defines that the next path parameter after todos is
    // treated as a parameter and passed to the TodoResources
    // Allows to type http://localhost:8080/de.vogella.jersey.todo/rest/todos/1
    // 1 will be treated as parameter todo and passed to TodoResource
    @Path("{bike}")
    public BikeResource getBike(@PathParam("bike") String id) {
        return new BikeResource(uriInfo, request, id);
    }
}
