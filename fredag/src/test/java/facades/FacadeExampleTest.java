/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
////import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author thomas
 */
public class FacadeExampleTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final BankFacade FE = BankFacade.getFacadeExample(ENF);
    
    public FacadeExampleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test a method here.
     */
    @Test
    public void testGetAllEmployee() {
        List<BankCustomer> b1 = new ArrayList(FE.getAllCustomers());
        
        //Asserting size, should be 4
        assertEquals(b1.size(), 4);
    }
    @Test
    public void testFindByName() {
        List<CustomerDTO> b1 = new ArrayList(FE.findByName("Aske")); 
        
        assertEquals(b1.get(0).getCustomerID(), 1);
        assertEquals(b1.get(0).getBalance(), 10000);
        
    }
    @Test
    public void testGetCustomerByID(){
        CustomerDTO b1 = FE.getCustomerByID(1);
        
        assertEquals(b1.getFullName(), "Aske Thorsen");
    }
    
}
