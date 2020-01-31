package rest.service;

import com.google.gson.Gson;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.BankFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {
    
    //NOTE: Change Persistence unit name according to your setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    BankFacade facade =  BankFacade.getFacadeExample(emf);
    Gson gson = new Gson();
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String getCustomerFromId(@PathParam("id") int id) {
        CustomerDTO e1 = facade.getCustomerByID(id);
        
        return gson.toJson(e1);
    }
    
    @GET
    @Path("all")
    @Produces
    public String getAllEmployee() {
        List<BankCustomer> allCustomers = facade.getAllCustomers();
        
        return new Gson().toJson(allCustomers);
    }
}
