package medicalboard.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.ReferMappingMstDATA;
import medicalboard.masters.controller.fb.ReferMappingMstFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.UserVO;

public class ReferMappingMstUTL extends ControllerUTIL{

	
	 public static void getEssential(ReferMappingMstFB fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			Map mp=new HashMap();
			List certificateTypeName=new ArrayList();
			try{
			UserVO userVO=getUserVO(_request);
			setSysdate(_request);
			fb.setNoOfRow("0");
			
			if(fb.getControls()[0]!=null){
		    String certificateTypeId=fb.getControls()[0];
			fb.setCertificateTypeID(certificateTypeId);}
			
			mp=ReferMappingMstDATA.getReferEssentials(userVO);
			
			
			certificateTypeName=(List)mp.get(MedicalBoardConfig.CERTIFICATE_TYPE_LIST);
			
			   for(int i=0;i<certificateTypeName.size();i++)
			   {
				   Entry entry=new Entry();
				   entry=(Entry)certificateTypeName.get(i);
				  if(fb.getCertificateTypeID().equals(entry.getValue()))
				  {
					  fb.setCertificateTypeName(entry.getLabel());
				  }
			   }
			   WebUTIL.setMapInSession(mp,_request);
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");
				e.printStackTrace();
			}		
			catch(HisRegistrationTimingExpiredException e){
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
				e.printStackTrace();
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "","Application Execution Error");
				e.printStackTrace();
			}
			catch(HisException e){
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
			    System.out.println("objStatus in finally"+objStatus);		 
			    System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}

	 
	 
	 
	 public static void addDepartmentUnitList(ReferMappingMstFB fb,HttpServletRequest rq)
		{ 
		   String hmode=fb.getMode();
			Status objStatus=new Status();
			HttpSession session = WebUTIL.getSession(rq);
//			MbReferMappingMstVO referMappingMstVO = new MbReferMappingMstVO();
			List<Entry> deptList=new ArrayList<Entry>();
			List<Entry> unitList=new ArrayList<Entry>();
			//fb.setNoOfRow("0");
//			boolean exists = false;
			List<MbReferMappingMstVO> selectedDept= new ArrayList<MbReferMappingMstVO>();
			try
			{
				if(!(session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST) == null))
				{
				  selectedDept =(ArrayList<MbReferMappingMstVO>)session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST);
				}
				
				
				if(!(session.getAttribute(MedicalBoardConfig.ALL_DEPARTMENT_LIST) == null))
				{
					deptList =(ArrayList<Entry>)session.getAttribute(MedicalBoardConfig.ALL_DEPARTMENT_LIST);
				}
				
				if(!(session.getAttribute(MedicalBoardConfig.ALL_UNIT_LIST) == null))
				{
					unitList =(ArrayList<Entry>)session.getAttribute(MedicalBoardConfig.ALL_UNIT_LIST);
				}
				
				
				
				
				
				
				MbReferMappingMstVO vo = new MbReferMappingMstVO();
//				String deptOrUnitName="";
				if(fb.getReferType().equals("1"))
				{
					Iterator itr=deptList.iterator();
					 while(itr.hasNext())
					 {
						 Entry entry=new Entry();
						 entry=(Entry)itr.next();
						 if(entry.getValue().equals(fb.getDeptCode()))
						 {  vo.setDeptOrUnitName(entry.getLabel());
						 itr.remove();   /// to remove selected department Or unit from list//
						   
						 break;}
						 
					 }
					 
				}else{
					
					String deptAndUnitCode=fb.getDeptUnitCode();
					String[] deptUnitCode=deptAndUnitCode.split("@");
					fb.setDeptCode(deptUnitCode[1]);
					fb.setDeptUnitCode(deptUnitCode[0]);
					
					Iterator itr=unitList.iterator();
					 while(itr.hasNext())
					 {
						 Entry entry=new Entry();
						 entry=(Entry)itr.next();

						 String deptAndUnit=entry.getValue();
						 String[] deptUnit=deptAndUnit.split("@");
						 if(deptUnit[0].equals(fb.getDeptUnitCode()))
						 {  vo.setDeptOrUnitName(entry.getLabel());
						   itr.remove();   /// to remove selected department Or unit from list//
						 break; }
						 
					 }
					 
					 
				}
				
			     if(fb.getIsOptional().equals("0"))
			     {
			    	 vo.setIsOptionalLabel(MedicalBoardConfig.YESNO_FLAG_NO);
			     }else{
			    	 vo.setIsOptionalLabel(MedicalBoardConfig.YESNO_FLAG_YES);
			     }
				
				
				
				
					
