/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import data.Car;
import data.Person;
import data.ejb.CarBean;
import data.ejb.PersonBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author scyzeq
 */
@ManagedBean
@RequestScoped
public class CarController implements Serializable{
    
    
    String carBrand="";
    String carModel="";
    String name="";
    String surname="";
    
    @EJB
    private CarBean carBean;
    
    @EJB
    private PersonBean personBean;
        
    @PostConstruct
    protected void init()
    {
    }
    
    public String createCar()
    {   
        FacesContext context = FacesContext.getCurrentInstance();
    
        Car car = new Car();
        
        car.setBrand(carBrand);
        car.setModel(carModel);
        
        carBean.createCar(car);
        
        return "index";
    }
    
    public void assignCarToUser()
    {
        Person person=null;
        Car car=null;
        
        person=personBean.findPerson(name, surname);
        car=carBean.findCar(carBrand, carModel);
        car.setPerson(person);
        carBean.assignCar(car);
        
        
    }

    public CarBean getCarBean() {
        return carBean;
    }

    public void setCarBean(CarBean carBean) {
        this.carBean = carBean;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
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
    
    
}
