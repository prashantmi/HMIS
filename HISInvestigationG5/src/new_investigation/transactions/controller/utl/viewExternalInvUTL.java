package new_investigation.transactions.controller.utl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.SampleAcceptanceDATA;
import new_investigation.transactions.controller.data.viewExternalInvDATA;
import new_investigation.transactions.controller.fb.SampleAcceptanceFB;
import new_investigation.transactions.controller.fb.viewExternalInvFB;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.viewExternalInvVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.UserVO;
public class viewExternalInvUTL extends ControllerUTIL {

	
	public static void showPatDetails(viewExternalInvFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO = ControllerUTIL.getUserVO(request);
     List<viewExternalInvFB> lstInfo=new ArrayList<viewExternalInvFB>();
 	/*List lstfiles=new ArrayList();
	List lstcontenttype=new ArrayList();
	List lstid=new ArrayList();*/
     
	viewExternalInvVO vo=new viewExternalInvVO();
     String reqNos="";
     
		try{	
		
			Map mp=new HashMap(); 
			/*Map mp1=new HashMap();
			Map mp2=new HashMap();
			Map mp3=new HashMap();*/
			//MongoClient mongo = new MongoClient("10.226.2.169", 27017);

			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			//DB db = mongo.getDB("testdb");

			
/*			//fetch in respect to crno
			DBCollection table119 = MongoXmlHandler.getInstance().getDbCollection();
			BasicDBObject document3119 = new BasicDBObject();

			document3119.put("crno", fb.getPatCrNo());
			BasicDBObject document321149 = new BasicDBObject();
		     document321149.put("_id", 0);
			DBCursor c=table119.find(document3119,document321149);
			while(c.hasNext())
			{
				System.out.println(c.next());
			}
		 	StringBuffer sbfilereqno=new StringBuffer();
			  
 			Iterable<DBObject> results=	(Iterable<DBObject>) table119.find(document3119,document321149).iterator();
		 	if(false){
		 		 for (DBObject result : results)
				 	{
				 		
				 		BasicDBList bson19 = ( BasicDBList )result.get("requisitions");
				 		
				 		for(int i=0;i<bson19.size();i++)
				 		{
				 			
				 			
				 			BasicDBObject tempObject=(BasicDBObject)bson19.get(i);
				 			
				 			String reqno=tempObject.get("reqno").toString();
				 			sbfilereqno.append(reqno);
				 			sbfilereqno.append(",");
				 			
				 			
				 		
				 			BasicDBList bson1919 = ( BasicDBList )tempObject.get("files");
				 			
				 			int count=1;
				 			StringBuffer sbfilename=new StringBuffer();
			 	 			StringBuffer sbfileid=new StringBuffer();
			 	 			StringBuffer sbcontenttype=new StringBuffer();
			 			  
				 		
				 			for(int i1=0;i1<bson1919.size();i1++)
				 			{
				 			
				 				
				 				
				 				BasicDBObject tempObject1=(BasicDBObject)bson1919.get(i1);
				 			String filename=	tempObject1.get("filename").toString();
				 			String fileid=tempObject1.get("_Id").toString();
				 			String contentType=tempObject1.get("contenttype").toString();
				 			sbfilename.append(filename);
				 			sbfilename.append("#");
				 			sbfileid.append(fileid);
				 			sbfileid.append("#");
				 			sbcontenttype.append(contentType);
				 			sbcontenttype.append("#");
				 			
				 				//System.out.println("Files "+count+":"+tempObject1.get("filename"));
				 				//System.out.println(tempObject1.get("_Id").toString());
				 				count++;
				 			}
				 			String fileName=sbfilename.toString();
				 			String contenttype=sbcontenttype.toString();
				 			String id=sbfileid.toString();
				 		
				 			lstfiles.add(fileName);
				 			lstcontenttype.add(contenttype);
				 			lstid.add(id);
				 			
				 			//System.out.println("fileid"+sbfileid.toString());
				 			//System.out.println("filename"+sbfilename.toString());
				 			//System.out.println("reqno"+sbfilereqno.toString());
				 			//System.out.println("contenttype"+sbcontenttype.toString());
				 			
				 			//System.out.println(bson1919.size());
				 			//tempObject.get("files[0]");
				 	
				 	
				 		
				 		
				 			
				 		}
				 			
						   sbfilereqno.deleteCharAt(sbfilereqno.length() - 1);
				 		    reqNos=sbfilereqno.toString();
				 		  // System.out.println(reqNos);
				 	
				 	}
		 	
		 	
		     mp1.put(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESNAME, lstfiles);
		     mp2.put(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESCONTENTTYPE, lstcontenttype);
		     mp3.put(InvestigationConfig.LIST_PATIENT_VIEW_EXTERNAL_PROCESS_ESSENTIALS_VO_FILESID, lstid);
		 	}*/
		 		
			// HelperMethods.populate(vo,fb);
		 		
		 		vo.setPatCrNo(fb.getPatCrNo());
		 		mp=viewExternalInvDATA.showPatDetails(vo, userVO,reqNos);
			
		 		WebUTIL.setMapInSession(mp, request);
		 		/*WebUTIL.setMapInSession(mp1, request);
		 		WebUTIL.setMapInSession(mp2, request);
		 		WebUTIL.setMapInSession(mp3, request);*/
				
				objStatus.add(Status.TRANSINPROCESS);
			
			
			
			
		
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}		

	    catch (MongoException e) {
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Mongo Exception");
	    	e.printStackTrace();
	    }
		

	}
	
	
	public static void printReport(viewExternalInvFB fb, HttpServletRequest objRequest_p,HttpServletResponse response)
	{
		
		 
		HttpSession session=objRequest_p.getSession();
		String strMsgText = "";
		String  strTestCombo="";
		Map mp=new HashMap();
		Status status = new Status();
		List<byte[]> pdfs = new ArrayList<byte[]>();
		

		try
		{
			
			byte[] Pdf=MongoXmlHandler.getInstance().latestFetchFile1(fb.getFileName());
			/*String base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(Pdf);
			byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(base64EncodedString);
			OutputStream out = new FileOutputStream("E:/out2.pdf");
	        out.write(bytearray);
	        out.close();  
			*/
			
			pdfs.clear();
			pdfs.add(Pdf);	
		if(!fb.getContentType().equals("application/pdf"))
				{
			response.setContentType(fb.getContentType());
			

			 response.setHeader("Cache-Control", "no-cache");
				OutputStream output = response.getOutputStream();
				OutputStream os = response.getOutputStream();
				os.flush();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				bos.flush();
				bos.write(Pdf, 0, Pdf.length);
				MergeAllPdfNewInv.concatallPDFs1(pdfs, bos, null,objRequest_p,fb);		
				output.flush();
				output.close();
				bos.close();
				
				
			
				}
		else
		{
		 response.setContentType(fb.getContentType());
		/* response.setHeader("Content-Disposition","attachment;filename="+fb.getContentType()+"");
		 InputStream in = new ByteArrayInputStream(Pdf);

     	ServletOutputStream out = response.getOutputStream();
     	 
     	byte[] outputByte = new byte[4096];
     	//copy binary contect to output stream
     	while(in.read(outputByte, 0, 4096) != -1)
     	{
     		out.write(outputByte, 0, 4096);
     	}
     	in.close();
     	out.flush();
     	out.close();*/

			 response.setHeader("Cache-Control", "no-cache");
				OutputStream output = response.getOutputStream();
				MergeAllPdfNewInv.concatallPDFs(pdfs, output, null,objRequest_p);		
				output.flush();
				output.close();
		}		//return;
			
		}
		catch (Exception e)
		{
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
			//HisException eObj = 
			new HisException(strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
			//WebUTIL.setStatus(objRequest_p, status);
		}
	
	}	
	
	
}
