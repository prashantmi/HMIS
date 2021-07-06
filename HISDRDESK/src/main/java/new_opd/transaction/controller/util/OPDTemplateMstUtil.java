package new_opd.transaction.controller.util;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import HisWeb.webservice.TemplatePrinter;
import hisglobal.utility.HisUtil;
import new_opd.transaction.controller.fb.DoctorDeskFB;
import new_opd.vo.OPDTemplateMstVO;

public class OPDTemplateMstUtil {
	
	
	 public static String getPrintBase64(String printData, HttpServletRequest request ,String myJSON , String varid,String imgs) throws Exception {
		 String logoPath="/opt/AIIMSBBW/AHIMSG5/reportimage/logo.jpg"; //linux path by default
		// String logoPath="/opt/AIIMSP/AHIMSG5/reportimage/logo.jpg"; //linux path by default mangalgiri
		 Map<String,Object> tmpData=null;byte[] encodedBytes=null;
		 try {
			 	 
			 if(System.getProperties().getProperty("os.name").startsWith("Win"))
				 logoPath =	"C:\\AIIMSBBW\\AHIMSG5\\reportimage\\logo.jpg" ;  //if windows
			 	 
			  Date dt = new Date();
		      SimpleDateFormat dateFormat;
		      dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:MM");

			  JSONObject obj=new JSONObject(myJSON);
			 
			 
				String patDtl="<table>"
						
						+ "<tr>"
						+"<td>"
							+"<img alt='hospital_logo' src="+logoPath+" height='80px'>" 
							+"</td>"
							+"<td colspan='3'><h3>"+obj.get("strHospitalName")+"</h3><h5 align='center'>"+obj.get("strHospitalAddres")+"</h5></td>"
							+"</tr></table>"
							+"<table><tr><td align='center'><u><font color='blue'>OPD Prescription</font></u></td></tr></table>"
							+"<p>_____________________________________________________________________________</p>"
							+"<table width='100%' style='font-size:small;font-family:Arial'>"
							+"<tr >"
							+"<td style='font-weight: bold;'>Patient Name:</td>" 
							+"<td>"+obj.get("pat_Name")+"</td>"
							+"<td style='font-weight: bold;'>CR No:</td>"
							+"<td>"+obj.get("CR_No")+"</td>"
						+"</tr>"
						
						+"<tr>"
						+ "<td style='font-weight: bold;'>Age/Gender:</td>"
							+ "<td>"+obj.get("patAge")+"/"+obj.get("patGender")+"</td>"
							+ "<td style='font-weight: bold;'>Department(Unit):</td>"
							+ "<td>"+obj.get("patGeneralDtl").toString().split("#")[1]+"</td>"
						+ "</tr>"
						
						+ "<tr>"
							+ "<td style='font-weight: bold;'>Visit Date:</td>"
							+ "<td>"+obj.get("patGeneralDtl").toString().split("#")[7]+"</td>"
							+ "<td style='font-weight: bold;'>Mobile No:</td>"
							+ "<td>"+obj.get("patQueueNo")+"</td>"
						+ "</tr>"
							
						+ "</table>" 
						+ "<p>_____________________________________________________________________________</p>"
						
					//	+"<p><font align='left' size='x-large;'><b>"+"Rx: "+obj.get("tempName")+"</b></font><br><br>";
						+"<p style='font-size:medium;font-family:Arial;font-weight: bold'>"+"Rx: "+obj.get("tempName")+"</p><br><br>";
						printData=patDtl+printData;
						//printData+="<br><br><br><p style='font-size:medium;font-family:Arial;font-weight: bold'>Signature of Consultant/Resident:<p><font size='medium;'>"+obj.get("patconsultantname").toString()+"</font></p><p style='font-size:medium;font-family:Arial'>"+dateFormat.format(dt)+"</p>";
						printData+="<br><br><br><p style='font-size:medium;font-family:Arial;font-weight: bold'>Signature of Consultant/Resident:</p><p style='font-size:medium;font-family:Arial'>"+obj.get("strUSerName").toString()+"</font></p><p style='font-size:medium;font-family:Arial'>"+dateFormat.format(dt)+"</p>";
				
					
					tmpData=new HashMap<String, Object>();
					
					tmpData.put("PRINTDATA", printData);
					tmpData.put("IMAGES", imgs);
					tmpData.put("FOOTER", obj.get("CR_No").toString());
					
					encodedBytes=getTemplatePrint(tmpData,request);
					uploadpdf(  encodedBytes, myJSON , varid);

				
		 }catch(Exception e) {
			e.printStackTrace(); 
		 }
				
				
				
			 return new String (encodedBytes);
				
	 
		 
	 }
	
	
	 public static byte[] getTemplatePrint(Map printData, HttpServletRequest request) throws Exception {
		 TemplatePrinter tmp=null;
		 tmp=(tmpD,req)-> {
			 final StringReader templatePrint;
			 String paths=req.getSession().getServletContext().getRealPath("/hisglobal/template/tempHelper"),prData="",compPaths="";
			 JSONArray imgs=null;
			 Document pdfDoc=null;
			 PdfWriter writer =null;
			 byte[] input_file =null,data=null,encodedBytes=null; 
			 try{
				 prData=tmpD.get("PRINTDATA").toString();
				 JSONObject object=new JSONObject(tmpD.get("IMAGES"));
				 imgs= (JSONArray) object.get("images");

				 
				 
				  pdfDoc = new Document(PageSize.A4); writer = PdfWriter.getInstance(pdfDoc,
						   new FileOutputStream(new File(paths,"printTemp.pdf"))); 
				  		   pdfDoc.open();
						   HTMLWorker htmlWorker = new HTMLWorker(pdfDoc);				  
								  for (int i=0;i<imgs.length();i++) {
									  try( OutputStream stream = new BufferedOutputStream(new FileOutputStream(paths+"\\tmp\\"+"imgTmp_"+i+".png"),(8 * 1024))){
										  data = Base64.decodeBase64(imgs.get(i).toString().split(";base64,")[1]);									  
										  stream.write(data);						
										  compPaths=paths+"\\tmp\\"+"imgTmp_"+i+".png";
										  compPaths=compPaths.replace("\\", "\\\\");								  
										  prData=prData.replaceFirst("imgTmp_"+i,compPaths);					  
									  }catch(Exception e) {
										   e.printStackTrace();
									   }
						   }

				  
				  templatePrint= new StringReader(prData);
				  htmlWorker.parse(templatePrint);
				    
				  
				  PdfPageEventHelper WatermarkPageEvent1 =new PdfPageEventHelper(){
					  
					  @Override public void onEndPage(PdfWriter writer, Document document) {
					  
					  try { ColumnText.showTextAligned(writer.getDirectContent(),
					  Element.ALIGN_CENTER, new Phrase(tmpD.get("FOOTER").toString()), 110, 30, 0); }
					  catch (Exception e) { 
						  // TODO Auto-generated catch block 
						  e.printStackTrace();
					  }
					  
					  ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
					  new Phrase("page " +
					  (writer.getCurrentPageNumber()+"of"+writer.getPageNumber())), 550, 30, 0);
					  
					  };
					  
					  };WatermarkPageEvent1.onEndPage(writer, pdfDoc);

					  pdfDoc.close(); writer.flush(); writer.close(); 
					  
					  
					  if(System.getProperties().getProperty("os.name").startsWith("Win"))
						  input_file = Files.readAllBytes(Paths.get(paths+"\\printTemp.pdf")); 
					  else 
						  input_file = Files.readAllBytes(Paths.get(paths+"/printTemp.pdf")); 
					  			  
					  encodedBytes = Base64.encodeBase64(input_file); 
						 
					  }catch(Exception e){ e.printStackTrace(); }
					  
					  finally{ 
					  pdfDoc.close();
					  writer.flush();writer.close(); 
					  data=null;input_file=null;
					  }
			 		return encodedBytes;
					  
					  
					  
			};
		
