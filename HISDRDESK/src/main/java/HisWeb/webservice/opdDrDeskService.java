package HisWeb.webservice;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import HisWeb.dao.opdDrDeskDao;
import HisWeb.util.opdDrDeskUtil;
import new_opd.DAO.DoctorDeskDAO;
import new_opd.vo.DoctorDeskVO;

@Path("/DrDesk")
public class opdDrDeskService {
	
	
	
	@POST
	@Path("/saveData")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistrationDepartments(@FormParam("JsonData") String JsonData ,@FormParam("FormattedJsonDataArray") String FormattedJsonDataArray) throws JSONException, ParseException{
		opdDrDeskUtil.SaveDrDesk(JsonData);
		//System.out.println("JsonData"+JsonData);
		//System.out.println("FormattedJsonDataArray"+FormattedJsonDataArray);
		this.InsertFormattedData(FormattedJsonDataArray);
		 return Response.ok()
	               .entity(JsonData)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}	
	
	

	@POST
	@Path("/saveData1")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistrationDepartments1(@FormParam("JsonData") String JsonData ,@FormParam("FormattedJsonDataArray") String FormattedJsonDataArray) throws JSONException, ParseException{
		//opdDrDeskUtil.SaveDrDesk(JsonData);
		//System.out.println("JsonData"+JsonData);
		//System.out.println("FormattedJsonDataArray"+FormattedJsonDataArray);
		//this.InsertFormattedData(FormattedJsonDataArray);
		opdDrDeskUtil.SaveDrDeskFormattedData1(FormattedJsonDataArray);
		 return Response.ok()
	               .entity(JsonData)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}	
	
	
	public void InsertFormattedData(String JsonData) throws JSONException{
		opdDrDeskUtil.SaveDrDeskFormattedData(JsonData);
	}
	
	
	@POST
	@Path("/referPatient")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response referPatient(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		opdDrDeskUtil.referPatient(JsonData);
		//System.out.println("JsonData"+JsonData);
		
		 return Response.ok()
	               .entity(JsonData)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}	
	
	
	@GET
	@Path("/drugList")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDrugList(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		DoctorDeskVO vo = new DoctorDeskVO();
		vo.setStrHospitalCode("37913");
		vo.setStrSeatId("10018");
		DoctorDeskDAO.getdrugdtl(vo);
		Map<String ,List> DrugMap=null;
		List <String> DrugList=null;
        JSONArray obj2 = new JSONArray();   
		try{
			DrugMap=new HashMap<String , List>();
			if(vo.getStrDrugWb().size() > 0)
			{
				while(vo.getStrDrugWb().next())
				{
				
					DrugList=new ArrayList<String>();
				int ColumnLength=	vo.getStrDrugWb().getMetaData().getColumnCount();
					for(int i=1 ;i<=ColumnLength;i++)
					{
						DrugList.add(vo.getStrDrugWb().getString(i));
					}
					DrugMap.put(vo.getStrDrugWb().getString(2), DrugList);
				}
			}
			
			HashMap<String ,List> map1 = (HashMap) DrugMap;  
				for(Map.Entry m1:map1.entrySet()){  
                      JSONObject obj = new JSONObject(); 
                      ArrayList lst = (ArrayList) m1.getValue();
					  obj.put("drugName", lst.get(1)!=null?lst.get(1):"");
					  obj.put("drugId", lst.get(0)!=null?lst.get(0):"");
					  obj.put("drugQuan", lst.get(2)!=null?(lst.get(2).equals("0")?"":lst.get(2)):"");
					  obj.put("expDate", lst.get(3)!=null?lst.get(3):"");
					  obj.put("mfcDate", lst.get(4)!=null?lst.get(4):"");
					  obj.put("rate", lst.get(5)!=null?lst.get(5):"");
                      obj2.put(obj);
				}
		}
		catch(Exception e){
			
		}
		//System.out.println("JsonData"+obj2);
		
		 return Response.ok()
	               .entity(obj2.toString())
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}
	@GET
	@Path("/diagnosisList")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiagnosisList(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		DoctorDeskVO vo = new DoctorDeskVO();
		vo.setStrHospitalCode("37913");
		vo.setStrSeatId("10018");
		vo.setStrDeptCode("0");
		vo.setStrRoomCode("0");
		DoctorDeskDAO.getDIAGNOSISDtl(vo);
		Map<String ,List> DiagnosisMap=null;
		List <String> DiagnosisList=null;
        JSONArray mapIcdCodeLst = new JSONArray();   
		try{ 
			DiagnosisMap=new HashMap<String , List>();
			if(vo.getStrDiagnosisWb().size() > 0)
			{
				while(vo.getStrDiagnosisWb().next())
				{
				
					DiagnosisList=new ArrayList<String>();
				int ColumnLength=	vo.getStrDiagnosisWb().getMetaData().getColumnCount();
					for(int i=1 ;i<=ColumnLength;i++)
					{
						DiagnosisList.add(vo.getStrDiagnosisWb().getString(i));
					}
					DiagnosisMap.put(vo.getStrDiagnosisWb().getString(1), DiagnosisList);
				}
			}
			 
			for(Map.Entry mapIcdCodeItem:DiagnosisMap.entrySet()){  
                  JSONObject mapIcdCodeLstSubObj = new JSONObject(); 
				  String strCode=(String)mapIcdCodeItem.getKey();
                  ArrayList lst = (ArrayList) mapIcdCodeItem.getValue();
                  mapIcdCodeLstSubObj.put("icdCode", strCode!=null?strCode:"");
                  mapIcdCodeLstSubObj.put("diagnosisName", lst.get(1)!=null?lst.get(1):""); 
                  mapIcdCodeLst.put(mapIcdCodeLstSubObj);
			} 
		}
		catch(Exception e){
			
		}
		//System.out.println("JsonData"+mapIcdCodeLst);
		
		 return Response.ok()
	               .entity(mapIcdCodeLst.toString())
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}
	
	
	@POST
	@Path("/saveVital")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveVitalData(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		String RetValue=opdDrDeskUtil.saveVitalData(JsonData);
		//System.out.println("JsonData"+JsonData);
		 return Response.ok()
	               .entity(RetValue)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}

	@POST
	@Path("/ModifyVital")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response ModifyVital(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		String RetValue=opdDrDeskUtil.getModifyVitalData(JsonData);
		//System.out.println("RetValue"+RetValue);
		 return Response.ok()
	               .entity(RetValue)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
	}
	
	@POST
	@Path("/savePrscriptionProfileData")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePrscriptionProfileData(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		String RetValue=opdDrDeskUtil.savePrescriptionProfileData(JsonData);
		//System.out.println("JsonData"+JsonData);
		 return Response.ok()
	               .entity(RetValue)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}
}
