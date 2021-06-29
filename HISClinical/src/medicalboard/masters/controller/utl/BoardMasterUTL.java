package medicalboard.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.BoardMasterDATA;
import medicalboard.masters.controller.fb.BoardMasterFB;
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
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.UserVO;


public class BoardMasterUTL extends ControllerUTIL
{
	   
	
    public static void getEssential(BoardMasterFB fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		Map mp=new HashMap();
		List boardTypeName=new ArrayList();
		try{
		UserVO userVO=getUserVO(_request);
		setSysdate(_request);
		
		
		if(fb.getControls()[0]!=null){
	    String boardTypeId=fb.getControls()[0];
		fb.setBoardTypeID(boardTypeId);}
		
		mp=BoardMasterDATA.getBoardEssentials(userVO);
		
		
		
		boardTypeName=(List)mp.get(MedicalBoardConfig.BOARD_TYPE_LIST);
		
		   for(int i=0;i<boardTypeName.size();i++)
		   {
			   Entry entry=new Entry();
			   entry=(Entry)boardTypeName.get(i);
			  if(fb.getBoardTypeID().equals(entry.getValue()))
			  {
				  fb.setBoardTypeName(entry.getLabel());
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

	
	
	// On Add Page saving Values into Database
	public static boolean saveBoardInfo(BoardMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
//			boolean hasFlage=true;
			UserVO userVO = getUserVO(_request);
			MedicalBoardMasterVO medicalBoardMasterVO = new MedicalBoardMasterVO();
			String[] empId=_fb.getEmpIDArray();
			String[] escortedBy=_fb.getEscortedEmpIDArray();
			String[] roleID=_fb.getRoleIDArray(); 
			
			String boardtypeId=_fb.getBoardTypeID();
			
		    HelperMethods.populate(medicalBoardMasterVO,_fb);
			
			BoardMasterDATA.saveBoardInfo(medicalBoardMasterVO,empId,escortedBy,roleID,userVO);
			
			_fb.setBoardTypeID(boardtypeId);
			
			 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 

		}
		catch(HisDuplicateRecordException e)
		{    hasFlag = false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisRecordNotFoundException e)
		{ hasFlag = false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{ hasFlag = false;
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{  hasFlag = false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch(HisException e)
		{   hasFlag = false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	
	public static boolean getBoardDetail(BoardMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MedicalBoardMasterVO medicalBoardMasterVO = new MedicalBoardMasterVO();
		List boardTypeName=new ArrayList();
		
		
		Map mp = new HashMap();
//		String str = new String();
//		List districtList=new ArrayList();
//		List RemaingDistrictList=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sBoardId = concatid[0];
			String sSlno = concatid[1];
//			String shospitalCode = concatid[2];
			
			// putting the selected Record Primary Key into Vo

			fb.setBoardID(sBoardId);
			fb.setSlNo(sSlno);
			
			String boardTypeId=fb.getControls()[0];
		
			//medicalBoardMasterVO.setSlNo(sSlno);
			medicalBoardMasterVO.setBoardID(sBoardId);
			
			mp = BoardMasterDATA.fetchBoardDetail(medicalBoardMasterVO,userVO);
			fb.setBoardTypeID(boardTypeId);

		    
			MedicalBoardMasterVO[] masterVOs=null;
			
			masterVOs=(MedicalBoardMasterVO[])mp.get(MedicalBoardConfig.BOARD_DETAIL_VOS);
			
			
			  String empId="";
			  String roleId="";
			  String escortedById="";
			 
			  
			  String empName="";
			  String roleName="";
			  String escortedByName="";
			
			
			for(int i=0;i<masterVOs.length;i++)
			{
			   MedicalBoardMasterVO mBoardMasterVO=(MedicalBoardMasterVO)masterVOs[i];
			  
				if(mBoardMasterVO.getRoleID().equals("0"))
				{  
					if(escortedById.equals(""))
					{
						escortedById=mBoardMasterVO.getEmpID();
						escortedByName=mBoardMasterVO.getEmpName();
					}else{
						escortedById=escortedById+"@"+mBoardMasterVO.getEmpID();
						escortedByName=escortedByName+"@"+mBoardMasterVO.getEmpName();
					}
				}else{
					
					if(empId.equals(""))
					{
						empId=mBoardMasterVO.getEmpID();
						empName=mBoardMasterVO.getEmpName();
						roleId=mBoardMasterVO.getRoleID();
						roleName=mBoardMasterVO.getRoleName();
					}else{
						empId=empId+"@"+mBoardMasterVO.getEmpID();
						empName=empName+"@"+mBoardMasterVO.getEmpName();
						roleId=roleId+"@"+mBoardMasterVO.getRoleID();
						roleName=roleName+"@"+mBoardMasterVO.getRoleName();
					}
					
				}
				fb.setBoardTypeID(mBoardMasterVO.getBoardTypeID());
				
			}
			
//			String[] arr=new String[0];
			 
			 
			 
			fb.setBoardName(masterVOs[0].getBoardName());
			fb.setEscortedEmpName(escortedByName);
			fb.setEscortedEmpIDValue(escortedById);
			fb.setEmpIDValue(empId);
			fb.setEmpName(empName);
			fb.setRoleName(roleName);
			fb.setRoleIDValue(roleId);
		    
		 
			  WebUTIL.setMapInSession(mp,_request);
			
			
		/// doctorList not Added	
			List doctorListNew=new ArrayList();
			List doctorListOld=new ArrayList();
			
			
			doctorListOld=(List)mp.get(MedicalBoardConfig.LIST_OF_EMP_DOCTOR);
			String[] doctorId=null;
			doctorId= empId.split("@") ;
			
			  for(int i=0;i<doctorListOld.size();i++)
			  {  int flag=0;
				  Entry entry=new Entry();
				  entry=(Entry)doctorListOld.get(i);
				
				  for(int j=0;j<doctorId.length;j++)
				  {
					  if(entry.getValue().equals(doctorId[j]))
					  {
						 flag=1;
						 break;
					  }
				  }
				  if(flag==0)
				  {
					  doctorListNew.add(entry);
				  }
			  }
			
		 WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.LIST_OF_EMP_DOCTOR ,doctorListNew);  
			
			/// Escorted By List Not Added 
			  
				
				List escortedByListNew=new ArrayList();
				List escortedByListOld=new ArrayList();
				
				
				escortedByListOld=(List)mp.get(MedicalBoardConfig.LIST_OF_EMP_ESCORTS);
				String[] escortedBy=null;
				escortedBy= escortedById.split("@") ;
				
				  for(int i=0;i<escortedByListOld.size();i++)
				  {   int hasFlag=0;
					  Entry entry=new Entry();
					  entry=(Entry)escortedByListOld.get(i);
					
					  for(int j=0;j<escortedBy.length;j++)
					  {
						  if(entry.getValue().equals(escortedBy[j]))
						  {
							  hasFlag=1;
							  break;
						  }
					  }
					  if(hasFlag==0)
					  {
						  escortedByListNew.add(entry);
					  }
				  }
				  
			WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.LIST_OF_EMP_ESCORTS ,escortedByListNew); 
			  
			
			boardTypeName=(List)mp.get(MedicalBoardConfig.BOARD_TYPE_LIST);
			
			   for(int i=0;i<boardTypeName.size();i++)
			   {
				   Entry entry=new Entry();
				   entry=(Entry)boardTypeName.get(i);
				  if(fb.getBoardTypeID().equals(entry.getValue()))
				  {
					  fb.setBoardTypeName(entry.getLabel());
				  } 
			   }
			
			   
			   
			   
			   
			 
		 
			//objStatus.add(Status.NEW);
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
	
	public static boolean saveModBoardInfo(BoardMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
        boolean hasFlag=true;
        Map mp = new HashMap();
        HttpSession session =WebUTIL.getSession(_request);
		try
		{
			UserVO userVO = getUserVO(_request);
			MedicalBoardMasterVO medicalBoardMasterVO = new MedicalBoardMasterVO();
			String[] empId=_fb.getEmpIDArray();
			String[] escortedBy=_fb.getEscortedEmpIDArray();
			String[] roleID=_fb.getRoleIDArray(); 
			
			String boardTypeId=_fb.getBoardTypeID();
			
			
		    HelperMethods.populate(medicalBoardMasterVO,_fb);
			
			BoardMasterDATA.saveModBoardInfo(medicalBoardMasterVO,empId,escortedBy,roleID,userVO);
			
			_fb.setBoardTypeID(boardTypeId);
			
			mp=BoardMasterDATA.getBoardEssentials(userVO);
			
			List boardTypeName=new ArrayList();
			
			boardTypeName=(List)mp.get(MedicalBoardConfig.BOARD_TYPE_LIST);
			
			   for(int i=0;i<boardTypeName.size();i++)
			   {
				   Entry entry=new Entry();
				   entry=(Entry)boardTypeName.get(i);
				  if(_fb.getBoardTypeID().equals(entry.getValue()))
				  {
					  _fb.setBoardTypeName(entry.getLabel());
				  }
			   }
			
		}
		catch(HisDuplicateRecordException e)
		{ 
			hasFlag=false;
			List boardTypeName=new ArrayList();
			
			boardTypeName=(List)session.getAttribute(MedicalBoardConfig.BOARD_TYPE_LIST);
			
			   for(int i=0;i<boardTypeName.size();i++)
			   {
				   Entry entry=new Entry();
				   entry=(Entry)boardTypeName.get(i);
				  if(_fb.getBoardTypeID().equals(entry.getValue()))
				  {
					  _fb.setBoardTypeName(entry.getLabel());
				  }
			   }
			
			_fb.setHmode("MODIFY");
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisRecordNotFoundException e)
		{
			hasFlag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			hasFlag=false;
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			hasFlag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{  hasFlag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	
	
}
