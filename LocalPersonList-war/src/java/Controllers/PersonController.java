/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import data.Person;
import data.ejb.PersonBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author scyzeq
 */

@ManagedBean
@RequestScoped
public class PersonController implements Serializable{
    
    String name="";
    String surname="";
    String city="";
    String street="";
    
    public List<Person> personList;
    
    @EJB
    private PersonBean personBean;
    
    public String addPerson()
    {
        Person person = new Person();
        
        person.setName(name);
        person.setSurname(surname);
        person.setCity(city);
        person.setStreet(street);
        
        personBean.addPerson(person);
        
        return "index";
    }
    
    public void AllUsers()
    {
        personList = new ArrayList<>();
//        personList=
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public PersonBean getPersonBean() {
        return personBean;
    }

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }
    
    
    
}
