/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Aske
 */
public class MakeTestData {
    
    public static EntityManagerFactory emf;
        public static MakeTestData instance;
    
        public static MakeTestData getMakeTestData(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MakeTestData();
        }
        return instance;
    }
    
    public static void main(String [] args){
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        MakeTestData facade = MakeTestData.getMakeTestData(emf);
        BankCustomer b1 = new BankCustomer("Aske", "Thorsen", "85203", 10000.0, 3, "20200931");
        BankCustomer b2 = new BankCustomer("Jens", "Ohlen", "85204", 12000.0, 2, "31200934");
        BankCustomer b3 = new BankCustomer("Mikkel", "Lind", "85205", 15000.0, 1, "20281931");
        BankCustomer b4 = new BankCustomer("Casper", "Prejler", "85206", 18000.0, 4, "50200931");
        
        facade.addCustomer(b1);
        facade.addCustomer(b2);
        facade.addCustomer(b3);
        facade.addCustomer(b4);
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


}