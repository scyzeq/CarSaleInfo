/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.ejb;

import data.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author scyzeq
 */
@Stateless
@LocalBean
public class PersonBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public Person addPerson(Person person)
    {
        em.persist(person);
        return person;
    }

    public Person findPerson(String name, String surname)
    {
        Integer counter=0;
        Integer counterDB=1;
        boolean iterationEnd=false;
        Integer elementsInside=em.createQuery("Select p From Person p").getResultList().size();
        String data="";
        Person person = null;
        
        while(iterationEnd==false)
        {
            person=em.find(Person.class, counterDB);
            while(person==null&&counter!=elementsInside)
            {
                counterDB++;
                person=em.find(Person.class, counterDB);
            }
            counter++;
            if(counter>elementsInside)
            {
                person=null;
                iterationEnd=true;
            }
            else
            {
                data=person.getSurname().toString();
            }
            if(surname.equals(data))
            {
                data=person.getName().toString();
                if(name.equals(data))
                {
                    iterationEnd=true;
                }
            }
        }
        
        return person;
    }
    
    public List<Person> allContent()
    {
        List<Person> personList = new ArrayList<>();
        
        
        
        return personList;
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
