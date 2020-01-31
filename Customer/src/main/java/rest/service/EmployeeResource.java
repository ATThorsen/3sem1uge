package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import entities.RenameMe;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("Employee")
public class EmployeeResource {
        EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    Gson gson = new Gson();

    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("all")
    @Produces
    public String getAllEmployee() {
        List<Employee> allEmployee = facade.getAllEmployee();
        ArrayList<EmployeeDTO> allDTO = new ArrayList();
        for(Employee employee: allEmployee){
            allDTO.add(new EmployeeDTO (employee));
        }
        return new Gson().toJson(allDTO);
    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(RenameMe entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerFromId(@PathParam("id") int id) {
        Employee e1 = facade.findEmployee(id);
        
        EmployeeDTO e2 = new EmployeeDTO(e1);
        return gson.toJson(e2);
    }
    @GET
    @Path("hp")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
        List<Employee> HighList1 = facade.getHighestSalary();
        List<EmployeeDTO> allDTO = new ArrayList();
        for(Employee employee: HighList1){
            allDTO.add(new EmployeeDTO (employee));
        }
        return new Gson().toJson(allDTO);
    }
        @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerFromName(@PathParam("name") String name) {
        List<Employee> list = facade.findByName(name);
        ArrayList<EmployeeDTO> allDTO = new ArrayList();
        for(Employee employee: list){
            allDTO.add(new EmployeeDTO (employee));
        }
        return gson.toJson(allDTO);
    }
}
