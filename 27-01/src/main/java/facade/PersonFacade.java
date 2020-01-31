/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Aske
 */
public class PersonFacade {
 
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public EntityManager getManager(){
        return emf.createEntityManager();
        
    }
    public List<Person> getAllPersons(){
        EntityManager em = getManager();
        TypedQuery q = em.createQuery("SELCTE p from Person p", Person.class);
        return q.getResultList();
    }
    public static void main(String[] args){
        PersonFacade pf = new PersonFacade();
        
        EntityManager em = pf.getManager();
        em.getTransaction().begin();
        em.persist(new Person ("Holger", 32));
        em.persist(new Person ("Henriette", 3));
        em.getTransaction().commit();
        pf.getAllPersons().forEach((person)->{System.out.println(person);});
    }
}
