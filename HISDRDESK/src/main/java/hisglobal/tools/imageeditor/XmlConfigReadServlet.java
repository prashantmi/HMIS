package hisglobal.tools.imageeditor;

 /*This is my servlet program*/  
   

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;
import investigation.cacheImplementation.cachemanager.InvestigationDocumentCacheManager;

  
//import javax.media.util.ImageToBuffer;
  
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


 public class XmlConfigReadServlet extends HttpServlet {  
	 
	
     private Object receiveObject( HttpServletRequest req ) throws Exception  
     {  
         ObjectInputStream in = new ObjectInputStream( req.getInputStream() );  
         Object obj = in.readObject();  
         in.close();  
         return obj ;  
     }  
     private void sendObject( HttpServletResponse resp , Object obj )  
     throws Exception  
     {  
         ObjectOutputStream out = new ObjectOutputStream( resp.getOutputStream() );  
         out.writeObject( obj );  
         out.close();  
     }  
     public void doGet(HttpServletRequest req , HttpServletResponse  
             resp){  
         doPost(req,resp);  
     }  
   
     
     public void doPost( HttpServletRequest req , HttpServletResponse  
             resp )  
     {  
    	 UserVO userVO=ControllerUTIL.getUserVO(req);
    	 InvestigationDocumentCacheManager investigationDocumentCacheManager= InvestigationDocumentCacheManager.getInstance();
    	 DocumentBuilder documentBuilder=investigationDocumentCacheManager.getDocumentBuilder();;
 		try {
 			Document xmlConfigurator=null;
 			
    	
    		
    		String mode=req.getParameter("mode");
    		
    		if(mode==null)
    			xmlConfigurator=documentBuilder.parse(InvestigationDocumentCacheManager.getInstance().getClobObjectFromDatabaseAppletConfiguratorDocument(userVO.getHospitalCode()));//path+"appletConfigurator.xml");
    		else
    			xmlConfigurator=documentBuilder.parse(InvestigationDocumentCacheManager.getInstance().getClobObjectFromDatabaseImageUtilityConfDocument(userVO.getHospitalCode()));//path+"appletConfigurator.xml");
    		
    		
        	
        	
        	
    		TransformerFactory transformerFactory=TransformerFactory.newInstance();
    		Transformer transformer =  transformerFactory.newTransformer();
    		transformer.transform(new DOMSource(xmlConfigurator), new StreamResult(resp.getOutputStream()) );
        	
    		          
             //sendObject( resp , str);  
             System.out.println("XmlConfigReadServlet: ImageCreated and send Object");
         } catch ( Exception e ){  
             System.out.println( "Clang! Thunk: " + e );  
         }  
     }   
     
 }   
     
 
 
 