/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.ejb;

import data.Images;
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
public class ImageBean {
    
    
    @PersistenceContext
    private EntityManager em;
    
    public Images takeImage()
    {
        Images images = em.find(Images.class, 1);
        return images;
    }
    
    
}
