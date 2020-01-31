package facades;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import static org.eclipse.persistence.jpa.jpql.tools.utility.iterable.EmptyIterable.instance;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    public static EntityManagerFactory emf;
    public static EmployeeFacade instance;
    
    //Private Constructor to ensure Singleton
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
    
    public static void main(String [] args){
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        
        ArrayList<Employee> list = new ArrayList<>();
        Employee e1 = new Employee("Jens", "Jens1", 1);
        Employee e2 = new Employee("Aske", "aske1", 3);
        Employee e3 = new Employee("Mikkel", "Langebro", 5);
        Employee e4 = new Employee("Casper", "Lyngby", 6);
        
        facade.addEmployee(e1);
        facade.addEmployee(e2);
        facade.addEmployee(e3);
        facade.addEmployee(e4);
        
        
    
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
        public Employee findEmployee(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Employee employee = em.find(Employee.class,(long)id);
            return employee;
        }finally {
            em.close();
        }
    }
            public List<Employee> findByName (String name){
        EntityManager em = emf.createEntityManager();
        try{
          TypedQuery<Employee> query = em.createQuery("SELECT c FROM Employee c WHERE c.name = :name",Employee.class);
          query.setParameter("name", name);
          return query.getResultList();
 
        }finally{
            em.close();
        }
    }
     public List<Employee> getAllEmployee(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select c from Employee c",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
     public List<Employee> getHighestSalary(){
     EntityManager em = emf.createEntityManager();
        try{
              TypedQuery<Employee> query = 
                      em.createQuery("SELECT c FROM Employee c WHERE c.salary = (SELECT MAX(c.salary) FROM Employee c) ",Employee.class);
               return query.getResultList();
               }finally {
            em.close();
        }  
     }
         public Employee addEmployee(Employee e){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        }finally {
            em.close();
        }
    }

}
