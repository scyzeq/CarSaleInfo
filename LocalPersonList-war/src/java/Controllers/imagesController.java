/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import data.Images;
import data.ejb.ImageBean;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import javax.faces.context.FacesContext;

/**
 *
 * @author scyzeq
 */
@ManagedBean
@RequestScoped
public class imagesController implements Serializable{
    
    
    @EJB
    private ImageBean imageBean;
    
    private Images dispImage;
    
    private UploadedFile file;
    
    private StreamedContent fileContent;

//    public StreamedContent getFileContent() {
//        logger.trace("Entered method getFileContent.");
//          ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//          String photoId = externalContext.getRequestParameterMap().get("photo_id");
//          if(photoId  == null || photoId.equals(""))
//          {
//           fileContent = defaultFileContent;
//           logger.info("Id was null or empty. Retrieved default file content.");
//          }
//          else
//          {
//           int parsedId = Integer.parseInt(photoId);
//           if(parsedId < 0 || parsedId > ImageBean.MAX_IMAGE_COUNT)
//           {
//            fileContent = defaultFileContent;
//            logger.info("Invalid Id. Retrieved default file content.");
//           }
//           ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//           InputStream inputStream = contextClassLoader.getResourceAsStream("resources/images/Photo - " + parsedId + ".png");
//           fileContent = new DefaultStreamedContent(inputStream, "image/png");
//           logger.info("Retrieved file content for image {}.", parsedId);
//          }
//          logger.trace("Exited method getFileContent.");
//        return fileContent;
//    }

    public void setFileContent(StreamedContent fileContent) {
        this.fileContent = fileContent;
    }
    
    public ImageBean getImageBean() {
        return imageBean;
    }

    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    public Images getDispImage() {
        return dispImage;
    }

    public void setDispImage(Images dispImage) {
        this.dispImage = dispImage;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void upload() throws IOException
    {       
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?zeroDateTimeBehavior=convertToNull","root","root");

            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement("Insert Into images (image) values (?)");

            statement.setBinaryStream(1, file.getInputstream());

            statement.executeUpdate();

            connection.commit();
            connection.close();
        }
        catch(Exception e)
        {}
    }
    
    public StreamedContent showImage() throws IOException, SQLException
    {
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        byte[] imageByte = null;

        Images images = null;

        dispImage=imageBean.takeImage();
            
//        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
//        // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
//        return new DefaultStreamedContent();
//        }
//        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String id = context.getExternalContext().getRequestParameterMap().get("id");
//            Image image = service.find(Long.valueOf(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(dispImage.getImage().getBytes(1, 10000)));
//        }
        
//        try
//        {
//            byte[] imageByte = null;
//            
//            Images images = null;
//            
//            Class.forName("com.mysql.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars?zeroDateTimeBehavior=convertToNull","root","root");
//            
//            PreparedStatement statement = connection.prepareStatement("Select id, images from images ORDER BY id DESC LIMIT 1");
//            
//            ResultSet rs = statement.executeQuery();
//            
//            while(rs.next())
//            {
//                imageByte = rs.getBytes("file");
//            }            
//            connection.close();
//            
//        }
//        catch(Exception e)
//        {
//            
//        }
    }
}
