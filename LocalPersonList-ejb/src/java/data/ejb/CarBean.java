/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.ejb;

import data.Car;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author scyzeq
 */
@Stateless
@LocalBean
public class CarBean {

    @PersistenceContext
    private EntityManager em;
    
    public Car createCar(Car car)
    {
        em.persist(car);
        return car;
    }
    
    public Car assignCar(Car car)
    {
        em.merge(car);
        return car;
    }
    
    public Car findCar(String brand, String model)
    {
        Integer counter=0;
        Integer counterDB=1;
        boolean iterationEnd=false;
        Integer elementsInside=em.createQuery("Select c From Car c")
                .getResultList().size();
        String data="";
        Car car = null;
        
        while(iterationEnd==false)
        {
            car=em.find(Car.class, counterDB);
            while(car==null&&counter!=elementsInside)
            {
                counterDB++;
                car=em.find(Car.class, counterDB);
            }
            
            counter++;
            if(counter>elementsInside)
            {
                car=null;
                iterationEnd=true;
            }
            else
                data=car.getBrand().toString();
            
            if(brand.equals(data))
            {
                data=car.getModel().toString();
                if(model.equals(data))
                {
                    iterationEnd=true;
                }
            }
            else
                counterDB++;
        }        
        return car;
    }
    
}
