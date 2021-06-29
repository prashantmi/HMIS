package new_investigation.transactions.controller.utl;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.core.MediaType;

//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;








import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;


//import org.json.JSONObject;
//import org.json.JSONArray;










import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.linear.twoOfFive.Int2of5Barcode;
import net.sourceforge.barbecue.linear.twoOfFive.Std2of5Barcode;
import net.sourceforge.barbecue.output.OutputException;
import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invRaisingCumSamCollectionFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
public class Barcodee {

	
	
	 public static void main(String args[]) throws OutputException, BarcodeException{  
	 
		 HashMap<String, byte[]> t = new HashMap<String, byte[]>(); 
		 String SampleNo="2366R0001";
		 StringBuffer htmlContents=new StringBuffer();
		      OutputStream os=new ByteArrayOutputStream();
		      
		      HttpServletRequest _request=new HttpServletRequest ();
				HttpSession session=_request.getSession();

		      for(int i=0;i<1;i++)
		      {
	 		Barcode     barcode = BarcodeFactory.createCode128(SampleNo);
 			
	 		barcode.setBarWidth(1);
  		       barcode.setResolution(203);
  		       //barcode.setBarHeight(10);
			  
  		     Font font=new Font("Plain",Font.PLAIN,0);
  		       barcode.setFont(font);
  		     BarcodeImageHandler.writePNG(barcode, os);
  		   String barCodeGenSiString=String.valueOf(1);
  		  
  		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
  			 byte[] data=bos.toByteArray();
  			 t.put(SampleNo, data);
  			
  			try {
  				bos.flush();
				os.flush();
				bos.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 		
  			htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
            htmlContents.append("<tr><td width='50%'  ><div id='"+i+"diivBarCodeControl'><img style='margin-left:-13px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+SampleNo+"\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:-25px;height: 30px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+SampleNo+"\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
            htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='"+i+"diivBarCodeControl'><center>"+SampleNo+"</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='"+i+"diivBarCodeControlAll'><center>"+SampleNo+"</center></div></td></tr>");
            
            htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0' cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>961012356985698</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>Biochemistry</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>30-08-19/30-08-19</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>Chandan Gupta</td></tr></table></td><td width='50%' height='8'><table cellspacing='0' cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>961012356985698</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>Biochemistry</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>30-08-19/30-08-19</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>Chandan Gupta</td></tr></table></td></tr>");
            
          //  htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:30px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"/"+datee+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patName+"</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[0]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab Name:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[4]+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Req/Col Date:</b></td><td height='8' style='font-size:9px;'>"+arrSaveString[5]+"/"+datee+"</td></tr><tr><td height='8' style='font-size:9px;'><b>Pt:</b></td><td height='8' style='font-size:9px;'>"+patName+"</td></tr> </table></td></tr>");
            
            
            
            htmlContents.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
		      
		      }
		      
		      session.setAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY, t); 
			 	session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
			 	
	 
	 
	 }  
	 
	
}