					vo.setCertificateTypeID(fb.getCertificateTypeID());
					vo.setReferType(fb.getReferType());
					if(fb.getReferType().equals(MedicalBoardConfig.REFER_TYPE_FLAG_DEPARTMENT))
					{
						vo.setReferTypeName(MedicalBoardConfig.REFER_TYPE_DEPARTMENT);
					}
					else
					{
						vo.setReferTypeName(MedicalBoardConfig.REFER_TYPE_UNIT);
					}
					vo.setDeptCode(fb.getDeptCode());
					vo.setDeptUnitCode(fb.getDeptUnitCode());
					vo.setIsOptional(fb.getIsOptional());
					vo.setRemarks(fb.getRemarks());
				
					
					selectedDept.add(vo);
					WebUTIL.setAttributeInSession(rq, MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST, selectedDept);
				
					
					
					
					
					
					WebUTIL.setAttributeInSession(rq, MedicalBoardConfig.ALL_DEPARTMENT_LIST, deptList);
					WebUTIL.setAttributeInSession(rq, MedicalBoardConfig.ALL_UNIT_LIST, unitList);
					
					
					
					fb.setHmode(hmode);
					fb.setNoOfRow(String.valueOf(selectedDept.size()));
					
					objStatus.add(Status.LIST);
				
			
				
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found ");
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			}
			catch (HisException e)
			{
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(rq, objStatus);
			}

		}
	 
	 
	 
	 
	 public static void removeDepartmentUnitList(ReferMappingMstFB fb,HttpServletRequest rq)
		{
		    String hmode=fb.getMode();
			Status objStatus=new Status();
			HttpSession session = WebUTIL.getSession(rq);
//			MbReferMappingMstVO vo = null;
			List<Entry> deptListold=new ArrayList<Entry>();
			List<Entry> unitListold=new ArrayList<Entry>();
			
			List<Entry> deptList=new ArrayList<Entry>();
			List<Entry> unitList=new ArrayList<Entry>();
			
		//	fb.setNoOfRow("0");
			List<MbReferMappingMstVO> selectedDept = new ArrayList<MbReferMappingMstVO>();
			try
			{
				if(!(session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST) == null))
				{
					selectedDept = (ArrayList<MbReferMappingMstVO>)session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST);
				}

				
				
				if(!(session.getAttribute(MedicalBoardConfig.ALL_DEPARTMENT_LIST_OLD) == null))
				{
					deptListold =(ArrayList<Entry>)session.getAttribute(MedicalBoardConfig.ALL_DEPARTMENT_LIST_OLD);
					
					Iterator itr=deptListold.iterator();
					while(itr.hasNext())
					{
					  	Entry entry=new Entry();
					  	entry =(Entry)itr.next();
					  	deptList.add(entry);
					}
					
					
				}
				
				if(!(session.getAttribute(MedicalBoardConfig.ALL_UNIT_LIST_OLD) == null))
				{
					unitListold =(ArrayList<Entry>)session.getAttribute(MedicalBoardConfig.ALL_UNIT_LIST_OLD);

					Iterator itr=unitListold.iterator();
					while(itr.hasNext())
					{
					  	Entry entry=new Entry();
					  	entry =(Entry)itr.next();
					  	unitList.add(entry);
					}
				}
				
				Iterator itr=selectedDept.iterator();
				
				Integer count=0;
				while(itr.hasNext())
				{ 
				 	Entry entry=new Entry();
			MbReferMappingMstVO mappingMstVO=(MbReferMappingMstVO)itr.next();
					if(count.toString().equals(fb.getIndex()))
					{
						entry.setLabel(mappingMstVO.getDeptOrUnitName()); //Added on 15th-oct-2014 by anant patel
						entry.setLabel(mappingMstVO.getDeptUnitCode());  //
						itr.remove();
					}
					  
					count++;
				} 
				
				
			if(selectedDept.size()>0)
			 {	
				Iterator selectedvalue=selectedDept.iterator();
				while(selectedvalue.hasNext()){
					MbReferMappingMstVO mappingMstVO=(MbReferMappingMstVO)selectedvalue.next();
					
				  if(mappingMstVO.getReferType().equals("1"))
				    {
					Iterator deptItr=deptList.iterator();
					 while(deptItr.hasNext())
					 {
						 Entry entry=new Entry();
						 entry=(Entry)deptItr.next();
						 if(entry.getValue().equals(mappingMstVO.getDeptCode()))
						 { deptItr.remove();   /// to remove selected department Or unit from list//
						 break;}
						 
					 }
				   }else{
					
					Iterator unitItr=unitList.iterator();
					 while(unitItr.hasNext())
					 {
						 Entry entry=new Entry();
						 entry=(Entry)unitItr.next();
						 String deptAndUnitCode=entry.getValue();
						 String[] deptUnitCode=deptAndUnitCode.split("@");
						 
						 if(deptUnitCode[0].equals(mappingMstVO.getDeptUnitCode()))
						 {  unitItr.remove();   /// to remove selected department Or unit from list//
						 break; }
						 
					 }
				   }
				}
			 }	
			 
			    WebUTIL.setAttributeInSession(rq, MedicalBoardConfig.ALL_DEPARTMENT_LIST, deptList);
			    WebUTIL.setAttributeInSession(rq, MedicalBoardConfig.ALL_UNIT_LIST, unitList);
				
					if(selectedDept.size()>0)
					{
						WebUTIL.setAttributeInSession(rq, MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST, selectedDept);
						objStatus.add(Status.LIST);
					}
					else
					{
						objStatus.add(Status.NEW);
					}
					
					fb.setHmode(hmode);
					fb.setNoOfRow(String.valueOf(selectedDept.size()));
				}
		catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found ");
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			}
			catch (HisException e)
			{
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(rq, objStatus);
			}

		}
		
	 
	 
	 
	 
	 public static void saveReferMappingData(HttpServletRequest rq)
		{
			Status objStatus=new Status();
			HttpSession session = WebUTIL.getSession(rq);
			UserVO userVO = getUserVO(rq);
//			MbReferMappingMstVO[] _donorParaVO = null;
			List<MbReferMappingMstVO> selectedDeptOrUnit = new ArrayList<MbReferMappingMstVO>();
			try
			{
				if(!(session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST) == null))
				{
					selectedDeptOrUnit = (ArrayList<MbReferMappingMstVO>)session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST);
				}
		
				ReferMappingMstDATA.saveReferMappingData(selectedDeptOrUnit,userVO);
		
				objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			}
			catch (HisException e)
			{
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(rq, objStatus);
			}

		}
		
	
		public static boolean getReferMappingDetail(ReferMappingMstFB fb, HttpServletRequest _request)
		{
			String hmode=fb.getMode();
			Status objStatus = new Status();
			MbReferMappingMstVO mappingMstVO = new MbReferMappingMstVO();
			
			MbReferMappingMstVO[] mappingMstVOs=null; 
			
		
			
			Map mp = new HashMap();
//			HttpSession session = WebUTIL.getSession(_request);
			List<Entry> deptList=new ArrayList<Entry>();
			List<Entry> unitList=new ArrayList<Entry>();
			List<Entry> certificateTypeName=new ArrayList<Entry>();
		
			List<MbReferMappingMstVO> selectedDept= new ArrayList<MbReferMappingMstVO>();
			try
			{
				UserVO userVO = getUserVO(_request);
				setSysdate(_request);

				// Fetching Selected Record Primary Key
				String chk = fb.getChk()[0].replace("^", "@");
				String[] concatid = chk.split("@");

				String scertificateTypeId = concatid[0];
				String sSlno = concatid[1];
//				String shospitalCode = concatid[2];
				
				// putting the selected Record Primary Key into Vo

				fb.setCertificateTypeID(scertificateTypeId);
				fb.setSlNo(sSlno);
				
			
				//MbReferMappingMstVO.setSlNo(sSlno);
				mappingMstVO.setCertificateTypeID(scertificateTypeId);
				
				mp = ReferMappingMstDATA.getReferMappingDetail(mappingMstVO,userVO);
				
				WebUTIL.setMapInSession(mp,_request);
				
				if(!(mp.get(MedicalBoardConfig.ALL_DEPARTMENT_LIST) == null))
				{
					deptList =(ArrayList<Entry>)mp.get(MedicalBoardConfig.ALL_DEPARTMENT_LIST);
				}
				
				if(!(mp.get(MedicalBoardConfig.ALL_UNIT_LIST) == null))
				{
					unitList =(ArrayList<Entry>)mp.get(MedicalBoardConfig.ALL_UNIT_LIST);
				}
			
				if(!(mp.get(MedicalBoardConfig.CERTIFICATE_TYPE_LIST) == null))
				{
				   certificateTypeName=(List)mp.get(MedicalBoardConfig.CERTIFICATE_TYPE_LIST);
				}
				  
				
				  for(int i=0;i<certificateTypeName.size();i++)
				   {
					   Entry entry=new Entry();
					   entry=(Entry)certificateTypeName.get(i);
					  if(fb.getCertificateTypeID().equals(entry.getValue()))
					  {
						  fb.setCertificateTypeName(entry.getLabel());
					  }
				   }
				
				
				
				
				mappingMstVOs=(MbReferMappingMstVO[])mp.get(MedicalBoardConfig.REFER_DETAIL_VOS);
				
				for(int i=0;i<mappingMstVOs.length;i++)
				{
					
					MbReferMappingMstVO mstVO =(MbReferMappingMstVO)mappingMstVOs[i];
					MbReferMappingMstVO vo = new MbReferMappingMstVO();
					
					
//					String deptOrUnitName="";
					if(mstVO.getReferType().equals("1"))
					{
						Iterator itr=deptList.iterator();
						 while(itr.hasNext())
						 {
							 Entry entry=new Entry();
							 entry=(Entry)itr.next();
							 if(entry.getValue().equals(mstVO.getDeptCode()))
							 {  vo.setDeptOrUnitName(entry.getLabel());
							 itr.remove();   /// to remove selected department Or unit from list//
							   
							 break;}
							 
						 }
						 
					}else{
						
						Iterator itr=unitList.iterator();
						 while(itr.hasNext())
						 {
							 Entry entry=new Entry();
							 entry=(Entry)itr.next();

							 String deptAndUnit=entry.getValue();
							 String[] deptUnit=deptAndUnit.split("@");
							 if(deptUnit[0].equals(mstVO.getDeptUnitCode()))
							 {  vo.setDeptOrUnitName(entry.getLabel());
							   itr.remove();   /// to remove selected department Or unit from list//
							 break; }
						 }
					}
					
				     if(mstVO.getIsOptional().equals("0"))
				     {
				    	 vo.setIsOptionalLabel(MedicalBoardConfig.YESNO_FLAG_NO);
				     }else{
				    	 vo.setIsOptionalLabel(MedicalBoardConfig.YESNO_FLAG_YES);
				     }
					
				     
				     Iterator certificateitr=certificateTypeName.iterator();
					 while(certificateitr.hasNext())
					 {
						 Entry entry=new Entry();
						 entry=(Entry)certificateitr.next();
						 if(entry.getValue().equals(mstVO.getCertificateTypeID()))
						 {  vo.setCertificateTypeName(entry.getLabel());
						 break;}
						 
					 }
				     
				     	vo.setCertificateTypeID(mstVO.getCertificateTypeID());
						vo.setReferType(mstVO.getReferType());
						if(mstVO.getReferType().equals(MedicalBoardConfig.REFER_TYPE_FLAG_DEPARTMENT))
						{
							vo.setReferTypeName(MedicalBoardConfig.REFER_TYPE_DEPARTMENT);
						}
						else
						{
							vo.setReferTypeName(MedicalBoardConfig.REFER_TYPE_UNIT);
						}
						vo.setDeptCode(mstVO.getDeptCode());
						vo.setDeptUnitCode(mstVO.getDeptUnitCode());
						vo.setIsOptional(mstVO.getIsOptional());
						vo.setRemarks(mstVO.getRemarks());
					
						selectedDept.add(vo);
				}
				
				WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST, selectedDept);
				  WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ALL_DEPARTMENT_LIST, deptList);
				   WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.ALL_UNIT_LIST, unitList);
				
				   fb.setHmode(hmode);
				
				fb.setNoOfRow(String.valueOf(selectedDept.size()));
				objStatus.add(Status.LIST);
				
			}
			catch(HisDuplicateRecordException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			}
			catch (HisRecordNotFoundException e)
			{
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
			}
			catch (HisDataAccessException e)
			{
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			}
			catch (HisException e)
			{
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(_request, objStatus);
				System.out.println("   -----> objStatus in finally  : " + objStatus);
				System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
			}
			return true;
		}

		
		
		
		
		
		
		 public static boolean saveModReferMappingData(HttpServletRequest rq)
			{
				Status objStatus=new Status();
				boolean hasFlage=true;
				HttpSession session = WebUTIL.getSession(rq);
				UserVO userVO = getUserVO(rq);
//				MbReferMappingMstVO[] _donorParaVO = null;
				List<MbReferMappingMstVO> selectedDeptOrUnit = new ArrayList<MbReferMappingMstVO>();
				try
				{
					if(!(session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST) == null))
					{
						selectedDeptOrUnit = (ArrayList<MbReferMappingMstVO>)session.getAttribute(MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST);
					}
					ReferMappingMstDATA.saveModReferMappingData(selectedDeptOrUnit,userVO);
			
					//objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
				}
				catch (HisRecordNotFoundException e)
				{  hasFlage=false;
					System.out.println("Inside HisRecordNotFoundException");
					objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
				}
				catch (HisDataAccessException e)
				{   hasFlage=false;
					System.out.println("Inside HisDataAccessException");
					e.printStackTrace();
					objStatus.add(Status.ERROR_DA, "", "Data Access Error");
				}
				catch (HisApplicationExecutionException e)
				{   hasFlage=false;
					System.out.println("Inside HisApplicationExecutionException");
					objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
				}
				catch (HisException e)
				{   hasFlage=false;
					System.out.println("Inside HisException");
					objStatus.add(Status.ERROR, "", "Error");
				}
				finally
				{
					WebUTIL.setStatus(rq, objStatus);
				}
                  return hasFlage;
			}

		
		
		
	
}
