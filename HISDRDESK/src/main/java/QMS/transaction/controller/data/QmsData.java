package QMS.transaction.controller.data;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.json.JSONArray;
import org.json.JSONObject;

import QMS.bo.QmsBo;
import QMS.transaction.controller.fb.QmsFB;
import QMS.vo.QmsVO;
import hisglobal.config.HISConfig;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

public class QmsData extends ControllerDATA {
	
	public static void getInitailData(QmsFB  formBean,
			HttpServletRequest request) {
		QmsVO vo=null;
		QmsBo bo=null;
		Map<String ,List> m=null;
		List <String> list=null;
		Map<String ,List> TestMap=null;
		Map<String ,List> DiagnosisMap=null;
		Map<String ,List> DrugMap=null;
		Map<String ,List> DosageMap=null;
		Map<String, String> MacrosMap=null;
		Map<String, String> clinicalProcedureMap=null;
		List <String> TestList=null;
		List <String> DiagnosisList=null;
		List <String> DrugList=null;
		List <String> DosageList=null;
		List <String> BookmarkList=null;
		Map<String, String> DeptCmb=null;
		Map<String, String> ReffralDeptCmb=null;
		 ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
		Map <String ,List> BookmarkMap=null;
		try
		{
			vo=new QmsVO();
			bo=new QmsBo();
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			//System.out.println("Seat id -->> "+SeatId);
			//System.out.println("user vo Seat id -->> "+userVO.getSeatId());
			vo.setStrSeatId(SeatId);
			//System.out.println("userVO:::::::::"+userVO.getUserName());
			//System.out.println("userVO:::::::"+userVO.getUsrName());
			vo.setStrRoomCode(formBean.getStrRoomCode());
			vo.setStrDeptCode(formBean.getDeptUnitName());
			
			vo.setStrDeptUnitCode(formBean.getDeptUnitName());
			vo.setStrPrevDate(formBean.getStrPrevDate());
			System.out.println("formBean.getStrPrevDate():::::"+formBean.getStrPrevDate());
			bo.getInitilasData1(vo, request);
			//if(!(formBean.getDeptUnitName().equalsIgnoreCase("0#0#0"))){
				bo.getInitilasData(vo);	
			//}
			
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
		
				
			MultiValueMap RoomsWise = null;
				RoomsWise = new MultiValueMap();
				length=	vo.getStrInitialWb().getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(vo.getStrInitialWb().getMetaData().getColumnName(i).toUpperCase());
				}
				
				if (vo.getStrInitialWb() != null && vo.getStrInitialWb().size() > 0) {
		              while (vo.getStrInitialWb().next()) {
		            	  int j=0;
		                	//status="1";
		                	JSONObject js=new JSONObject();
		                	for (int i=1;i<=length;i++)
		                	{
		                		String key=columnlist.get(i-1);
		                		String value=vo.getStrInitialWb().getString(i);
		                		js.put(key, value);
		                		
		                	}
		                	RoomsWise.put(vo.getStrInitialWb().getString(6)+""+vo.getStrInitialWb().getString(11)+"#"+vo.getStrInitialWb().getString(5), js);               	
		                	
		                }
		              //ja.put(jsonolist);
		              }
				System.out.println("Patient Data"+RoomsWise);
			
				DeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrDeptWb().size()>0)
				{
					while (vo.getStrDeptWb().next()) {
						DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
						
					}
				}
				
				if(formBean.getDeptUnitName()!=null)
				{
					request.getSession().setAttribute("SelectedDeptId", formBean.getDeptUnitName());
				}else{
					request.getSession().setAttribute("SelectedDeptId", "0");
				}
			
