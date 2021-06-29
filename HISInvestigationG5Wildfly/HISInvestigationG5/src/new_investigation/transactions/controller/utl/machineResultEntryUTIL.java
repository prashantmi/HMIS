package new_investigation.transactions.controller.utl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;








import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.machineResultEntryDATA;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.fb.machineResultEntryFB;
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
import new_investigation.vo.machineResultEntryVO;

public class machineResultEntryUTIL extends ControllerUTIL
{
	public static void getEssential(machineResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		 
		UserVO userVO = ControllerUTIL.getUserVO(request);
		machineResultEntryVO invmachineresultentryVO=new machineResultEntryVO();
		 	 
	try{	
		Map mp=new HashMap(); 
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		mp=machineResultEntryDATA.LabComboForMachineResultEntry(invmachineresultentryVO, userVO);
		WebUTIL.setMapInSession(mp, request);
		//HelperMethods.populate(fb, invmachineResultEntryVO);
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
	
	
	
	}
	
	
	
	public static void getLabBasedMachine(machineResultEntryFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	

		UserVO userVO = ControllerUTIL.getUserVO(request);
		machineResultEntryVO invmachineresultentryVO=new machineResultEntryVO();
		 	 
	try{	
		Map mp=new HashMap(); 
		
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		invmachineresultentryVO.setLabCode(fb.getLabCode());
		mp=machineResultEntryDATA.getLabBasedMachine(invmachineresultentryVO, userVO);
		WebUTIL.setMapInSession(mp, request);
		
		fb.setMachineCode("-1");
		fb.setRecord("-1");
		
		
		//HelperMethods.populate(fb, invmachineResultEntryVO);
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
	
	
	
	}
	
	
	public static boolean setPatientEssentials(machineResultEntryFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		machineResultEntryVO invmachineResultEntryVO = new machineResultEntryVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
					
			invmachineResultEntryVO.setSampleCollDate(fb.getSampleCollDate()); 
			invmachineResultEntryVO.setResultEntryDate(fb.getResultEntryDate());
			invmachineResultEntryVO.setMachineCode(fb.getMachineCode()); 
			invmachineResultEntryVO.setLabCode(fb.getLabCode());
			invmachineResultEntryVO.setRecord(fb.getRecord());
			invmachineResultEntryVO.setIsrepeattest(fb.getIsrepeattest()==null?"0":fb.getIsrepeattest());
			if(fb.getPatcrno1().length()==15)
			invmachineResultEntryVO.setPatcrno1(fb.getPatcrno1());
			else{}
				
			invmachineResultEntryVO.setSamplenoo((fb.getSamplenoo().equals("")?null:fb.getSamplenoo()));

			mp=machineResultEntryDATA.getPatientMachineResultEntry(invmachineResultEntryVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			//HelperMethods.populate(fb, invmachineResultEntryVO);
			  
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	


	// Save Logic
		public static void saveMachineResultEntry(machineResultEntryFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			Map mp=new HashMap();
			int SizeMapOne=0;
			HashMap<String,String> fetchMap=new HashMap();
			try
			{
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				UserVO _userVO=getUserVO(_request);

				List<machineResultEntryVO> invmachineResultEntryVO=new ArrayList<machineResultEntryVO>();
				List<machineResultEntryVO> invResultEntryForParameterDtlVO=new ArrayList<machineResultEntryVO>();

				//Map <labNo#testCode#machineCode#reqDno ( labtestString ), List< machineResultEntryVO> >
				Map<String,List<machineResultEntryVO>> mp_resultEntry = new LinkedHashMap<String,List<machineResultEntryVO>>();
				
				
				String[] selectedLabTestCodeArray=_fb.getChkSamplePatient();
							
				int labRowCount=selectedLabTestCodeArray.length;
							
							
							
				for(int i=0;i<labRowCount;i++)
				{
				
					
				//	Getting chkBoxValues from split array
					
				
					String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
					
				
					String requisitionDNo=testCodeArray[0];
					String machineRecordId=testCodeArray[1];
					String machineCode=testCodeArray[2];
					String machineResult=testCodeArray[3];
					String machineReqId=testCodeArray[4];
					String testCode=testCodeArray[5];
					String parameterCode=testCodeArray[6];
					String machineLabSampleNo=testCodeArray[7];
					String machineTestParameterParaCount=testCodeArray[8];
					String crNo=testCodeArray[9];
					String patGender=testCodeArray[10]==null?"":testCodeArray[10];
					String patAge=testCodeArray[11]==null?"":testCodeArray[11];;
					
					
					String labTestString=machineLabSampleNo+"#"+testCode+"#"+machineCode+"#"+requisitionDNo;
					List<machineResultEntryVO> lstResultEntryVO=mp_resultEntry.get(labTestString);
					
					if(lstResultEntryVO==null||lstResultEntryVO.size()==0)
					{		
						
				    lstResultEntryVO= new ArrayList<machineResultEntryVO>();	
					machineResultEntryVO voResultEntry=new machineResultEntryVO();
					
					voResultEntry.setReqDNo(requisitionDNo);
					voResultEntry.setMachineRecordId(machineRecordId);
					voResultEntry.setMachineCode(machineCode);
					voResultEntry.setMachineResult(machineResult);
					voResultEntry.setMachineReqId(machineReqId);
					voResultEntry.setTestCode(testCode);
					voResultEntry.setParameterCode(parameterCode);
					voResultEntry.setMachineLabSampleNo(machineLabSampleNo);
					voResultEntry.setmachineTestParameterParaCount(machineTestParameterParaCount);
					voResultEntry.setRecord(_fb.getRecord());
					voResultEntry.setPatCrNo(crNo);
					voResultEntry.setPatGender(patGender);
					voResultEntry.setPatAge(patAge);
					
					lstResultEntryVO.add(voResultEntry);
					mp_resultEntry.put(labTestString, lstResultEntryVO);
					
					}
					else
					{
						machineResultEntryVO voResultEntry=new machineResultEntryVO();
						
						voResultEntry.setReqDNo(requisitionDNo);
						voResultEntry.setMachineRecordId(machineRecordId);
						voResultEntry.setMachineCode(machineCode);
						voResultEntry.setMachineResult(machineResult);
						voResultEntry.setMachineReqId(machineReqId);
						voResultEntry.setTestCode(testCode);
						voResultEntry.setParameterCode(parameterCode);
						voResultEntry.setMachineLabSampleNo(machineLabSampleNo);
						voResultEntry.setmachineTestParameterParaCount(machineTestParameterParaCount);
						voResultEntry.setRecord(_fb.getRecord());
						voResultEntry.setPatCrNo(crNo);
						voResultEntry.setPatGender(patGender);
						voResultEntry.setPatAge(patAge);
						
						lstResultEntryVO.add(voResultEntry);
						mp_resultEntry.put(labTestString, lstResultEntryVO);
					}
					
		}// end for loop
				
		mp=machineResultEntryDATA.saveMachineResultEntry(mp_resultEntry, _userVO, _request);
			
		List lstResultEntry=(List)mp.get(InvestigationConfig.LIST_RESULT_ENTRY_STATUS);
				
		
		StringBuilder str = new StringBuilder();	
		 objStatus.add(Status.DONE, str.toString(),
				 "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+ "Result Validated Successfully" + "</font>");
				       


					objStatus.add(Status.NEW, "", "");
					objStatus.add(Status.TRANSINPROCESS, "", "");
				   
			}
			catch (HisRecordNotFoundException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				System.out.println(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				System.out.println(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
				System.out.println(e.getMessage());
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, "", "Error");
				System.out.println(e.getMessage());
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
			}
		}
	
		 
		
		
		public static JsonObject AjaxGetLabList(machineResultEntryFB fb, HttpServletRequest request) {
			
			Status objStatus= new Status();
			
			machineResultEntryVO vo = new machineResultEntryVO();
			List<machineResultEntryVO> listMachineTestReportNewVO = new ArrayList<machineResultEntryVO>();
			Map mp=new HashMap();
			
			JsonObject jsonResponse = new JsonObject();
			JsonArray labListObjectRowsContainer= new JsonArray(); 
			
			try {
				
				UserVO userVO = ControllerUTIL.getUserVO(request);
				HelperMethods.populate(vo, fb);
				ControllerUTIL.setSysdate(request);
				
				mp=machineResultEntryDATA.AjaxGetLabList(vo, userVO);
				listMachineTestReportNewVO=(List<machineResultEntryVO>) mp.get("labList_newmachineentry");
				
				int i=0;
				for(machineResultEntryVO vo2 : listMachineTestReportNewVO) {
					i++;
					
					JsonObject objectRow = new JsonObject();
					
			        objectRow.addProperty("labName", vo2.getLabName()==null?"NA":vo2.getLabName() );
			        objectRow.addProperty("labCode", vo2.getLabCode()==null?"NA":vo2.getLabCode() );
			        
					labListObjectRowsContainer.add(objectRow);
				}
				
				jsonResponse.add("labList", labListObjectRowsContainer);
				
				
				objStatus.add(Status.TRANSINPROCESS);
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
			return jsonResponse;
			
		}


		
		public static JsonObject AjaxGetMachineList(machineResultEntryFB fb, HttpServletRequest request) {
			
			Status objStatus= new Status();
			
			machineResultEntryVO vo = new machineResultEntryVO();
			List<machineResultEntryVO> listmachineResultEntryVO = new ArrayList<machineResultEntryVO>();
			Map mp=new HashMap();
			
			JsonObject jsonResponse = new JsonObject();
			JsonArray machineListObjectRowsContainer= new JsonArray(); 
			
			try {
				
				UserVO userVO = ControllerUTIL.getUserVO(request);
				HelperMethods.populate(vo, fb);
				ControllerUTIL.setSysdate(request);
				
				mp=machineResultEntryDATA.AjaxGetMachineList(vo, userVO);
				listmachineResultEntryVO=(List<machineResultEntryVO>) mp.get("machineList_machineform");
				
				int i=0;
				boolean flg=true;

				
				for(machineResultEntryVO vo2 : listmachineResultEntryVO) {
			
		           	JsonObject objectRow = new JsonObject();
				objectRow.addProperty("machineName", "ALL" );
		        objectRow.addProperty("machineId", "-9" );
		        	
		        machineListObjectRowsContainer.add(objectRow);
			
		        break;
				}
						for(machineResultEntryVO vo2 : listmachineResultEntryVO) {
					i++;
					
					
					JsonObject objectRow = new JsonObject();
					
					
			        objectRow.addProperty("machineName", vo2.getMachinename()==null?"NA":vo2.getMachinename() );
			        objectRow.addProperty("machineId", vo2.getMachineId()==null?"NA":vo2.getMachineId() );
			        
			        machineListObjectRowsContainer.add(objectRow);
				}
				
				jsonResponse.add("machineList", machineListObjectRowsContainer);
				
				
				objStatus.add(Status.TRANSINPROCESS);
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
			return jsonResponse;
			
		}
		
		
		
		
		
		public static JsonObject setPatientEssentialsnewform(machineResultEntryFB fb, HttpServletRequest _request)
		{
			Status objStatus = new Status();
			machineResultEntryVO invmachineResultEntryVO = new machineResultEntryVO();
			String json="";

			JsonObject jsonResponse = new JsonObject();
			JsonArray invResultValReqnListJsonArr = new JsonArray();
			HttpSession session=_request.getSession();
  		
			try
			{
				UserVO userVO = ControllerUTIL.getUserVO(_request);
				ControllerUTIL.setSysdate(_request);
				Map mp=new HashMap(); 
			
				Map<String, String> reqdno_ranges=new HashMap<String, String>() ;			
				Map<String, String> reqdno_rangestype=new HashMap<String, String>() ;			
				Map<String, String> all_ranges=new HashMap<String, String>() ;			

			if(fb.getMachineCode()!=null && fb.getMachineCode().equals("-9"))
				{
					fb.setMachineCode("%") ;
				}
				
				invmachineResultEntryVO.setSampleCollDate(fb.getSampleCollDate()); 
				invmachineResultEntryVO.setResultEntryDate(fb.getResultEntryDate());
				invmachineResultEntryVO.setMachineCode(fb.getMachineCode()); 
				invmachineResultEntryVO.setLabCode(fb.getLabCode());
				invmachineResultEntryVO.setRecord(fb.getRecord());
				invmachineResultEntryVO.setIsrepeattest(fb.getIsrepeattest()==null?"0":fb.getIsrepeattest());
				if(fb.getPatCrNo().length()==15)
				invmachineResultEntryVO.setPatcrno1(fb.getPatCrNo());
				else{}
					
				invmachineResultEntryVO.setSamplenoo((fb.getSamplenoo().equals("")?null:fb.getSamplenoo()));

				mp=machineResultEntryDATA.getPatientMachineResultEntrynew(invmachineResultEntryVO, userVO);
			
				List<machineResultEntryVO> lstInvResultEntryVOall=(List<machineResultEntryVO>)mp.get("essentialdata_machineform");
				
				
				if(lstInvResultEntryVOall!=null && lstInvResultEntryVOall.size()>=0)
				{
					
					int i=0;
					for(machineResultEntryVO vo2 : lstInvResultEntryVOall) {
					

						String newdata="";
						String newborn="";
						String dead="";
						String vip="";
						String mlc="";
						String preg="";
						String priority="";
						String pattype="";
						String pattypecode="";
						
								
						if(vo2.getPattype()==null)
						{
							vo2.setPattype("") ;
						}
						
						
						if(vo2.getPattype().equals(""))
						{
							vo2.setPattype("Non-admitted");
						}
						
						if(vo2.getPattype().equalsIgnoreCase("emergency"))
						{
							vo2.setPattype("Emergency(Non-admitted)");
						}
						
						
						JsonObject reqnJsonObj = new JsonObject();
						
						
						
						
						
						
						
						reqnJsonObj.addProperty("sno", ++i);
					
						
						reqnJsonObj.addProperty("crno", vo2.getPatCrNo());
						reqnJsonObj.addProperty("labno", vo2.getMachineLabSampleNo());
						reqnJsonObj.addProperty("testname", vo2.getTestname());
						reqnJsonObj.addProperty("paraname", vo2.getParaname());
						reqnJsonObj.addProperty("machineename", vo2.getMachinename());
						reqnJsonObj.addProperty("machinetestcode", vo2.getMachineTestParameterCode());
						reqnJsonObj.addProperty("groupname", vo2.getGrpname()==null?"":vo2.getGrpname());
					

						
								if(vo2.getIs_newborn()!=null && vo2.getIs_newborn().equals("1"))
						{
							newborn="<font size='1' color='green'><b>(NEW BORN)</b></font>";
						}
						
						if(vo2.getIs_patdead()!=null && vo2.getIs_patdead().equals("1"))
						{
							dead="<font size='1' color='red'><b>(DEAD)</b></font>";
						}
						
						if(vo2.getIs_pregnant()!=null && vo2.getIs_pregnant().equals("2"))
						{
							preg="<font size='1' color='green'><b>(PREGNANT)</b></font>";
						}
						
						if(vo2.getIs_vip()!=null && vo2.getIs_vip().equals("1"))
						{
							vip="<font size='1' color='red'><b>(VIP)</b></font>";
						}
						
						if(vo2.getAs_patmlc()!=null && vo2.getAs_patmlc().equals("1"))
						{
							if(vo2.getMlcno()==null)
								vo2.setMlcno("");
							
							mlc="<font size='1' color='red' title="+vo2.getMlcno()+"><b>(MLC)</b></font>";
						}
						
						if(vo2.getPriority()!=null && ( vo2.getPriority().equals("1") || vo2.getPriority().equals("2") ||  vo2.getPriority().equals("4") ||  vo2.getPriority().equals("5") ))
						{
							priority=vo2.getPriority();
						}
						
						if(vo2.getPattype()!=null && !vo2.getPattype().equals(""))
						{
							pattype="<font size='1' color='green'><b>("+vo2.getPattype().toUpperCase()+")</b></font>";
							
						}
						String emer="0";
						
						String pattype_svg="";
				
						
						if(vo2.getPattype()!=null && !vo2.getPattype().equals(""))
						{
						    
							if(vo2.getPattype().equalsIgnoreCase("Non-admitted"))
							{
								pattypecode="1" ;
								
							}
							
							if(vo2.getPattype().equalsIgnoreCase("Emergency(Non-admitted)"))
							{
								emer="1";
								pattypecode="3" ;
								pattype_svg="<img title=\"Emergency Patient\" height=\"25\" width=\"25\" src=\"media/images/emergency.svg\">";	
									
							}
							
							if(vo2.getPattype().equalsIgnoreCase("admitted"))
							{
								pattypecode="2" ;
								pattype_svg="<img title=\"Admitted Patient\" height=\"25\" width=\"25\" src=\"media/images/hospital-bed (1).svg\">";	
							}
						
				
				
							
							if(vo2.getPattype().equalsIgnoreCase("admitted"))
							{
								pattypecode="2" ;
								pattype_svg="<img title=\"Admitted Patient\" height=\"25\" width=\"25\" src=\"media/images/hospital-bed (1).svg\">";	
							}
						
							
							if(vo2.getIs_vip()!=null && vo2.getIs_vip().equals("1"))
							{
								if(pattype_svg=="")
								pattype_svg="<img title=\"VIP Patient\" height=\"25\" width=\"25\" src=\"media/images/vipp.jpg\">";	
								else
									pattype_svg=pattype_svg+"&nbsp;&nbsp;"+"<img title=\"VIP Patient\" height=\"25\" width=\"25\" src=\"media/images/vipp.jpg\">";	
								
							}
						}
						
						
						String isgroup="0";
						
						if(vo2.getGrpcode()!=null && !vo2.getGrpcode().equals("0") && !vo2.getGrpcode().equals("-1") && !vo2.getGrpcode().equals(""))
						{
							isgroup="1" ;
						}
						
						String unknoen="";
						
						
						if(vo2.getIs_unknown()!=null && vo2.getIs_unknown().equals("1"))
						{
							
							unknoen="<font size='1' color='red' title='Unknown Patient'><b>(UNKNOWN)</b></font>";
						}
						
						newdata=newborn+" "+preg+" "+mlc+" "+" "+dead+" "+unknoen ;
						
						
						
						
						if(vo2.getMachineTestParameterName()==null || vo2.getMachineTestParameterName().equals(""))
						{
							vo2.setMachineTestParameterName("-");
						}
						
						reqnJsonObj.addProperty("machinrtestparaname", vo2.getMachineTestParameterName()==null?"-":vo2.getMachineTestParameterName());
						reqnJsonObj.addProperty("machineresult", vo2.getMachineResult());
						
						
						reqnJsonObj.addProperty("range", vo2.getRanges());
						reqnJsonObj.addProperty("rangetype", vo2.getIs_inrange());
					
						reqdno_ranges.put(vo2.getReqDNo()+vo2.getTestCode()+vo2.getParameterCode(), vo2.getRanges()) ;
						reqdno_rangestype.put(vo2.getReqDNo()+vo2.getTestCode()+vo2.getParameterCode(), vo2.getIs_inrange()) ;
						all_ranges.put(vo2.getReqDNo()+vo2.getTestCode()+vo2.getParameterCode(), vo2.getAllrefrange()) ;
						
						reqnJsonObj.addProperty("entrydate", vo2.getMachineResultEntryDate());
						

						reqnJsonObj.addProperty("reqdno", vo2.getReqDNo());
						reqnJsonObj.addProperty("mrecordid", vo2.getMachineRecordId());
						reqnJsonObj.addProperty("mcode", vo2.getMachineCode());
						reqnJsonObj.addProperty("mreqid", vo2.getMachineReqId());
						reqnJsonObj.addProperty("testcode", vo2.getTestCode());
						reqnJsonObj.addProperty("paracode", vo2.getParameterCode());
						reqnJsonObj.addProperty("mtestparacount", vo2.getmachineTestParameterParaCount());
						reqnJsonObj.addProperty("patgender", (vo2.getPatGender()==null?"":vo2.getPatGender()));
						reqnJsonObj.addProperty("patage", (vo2.getPatAge()==null?"":vo2.getPatAge()));
						reqnJsonObj.addProperty("newdata", newdata);
						reqnJsonObj.addProperty("priority", priority);
						reqnJsonObj.addProperty("pattype", vo2.getPattype().toUpperCase());
						reqnJsonObj.addProperty("pattypecode", pattypecode);
									
						
						if(vo2.getIs_pregnant()!=null && !vo2.getIs_pregnant().equals("2"))
						{	
							vo2.setIs_pregnant("0");
						
						}else if(vo2.getIs_pregnant()!=null && vo2.getIs_pregnant().equals("2"))
						{
							vo2.setIs_pregnant("1");
						}	
						
						reqnJsonObj.addProperty("preg", vo2.getIs_pregnant());
						reqnJsonObj.addProperty("mlc", vo2.getAs_patmlc());
						reqnJsonObj.addProperty("dead", vo2.getIs_patdead());
						reqnJsonObj.addProperty("vip", vo2.getIs_vip());
						reqnJsonObj.addProperty("newborn", vo2.getIs_newborn());
						reqnJsonObj.addProperty("pattypecode1", pattypecode);
						reqnJsonObj.addProperty("unknown", vo2.getIs_unknown());
						
						reqnJsonObj.addProperty("bedname", vo2.getBedName());
						reqnJsonObj.addProperty("admissionno", vo2.getAdmissionno());
						reqnJsonObj.addProperty("mobileno", vo2.getMobileNo());
						reqnJsonObj.addProperty("machinename", vo2.getMachinename());
							
						
						reqnJsonObj.addProperty("patstatuss", vo2.getPatStatus());
						reqnJsonObj.addProperty("ward", vo2.getWardName()==null?"":vo2.getWardName());
						reqnJsonObj.addProperty("category", vo2.getPatCategory()==null?"":vo2.getPatCategory());
						reqnJsonObj.addProperty("emer",emer );

						reqnJsonObj.addProperty("priority", priority);
						
						
				        if(!priority.equals("2") && !priority.equals("5"))	
					reqnJsonObj.addProperty("crnoo", vo2.getPatCrNo()+"<span>&nbsp;"+pattype_svg+"</span><br/><small>"+newdata+"</small>");
				        else
				        	reqnJsonObj.addProperty("crnoo", vo2.getPatCrNo()+"<span>&nbsp;"+pattype_svg+"&nbsp;<font color='red'><span title='Urgent Request'>(U)</font>&nbsp;"+"</span><br/><small>"+newdata+"</small>");
						
				        
						
						invResultValReqnListJsonArr.add(reqnJsonObj);
						 
						
					}
					
				
				}
				
				
				session.setAttribute("reqdno_ranges", reqdno_ranges);
				session.setAttribute("reqdno_rangestype", reqdno_rangestype);
				session.setAttribute("reqdno_allranges", all_ranges);
				
				jsonResponse.add("Machinenewformlist", invResultValReqnListJsonArr);
				jsonResponse.addProperty("isSuccess", "1");
				
				objStatus.add(Status.TRANSINPROCESS);	
				
			
			}
			catch (HisRecordNotFoundException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.toString());
				
				System.out.println(e.getMessage());
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			}
			catch (HisDataAccessException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.toString());
				
				System.out.println(e.getMessage());
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.toString());
				
				System.out.println(e.getMessage());
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			}
			catch (HisException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.getMessage());
				
				System.out.println(e.getMessage());
				e.printStackTrace();
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
			}
			return jsonResponse;
		}		

		
		
		
		public static JsonObject saveMachineResultEntrynew(machineResultEntryFB _fb,HttpServletRequest _request)
		{
			Status objStatus=new Status();	
			HttpSession session=_request.getSession();
			Map mp=new HashMap();
			int SizeMapOne=0;
			HashMap<String,String> fetchMap=new HashMap();
			

			JsonObject jsonResponse = new JsonObject();
					JsonArray invResultValReqnListJsonArr = new JsonArray();
				
			try
			{
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				UserVO _userVO=getUserVO(_request);

				List<machineResultEntryVO> invmachineResultEntryVO=new ArrayList<machineResultEntryVO>();
				List<machineResultEntryVO> invResultEntryForParameterDtlVO=new ArrayList<machineResultEntryVO>();

				//Map <labNo#testCode#machineCode#reqDno ( labtestString ), List< machineResultEntryVO> >
				Map<String,List<machineResultEntryVO>> mp_resultEntry = new LinkedHashMap<String,List<machineResultEntryVO>>();
				
				
				String[] selectedLabTestCodeArray=_fb.getChkSamplePatient()[0].split("@@@");
							
				int labRowCount=selectedLabTestCodeArray.length;
							
							
							
				for(int i=0;i<labRowCount;i++)
				{
				
					
				//	Getting chkBoxValues from split array
					
				
					String[] testCodeArray=selectedLabTestCodeArray[i].split("#");
					
				
					String requisitionDNo=testCodeArray[0];
					String machineRecordId=testCodeArray[1];
					String machineCode=testCodeArray[2];
					String machineResult=testCodeArray[3];
					String machineReqId=testCodeArray[4];
					String testCode=testCodeArray[5];
					String parameterCode=testCodeArray[6];
					String machineLabSampleNo=testCodeArray[7];
					String machineTestParameterParaCount=testCodeArray[8];
					String crNo=testCodeArray[9];
					String patGender=testCodeArray[10]==null?"":testCodeArray[10];
					String patAge=testCodeArray[11]==null?"":testCodeArray[11];;
					
					
					String labTestString=machineLabSampleNo+"#"+testCode+"#"+machineCode+"#"+requisitionDNo;
					List<machineResultEntryVO> lstResultEntryVO=mp_resultEntry.get(labTestString);
					
					if(lstResultEntryVO==null||lstResultEntryVO.size()==0)
					{		
						
				    lstResultEntryVO= new ArrayList<machineResultEntryVO>();	
					machineResultEntryVO voResultEntry=new machineResultEntryVO();
					
					voResultEntry.setReqDNo(requisitionDNo);
					voResultEntry.setMachineRecordId(machineRecordId);
					voResultEntry.setMachineCode(machineCode);
					voResultEntry.setMachineResult(machineResult);
					voResultEntry.setMachineReqId(machineReqId);
					voResultEntry.setTestCode(testCode);
					voResultEntry.setParameterCode(parameterCode);
					voResultEntry.setMachineLabSampleNo(machineLabSampleNo);
					voResultEntry.setmachineTestParameterParaCount(machineTestParameterParaCount);
					voResultEntry.setRecord(_fb.getRecord());
					voResultEntry.setPatCrNo(crNo);
					voResultEntry.setPatGender(patGender);
					voResultEntry.setPatAge(patAge);
					voResultEntry.setIs_callfrom_newmachine_process("1");
					
					lstResultEntryVO.add(voResultEntry);
					mp_resultEntry.put(labTestString, lstResultEntryVO);
					
					}
					else
					{
						machineResultEntryVO voResultEntry=new machineResultEntryVO();
						
						voResultEntry.setReqDNo(requisitionDNo);
						voResultEntry.setMachineRecordId(machineRecordId);
						voResultEntry.setMachineCode(machineCode);
						voResultEntry.setMachineResult(machineResult);
						voResultEntry.setMachineReqId(machineReqId);
						voResultEntry.setTestCode(testCode);
						voResultEntry.setParameterCode(parameterCode);
						voResultEntry.setMachineLabSampleNo(machineLabSampleNo);
						voResultEntry.setmachineTestParameterParaCount(machineTestParameterParaCount);
						voResultEntry.setRecord(_fb.getRecord());
						voResultEntry.setPatCrNo(crNo);
						voResultEntry.setPatGender(patGender);
						voResultEntry.setPatAge(patAge);
						voResultEntry.setIs_callfrom_newmachine_process("1");
							
						lstResultEntryVO.add(voResultEntry);
						mp_resultEntry.put(labTestString, lstResultEntryVO);
					}
					
		}// end for loop
				
		mp=machineResultEntryDATA.saveMachineResultEntrynew(mp_resultEntry, _userVO, _request);
			
		List lstResultEntry=(List)mp.get(InvestigationConfig.LIST_RESULT_ENTRY_STATUS);
			
		
		jsonResponse.addProperty("isSuccess", "1");
		
		StringBuilder str = new StringBuilder();	
		 objStatus.add(Status.DONE, str.toString(),
				 "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"
									+ "Result Validated Successfully" + "</font>");
				       


					objStatus.add(Status.NEW, "", "");
					objStatus.add(Status.TRANSINPROCESS, "", "");
				   
			}
			catch (HisRecordNotFoundException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.getMessage());
				
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				System.out.println(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.getMessage());
				
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				System.out.println(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.getMessage());
				
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
				System.out.println(e.getMessage());
			}
			catch (HisException e)
			{
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", e.getMessage());
				
				objStatus.add(Status.ERROR, "", "Error");
				System.out.println(e.getMessage());
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
			}
			
			return jsonResponse;
		}
	
		 

		
		
		
}


