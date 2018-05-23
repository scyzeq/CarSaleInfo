/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author scyzeq
 */
@ManagedBean
@RequestScoped
public class MenuView implements Serializable{
    
    public String carControl(int choice)
    {
        if(choice==1)
        {
            return "newcar";
        }
        else if(choice == 2)
        {
            return "assigncar";
        }
        else
            return "index";
    }
    
    public String personControl(int choice)
    {
        if(choice==1)
        {
            return "newperson";
        }
        else
            return "index";
    }    
    
    public String additionalControl(int choice)
    {
        if(choice==1)
        {
            return "uploadimage";
        }
        else
            return "index";
    }
}
