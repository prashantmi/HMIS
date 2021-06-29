/* Module Name: Global
*  Name of Process: Upload Image 
*  Name of Developer: Sh. Ashwini Mishra
*  Name of TL: Sh. Ashwini Mishra 
*  Date of Creation: 07-09-2014
*/

package hr.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
 
public class UploadAction extends ActionSupport implements
        ServletRequestAware {
    public File userImage;
    public String userImageContentType;
    public String userImageFileName;
    public String flag;
    public String elementName;
    public String fileName;
    
    
 
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public HttpServletRequest servletRequest;
 
    public String execute() {
    	System.out.println("inside execute().............. ");
        try {
            String filePath = "C:/"; // servletRequest.getSession().getServletContext().getRealPath("/");
            System.out.println("Server path:" + filePath);
            System.out.println("this.userImageFileName=="+this.userImageFileName);
            File fileToCreate = new File(filePath, this.userImageFileName);
 
            FileUtils.copyFile(this.userImage, fileToCreate);
            setFileName(this.userImageFileName);
            
            //newwwwww
            
            BufferedImage originalImage = ImageIO.read(fileToCreate);
            
         // convert BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte;
            imageInByte = baos.toByteArray();
            baos.close();
            
            
    		HttpSession session=servletRequest.getSession();
    		session.setAttribute("uploadedfile",imageInByte);
    		session.setAttribute("uploadedfileName",fileName);
    		
    		 this.flag="on";
    		
    		///////////////////
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
 
            return INPUT;
        }
        return SUCCESS;
    }
    
    public void save() {
    	System.out.println("inside execute().............. ");
        try {
            String filePath = "C:/"; // servletRequest.getSession().getServletContext().getRealPath("/");
            System.out.println("Server path:" + filePath);
            System.out.println("this.userImageFileName=="+this.userImageFileName);
            File fileToCreate = new File(filePath, this.userImageFileName);
 
            FileUtils.copyFile(this.userImage, fileToCreate);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
 
            //return INPUT;
        }
       // return SUCCESS;
    }
    
  
 
    public File getUserImage() {
        return userImage;
    }
 
    public void setUserImage(File userImage) {
        this.userImage = userImage;
    }
 
    public String getUserImageContentType() {
        return userImageContentType;
    }
 
    public void setUserImageContentType(String userImageContentType) {
        this.userImageContentType = userImageContentType;
    }
 
    public String getUserImageFileName() {
        return userImageFileName;
    }
 
    public void setUserImageFileName(String userImageFileName) {
        this.userImageFileName = userImageFileName;
    }
 
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
 
    }

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
}