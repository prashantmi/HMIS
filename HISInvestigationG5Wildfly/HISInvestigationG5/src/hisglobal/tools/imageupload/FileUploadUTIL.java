package hisglobal.tools.imageupload;


import hisglobal.tools.imageupload.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;


public class FileUploadUTIL {
public static String getFileName()
{
	String fileName="";
	return fileName;

}
public static void renderImages(HttpServletRequest request,FileUploadFB fb)
{
	System.out.println("---------------------------inside render image function in UTIL File");
	String[] path={"C:/imageUploadTesting/image1.jpg","C:/imageUploadTesting/image2.jpg","C:/imageUploadTesting/image3.jpg","C:/imageUploadTesting/image4.jpg","C:/imageUploadTesting/image5.jpg","C:/imageUploadTesting/photo01.jpg","C:/imageUploadTesting/photo02.jpg","C:/imageUploadTesting/photo03.jpg","C:/imageUploadTesting/photo04.jpg","C:/imageUploadTesting/photo05.jpg","C:/imageUploadTesting/photo06.jpg","C:/imageUploadTesting/photo07.jpg","C:/imageUploadTesting/photo08.jpg","C:/imageUploadTesting/photo09.jpg","C:/imageUploadTesting/photo10.jpg"};
	FormFile myFile = fb.getUploadedFileName();
   // String contentType = myFile.getContentType();
    //String fileName    = myFile.getFileName();
    //int fileSize       = myFile.getFileSize();
    List<byte[]> imageList=new ArrayList<byte[]>();
   
    try
    {
    	for(String filePath:path)
    	{
    		 File file=new File(filePath);
    		InputStream is = new FileInputStream(file);
    	    
            // Get the size of the file
            long length = file.length();
        
            // You cannot create an array using a long type.
            // It needs to be an int type.
            // Before converting to an int type, check
            // to ensure that file is not larger than Integer.MAX_VALUE.
            if (length > Integer.MAX_VALUE) {
                // File is too large
            }
        
            // Create the byte array to hold the data
            byte[] bytes = new byte[(int)length];
        
            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "+file.getName());
            }
        
            // Close the input stream and return bytes
            is.close();
           
    	imageList.add(bytes);
    	
    	}
    }catch(Exception e){e.printStackTrace();}
    System.out.println("imageList size is equal to----------"+imageList.size());
    request.getSession().setAttribute("IMAGELIST",imageList);
    
    
}

//end of the file
}
