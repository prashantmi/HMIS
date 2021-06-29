package new_investigation.transactions.controller.action;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.core.MediaType;

//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;











import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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

public class Barcodee extends CSRFGardTokenAction
{
	
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		

		return this.NEW(mapping,form,request,response);
	 
		//return this.NEW(mapping,form,request,response);
	}
	 
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm objForm_p, HttpServletRequest request,
			HttpServletResponse objResponse_p) throws Exception
	{
		 HashMap<String, byte[]> t = new HashMap<String, byte[]>(); 
		
		 String[] sampleno = {"2306R0001","2306R0002","2306R0003"};
		 
		 StringBuffer htmlContents=new StringBuffer();
		   
		
		      for(int i=0;i<sampleno.length;i++)
		      {
	 		Barcode     barcode = BarcodeFactory.createCode128(sampleno[i]);
	 	   OutputStream os=new ByteArrayOutputStream();
	       
	 		barcode.setBarWidth(1);
  		       barcode.setResolution(203);
  		       //barcode.setBarHeight(10);
			  
  		     Font font=new Font("Plain",Font.PLAIN,0);
  		       barcode.setFont(font);
  		     BarcodeImageHandler.writePNG(barcode, os);
  		   String barCodeGenSiString=String.valueOf(sampleno.length);
  		  
  		  ByteArrayOutputStream bos=(ByteArrayOutputStream)os;
  			 byte[] data=bos.toByteArray();
  			 t.put(sampleno[i], data);
  			
  			try {
  				bos.flush();
				os.flush();
				bos.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 		
  			 htmlContents.append("<table width='105%' height='30' cellspacing='0' cellpadding='0' >"); 
  			 htmlContents.append("<tr><td width='50%' ><div id='"+i+"diivBarCodeControl'><img style='margin-left:30px;height: 30px;width:140;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+sampleno[i]+"\" alt=\"no image found in session\" width='100%' ></div></td><td width='50%' align='left' ><div id='"+i+"diivBarCodeControlAll'><img style='margin-left:20px;height: 27px;width:140px;margin-top:5' src=\"/HISInvestigationG5/ShowImageOutofAnArray?sampleno="+sampleno[i]+"\" alt=\"no image found in session\" width='100%' ></div></td></tr>"); 
  			 htmlContents.append("<tr><td width='50%' style='font-size:12px;' ><div id='"+i+"diivBarCodeControl' style='margin-left:80'>"+sampleno[i]+"</div></td><td width='50%' align='left' style='font-size:12px;'><div id='"+i+"diivBarCodeControlAll' style='margin-left:80'>"+sampleno[i]+"</div></td></tr>"); 
  			 htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0' cellpadding='0' style='margin-left:60px'><tr style='height:8 '><td style='font-size:8px; height='10'><b>&nbsp;</b></td><td height='8' style='font-size:8px;'>&nbsp;</td></tr><tr style='height:10 '><td height='8' style='font-size:8px;'>961012563658965/chanda</td></tr><tr><td height='8' style='font-size:8px;'>Biochemistry</td></tr><tr><td height='8' style='font-size:8px;'>28-08-19/29-08-19</td></tr></table></td><td width='50%' height='8'><table cellspacing='0' cellpadding='0' style='margin-left:45px'><tr style='height:8 '><td style='font-size:8px; height='10'><b>&nbsp;</b></td><td height='8' style='font-size:8px;'>&nbsp;</td></tr><tr style='height:10 '><td height='8' style='font-size:8px;'>961012563658965/chanda</td></tr><tr><td height='8' style='font-size:8px;'>Biochemistry</td></tr><tr><td height='8' style='font-size:8px;'>28-08-19/29-08-19</td></tr></table></td></tr>");
  			 htmlContents.append("<input type='hidden' id='barCodeGenSize' value='"+barCodeGenSiString+"' name='barCodeGenSize'/></table>");
  			 
  			 
		      
		      }
		      
		      request.getSession().setAttribute(InvestigationConfig.UPLOADED_FILE_AS_ARRAY, t); 
		      request.getSession().setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
		
	 
				return mapping.findForward("NEW");
		
		
	}
	
}
