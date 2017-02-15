package com.teamtreehouse.contactmgr;

import com.teamtreehouse.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


/**
 * Created by karinfernandez on 2/9/17.
 */
public class Application {

    private static final SessionFactory sessionFactory= buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        final ServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact=new Contact.ContactBuilder("Chris","Ramacciotti")
                .withEmail("ramaciotti@teamtreehouse.com")
                .withPhone(7788990011L)
                .build();
                save(contact);

                //Display a list of contacts
                fetchAllContacts().stream().forEach(System.out::println);

    }

    //Getting data from database, so a session needs to be open
    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts(){

        // Open a session
        Session session = sessionFactory.openSession();

        // UPDATED: Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // UPDATED: Create CriteriaQuery
        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);

        // UPDATED: Specify criteria root
        criteria.from(Contact.class);

        // UPDATED: Execute query
        List<Contact> contacts = session.createQuery(criteria).getResultList();

        // Close the session
        session.close();

        return contacts;
    }

    private static Contact findByContactId(int id){

        // Open a session
        Session session = sessionFactory.openSession();

        //Retrieve persistent object or null if not found
        Contact contact=session.get(Contact.class,id);

        // Close the session
        session.close();

        //Return object
        return contact;
    }

    private static void update(Contact contact){

        //Open session
        Session session=sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to update the contact
        session.update(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close session
        session.close();
    }

    private static void delete(Contact contact){

        //Open session
        Session session=sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to update the contact
        session.delete(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close session
        session.close();
    }



    private static int save(Contact contact){

        //Open a session
        Session session=sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();

        //Use the session to save the contact
        int id= (int)session.save(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();

        return id;
    }
}

