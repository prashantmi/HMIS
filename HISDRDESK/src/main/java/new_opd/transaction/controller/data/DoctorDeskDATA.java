package new_opd.transaction.controller.data;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;

import new_opd.bo.DoctorDeskBO;
import new_opd.transaction.controller.fb.DoctorDeskFB;
import new_opd.transaction.controller.util.OPDTemplateMstUtil;
import new_opd.vo.DoctorDeskVO;
import hisglobal.config.HISConfig;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

public class DoctorDeskDATA extends ControllerDATA {
	
	
	public static void getDeptDtlData(DoctorDeskFB formBean,
			HttpServletRequest request) {
		DoctorDeskVO vo=null;
		DoctorDeskBO bo=null;
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
		Map<String, String> strExternalHospital=null;
		Map<String, String> strExternalInstitute=null;
		
		Map <String ,List> BookmarkMap=null;
		try
		{
			vo=new DoctorDeskVO();
			bo=new DoctorDeskBO();
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
			//System.out.println("formBean.getStrPrevDate():::::"+formBean.getStrPrevDate());
			bo.getInitilasData1(vo, request);
			//bo.getInitilasData(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			/*m=new LinkedHashMap<String , List>();
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
				
			DosageMap=new LinkedHashMap<String , List>();
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
				
				clinicalProcedureMap=new HashMap<String, String>();
				
				if(vo.getStrCinicalProcudreWb().size()>0)
				{
					while (vo.getStrCinicalProcudreWb().next()) {
						clinicalProcedureMap.put(vo.getStrCinicalProcudreWb().getString(1), vo.getStrCinicalProcudreWb().getString(2));
						
					}
				}
				
				
				
				*/
				DeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrDeptWb().size()>0)
				{
					while (vo.getStrDeptWb().next()) {
						DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
						
					}
				}
				/*
				/*ReffralDeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrRefferalDeptWb().size()>0)
				{
					while (vo.getStrRefferalDeptWb().next()) {
						ReffralDeptCmb.put(vo.getStrRefferalDeptWb().getString(1), vo.getStrRefferalDeptWb().getString(2));
						
					}
				}*/
				/*System.out.println("formBean.getDeptUnitName()"+formBean.getDeptUnitName());
				if(formBean.getDeptUnitName()!=null)
				{
					request.getSession().setAttribute("SelectedDeptId", formBean.getDeptUnitName());
				}else{
					request.getSession().setAttribute("SelectedDeptId", "0");
				}*/
			
				/*BookmarkMap=new HashMap<String , List>();
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
						
						
						HashMap<String ,List> clinicalProcedureMapDtl = (HashMap) clinicalProcedureMap;                       
	                    JSONArray clinicalProcedureMapDtlList = new JSONArray();   
							for(Map.Entry mapTestDtlItem:clinicalProcedureMapDtl.entrySet()){  
		                          JSONObject mapTestDtlLstSubObj = new JSONObject(); 
		                          //ArrayList lst = (ArrayList) mapTestDtlItem.getValue();
		                          mapTestDtlLstSubObj.put("testName", mapTestDtlItem.getValue()!=null?mapTestDtlItem.getValue():"");
		                          mapTestDtlLstSubObj.put("testId", mapTestDtlItem.getKey()!=null?mapTestDtlItem.getKey():"");
		                         // mapTestDtlLstSubObj.put("labName", lst.get(2)!=null?lst.get(2):"");
		                          clinicalProcedureMapDtlList.put(mapTestDtlLstSubObj);
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
				*/
				
			// System.out.println("BookmarkMap\n"+BookmarkMap);
			//System.out.println("DosageDTL\n"+DosageMap);
/*			request.getSession().setAttribute("OPDDRDESKDATA", m);
			System.out.println("TestMap"+TestMap.size());
			System.out.println("DrugDTL"+DrugMap.size());
			request.getSession().setAttribute("TESTDTL", mapTestDtlLst);
			request.getSession().setAttribute("CILINICALPROCEDURE", clinicalProcedureMapDtlList);
			request.getSession().setAttribute("DrugDTL", obj2);
			request.getSession().setAttribute("DosageDTL", DosageMap);
			request.getSession().setAttribute("MacrosDTL", MacrosMap);*/
			request.getSession().setAttribute("DeptDTL", DeptCmb);
			formBean.setStrHiddenDeptCode(vo.getStrHiddenDeptCode());
			/*request.getSession().setAttribute("ReffralDeptCmb", ReffralDeptCmb);
			
			request.getSession().setAttribute("BookmarkDTL", BookmarkMap);
			request.getSession().setAttribute("DiagnosisDTL", mapIcdCodeLst);*/
			request.getSession().setAttribute("UserName", userVO.getUsrName());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	
	public static void getInitailData(DoctorDeskFB formBean,
			HttpServletRequest request) {
		DoctorDeskVO vo=null;
		DoctorDeskBO bo=null;
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
		Map<String, String> strExternalHospital=null;
		Map<String, String> strExternalInstitute=null;
		Map<String, String> strExternalDepartmentList=null;
		
		
		ArrayList<String> columnlist = new ArrayList<String>();
		ArrayList<String> columnlist1 = new ArrayList<String>();
		Map <String ,List> BookmarkMap=null;
		Map <String ,String> strTemplateHtmlMap=null;
		try
		{
			vo=new DoctorDeskVO();
			bo=new DoctorDeskBO();
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrMode(formBean.getStrmode());
			//System.out.println("Seat id -->> "+SeatId);
			//System.out.println("user vo Seat id -->> "+userVO.getSeatId());
			vo.setStrSeatId(SeatId);
			formBean.setStrSeatId(SeatId);
			
			//System.out.println("userVO:::::::::"+userVO.getUserName());
			//System.out.println("formBean.getStrHiddenDeptCode()"+formBean.getStrHiddenDeptCode());
			//System.out.println("userVO:::::::"+userVO.getUsrName());
			vo.setStrRoomCode(formBean.getStrRoomCode());
			vo.setStrDeptCode(formBean.getStrHiddenDeptCode());
			
			vo.setStrDeptUnitCode(formBean.getStrHiddenDeptCode());
			vo.setStrPrevDate(formBean.getStrPrevDate());
			//System.out.println("formBean.getStrPrevDate():::::"+formBean.getStrPrevDate());
			//bo.getInitilasData1(vo, request);
			bo.getInitilasData(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			if(vo.getStrOPDConfigWb()!=null && vo.getStrOPDConfigWb().size()>0 ) {
				while(vo.getStrOPDConfigWb().next())
				{
					formBean.setStrRailTailFlg(vo.getStrOPDConfigWb().getString(1));
				}
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
				
			DosageMap=new LinkedHashMap<String , List>();
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
			/*	MacrosMap=new HashMap<String, String>();
				
				if(vo.getStrMacrosWb().size()>0)
				{
					while (vo.getStrMacrosWb().next()) {
						MacrosMap.put(vo.getStrMacrosWb().getString(1), vo.getStrMacrosWb().getString(2));
						
					}
				}
				*/
				
				
				 ArrayList<String> macrosmultimapList = new ArrayList<String>();
				MultiValueMap macrosmultimap = null;
				macrosmultimap = new MultiValueMap();
				int macrosmultimaplength=	vo.getStrMacrosWb().getMetaData().getColumnCount();
				for(int i=1;i<=macrosmultimaplength;i++)
				{
					macrosmultimapList.add(vo.getStrMacrosWb().getMetaData().getColumnName(i).toUpperCase());
				}
				
				if (vo.getStrMacrosWb() != null && vo.getStrMacrosWb().size() > 0) {
		              while (vo.getStrMacrosWb().next()) {
		            	  int j=0;
		                	//status="1";
		                	JSONObject js=new JSONObject();
		                	for (int i=1;i<=macrosmultimaplength;i++)
		                	{
		                		String key=macrosmultimapList.get(i-1);
		                		String value=vo.getStrMacrosWb().getString(i);
		                		js.put(key, value);
		                		
		                	}
		                	macrosmultimap.put(vo.getStrMacrosWb().getString(1), js);               	
		                	
		                }
		              //ja.put(jsonolist);
		              }
				
				//System.out.println("macrosmultimap.toString()"+macrosmultimap.toString());
				
				clinicalProcedureMap=new HashMap<String, String>();
				
				if(vo.getStrCinicalProcudreWb().size()>0)
				{
					while (vo.getStrCinicalProcudreWb().next()) {
						clinicalProcedureMap.put(vo.getStrCinicalProcudreWb().getString(1), vo.getStrCinicalProcudreWb().getString(2));
						
					}
				}
				
				
				
				
				/*DeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrDeptWb().size()>0)
				{
					while (vo.getStrDeptWb().next()) {
						DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
						
					}
				}*/
				
				ReffralDeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrRefferalDeptWb().size()>0)
				{
					while (vo.getStrRefferalDeptWb().next()) {
						ReffralDeptCmb.put(vo.getStrRefferalDeptWb().getString(1), vo.getStrRefferalDeptWb().getString(2));
						
					}
				}
				
				
				
				//System.out.println("formBean.getDeptUnitName()"+formBean.getDeptUnitName());
				if(formBean.getDeptUnitName()!=null)
				{
					request.getSession().setAttribute("SelectedDeptId", formBean.getDeptUnitName());
				}else{
					request.getSession().setAttribute("SelectedDeptId", "0");
				}
			
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
						
						
						HashMap<String ,List> clinicalProcedureMapDtl = (HashMap) clinicalProcedureMap;                       
	                    JSONArray clinicalProcedureMapDtlList = new JSONArray();   
							for(Map.Entry mapTestDtlItem:clinicalProcedureMapDtl.entrySet()){  
		                          JSONObject mapTestDtlLstSubObj = new JSONObject(); 
		                          //ArrayList lst = (ArrayList) mapTestDtlItem.getValue();
		                          mapTestDtlLstSubObj.put("testName", mapTestDtlItem.getValue()!=null?mapTestDtlItem.getValue():"");
		                          mapTestDtlLstSubObj.put("testId", mapTestDtlItem.getKey()!=null?mapTestDtlItem.getKey():"");
		                         // mapTestDtlLstSubObj.put("labName", lst.get(2)!=null?lst.get(2):"");
		                          clinicalProcedureMapDtlList.put(mapTestDtlLstSubObj);
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
						int	length=0 ;
						MultiValueMap strDrugProfile = null;
		  				strDrugProfile = new MultiValueMap();
		  			    length=	vo.getStrDrugProfileWs().getMetaData().getColumnCount();
		  			    
		  			  for(int i=1;i<=length;i++)
						{
							columnlist.add(vo.getStrDrugProfileWs().getMetaData().getColumnName(i).toUpperCase());
						}
						
		  			if (vo.getStrDrugProfileWs() != null && vo.getStrDrugProfileWs().size() > 0) {
			              while (vo.getStrDrugProfileWs().next()) {
			            	  int j=0;
			                	//status="1";
			                	JSONObject js=new JSONObject();
			                	for (int i=1;i<=length;i++)
			                	{
			                		String key=columnlist.get(i-1);
			                		String value=vo.getStrDrugProfileWs().getString(i);
			                		js.put(key, value);
			                		
			                	}
			                	strDrugProfile.put(vo.getStrDrugProfileWs().getString(1)+"#"+vo.getStrDrugProfileWs().getString(2), js);               	
			                	
			                }
			              //ja.put(jsonolist);
			              }
		  			MultiValueMap TemplateProfile = null;
		  			int	lengthl=0 ;
		  			if (vo.getStrTemplateWebRowSet() != null && vo.getStrTemplateWebRowSet().size() > 0) {
		  			
					TemplateProfile = new MultiValueMap();
					strTemplateHtmlMap=new HashMap<String, String>();
					lengthl=	vo.getStrTemplateWebRowSet().getMetaData().getColumnCount();
	  			    
	  			  for(int i=1;i<=lengthl;i++)
					{
	  				columnlist1.add(vo.getStrTemplateWebRowSet().getMetaData().getColumnName(i).toUpperCase());
					}
		  		}
	  			 HashSet<String> htmlhashSet=new HashSet<>();
	  			if (vo.getStrTemplateWebRowSet() != null && vo.getStrTemplateWebRowSet().size() > 0) {
		              while (vo.getStrTemplateWebRowSet().next()) {
		            	  int j=0;
		                	//status="1";
		                	JSONObject js=new JSONObject();
		                	for (int i=1;i<=lengthl;i++)
		                	{
		                		String key=columnlist1.get(i-1);
		                		String value=vo.getStrTemplateWebRowSet().getString(i);
		                		js.put(key, value);
		                	}
		                	htmlhashSet.add(vo.getStrTemplateWebRowSet().getString(1));
		                	TemplateProfile.put(vo.getStrTemplateWebRowSet().getString(1), js);    
		                	//strTemplateHtmlMap.put(vo.getStrTemplateWebRowSet().getString(1),OPDTemplateMstUtil.getHtmlFileFromFTP(vo.getStrTemplateWebRowSet().getString(1).split("#")[0], HsopitalCode) );
		                }
		              }
	  			
	  			if(htmlhashSet !=null){
	  				if(htmlhashSet.size() > 0){
	  					
	  					 Iterator<String> value = htmlhashSet.iterator(); 
	  					
	  					 while (value.hasNext()) {
	  						 String strTemplateId=value.next();
	  						strTemplateHtmlMap.put(strTemplateId,OPDTemplateMstUtil.getHtmlFileFromFTP(strTemplateId.split("#")[0], HsopitalCode) );
	  						//strTemplateHtmlMap.put(strTemplateId,"" );
						}
	  				}
	  			}
	  			
	  			strExternalHospital=new LinkedHashMap<String, String>();
				if(vo.getStrExternalHospitalweb().size()>0)
				{
					while (vo.getStrExternalHospitalweb().next()) {
						strExternalHospital.put(vo.getStrExternalHospitalweb().getString(1), vo.getStrExternalHospitalweb().getString(2));
						
					}
				}
				
				strExternalInstitute=new LinkedHashMap<String, String>();
				if(vo.getStrExternalInstituteweb().size()>0)
				{
					while (vo.getStrExternalInstituteweb().next()) {
						strExternalInstitute.put(vo.getStrExternalInstituteweb().getString(1), vo.getStrExternalInstituteweb().getString(2));
						
					}
				}
				
				strExternalDepartmentList=new LinkedHashMap<String, String>();
				if(vo.getStrExternalDepartmentListweb().size()>0)
				{
					while (vo.getStrExternalDepartmentListweb().next()) {
						strExternalDepartmentList.put(vo.getStrExternalDepartmentListweb().getString(1), vo.getStrExternalDepartmentListweb().getString(2));
						
					}
				}
				
	  			
	  			ArrayList<String> teleConsultancyApprovalData = new ArrayList<String>();
	  			int teleConsultancyApprovalDatacount=0;
	  			
	  			
		  			while (vo.getStrTeleConsultancyDataWb().next()) {
		  				teleConsultancyApprovalDatacount++;
		  				teleConsultancyApprovalData.add(vo.getStrTeleConsultancyDataWb().getString(1));
		  				
					}
		  			
		  			
		  			ArrayList<String> RefeConsultancyApprovalData = new ArrayList<String>();
		  			int RefeConsultancyApprovalDatacount=0;
		  			
		  			
			  			while (vo.getStrRefeConsultancyDataWb().next()) {
			  				RefeConsultancyApprovalDatacount++;
			  				RefeConsultancyApprovalData.add(vo.getStrRefeConsultancyDataWb().getString(1));
			  				
						}
		  			
		  			
		  			if(vo.getStrHospitalHeaderWs().size()>0) {
		  				while (vo.getStrHospitalHeaderWs().next()) {
		  					request.getSession().setAttribute("HOSPITAL_NAME", vo.getStrHospitalHeaderWs().getString(2));
		  					request.getSession().setAttribute("HOSPITAL_ADDRESS", vo.getStrHospitalHeaderWs().getString(3));
				  			
						}
		  				}
		  			
		  			
		  			LinkedHashMap<String, String> strServiceAreaHashMap=new LinkedHashMap<>();
		  			
		  			if(vo.getStrServiceAreaWebRowSet().size()>0) {
		  				while(vo.getStrServiceAreaWebRowSet().next()){
		  					strServiceAreaHashMap.put(vo.getStrServiceAreaWebRowSet().getString(1), vo.getStrServiceAreaWebRowSet().getString(2));
		  				}
		  			}
		  			
		  			//System.out.println("strServiceAreaHashMap"+strServiceAreaHashMap.toString());
		  			//System.out.println("teleConsultancyApprovalData::::"+new JSONArray(teleConsultancyApprovalData).toString());
	  		//System.out.println("TemplateProfile ::::::::::: "+TemplateProfile.toString());
	  		
	  		//System.out.println("strTemplateHtmlMap ::::::::::: "+strTemplateHtmlMap.toString());
	  		
		  	//System.out.println("DrugProfileBookMarks"+strDrugProfile.toString());
			// System.out.println("BookmarkMap\n"+BookmarkMap);
			//System.out.println("DosageDTL\n"+DosageMap);
			request.getSession().setAttribute("OPDDRDESKDATA", m);
			//System.out.println("TestMap"+TestMap.size());
			//System.out.println("DrugDTL"+DrugMap.size());
			request.getSession().setAttribute("TESTDTL", mapTestDtlLst);
			request.getSession().setAttribute("CILINICALPROCEDURE", clinicalProcedureMapDtlList);
			request.getSession().setAttribute("DrugDTL", obj2);
			request.getSession().setAttribute("DosageDTL", DosageMap);
			request.getSession().setAttribute("MacrosDTL", macrosmultimap);
			//request.getSession().setAttribute("DeptDTL", DeptCmb);
			request.getSession().setAttribute("ReffralDeptCmb", ReffralDeptCmb);
			
			request.getSession().setAttribute("strExternalHospital", strExternalHospital);
			request.getSession().setAttribute("strExternalInstitute", strExternalInstitute);
			request.getSession().setAttribute("strExternalDepartmentList", strExternalDepartmentList);
			
			
			
			request.getSession().setAttribute("BookmarkDTL", BookmarkMap);
			request.getSession().setAttribute("DiagnosisDTL", mapIcdCodeLst);
			request.getSession().setAttribute("UserName", userVO.getUsrName());
			request.getSession().setAttribute("DrugProfile", strDrugProfile);
			
			request.getSession().setAttribute("ServiceAreaDtl", strServiceAreaHashMap);
			if(vo.getStrPrescriptionProfileJSON()!=null)
				request.getSession().setAttribute("PrescriptionBookMark", vo.getStrPrescriptionProfileJSON());
			else
				request.getSession().setAttribute("PrescriptionBookMark", null);
			
			if(vo.getStrPrescriptionProfileJSONArray()!=null && vo.getStrPrescriptionProfileJSONArray().length() > 0){
				request.getSession().setAttribute("PrescriptionBookMarkJsonArray", vo.getStrPrescriptionProfileJSONArray());
			}else{
				request.getSession().setAttribute("PrescriptionBookMarkJsonArray", null);
			}
				
			
			if(TemplateProfile !=null){
			request.getSession().setAttribute("TemplateProfile", TemplateProfile);
			request.getSession().setAttribute("strTemplateHtmlMap", strTemplateHtmlMap);
			}else{
				request.getSession().setAttribute("TemplateProfile", null);
				request.getSession().setAttribute("strTemplateHtmlMap", null);
			}
			request.getSession().setAttribute("teleConsultancyApprovalData", teleConsultancyApprovalData);
			
			request.getSession().setAttribute("RefeConsultancyApprovalData", RefeConsultancyApprovalData);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public static void getPrevDtl(HttpServletRequest request ,HttpServletResponse response)
	{
		
		DoctorDeskVO vo=null;
		DoctorDeskBO bo=null;
		ArrayList<JSONObject> strDrugJson=null;
		ArrayList<JSONObject> strinvJson=null;
		JSONObject mainObj = new JSONObject();
		try {
			
			 vo=new DoctorDeskVO();
			 bo=new DoctorDeskBO();
			 
			
			 //System.out.println("req:::::::::"+request.getAttribute("PUK"));
			 vo.setStrPuk(request.getParameter("PUK").toString());
			 vo.setStrEpisodeCode(request.getParameter("EPISODECODE").toString());
			 vo.setStrHospitalCode(request.getParameter("HOSPITALCODE").toString());
			 vo.setStrVistNo(request.getParameter("visitNo").toString());
			 vo.setStreTeleconsultancyReqId(request.getParameter("eTeleconsultancyReqId").toString());
			 
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
			 mainObj.put("ETELECONSULTANCYVITAL", vo.getStrEtelePreVitalJSON());
			 mainObj.put("REGISTRATIONVISIT", vo.getStrVisitReasonJSON());
			 
			 response.setContentType("application/json");
			 response.getWriter().print(mainObj);
			 //response.sendRedirect(arg0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void getInitailDataMobile(DoctorDeskFB formBean,
			HttpServletRequest request) {
		DoctorDeskVO vo=null;
		DoctorDeskBO bo=null;
		Map<String ,List> m=null;
		List <String> list=null;
		Map<String ,List> TestMap=null;
		Map<String ,List> DiagnosisMap=null;
		Map<String ,List> DrugMap=null;
		Map<String ,List> DosageMap=null;
		Map<String, String> MacrosMap=null;
		List <String> TestList=null;
		List <String> DiagnosisList=null;
		List <String> DrugList=null;
		List <String> DosageList=null;
		List <String> BookmarkList=null;
		Map<String, String> DeptCmb=null;
		
		Map <String ,List> BookmarkMap=null;
		try
		{
			vo=new DoctorDeskVO();
			bo=new DoctorDeskBO();
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
	public static void getInitailDataForMobileApp(DoctorDeskFB formBean,
			HttpServletRequest request) {
		DoctorDeskVO vo=null;
		DoctorDeskBO bo=null;
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
		ArrayList<String> columnlist1 = new ArrayList<String>();
		Map <String ,List> BookmarkMap=null;
		Map <String ,String> strTemplateHtmlMap=null;
		try
		{
			vo=new DoctorDeskVO();
			bo=new DoctorDeskBO();
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrMode("");
			//System.out.println("Seat id -->> "+SeatId);
			//System.out.println("user vo Seat id -->> "+userVO.getSeatId());
			vo.setStrSeatId(SeatId);
			//System.out.println("userVO:::::::::"+userVO.getUserName());
			//System.out.println("formBean.getStrHiddenDeptCode()"+formBean.getStrHiddenDeptCode());
			//System.out.println("userVO:::::::"+userVO.getUsrName());
			//vo.setStrRoomCode(formBean.getStrRoomCode());
			vo.setStrDeptCode(formBean.getDeptUnitName());
			
			//vo.setStrDeptUnitCode(formBean.getStrHiddenDeptCode());
			vo.setStrPrevDate(formBean.getStrPrevDate());
			//System.out.println("formBean.getStrPrevDate():::::"+formBean.getStrPrevDate());
			//bo.getInitilasData1(vo, request);
			bo.getInitilasDataForMobileApp(vo,request);
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
				
			DosageMap=new LinkedHashMap<String , List>();
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
			/*	MacrosMap=new HashMap<String, String>();
				
				if(vo.getStrMacrosWb().size()>0)
				{
					while (vo.getStrMacrosWb().next()) {
						MacrosMap.put(vo.getStrMacrosWb().getString(1), vo.getStrMacrosWb().getString(2));
						
					}
				}
				*/
				
				
				 ArrayList<String> macrosmultimapList = new ArrayList<String>();
				MultiValueMap macrosmultimap = null;
				macrosmultimap = new MultiValueMap();
				int macrosmultimaplength=	vo.getStrMacrosWb().getMetaData().getColumnCount();
				for(int i=1;i<=macrosmultimaplength;i++)
				{
					macrosmultimapList.add(vo.getStrMacrosWb().getMetaData().getColumnName(i).toUpperCase());
				}
				
				if (vo.getStrMacrosWb() != null && vo.getStrMacrosWb().size() > 0) {
		              while (vo.getStrMacrosWb().next()) {
		            	  int j=0;
		                	//status="1";
		                	JSONObject js=new JSONObject();
		                	for (int i=1;i<=macrosmultimaplength;i++)
		                	{
		                		String key=macrosmultimapList.get(i-1);
		                		String value=vo.getStrMacrosWb().getString(i);
		                		js.put(key, value);
		                		
		                	}
		                	macrosmultimap.put(vo.getStrMacrosWb().getString(1), js);               	
		                	
		                }
		              //ja.put(jsonolist);
		              }
				
				//System.out.println("macrosmultimap.toString()"+macrosmultimap.toString());
				
				clinicalProcedureMap=new HashMap<String, String>();
				
				if(vo.getStrCinicalProcudreWb().size()>0)
				{
					while (vo.getStrCinicalProcudreWb().next()) {
						clinicalProcedureMap.put(vo.getStrCinicalProcudreWb().getString(1), vo.getStrCinicalProcudreWb().getString(2));
						
					}
				}
				
				
				
				
				DeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrDeptWb().size()>0)
				{
					while (vo.getStrDeptWb().next()) {
						DeptCmb.put(vo.getStrDeptWb().getString(1), vo.getStrDeptWb().getString(2));
						
					}
				}
				
				ReffralDeptCmb=new LinkedHashMap<String, String>();
				if(vo.getStrRefferalDeptWb().size()>0)
				{
					while (vo.getStrRefferalDeptWb().next()) {
						ReffralDeptCmb.put(vo.getStrRefferalDeptWb().getString(1), vo.getStrRefferalDeptWb().getString(2));
						
					}
				}
				//System.out.println("formBean.getDeptUnitName()"+formBean.getDeptUnitName());
				if(formBean.getDeptUnitName()!=null)
				{
					request.getSession().setAttribute("SelectedDeptId", formBean.getDeptUnitName());
				}else{
					request.getSession().setAttribute("SelectedDeptId", "0");
				}
			
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
						
						
						HashMap<String ,List> clinicalProcedureMapDtl = (HashMap) clinicalProcedureMap;                       
	                    JSONArray clinicalProcedureMapDtlList = new JSONArray();   
							for(Map.Entry mapTestDtlItem:clinicalProcedureMapDtl.entrySet()){  
		                          JSONObject mapTestDtlLstSubObj = new JSONObject(); 
		                          //ArrayList lst = (ArrayList) mapTestDtlItem.getValue();
		                          mapTestDtlLstSubObj.put("testName", mapTestDtlItem.getValue()!=null?mapTestDtlItem.getValue():"");
		                          mapTestDtlLstSubObj.put("testId", mapTestDtlItem.getKey()!=null?mapTestDtlItem.getKey():"");
		                         // mapTestDtlLstSubObj.put("labName", lst.get(2)!=null?lst.get(2):"");
		                          clinicalProcedureMapDtlList.put(mapTestDtlLstSubObj);
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
						int	length=0 ;
						MultiValueMap strDrugProfile = null;
		  				strDrugProfile = new MultiValueMap();
		  			    length=	vo.getStrDrugProfileWs().getMetaData().getColumnCount();
		  			    
		  			  for(int i=1;i<=length;i++)
						{
							columnlist.add(vo.getStrDrugProfileWs().getMetaData().getColumnName(i).toUpperCase());
						}
						
		  			if (vo.getStrDrugProfileWs() != null && vo.getStrDrugProfileWs().size() > 0) {
			              while (vo.getStrDrugProfileWs().next()) {
			            	  int j=0;
			                	//status="1";
			                	JSONObject js=new JSONObject();
			                	for (int i=1;i<=length;i++)
			                	{
			                		String key=columnlist.get(i-1);
			                		String value=vo.getStrDrugProfileWs().getString(i);
			                		js.put(key, value);
			                		
			                	}
			                	strDrugProfile.put(vo.getStrDrugProfileWs().getString(1)+"#"+vo.getStrDrugProfileWs().getString(2), js);               	
			                	
			                }
			              //ja.put(jsonolist);
			              }
		  			
		  			
		  			
		  			
		  			
		  			MultiValueMap TemplateProfile = null;
		  			int	lengthl=0 ;
		  			if (vo.getStrTemplateWebRowSet() != null && vo.getStrTemplateWebRowSet().size() > 0) {
		  			
					TemplateProfile = new MultiValueMap();
					strTemplateHtmlMap=new HashMap<String, String>();
					lengthl=	vo.getStrTemplateWebRowSet().getMetaData().getColumnCount();
	  			    
	  			  for(int i=1;i<=lengthl;i++)
					{
	  				columnlist1.add(vo.getStrTemplateWebRowSet().getMetaData().getColumnName(i).toUpperCase());
					}
		  		}
	  			 HashSet<String> htmlhashSet=new HashSet<>();
	  			if (vo.getStrTemplateWebRowSet() != null && vo.getStrTemplateWebRowSet().size() > 0) {
		              while (vo.getStrTemplateWebRowSet().next()) {
		            	  int j=0;
		                	//status="1";
		                	JSONObject js=new JSONObject();
		                	for (int i=1;i<=lengthl;i++)
		                	{
		                		String key=columnlist1.get(i-1);
		                		String value=vo.getStrTemplateWebRowSet().getString(i);
		                		js.put(key, value);
		                	}
		                	htmlhashSet.add(vo.getStrTemplateWebRowSet().getString(1));
		                	TemplateProfile.put(vo.getStrTemplateWebRowSet().getString(1), js);    
		                	//strTemplateHtmlMap.put(vo.getStrTemplateWebRowSet().getString(1),OPDTemplateMstUtil.getHtmlFileFromFTP(vo.getStrTemplateWebRowSet().getString(1).split("#")[0], HsopitalCode) );
		                }
		              }
	  			
	  			if(htmlhashSet !=null){
	  				if(htmlhashSet.size() > 0){
	  					
	  					 Iterator<String> value = htmlhashSet.iterator(); 
	  					
	  					 while (value.hasNext()) {
	  						 String strTemplateId=value.next();
	  						//strTemplateHtmlMap.put(strTemplateId,OPDTemplateMstUtil.getHtmlFileFromFTP(strTemplateId.split("#")[0], HsopitalCode) );
	  						strTemplateHtmlMap.put(strTemplateId,"" );
						}
	  				}
	  			}
	  			
	  			
	  			ArrayList<String> teleConsultancyApprovalData = new ArrayList<String>();
	  			int teleConsultancyApprovalDatacount=0;
	  			
	  			
		  			while (vo.getStrTeleConsultancyDataWb().next()) {
		  				teleConsultancyApprovalDatacount++;
		  				teleConsultancyApprovalData.add(vo.getStrTeleConsultancyDataWb().getString(1));
		  				
					}
		  			
		  			
		  			
		  			if(vo.getStrHospitalHeaderWs().size()>0) {
		  				while (vo.getStrHospitalHeaderWs().next()) {
		  					request.getSession().setAttribute("HOSPITAL_NAME", vo.getStrHospitalHeaderWs().getString(2));
		  					request.getSession().setAttribute("HOSPITAL_ADDRESS", vo.getStrHospitalHeaderWs().getString(3));
				  			
						}
		  				}
		  			
		  			
		  			LinkedHashMap<String, String> strServiceAreaHashMap=new LinkedHashMap<>();
		  			
		  			if(vo.getStrServiceAreaWebRowSet().size()>0) {
		  				while(vo.getStrServiceAreaWebRowSet().next()){
		  					strServiceAreaHashMap.put(vo.getStrServiceAreaWebRowSet().getString(1), vo.getStrServiceAreaWebRowSet().getString(2));
		  				}
		  			}
		  			
		  			
		  			
		  			ArrayList<String> RefeConsultancyApprovalData = new ArrayList<String>();
		  			int RefeConsultancyApprovalDatacount=0;
		  			
		  			
			  			while (vo.getStrRefeConsultancyDataWb().next()) {
			  				RefeConsultancyApprovalDatacount++;
			  				RefeConsultancyApprovalData.add(vo.getStrRefeConsultancyDataWb().getString(1));
			  				
						}
		  			
		  	
	  		/*System.out.println("TemplateProfile ::::::::::: "+TemplateProfile.toString());
		  	System.out.println("DrugProfileBookMarks"+strDrugProfile.toString());*/
			// System.out.println("BookmarkMap\n"+BookmarkMap);
			//System.out.println("DosageDTL\n"+DosageMap);
			request.getSession().setAttribute("OPDDRDESKDATA", m);
			//System.out.println("TestMap"+TestMap.size());
			//System.out.println("DrugDTL"+DrugMap.size());
			request.getSession().setAttribute("TESTDTL", mapTestDtlLst);
			request.getSession().setAttribute("CILINICALPROCEDURE", clinicalProcedureMapDtlList);
			request.getSession().setAttribute("DrugDTL", obj2);
			request.getSession().setAttribute("DosageDTL", DosageMap);
			request.getSession().setAttribute("MacrosDTL", macrosmultimap);
			request.getSession().setAttribute("DeptDTL", DeptCmb);
			request.getSession().setAttribute("ReffralDeptCmb", ReffralDeptCmb);
			request.getSession().setAttribute("BookmarkDTL", BookmarkMap);
			request.getSession().setAttribute("DiagnosisDTL", mapIcdCodeLst);
			request.getSession().setAttribute("UserName", userVO.getUsrName());
			request.getSession().setAttribute("DrugProfile", strDrugProfile);
			
			request.getSession().setAttribute("ServiceAreaDtl", strServiceAreaHashMap);
			if(vo.getStrPrescriptionProfileJSON()!=null)
				request.getSession().setAttribute("PrescriptionBookMark", vo.getStrPrescriptionProfileJSON());
			else
				request.getSession().setAttribute("PrescriptionBookMark", null);
			
			if(vo.getStrPrescriptionProfileJSONArray()!=null && vo.getStrPrescriptionProfileJSONArray().length() > 0){
				request.getSession().setAttribute("PrescriptionBookMarkJsonArray", vo.getStrPrescriptionProfileJSONArray());
			}else{
				request.getSession().setAttribute("PrescriptionBookMarkJsonArray", null);
			}
				
			
			if(TemplateProfile !=null){
			request.getSession().setAttribute("TemplateProfile", TemplateProfile);
			request.getSession().setAttribute("strTemplateHtmlMap", strTemplateHtmlMap);
			}else{
				request.getSession().setAttribute("TemplateProfile", null);
				request.getSession().setAttribute("strTemplateHtmlMap", null);
			}
			request.getSession().setAttribute("teleConsultancyApprovalData", teleConsultancyApprovalData);
			request.getSession().setAttribute("RefeConsultancyApprovalData", RefeConsultancyApprovalData);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public static void getHospitalHeader(HttpServletRequest request ,HttpServletResponse response , DoctorDeskFB doctorDeskFB)
	{
		DoctorDeskBO bo=null;
		DoctorDeskVO vo=null;
		try {
			
			bo=new DoctorDeskBO();
			vo=new DoctorDeskVO();
			vo.setStrHospitalCode(doctorDeskFB.getStrMobileHospitalCode());
			bo.getHospitalHeader(vo);
			
			if(vo.getStrHospitalHeaderWs().size()>0) {
  				while (vo.getStrHospitalHeaderWs().next()) {
  					request.getSession().setAttribute("HOSPITAL_NAME", vo.getStrHospitalHeaderWs().getString(2));
  					request.getSession().setAttribute("HOSPITAL_ADDRESS", vo.getStrHospitalHeaderWs().getString(3));
  					doctorDeskFB.setStrHospitalName(vo.getStrHospitalHeaderWs().getString(2));
  					doctorDeskFB.setStrHospitalAddres(vo.getStrHospitalHeaderWs().getString(3));
		  			
				}
  				}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFileData(HttpServletRequest request ,HttpServletResponse response , DoctorDeskFB doctorDeskFB, String ftpPath)
	{
		DoctorDeskBO bo=null;
		DoctorDeskVO vo=null;
		try {
			
			bo=new DoctorDeskBO();
			vo=new DoctorDeskVO();
			
			vo.setStrDocumenttype(doctorDeskFB.getStrDocumenttype());
			vo.setStrFileData(doctorDeskFB.getStrFileData());
			vo.setStrLocation(doctorDeskFB.getStrLocation());
			String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			String SeatId=(String) request.getSession().getAttribute("SEATID");
			vo.setStrHospitalCode(HsopitalCode);
			vo.setStrSeatId(SeatId);
			vo.setStrFtpPath(ftpPath);
			bo.saveFileData(vo);
			
			doctorDeskFB.setStrMsgType(vo.getStrMsgType());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

