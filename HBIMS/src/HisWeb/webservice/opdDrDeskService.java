package HisWeb.webservice;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import HisWeb.dao.DrugListDAO;
//import HisWeb.dao.opdDrDeskDao;
//import HisWeb.util.opdDrDeskUtil;
//import new_opd.DAO.DoctorDeskDAO;
import HisWeb.vo.DrugListVO;

@Path("/DrugList")
public class opdDrDeskService {
	
	
	
	
	@GET
	@Path("/drugList")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDrugList(@QueryParam("storeId") String storeId,@QueryParam("hospCode") String hospCode,@QueryParam("seatId") String seatId) throws JSONException, ParseException{
		DrugListVO  vo = new DrugListVO ();
		vo.setStrHospitalCode((hospCode==null || hospCode=="") ? "96101":hospCode);
		vo.setStrSeatId((seatId==null || seatId=="")? "10018" : seatId);
		System.out.println(storeId);
		vo.setStrStoreId(storeId);
		DrugListDAO.getdrugdtl(vo);
		Map<String ,List> DrugMap=null;
		List <String> DrugList=null;
        JSONArray obj2 = new JSONArray();   
		try{
			DrugMap=new LinkedHashMap<String , List>();
			if(vo.getStrDrugWb().size() > 0)
			{
				while(vo.getStrDrugWb().next())
				{
				
					DrugList=new ArrayList<String>();
				int ColumnLength=	vo.getStrDrugWb().getMetaData().getColumnCount();
					for(int i=1 ;i<=ColumnLength;i++)
					{
						DrugList.add(vo.getStrDrugWb().getString(i));
						/*if(i==4)
						System.out.println(vo.getStrDrugWb().getString(i));*/
					}
					DrugMap.put(vo.getStrDrugWb().getString(3), DrugList);
				}
			}

			System.out.println(DrugMap.toString());
			
			LinkedHashMap<String ,List> map1 = (LinkedHashMap) DrugMap;  
				for(Map.Entry m1:map1.entrySet()){  
                      JSONObject obj = new JSONObject(); 
                      ArrayList lst = (ArrayList) m1.getValue();
					  obj.put("drugName", lst.get(0)!=null?lst.get(0):"");
					  obj.put("drugId", lst.get(1)!=null?lst.get(1):"");
					  obj.put("drugQuan", lst.get(3)!=null?(lst.get(3).toString().split("#")[0].equals("0")?"":lst.get(3).toString().split("#")[0]):"");
					  obj.put("brandId", lst.get(2)!=null?lst.get(2):"");
					  obj.put("batchesAndRates", lst.get(3)!=null?(lst.get(3).toString().split("#")[1].equals("0")?"":lst.get(3).toString().split("#")[1]):"");
/*					  obj.put("drugCpaCode", lst.get(4).toString().split("#")[0]!=null?lst.get(4).toString().split("#")[0]:"");
					  obj.put("drugTypeName", lst.get(4).toString().split("#")[1]!=null?lst.get(4).toString().split("#")[1]:""); */
					  
					  
					  /* obj.put("expDate", lst.get(4)!=null?lst.get(4):"");
					  obj.put("mfcDate", lst.get(5)!=null?lst.get(5):"");*/
					/*  System.out.println("Temp"+lst.get(3).toString());
					  System.out.println("Temp"+lst.get(3).toString().split("#")[1].replaceAll("@", "^").split("^")[0]);*/
					 /* System.out.println("it()[1]"+lst.get(3).toString().split("\\#")[1]);
					  System.out.println("lst.get(3).toString().split()[1]"+(lst.get(3).toString().split("#")[1]).split("@")[0]);
					  obj.put("batches", (lst.get(3).toString().split("#")[1]).split("\\@")[0]);*/
					  
					  //obj.put("batches", lst.get(3)!=null?(lst.get(3).toString().split("#")[1].split("\\@")[0].equals("0")?"":lst.get(3).toString().split("#")[0].split("\\@")[0]):"");
					  //obj.put("rates", lst.get(3)!=null?(lst.get(3).toString().split("#")[1].split("\\@")[0].equals("0")?"":lst.get(3).toString().split("#")[0].split("\\@")[0]):"");
					  /*obj.put("batches", lst.get(3)!=null?(lst.get(3).toString().split("#")[1].split("@")[0].equals("0")?"":lst.get(3).toString().split("#")[1].split("@")[0]):"");
					  obj.put("rates", lst.get(3)!=null?(lst.get(3).toString().split("#")[1].split("@")[1].equals("0")?"":lst.get(3).toString().split("#")[1].split("@")[1]):"");*/
                      obj2.put(obj);
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("JsonData"+obj2);
		
		 return Response.ok()
	               .entity(obj2.toString())
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}
/*	@GET
	@Path("/diagnosisList")   
	@Produces(MediaType.APPLICATION_JSON)*/
	/*public Response getDiagnosisList(@FormParam("JsonData") String JsonData) throws JSONException, ParseException{
		DoctorDeskVO vo = new DoctorDeskVO();
		vo.setStrHospitalCode("96101");
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
		System.out.println("JsonData"+mapIcdCodeLst);
		
		 return Response.ok()
	               .entity(mapIcdCodeLst.toString())
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}*/

}
