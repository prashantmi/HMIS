package hisglobal.tools.imageeditor.servlets;


//import FileOutputStream;
//import HttpServletRequestWrapper;
//import InputStream;
//import StringWriter;
//import ServletOutputStream;
import hisglobal.tools.imageeditor.HisImageEditorConfig;
import hisglobal.tools.imageeditor.util.ImageSerialObject;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.servlet.http.*;

import com.ibm.wsdl.util.IOUtils;

import java.nio.charset.*;
import java.nio.file.Path;
import java.io.*;

import javax.servlet.*;
import java.nio.charset.StandardCharsets;

public class ImageEditorServlet extends HttpServlet
{
	private String cMode; 
	private String inFileName;
	private String outFileName;
	private String inFilePath;
	private String outFilePath;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		 
	    // Added by VASU on 16-Aug-2017
		    PrintWriter out = response.getWriter();
		    response.setContentType("text/plain");

		    StringBuffer jb = new StringBuffer();
		    String line = null;
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		        jb.append(line);

		    String img64 = jb.toString(); 
		    String res[] = img64.split("%");
		           img64 = res[3];
		    //check if the image is really a base64 png, maybe a bit hard-coded
		    /*if(img64 != null && img64.startsWith("data:image/png;base64,")){*/
		       if(img64 != null && img64.startsWith("data:image/png;base64,")){
		        //Remove Content-type declaration
		        img64 = img64.substring(img64.indexOf(',') + 1);            
		    }else{
		        response.setStatus(403);
		        out.print("Format of image is not correct!");
		        return;
		    }   
		    try{
		        InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(img64.getBytes()));  
		        BufferedImage bfi = ImageIO.read(stream);
		        //String path = getServletConfig().getServletContext().getRealPath("/../HIS.war/hisglobal/images/testSave/testsaveK.png");
		        String path = getServletConfig().getServletContext().getRealPath("/../HIS.war/hisglobal/images/ImgForImgEdtr/"+res[1]+"-"+res[2]+"-"+res[0]+".png");
		        //path = "C:/PHDM/HISClinical/ImageFromImgEdtr/testsave.png";
		        
		        /*String path1 = getServletContext().getRealPath("HIS/hisglobal/images/testSave/myImage.png");
		        path1 = "/10.226.19.34:8480/HIS/hisglobal/images/testSave/myImage.png";*/
		        
		        File outputfile = new File(path);
		        outputfile.createNewFile();
		        ImageIO.write(bfi , "png", outputfile);
		        bfi.flush();
		        response.setStatus(200);
		        
