package com.ecentric.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;




import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.hornetq.utils.json.JSONObject;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.ecentric.servicemodels.WhiteCardIntoRegistration;
import com.ecentric.servicemodels.WhiteCardProfile;




/**
 * 
 * @author sheeldarshi
 *
 */

public class WhiteCardServiceUTIL {
	
	
	public String ageFromDOB(String _dateOfBirth)
	{
		String age="";
		return age;
	}
	
	public static void populateWhiteCardDtlsIntoRegForm(WhiteCardIntoRegistration _wToR,WhiteCardProfile _wProf) throws ParseException
	{
		_wToR.setName(_wProf.getName()!=null?_wProf.getName():"");
		_wToR.setPatGuardianName(_wProf.getCareof()!=null?_wProf.getCareof():"");
		if(!_wProf.getDob().equals("-N.A-"))
		_wToR.setPatAgeFromDOB(_wProf.getDob());
		else
			_wToR.setPatDOB("");		
		_wToR.setPatGenderCode(_wProf.getGender()!=null?_wProf.getGender():"");
		_wToR.setPatAddHNo(_wProf.getBuildingName()!=null?_wProf.getBuildingName():"");
		_wToR.setPatAddStreet(_wProf.getStreet()!=null?_wProf.getStreet():"");
		_wToR.setPatAddCity(_wProf.getVillage_name()!=null?_wProf.getVillage_name():"");		

		_wToR.setPatAddDistrict(_wProf.getDistrict_name()!=null?_wProf.getDistrict_name():"");
		_wToR.setPatAddDistrictCode(_wProf.getDistrict()!=null?_wProf.getDistrict():"");
		_wToR.setPatAddStateCode(_wProf.getStatecode()!=null?_wProf.getStatecode():"");
		
		_wToR.setPatAddPIN(_wProf.getPincode()!=null?_wProf.getPincode():"");
		_wToR.setPatAddMobileNo(_wProf.getPhoneNo()!=null?_wProf.getPhoneNo():"");
		_wToR.setPatAddCityLoc(_wProf.getVillage_name()!=null?_wProf.getVillage_name():"");
		if(!_wProf.getAge().equals("-N.A-"))
		_wToR.setPatAge(_wProf.getAge()!=null?_wProf.getAge():"");
		else
		_wToR.setPatAge("");	
		_wToR.setAgsNo(_wProf.getCardno()!=null?_wProf.getCardno():"");
		
	}
	
	public static void populateAllWhiteCardDtlsIntoRegForm(WhiteCardIntoRegistration[] _wToR,List<WhiteCardProfile> _wProf) throws ParseException
	{
		for(int i =0;i<_wProf.size();i++)
		{
			_wToR[i]= new WhiteCardIntoRegistration();
		_wToR[i].setName(_wProf.get(i).getName()!=null?_wProf.get(i).getName():"");
		_wToR[i].setPatGuardianName(_wProf.get(i).getCareof()!=null?_wProf.get(i).getCareof():"");
		if(!_wProf.get(i).getDob().equals("-N.A-"))
		_wToR[i].setPatAgeFromDOB(_wProf.get(i).getDob());
		else
			_wToR[i].setPatDOB("");		
		_wToR[i].setPatGenderCode(_wProf.get(i).getGender()!=null?_wProf.get(i).getGender().trim():"");
		if(_wProf.get(i).getGender().equals("1"))
			_wToR[i].setPatGenderCode("M");
		if(_wProf.get(i).getGender().equals("2"))
			_wToR[i].setPatGenderCode("F");
		_wToR[i].setPatAddHNo(_wProf.get(i).getBuildingName()!=null?_wProf.get(i).getBuildingName():"");
		_wToR[i].setPatAddStreet(_wProf.get(i).getStreet()!=null?_wProf.get(i).getStreet():"");
		_wToR[i].setPatAddCity(_wProf.get(i).getVillage_name()!=null?_wProf.get(i).getVillage_name():"");		

		_wToR[i].setPatAddDistrict(_wProf.get(i).getDistrict_name()!=null?_wProf.get(i).getDistrict_name():"");
		_wToR[i].setPatAddDistrictCode(_wProf.get(i).getDistrict()!=null?_wProf.get(i).getDistrict():"");
			
		_wToR[i].setPatAddStateCode(_wProf.get(i).getStatecode()!=null?_wProf.get(i).getStatecode():"");
		
		//COde added for State and District data mapping
		_wToR[i].setPatAddStateCode(_wToR[i].getStateFromMapper(_wToR[i].getPatAddStateCode()));
		_wToR[i].setPatAddDistrictCode(_wToR[i].getDistrictFromMapper(_wToR[i].getPatAddDistrictCode()));
		
		_wToR[i].setPatAddPIN(_wProf.get(i).getPincode()!=null?_wProf.get(i).getPincode():"");
		
		_wToR[i].setPatAddMobileNo((_wProf.get(i).getPhoneNo()!=null && !_wProf.get(i).getPhoneNo().equalsIgnoreCase("N") && !_wProf.get(i).getPhoneNo().equalsIgnoreCase("Y"))?_wProf.get(i).getPhoneNo():"");
		
		_wToR[i].setPatAddCityLoc(_wProf.get(i).getVillage_name()!=null?_wProf.get(i).getVillage_name():"");
		if(!_wProf.get(i).getAge().equals("-N.A-"))
		_wToR[i].setPatAge(_wProf.get(i).getAge()!=null?_wProf.get(i).getAge():"");
		else
		_wToR[i].setPatAge("");	
		_wToR[i].setPatAgeUnit("Yr");	
		_wToR[i].setAgsNo(_wProf.get(i).getCardno()!=null?_wProf.get(i).getCardno():"");
		}
	}
	
	public static void getPatDtlOnCardId(HttpServletRequest objRequest, HttpServletResponse objResponse) {
		
		 StringBuilder strResponse = new StringBuilder();
		 try{
			ResteasyClient client = new ResteasyClientBuilder().build();	
			String cardId= (String)objRequest.getParameter("wapId");
			String cardType= (String)objRequest.getParameter("cardType");
			//String uri = "http://223.30.18.35:8080/AHCTRestfulService/rest/findHosp/getWapDetails?wapId=WAD118109600179/1&cardType=WAP";
			String uri = "http://223.30.18.35:8080/AHCTRestfulService/rest/findHosp/getWapDetails?wapId="+cardId+"&cardType="+ cardType;
			ResteasyWebTarget target = client.target(uri);
			target.register(new BasicAuthentication("ahct", "@#ct20!6$"));
			Response response = target.request().get();
			String patientJsonObjString = "";
			
	        if(response.getStatus() != 200){
	            System.err.println("Unable to connect to the server");
	        }
	        String output = response.readEntity(String.class);
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);				
			List<WhiteCardProfile> _WhiteCardProfile= Arrays.asList( mapper.readValue(output,WhiteCardProfile[].class));
			WhiteCardIntoRegistration[] _wRegistration=new WhiteCardIntoRegistration[_WhiteCardProfile.size()];
			populateAllWhiteCardDtlsIntoRegForm(_wRegistration, _WhiteCardProfile);	
		    strResponse.append("[");
		    if(_wRegistration!=null && _wRegistration.length>0){
				for(int i=0; i<_wRegistration.length; i++){
					if(i==0)
						strResponse.append(new JSONObject( _wRegistration[i] ));
					else
						strResponse.append(","+new JSONObject( _wRegistration[i] ));
				}
			}
			strResponse.append("]");
	        System.out.println("response: "+strResponse);
		 	}
	        catch(Exception e)
	        {
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