			request.getSession().setAttribute("UserName", userVO.getUsrName());
			request.getSession().setAttribute("ROOMWISEDTL", RoomsWise);
			request.getSession().setAttribute("QMSDEPTDTL", DeptCmb);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public static void getPrevDtl(HttpServletRequest request ,HttpServletResponse response)
	{
		
		QmsVO vo=null;
		QmsBo bo=null;
		ArrayList<JSONObject> strDrugJson=null;
		ArrayList<JSONObject> strinvJson=null;
		JSONObject mainObj = new JSONObject();
		try {
			
			 vo=new QmsVO();
			 bo=new QmsBo();
			 
			
			 //System.out.println("req:::::::::"+request.getAttribute("PUK"));
			 vo.setStrPuk(request.getParameter("PUK").toString());
			 vo.setStrEpisodeCode(request.getParameter("EPISODECODE").toString());
			 vo.setStrHospitalCode(request.getParameter("HOSPITALCODE").toString());
			 vo.setStrVistNo(request.getParameter("visitNo").toString());
			 bo.getPrevData(vo);
			 if(vo.getStrMsgType().equals("1"))
			 {
				 throw new Exception();
			 }
			 mainObj.put("DRUGDATA", vo.getStrDrugPrevCodeJSON());
			 mainObj.put("INVDATA", vo.getStrInvPrevCodeJSON());
			 mainObj.put("VISTREASON", vo.getStrInvPrevVistReasonJSON());
			 //mainObj.put("DIAGNOSIS", vo.getStrInvPrevDiagnosisJSON());
			 mainObj.put("VITALDTLS", vo.getStrPreVitalJSON());
			 response.setContentType("application/json");
			 response.getWriter().print(mainObj);
			 //response.sendRedirect(arg0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void getInitailDataMobile(QmsFB  formBean,
			HttpServletRequest request) {
		QmsVO vo=null;
		QmsBo bo=null;
		Map<String ,List> m=null;
		List <String> list=null;
		Map<String ,List> TestMap=null;
		Map<String ,List> DiagnosisMap=null;
		Map<String ,List> DrugMap=null;
		Map<String ,List> DosageMap=null;
		Map<String, String> MacrosMap=null;
		List <String> TestList=null;
		List <String> DiagnosisList=null;
		List <String> DrugList=null;```
		List <String> DosageList=null;
		List <String> BookmarkList=null;
		Map<String, String> DeptCmb=null;
		
		Map <String ,List> BookmarkMap=null;
		try
		{
			vo=new QmsVO();
			bo=new QmsBo();
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			//String userName=userVO.getUsrName();
			String userName=(String) request.getSession().getAttribute("USER_NAME");
			vo.setStrHospitalCode(HsopitalCode);
			//vo.setStrSeatId(userVO.getSeatId());
			vo.setStrSeatId(SeatId);
			//System.out.println("userVO:::::::::"+userVO.getUserName());
			//System.out.println("userVO:::::::"+userVO.getUsrName());
			vo.setStrRoomCode(formBean.getStrRoomCode());
			vo.setStrDeptCode(formBean.getDeptUnitName());
			vo.setStrPrevDate(formBean.getStrPrevDate());
			System.out.println("formBean.getStrPrevDate():::::"+formBean.getStrPrevDate());
			bo.getInitilasData(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			m=new LinkedHashMap<String , List>();
				if(vo.getStrInitialWb().size() > 0)
				{
					while(vo.getStrInitialWb().next())
					{
					
					list=new ArrayList<String>();
					int ColumnLength=	vo.getStrInitialWb().getMetaData().getColumnCount();
						for(int i=1 ;i<=ColumnLength;i++)
						{
							list.add(vo.getStrInitialWb().getString(i));
						}
						m.put(vo.getStrInitialWb().getString(39), list);
					}
				}
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception();
				}
				
				
				TestMap=new HashMap<String , List>();
				if(vo.getStrTestWb().size() > 0)
				{
					while(vo.getStrTestWb().next())
					{
					
						TestList=new ArrayList<String>();
					int ColumnLength=	vo.getStrTestWb().getMetaData().getColumnCount();
						for(int i=1 ;i<=ColumnLength;i++)
						{
							TestList.add(vo.getStrTestWb().getString(i));
						}
						TestMap.put(vo.getStrTestWb().getString(1), TestList);
					}
				}
				
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
				
			DosageMap=new HashMap<String , List>();
				if(vo.getStrDosageWb().size() > 0)
				{
					while(vo.getStrDosageWb().next())
					{
					
						DosageList=new ArrayList<String>();
					int ColumnLength=	vo.getStrDosageWb().getMetaData().getColumnCount();
						for(int i=1 ;i<=ColumnLength;i++)
						{
							DosageList.add(vo.getStrDosageWb().getString(i));
						}
						DosageMap.put(vo.getStrDosageWb().getString(1), DosageList);
					}
				}
				MacrosMap=new HashMap<String, String>();
				
				if(vo.getStrMacrosWb().size()>0)
				{
					while (vo.getStrMacrosWb().next()) {
						MacrosMap.put(vo.getStrMacrosWb().getString(1), vo.getStrMacrosWb().getString(2));
						
					}
				}
				DeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrDeptWb().size()>0)
				{
					while (vo.getStrDeptWb().next()) {
						DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
						
					}
				}
				System.out.println("formBean.getDeptUnitName()"+formBean.getDeptUnitName());
				if(formBean.getDeptUnitName()!=null)
				{
					request.getSession().setAttribute("SelectedDeptId", formBean.getDeptUnitName());
				}else{
					request.getSession().setAttribute("SelectedDeptId", "0");
				}
			/*
				BookmarkMap=new HashMap<String , List>();
				if(vo.getStrBookMarksTestWb().size() > 0)
				{
					while(vo.getStrBookMarksTestWb().next())
					{
					if(BookmarkMap.containsKey(vo.getStrBookMarksTestWb().getString(1)))
					{
						
					}else
					{
						BookmarkList=new ArrayList<String>();
					}
					
					int ColumnLength=	vo.getStrBookMarksTestWb().getMetaData().getColumnCount();
						for(int i=1 ;i<=ColumnLength;i++)
						{
							BookmarkList.add(vo.getStrBookMarksTestWb().getString(i));
						}
						BookmarkMap.put(vo.getStrBookMarksTestWb().getString(1), BookmarkList);
					}
				}	
				
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
				 
                	JSONArray mapIcdCodeLst = new JSONArray();   
					for(Map.Entry mapIcdCodeItem:DiagnosisMap.entrySet()){  
                          JSONObject mapIcdCodeLstSubObj = new JSONObject(); 
						  String strCode=(String)mapIcdCodeItem.getKey();
                          ArrayList lst = (ArrayList) mapIcdCodeItem.getValue();
                          mapIcdCodeLstSubObj.put("icdCode", strCode!=null?strCode:"");
                          mapIcdCodeLstSubObj.put("diagnosisName", lst.get(1)!=null?lst.get(1):""); 
                          mapIcdCodeLst.put(mapIcdCodeLstSubObj);
					} 
                   // System.out.println("obj icd:::::::::::::>>>>>>>>> "+mapIcdCodeLst.toString());
					HashMap<String ,List> mapTestDtl = (HashMap) TestMap;                       
                    JSONArray mapTestDtlLst = new JSONArray();   
						for(Map.Entry mapTestDtlItem:mapTestDtl.entrySet()){  
	                          JSONObject mapTestDtlLstSubObj = new JSONObject(); 
	                          ArrayList lst = (ArrayList) mapTestDtlItem.getValue();
	                          mapTestDtlLstSubObj.put("testName", lst.get(1)!=null?lst.get(1):"");
	                          mapTestDtlLstSubObj.put("testId", lst.get(0)!=null?lst.get(0):"");
	                          mapTestDtlLstSubObj.put("labName", lst.get(2)!=null?lst.get(2):"");
							  mapTestDtlLst.put(mapTestDtlLstSubObj);
						} 
						
						
					HashMap<String ,List> map1 = (HashMap) DrugMap;  
                    JSONArray obj2 = new JSONArray();   
						for(Map.Entry m1:map1.entrySet()){  
	                          JSONObject obj = new JSONObject(); 
	                          ArrayList lst = (ArrayList) m1.getValue();
							  obj.put("drugName", lst.get(1)!=null?lst.get(1):"");
							  obj.put("drugId", lst.get(0)!=null?lst.get(0):"");
							  obj.put("drugQuan", lst.get(2)!=null?(lst.get(2).equals("0")?"":lst.get(2)):"");
	                          obj2.put(obj);
						}
				
				
			// System.out.println("BookmarkMap\n"+BookmarkMap);
			//System.out.println("DosageDTL\n"+DosageMap);
			request.getSession().setAttribute("OPDDRDESKDATA", m);
			System.out.println("TestMap"+TestMap.size());
			System.out.println("DrugDTL"+DrugMap.size());
			request.getSession().setAttribute("TESTDTL", mapTestDtlLst);
			request.getSession().setAttribute("DrugDTL", obj2);
			request.getSession().setAttribute("DosageDTL", DosageMap);
			request.getSession().setAttribute("MacrosDTL", MacrosMap);
			request.getSession().setAttribute("DeptDTL", DeptCmb);
			request.getSession().setAttribute("BookmarkDTL", BookmarkMap);
			request.getSession().setAttribute("DiagnosisDTL", mapIcdCodeLst);
			request.getSession().setAttribute("UserName", userName);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}*/

}

