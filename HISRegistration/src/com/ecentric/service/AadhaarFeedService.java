package com.ecentric.service;

import hisglobal.utility.filetransfer.config.FileTransferConfig;

import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.hornetq.utils.json.JSONObject;

import com.ecentric.servicemodels.AadhaarProfile;
import com.ecentric.servicemodels.AadhaarIntoRegistration;

import srdhuidinfoservices.ecentric.com.ServicesPortTypeProxy;


/**
 * 
 * @author s.singaravelan
 *
 */

public class AadhaarFeedService {
	
	
	public String getAdhaarProfileDetails(String adhaarId)
	{
		String adhaarProfileString="";
		try 
		{
			ServicesPortTypeProxy _adhaarService=new ServicesPortTypeProxy();
			adhaarProfileString=_adhaarService.getAadhaarInfo(adhaarId, null);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return adhaarProfileString;
	}
	
	
	public AadhaarProfile getAdhaarDetailsAsPOJO(String adhaarId)
	{
		AadhaarProfile _adhaarDtl=new AadhaarProfile();
		
		try{
			
			String adhaarProfileString=getAdhaarProfileDetails(adhaarId);
			System.out.println("Response From Service :: "+adhaarProfileString);
			
			InputStream is=new ByteArrayInputStream(adhaarProfileString.getBytes(StandardCharsets.UTF_8));
			XMLDecoder xmlDecoder = new XMLDecoder(is);
			_adhaarDtl=(AadhaarProfile)xmlDecoder.readObject();
					
			System.out.println("Addhar Card Holder Name :: "+_adhaarDtl.getName());
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return _adhaarDtl;
	}
	
	public String getAdhaarDetailsAsJSON(String adhaarId)
	{
		AadhaarProfile _adhaarDtl=new AadhaarProfile();
		AadhaarIntoRegistration _aRegistration=new AadhaarIntoRegistration();
		String _adhaarDtlJson="";
		
		try{
			
			String adhaarProfileString=getAdhaarProfileDetails(adhaarId);
			InputStream is=new ByteArrayInputStream(adhaarProfileString.getBytes(StandardCharsets.UTF_8));
			XMLDecoder xmlDecoder = new XMLDecoder(is);
			_adhaarDtl=(AadhaarProfile)xmlDecoder.readObject();
			
			if(_adhaarDtl.getStatus().equals("100")){
				populateAdhaarDtlsIntoRegForm(_aRegistration, _adhaarDtl);			
				JSONObject _adhaarjsonObj =new JSONObject( _aRegistration );	
				_aRegistration.setPatNationalId(adhaarId);
				System.out.println("JSon Adhaar Profile :: "+_adhaarjsonObj);
				_adhaarDtlJson=_adhaarjsonObj.toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return _adhaarDtlJson;
	}
	
	
	public static  String getAdhaarDetailDummysAsJSON(AadhaarProfile _adhaarDtl,String adhaarId)
	{
		AadhaarIntoRegistration _aRegistration=new AadhaarIntoRegistration();
		String _adhaarDtlJson="";
		
		try{
			
			//String adhaarProfileString=getAdhaarProfileDetails(adhaarId);
			InputStream is=new ByteArrayInputStream(adhaarId.getBytes(StandardCharsets.UTF_8));
			XMLDecoder xmlDecoder = new XMLDecoder(is);
			_adhaarDtl=(AadhaarProfile)xmlDecoder.readObject();
			
			if(_adhaarDtl.getStatus().equals("100")){
				populateAdhaarDtlsIntoRegForm(_aRegistration, _adhaarDtl);	
				_aRegistration.setPatNationalId(adhaarId);

				BufferedImage newImg;
				newImg = decodeToImage(_adhaarDtl.getBase64file());
				
				JSONObject _adhaarjsonObj =new JSONObject( _aRegistration );			
				System.out.println("JSon Adhaar Profile :: "+_adhaarjsonObj);
				_adhaarDtlJson=_adhaarjsonObj.toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return _adhaarDtlJson;
	}
	
	public AadhaarProfile getAdhaarDetailDummysAsPOJO(String adhaarId)
	{
		AadhaarProfile _adhaarDtl=new AadhaarProfile();
		String _adhaarDtlJson="";
		
		try{
			
			//String adhaarProfileString=getAdhaarProfileDetails(adhaarId);
			InputStream is=new ByteArrayInputStream(adhaarId.getBytes(StandardCharsets.UTF_8));
			XMLDecoder xmlDecoder = new XMLDecoder(is);
			_adhaarDtl=(AadhaarProfile)xmlDecoder.readObject();			
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		return _adhaarDtl;
	}
	
	public String ageFromDOB(String _dateOfBirth)
	{
		String age="";
		return age;
	}
	
	public static void populateAdhaarDtlsIntoRegForm(AadhaarIntoRegistration _aToR,AadhaarProfile _aProf) throws ParseException
	{
		_aToR.setName(_aProf.getName()!=null?_aProf.getName():"");
		_aToR.setPatGuardianName(_aProf.getCareof()!=null?_aProf.getCareof():"");

		_aToR.setPatAgeFromDOB(_aProf.getDob());
		_aToR.setPatGenderCode(_aProf.getGender()!=null?_aProf.getGender():"");
		_aToR.setPatAddHNo(_aProf.getBuildingName()!=null?_aProf.getBuildingName():"");
		_aToR.setPatAddStreet(_aProf.getStreet()!=null?_aProf.getStreet():"");
		_aToR.setPatAddCity(_aProf.getVillage_name()!=null?_aProf.getVillage_name():"");		

		_aToR.setPatAddDistrict(_aProf.getDistrict_name()!=null?_aProf.getDistrict_name():"");
		_aToR.setPatAddDistrictCode(_aProf.getDistrict()!=null?_aProf.getDistrict():"");
		_aToR.setPatAddStateCode(_aProf.getStatecode()!=null?_aProf.getStatecode():"");
		_aToR.setPatAddDistrictCode(_aToR.getDistrictFromMapper(_aToR.getPatAddDistrictCode()));
		
		_aToR.setPatAddPIN(_aProf.getPincode()!=null?_aProf.getPincode():"");
		_aToR.setPatAddMobileNo(_aProf.getPhoneNo()!=null?_aProf.getPhoneNo():"");
		_aToR.setPatAddCityLoc(_aProf.getVillage_name()!=null?_aProf.getVillage_name():"");

		
	}
	
	public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            imageByte = Base64.decodeBase64(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            String _osName=System.getProperties().getProperty("os.name");
            File outputfile=null;

            if(_osName.startsWith("Win"))			
				outputfile=new File("c:\\NIMS\\AHIMSG5\\aadhaarCandidatePhoto.png");
			else
				outputfile=new File("/opt/NIMS/AHIMSG5/aadhaarCandidatePhoto.png");
           
            ImageIO.write(image, "png", outputfile); 
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
	
	public static void getPatDtlOnAadhaarId(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		String patientJsonObjString = "";
		StringBuilder strResponse = new StringBuilder();
		try{
			String strAdhaarId	=	(String)objRequest.getParameter("aadhaarId");
			String s1=new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?><java version=\"1.7.0_45\" class=\"java.beans.XMLDecoder\"><object class=\"com.ecentric.servicemodels.AadhaarProfile\"><void property=\"base64file\"><string>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</string>  </void>  <void property=\"buildingName\">   <string>MC-277</string>  </void>  <void property=\"careof\">   <string> Gajjala Subbarayudu</string>  </void>  <void property=\"district\">   <string>15</string>  </void>  <void property=\"district_name\">   <string>RANGA REDDY</string>  </void>  <void property=\"dob\">   <string>01/03/1987</string>  </void>  <void property=\"eid\">   <string>1190621060439820130205123618</string>  </void>  <void property=\"gender\">   <string>M</string>  </void>  <void property=\"mandal\">   <string>15</string>  </void>  <void property=\"mandal_name\">   <string>SAROORNAGAR</string>  </void>  <void property=\"name\">   <string>Gajjala Bala Subramanya Kumar</string>  </void>  <void property=\"phoneNo\">   <string>101</string>  </void>  <void property=\"pincode\">   <string>500035</string>  </void>  <void property=\"srdhwstxn\">   <string>SRDHWS-TXN:20150622031456894</string>  </void>  <void property=\"statecode\">   <string>2</string>  </void>  <void property=\"status\">   <string>100</string>  </void>  <void property=\"street\">   <string>Govt Malakpet Colony</string>  </void>  <void property=\"uid\">   <string>320938215476</string>  </void>  <void property=\"village\">   <string>24</string>  </void>  <void property=\"village_name\">   <string>Saroornagar</string>  </void> </object></java>");

			//String s1=new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?><java version=\"1.7.0_45\" class=\"java.beans.XMLDecoder\"> <object class=\"com.ecentric.servicemodels.AadhaarProfile\">  <void property=\"base64file\">   <string>101</string>  </void>  <void property=\"buildingName\">   <string>101</string>  </void>  <void property=\"careof\">   <string>101</string>  </void>  <void property=\"district\">   <string>101</string>  </void>  <void property=\"dob\">   <string>101</string>  </void>  <void property=\"eid\">   <string>101</string>  </void>  <void property=\"gender\">   <string>101</string>  </void>  <void property=\"mandal\">   <string>101</string>  </void>  <void property=\"name\">   <string>101</string>  </void>  <void property=\"pincode\">   <string>101</string>  </void>  <void property=\"srdhwstxn\">   <string>SRDHWS-TXN:20150703114546831</string>  </void>  <void property=\"status\">   <string>101</string>  </void>  <void property=\"street\">   <string>101</string>  </void>  <void property=\"uid\">   <string>101</string>  </void>  <void property=\"village\">   <string>101</string>  </void> </object></java>");
			AadhaarFeedService _adhaarFeedService=new AadhaarFeedService();
			AadhaarProfile _adhaarDtl=new AadhaarProfile();
			//patientJsonObjString=_adhaarFeedService.getAdhaarDetailsAsJSON(strAdhaarId);
			//patientJsonObjString=_adhaarFeedService.getAdhaarDetailDummysAsJSON(_adhaarDtl,s1);
			
			//_adhaarDtl=_adhaarFeedService.getAdhaarDetailDummysAsPOJO(s1);
			_adhaarDtl=_adhaarFeedService.getAdhaarDetailsAsPOJO(strAdhaarId);
			AadhaarIntoRegistration _aRegistration=new AadhaarIntoRegistration();

			if(_adhaarDtl.getStatus().equals("100")){
				populateAdhaarDtlsIntoRegForm(_aRegistration, _adhaarDtl);	
				_aRegistration.setPatNationalId(strAdhaarId);

				BufferedImage newImg;
				newImg = decodeToImage(_adhaarDtl.getBase64file());
				
				JSONObject _adhaarjsonObj =new JSONObject( _aRegistration );			
				System.out.println("JSon Adhaar Profile :: "+_adhaarjsonObj);
				patientJsonObjString=_adhaarjsonObj.toString();
			
			
				byte[] imageByte = Base64.decodeBase64(_adhaarDtl.getBase64file());
				//InputStream input = new ByteArrayInputStream(imageByte);
				
				String _osName=System.getProperties().getProperty("os.name");
				FileInputStream fio=null;
				if(_osName.startsWith("Win"))			
					fio=new FileInputStream("c:\\NIMS\\AHIMSG5\\aadhaarCandidatePhoto.png");
				else
					fio=new FileInputStream("/opt/NIMS/AHIMSG5/aadhaarCandidatePhoto.png");
				
				objRequest.getSession().putValue(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, fio);
				objRequest.getSession().putValue(FileTransferConfig.KEY_UPLOADED_FILE_NAME, "aadhaarCandidatePhoto.png");

				strResponse.append("[");
				strResponse.append(patientJsonObjString);
				strResponse.append("]");
			}
			else{
				strResponse.append("[");
				strResponse.append(_adhaarDtl.getStatus());
				strResponse.append("]");
			}
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
		finally
		{
			writeResponse(objResponse, strResponse.toString());
		}	
		
	}
	
	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	

}
