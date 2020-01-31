package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankFacade {

    private static BankFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    public BankFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
   public CustomerDTO getCustomerByID(int id){
         EntityManager em = emf.createEntityManager();
        try{
            BankCustomer bc = em.find(BankCustomer.class,(long)id);
            CustomerDTO ctdo =  new CustomerDTO(bc);
         return ctdo;    
        }finally {
            
            em.close();
        }
   }    
        
    public List<CustomerDTO> findByName(String name){
        EntityManager em = emf.createEntityManager();
        List<CustomerDTO> r1 = new ArrayList<CustomerDTO>();
        try{
          TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM BankCustomer c WHERE c.firstName = :firstName", BankCustomer.class);
          query.setParameter("firstName", name);
          for(BankCustomer bc: query.getResultList()){
            r1.add(new CustomerDTO (bc));
        }
          
          return r1;
 
        }finally{
            em.close();
        }
    }
    
        public BankCustomer addCustomer(BankCustomer e){
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
     public List<BankCustomer> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = 
                       em.createQuery("Select c from BankCustomer c",BankCustomer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    

}