		        /*ByteArrayOutputStream bfi = new ByteArrayOutputStream();
		        byte[] imageInByte = bfi.toByteArray();*/
		        request.getSession().setAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM, bfi);
		        out.print("Success");         
		    }catch(IOException e){  
		        e.printStackTrace();
		        response.setStatus(500);
		        out.print("error while saving image: " + e.getMessage());      
		    }
        // END VASU
		
		   /* Part part = request.getPart("myImg");
		    BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream(),
		        Charset.forName("utf-8")));
		    String sImg = br.readLine();
		    sImg = sImg.substring("data:image/png;base64,".length());
		    byte[] bImg64 = sImg.getBytes();
		    byte[] bImg = Base64.decodeBase64(bImg64); // apache-commons-codec
		    FileOutputStream fos = new FileOutputStream("img.png");
		    fos.write(bImg);*/
		
	}
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("in HIS Image Editor doGet(...)");
		try
		{
			this.cMode=request.getParameter("cMode");
		}
		catch (Exception e)
		{
			System.out.println("Error : Reading Command Mode :: "+e);
			e.printStackTrace();    
		}

		if(this.cMode!=null)
		{
			if(this.cMode.equals("READIMAGE"))
			{
				try
				{
					this.inFileName=request.getParameter("inFile");
					String key=request.getParameter("inFilePath");
					this.inFilePath=getPathFromConfig(key);
					if(this.inFileName!=null)
					{
						readImage(request, response);
					}
				}
				catch (Exception e)
				{
					System.out.println("Error : In File Name & Path Read :: "+e);
					e.printStackTrace();
				}
			}
		}

	}


	private void readImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Getting Image from File
		BufferedImage image=null;
		try
		{
			File file=new File(this.inFilePath, this.inFileName);
			image=ImageIO.read(file);
		}
        catch (IOException e)
        {
			System.out.println("Error : Reading Image From File :: "+e);
        }
		
        // Sending Image To Applet
		ObjectOutputStream outputToApplet;
        try
        {
            outputToApplet = new ObjectOutputStream(response.getOutputStream());
        	//response.setContentType("text/html");
            ImageSerialObject ih = new ImageSerialObject();
            ih.setImage(image);
            outputToApplet.writeObject(ih);
            outputToApplet.flush();
            outputToApplet.close();
        }
        catch (IOException e)
        {
			e.printStackTrace(); 
        }
	}

	private void saveImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Getting Image from Applet
		ObjectInputStream inputFromApplet = null;
		BufferedImage image=null;
		PrintWriter out = null;
		try
		{  
			// get an input stream from the applet
            String imageString = request.getParameter("imageData");
            imageString = imageString.substring("data:image/png;base64,"
                    .length());
            byte[] contentData = imageString.getBytes();
            byte[] decodedData = Base64.decodeBase64(contentData);
           /* String imgName = ReloadableProps.getProperty("local.image.save.path")
                    + String.valueOf(System.currentTimeMillis()) + ".png";
            fos = new FileOutputStream(imgName);*/

            ByteArrayInputStream in = new ByteArrayInputStream(decodedData);
            
            inputFromApplet = new ObjectInputStream(in);
	        ImageSerialObject ih = (ImageSerialObject) inputFromApplet.readObject();
	        image=ih.getImage();
	        
	        // Setting Image in Session in Form of BufferedImage
	        request.getSession().setAttribute(HisImageEditorConfig.HIS_IMAGE_EDITOR_OUTPUT_IMAGE_DATA_STREAM, image);
	        
	        inputFromApplet.close();
	        
            out = new PrintWriter(response.getOutputStream());
            response.setContentType("text/plain");
            out.println("confirmed");
            out.flush();
            out.close();          
        }
        catch (Exception e)
        {
			e.printStackTrace();    
        }
        
        // Saving To File
        try
        {
        	//if(!this.outFileName.substring(this.outFileName.indexOf(".")).equalsIgnoreCase("png"))
        	
        	//Should be PNG or JPG or JPEG   ... but not GIF 
        	String imageFormat=this.outFileName.substring(this.outFileName.indexOf(".")+1).toLowerCase();
        	File dir = new File(this.outFilePath);
			if (!dir.exists())
			{	
				//dir.mkdir();
				String path = dir.getPath();
				String splitter = null;
				if (path.contains("/")) splitter = "/";
				else if (path.contains("\\")) splitter = "\\\\";
				String[] paths = path.split(splitter);
				String createPath = paths[0];
				for(int i=1; i<paths.length; i++)
				{
					createPath+=splitter+paths[i];
					File _dir = new File(createPath);
					if(!_dir.exists()) _dir.mkdir();				
				}				
			}
        	File file = new File(this.outFilePath, this.outFileName);
        	ImageIO.write(image, imageFormat, file);        	
        }
        catch (Exception e)
        {
			e.printStackTrace();    
        }
	}
	
	private String getPathFromConfig(String key)
	{
		String path="";
		
		char ch=(char) (key.charAt(0));
		ch-=32;
		key=key.substring(1,key.length());
		key=String.valueOf(ch)+key;
		HisImageEditorConfig cofigObject=new HisImageEditorConfig();
		Method method[]= cofigObject.getClass().getMethods();
		try
		{
			for(int j=0;j<method.length;j++)
				if(method[j].getName().equals("get"+key))
					path=(String)method[j].invoke(null,null);
		}
		catch(Exception e)
		{
			System.out.println("Error : Getting path From Config of Key "+key+":: "+e);
		}
		return path;
	}
	
}