			return tmp.genTempPrint(printData, request);
	 }
	
	public static final String FtpCredentilas="ftp://nimsftp:nims_123@10.10.10.76/ftpserver/OPDLiteTemplate/";
	 
	@SuppressWarnings("resource")
	public static void  UplodaHTML(OPDTemplateMstVO vo) {
		OutputStream ios=null;
		final	String ftpfilepath=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode()+"/"+vo.getStrTemplateNo()+".html";	
		//final	String ftpfilepath1=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode()+"/"+vo.getStrTemplateNo()+"_Modify.html";	
		//System.out.println("ftp path:::::::::::::::::::::::::::::::::::::::::::::::"+OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode());
		setCreantials(OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode());
		
		//ftp://ftpodisha:FTPlogin!23@10.10.10.54     /ftpserver/ftpserver/+OPDLiteTemplate/33201
		//setCreantials("ftpserver/OPDLiteTemplate/"+vo.getStrHospitalCode());
		
		URL urlftp=null;
		URLConnection urlc=null;
		//FileInputStream inputStream = null;
		
		try
		{
			//System.out.println("ftpfilepath  :"+ftpfilepath);
			urlftp =new URL(ftpfilepath);
			urlc=	urlftp.openConnection();
			ios=urlc.getOutputStream();
			ios.write(vo.getStrHtmlString().getBytes());
			//System.out.println("File Uploaded");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			//System.out.println("<!-- CreateRequisitionDirectory -->");
			//ex.printStackTrace();
		}
		finally
		{
			
			
			if(ios!=null)
			{
				try {
					ios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void  UplodaModifyHTML(OPDTemplateMstVO vo) {
		OutputStream ios=null;
		final	String ftpfilepath1=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode()+"/"+vo.getStrTemplateNo()+"_Modify.html";	
		URL urlftp=null;
		URLConnection urlc=null;
		//FileInputStream inputStream = null;
		
		try
		{
			//System.out.println("ftpfilepath  :"+ftpfilepath1);
			urlftp =new URL(ftpfilepath1);
			urlc=	urlftp.openConnection();
			ios=urlc.getOutputStream();
			ios.write(vo.getStrModifyData().getBytes());
			//System.out.println("Modify File Uploaded");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			//System.out.println("<!-- CreateRequisitionDirectory -->");
			//ex.printStackTrace();
		}
		finally
		{
			
			
			if(ios!=null)
			{
				try {
					ios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}
	
	
	 public static void main(String[] args) {
		// getHtmlFileFromFTP();
	}
	public static String  getHtmlFileFromFTP(String strTemplateFileCode , String HospCode) {
		InputStream ios=null;
		final	String ftpfilepath=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+HospCode.trim()+"/"+strTemplateFileCode.trim()+".html";	
		URL urlftp=null;
		URLConnection urlc=null;
		//FileInputStream inputStream = null;
		StringBuilder content = new StringBuilder();
		try
		{
			//System.out.println("ftpfilepath  :"+ftpfilepath);
			urlftp =new URL(ftpfilepath);
			urlc=	urlftp.openConnection();
			ios=urlc.getInputStream();
			
			 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

			 String line;

		      // read from the urlconnection via the bufferedreader
		      while ((line = bufferedReader.readLine()) != null)
		      {
		        content.append(line + "\n");
		      }
		      bufferedReader.close();
		    
			//ios.read();  //(vo.getStrHtmlString().getBytes());
		//System.out.println(" Html Content:::::  "+content.toString());
			return content.toString();
		}
		catch(Exception ex)
		{
			
			
			ex.printStackTrace();
			//System.out.println("<!-- Get Data from html directory -->");
			//ex.printStackTrace();
			return "FTP Access Required";
		}
		finally
		{
			
			
			if(ios!=null)
			{
				try {
					ios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
		
	}
	public static String  getModifyHtmlFileFromFTP(String strTemplateFileName , String HospCode) {
		InputStream ios=null;
		final	String ftpfilepath=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+HospCode.trim()+"/"+strTemplateFileName.trim()+"_Modify.html";	
		URL urlftp=null;
		URLConnection urlc=null;
		//FileInputStream inputStream = null;
		StringBuilder content = new StringBuilder();
		try
		{
			//System.out.println("ftpfilepath  :"+ftpfilepath);
			urlftp =new URL(ftpfilepath);
			urlc=	urlftp.openConnection();
			ios=urlc.getInputStream();
			
			 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

			 String line;

		      // read from the urlconnection via the bufferedreader
		      while ((line = bufferedReader.readLine()) != null)
		      {
		        content.append(line + "\n");
		      }
		      bufferedReader.close();
		    
			//ios.read();  //(vo.getStrHtmlString().getBytes());
			//System.out.println(" Html Content:::::  "+content.toString());
			//return content.toString();
		}
		catch(Exception ex)
		{
			
			ex.printStackTrace();
			//System.out.println("<!-- Get Data from html directory -->");
			//ex.printStackTrace();
		}
		finally
		{
			
			
			if(ios!=null)
			{
				try {
					ios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		return  content.toString();
		
	}
	
	public static String getFtpPath() {
		
		return HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_URL");
	}
	
public static String getUserName() {
		
		return HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_USERNAME");
	}
	

public static String getPassowrd() {
	
	return HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_PASSWORD");
}
public static String getFTPServerIP() {
	
	return HisUtil.getParameterFromHisPathXML("HIS_FTPSERVER_IP");
}

	public static void  uploadpdf(byte[] data , String myJSON , String varid) throws JSONException {
		OutputStream ios=null;
		JSONObject object=new JSONObject(myJSON);
		String fileName=object.getString("CR_No")+"_"+object.getString("episodeCode")+"_"+object.getString("episodeVisitNo")+"_"+varid;
		final	String ftpfilepath=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+object.getString("Hosp_Code")+"/"+fileName+".txt";	
		URL urlftp=null;
		URLConnection urlc=null;
	
		try
		{
			//System.out.println("ftpfilepath  :"+ftpfilepath);
			urlftp =new URL(ftpfilepath);
			urlc=	urlftp.openConnection();
			ios=urlc.getOutputStream();
			ios.write(data);
			//System.out.println("File Uploaded");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			//System.out.println("<!-- CreateRequisitionDirectory -->");
			//ex.printStackTrace();
		}
		finally
		{
			
			
			if(ios!=null)
			{
				try {
					ios.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}
	public static String  getPdfFileFromFTP(String filename , String HospCode) {
		InputStream ios=null;
		final	String ftpfilepath=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+HospCode+"/"+filename+".txt";	
		URL urlftp=null;
		URLConnection urlc=null;
		//FileInputStream inputStream = null;
		StringBuilder content = new StringBuilder();
		   byte[] fileBytes=null;
		try
		{
			urlftp =new URL(ftpfilepath);
			urlc=	urlftp.openConnection();
			ios=urlc.getInputStream();
			
			 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			 
			
			 String line;
		
		      while ((line = bufferedReader.readLine()) != null)
		      {
		        content.append(line + "\n");
		      }
		      bufferedReader.close();
		    
			//ios.read();  //(vo.getStrHtmlString().getBytes());
		     // System.out.println(java.util.Base64.getEncoder().encodeToString(content.toString().getBytes()));
			//System.out.println(" Html Content:::::  "+content.toString().getBytes());
			return content.toString();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			//System.out.println("<!-- Get Data from html directory -->");
			//ex.printStackTrace();
			return "FTP Access Required";
		}
		finally
		{
			
			
			if(ios!=null)
			{
				try {
					ios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
		
	}
	
	public static  void setCreantials(String dir) {
        String server = getFTPServerIP();
        
        int port = 21;
        String user = getUserName();
        String pass = getPassowrd();
 //HIS_FTPSERVER_USERNAME_

        FTPClient ftpClient = new FTPClient();
 
        try {
            // connect and login to the server
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            // use local passive mode to pass firewall
            ftpClient.enterLocalActiveMode();
 
            //System.out.println("Connected");
 
            //String dirPath = "/projects/java/ftp/demo/connect";
 
            makeDirectories(ftpClient, dir);
 
            // log out and disconnect from the server
            ftpClient.logout();
            ftpClient.disconnect();
 
            //System.out.println("Disconnected");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	
	 public static boolean makeDirectories(FTPClient ftpClient, String dirPath)
	            throws IOException {
	    //    String[] pathElements = (dirPath.split("@")[1]).split("/");
		 
		String[] pathElements = dirPath.split("//");
		pathElements = pathElements[1].split("/",2);
		pathElements = pathElements[1].split("/");

	       //ftp://ftpodisha:FTPlogin!23@10.10.10.54/ftpserver/ftpserver/+OPDLiteTemplate/33201
	        if (pathElements != null && pathElements.length > 0) {
	            for (String singleDir : pathElements) {
	                boolean existed = ftpClient.changeWorkingDirectory(singleDir);
	                //System.out.println("existed   :::::::::::::::  "+existed);
	                if (!existed) {
	                    boolean created = ftpClient.makeDirectory(singleDir);
	                    //System.out.println("created   ::::::::::::::: "+created);
	                    if (created) {
	                        //System.out.println("CREATED directory: " + singleDir);
	                        ftpClient.changeWorkingDirectory(singleDir);
	                    } else {
	                        //System.out.println("COULD NOT create directory: " + singleDir);
	                        return false;
	                    }
	                }
	            }
	        }
	        return true;
	    }
	 
	 public static String  uploadFileFromDrDesk(FormFile myFile , DoctorDeskFB formBean) {
			OutputStream ios=null;
			final	String ftpPath = OPDTemplateMstUtil.getFtpPath()+"/OPDLitePatientDocs/"+formBean.getStrFileData().split(",")[1]+"/";
			String filename = myFile.getFileName();
			if(filename != null)
				filename = filename.replaceAll(" ", "_");						//replace space by _ in filename while uploading
			//System.out.println("\n filename while uploading :::::  "+filename);
			final	String ftpfilepath=ftpPath+filename;	
			//final	String ftpfilepath1=OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode()+"/"+vo.getStrTemplateNo()+"_Modify.html";	
			//System.out.println("ftp path:::::::::::::::::::::::::::::::::::::::::::::::"+OPDTemplateMstUtil.getFtpPath()+"/OPDLiteTemplate/"+vo.getStrHospitalCode());
			setCreantials(OPDTemplateMstUtil.getFtpPath()+"/OPDLitePatientDocs/"+formBean.getStrFileData().split(",")[1]);
			
			//ftp://ftpodisha:FTPlogin!23@10.10.10.54     /ftpserver/ftpserver/+OPDLiteTemplate/33201
			//setCreantials("ftpserver/OPDLiteTemplate/"+vo.getStrHospitalCode());
			
			URL urlftp=null;
			URLConnection urlc=null;
			//FileInputStream inputStream = null;
			
			try
			{
				//System.out.println("ftpfilepath  :"+ftpfilepath);
				urlftp =new URL(ftpfilepath);
				urlc=	urlftp.openConnection();
				ios=urlc.getOutputStream();
				ios.write(myFile.getFileData());
				//System.out.println("File Uploaded");
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				//System.out.println("<!-- CreateRequisitionDirectory -->");
				//ex.printStackTrace();
			}
			finally
			{
				
				
				if(ios!=null)
				{
					try {
						ios.close();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
			}
			
			return ftpPath;
			
		}
	 
	 public static byte[]  getPdfFileFromFTP1(String filename , String cr) {
			InputStream ios=null;
			if(filename != null)
				filename = filename.replaceAll(" ", "_");						//replace space by _ in filename while fetching
			//System.out.println("\n filename while fetching :::::  "+filename);
			final	String ftpfilepath=OPDTemplateMstUtil.getFtpPath()+"/OPDLitePatientDocs/"+cr+"/"+filename;	
			URL urlftp=null;
			URLConnection urlc=null;
			//FileInputStream inputStream = null;
			StringBuilder content = new StringBuilder();
			   byte[] fileBytes=null;
			 //  System.out.println(ftpfilepath+"\n Html Content:::::  ");
			try
			{
				urlftp =new URL(ftpfilepath);
				urlc=	urlftp.openConnection();
				ios=urlc.getInputStream();
				
				fileBytes = IOUtils.toByteArray(ios);
				 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
				 
				
				 String line;
			
			      while ((line = bufferedReader.readLine()) != null)
			      {
			        content.append(line);
			      }
			      bufferedReader.close();
			    
				//ios.read();  //(vo.getStrHtmlString().getBytes());
			     // System.out.println(java.util.Base64.getEncoder().encodeToString(content.toString().getBytes()));
			//	System.out.println(ftpfilepath+"\n Html Content:::::  "+content.toString());
				return fileBytes;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				//System.out.println("<!-- Get Data from html directory -->");
				//ex.printStackTrace();
				return fileBytes;
			}
			finally
			{
				
				
				if(ios!=null)
				{
					try {
						ios.close();
					} catch (IOException e) {
						e.printStackTrace();
					}	
				}
			}
			
			
		}
	 
}
