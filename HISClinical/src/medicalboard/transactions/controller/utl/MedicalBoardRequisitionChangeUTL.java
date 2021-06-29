package medicalboard.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MedicalBoardRequisitionChangeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.data.MedicalBoardRequisitionChangeDATA;
import medicalboard.transactions.controller.fb.MedicalBoardRequisitionChangeFB;


public class MedicalBoardRequisitionChangeUTL  extends ControllerUTIL{
	/**
	 * get List of Medical Board Requisition schedule by crno which has been pending today and onwards
	 * @param _request -HttpServletRequest
	 */
		public static void getPatientMbRequisitionsByCrNo(MedicalBoardRequisitionChangeFB _fb,HttpServletRequest _request){	
		
			Status objStatus=new Status();
//			Map mp=new HashMap();
			List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList();
			try{
				UserVO userVO=getUserVO(_request);
				requisitionVOList=MedicalBoardRequisitionChangeDATA.getPatientMbRequisitionsByCrNo(_fb.getPatCrNo(),userVO);
		       
				WebUTIL.setAttributeInSession(_request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,requisitionVOList);
		        objStatus.add(Status.LIST);
			
			}
			
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.NEW,e.getMessage(),"");	
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
		
		
		/**
		 * saves Medical Board Requisition Change Detail
		 * @param _request
		 * @param _fb
		 */
		public static void saveMedicalBoardRequisitionChange(HttpServletRequest _request, MedicalBoardRequisitionChangeFB _fb){
			UserVO userVO=getUserVO(_request);
			Status objStatus =new Status();	
			List <MedicalBoardRequisitionVO> mRequisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
			List <MedicalBoardRequisitionChangeVO> mRequisitionChangeVOList=new ArrayList<MedicalBoardRequisitionChangeVO>();
			List <MedicalBoardRequisitionVO> mRequisitionVOOldList=(List)_request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
			
			try
			{
				 int countRequisition=_fb.getSelectedRequisition().length;
				 int index=0;
				  for(int i=0;i<countRequisition;i++)
				  {
					  index=Integer.parseInt(_fb.getSelectedRequisition()[i].split("#")[0]);
					  MedicalBoardRequisitionChangeVO vo=new MedicalBoardRequisitionChangeVO();
					  vo.setReqID(_fb.getSelectedRequisition()[i].split("#")[1]);
					  vo.setSlNo(_fb.getSelectedRequisition()[i].split("#")[2]);
					  vo.setChangeReason(_fb.getChangeReason()[i]);
					  vo.setNewExamDate(_fb.getExamDate()[index]); //new Exam Date
					  vo.setOldExamDate(_fb.getOldExamDate()[index]);
					  vo.setReqMode(_fb.getRequestBy());
					  mRequisitionChangeVOList.add(vo);
				  }
				
				// requisition //
				
				for(int i=0;i<countRequisition;i++)
			    {
					index=Integer.parseInt(_fb.getSelectedRequisition()[i].split("#")[0]);  
					MedicalBoardRequisitionVO vo=mRequisitionVOOldList.get(Integer.parseInt(_fb.getSelectedRequisition()[i].split("#")[0]));
					vo.setExamDate(_fb.getExamDate()[index]);//setting the new exam date
					mRequisitionVOList.add(vo);
			    }
			    
				MedicalBoardRequisitionChangeDATA.saveMedicalBoardRequisitionChange(mRequisitionVOList,mRequisitionChangeVOList,userVO);	
				
				objStatus.add(Status.DONE,"","Record Saved Successfully");	
				
			}
			catch(HisDataAccessException e){
				System.out.println("Inside HisDataAccessException");
				objStatus.add(Status.ERROR_DA,"",e.getMessage());		
			}
			catch(HisApplicationExecutionException e){		
				System.out.println("Inside HisApplicationExecutionException");
				objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			}
			catch(HisException e){
				System.out.println("Inside HisException");
				objStatus.add(Status.ERROR,"","Error");
			}		
			finally
			{
			WebUTIL.setStatus(_request,objStatus);
			 System.out.println("objStatus in finally"+objStatus);		 
			 System.out.println("objStatus list"+objStatus.getStatusList());
			}	
    
		}

		/**
		 * get All Medical Board Requisition which has not been performed yet and
		 * whose schedule date is greater than current date
		 * @param fb
		 * @param request
		 */
		public static void getAllMbRequisitions(
				MedicalBoardRequisitionChangeFB fb, HttpServletRequest request) {
			Status objStatus=new Status();
			
			List<MedicalBoardRequisitionVO> requisitionVOList=null;
			try{
				setSysdate(request);
				UserVO userVO=getUserVO(request);
				String patCrNo="%";
				requisitionVOList=MedicalBoardRequisitionChangeDATA.getPatientMbRequisitionsByCrNo(patCrNo, userVO);	
				String scheduleDate[]=null;
				if(requisitionVOList!=null){
					scheduleDate=new String[requisitionVOList.size()];
					for(int i=0;i<requisitionVOList.size();i++){
						scheduleDate[i]="";
					}
				}
				fb.setExamDate(scheduleDate);
				
				WebUTIL.setAttributeInSession(request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,requisitionVOList);
				WebUTIL.setAttributeInSession(request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_OLD ,requisitionVOList);
		        objStatus.add(Status.LIST);
			
			}
			
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.NEW,e.getMessage(),"");	
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
				WebUTIL.setStatus(request,objStatus);
			    System.out.println("objStatus in finally"+objStatus);		 
			    System.out.println("objStatus list"+objStatus.getStatusList());
			}	
			
		}
		
		/**
		 * get All Organisations List
		 * @param fb
		 * @param request
		 */
		public static void getOranisationsList(
				MedicalBoardRequisitionChangeFB fb, HttpServletRequest request) {
			Status objStatus=new Status();
			
			List organisationList=null;
			try{
				setSysdate(request);
				UserVO userVO=getUserVO(request);
				organisationList=MedicalBoardRequisitionChangeDATA.getOrganisationsList(userVO)	;
				
				WebUTIL.setAttributeInSession(request,MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME ,organisationList);
				objStatus.add(Status.NEW);
				
			}
			
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.NEW,e.getMessage(),"");	
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
				WebUTIL.setStatus(request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
			
		}

		
		/**
		 * getting the list of all schedule by a particular organisation
		 * @param fb
		 * @param request
		 */

		public static void getMbRequisitionsByOrg(MedicalBoardRequisitionChangeFB fb, HttpServletRequest request) {
			
			Status objStatus=new Status();
			List<MedicalBoardRequisitionVO> requisitionVOList=null;
			List <Entry> scheduleDateList=new ArrayList<Entry>();
			Map scheduleDate=new HashMap();
			try{
				UserVO userVO=getUserVO(request);
				String orgId=fb.getOrgID();
				String orgName=fb.getOtherOrgName();
				requisitionVOList=MedicalBoardRequisitionChangeDATA.getMbRequisitionsByOrg(orgId,orgName,userVO);
				
				if(requisitionVOList!=null){
					for(int i=0;i<requisitionVOList.size();i++){
						Entry entry=new Entry();
						String examDate=requisitionVOList.get(i).getExamDate();
						if(!examDate.equals(scheduleDate.get(examDate))){
							entry.setValue(examDate);
							entry.setLabel(examDate);
							scheduleDateList.add(entry);
							scheduleDate.put(examDate, examDate);
						}
					}
				}
				
				if(scheduleDateList.size()==1){
					objStatus.add(Status.LIST);
				}
				else{
					WebUTIL.setAttributeInSession(request,MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST ,scheduleDateList);
					objStatus.add(Status.NEW);
				}
				
				WebUTIL.setAttributeInSession(request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,requisitionVOList);
				WebUTIL.setAttributeInSession(request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_OLD ,requisitionVOList);
			}
			
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.NEW,e.getMessage(),"");	
				fb.setOrgID("-1");
				fb.setOtherOrgName("");
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
				WebUTIL.setStatus(request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		/**
		 * Get the list of the requisition schedule by a particular date
		 * @param fb
		 * @param request
		 */
		public static void getMbRequisitionsByDate(MedicalBoardRequisitionChangeFB fb, HttpServletRequest request) {
			
			Status objStatus=new Status();
			List<MedicalBoardRequisitionVO> requisitionVOOldList=null;
			List<MedicalBoardRequisitionVO> requisitionVOList=new ArrayList<MedicalBoardRequisitionVO>();
			
			try{
//				UserVO userVO=getUserVO(request);
				String scheduleDate=fb.getScheduleDate();
				
				requisitionVOOldList=(List)request.getSession().getAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST_OLD);
				if(requisitionVOOldList!=null){
					for(int i=0;i<requisitionVOOldList.size();i++){
						if(scheduleDate.equals(requisitionVOOldList.get(i).getExamDate())){
							requisitionVOList.add(requisitionVOOldList.get(i));
						}
					}
				}
				String examDate[]=null;
				if(requisitionVOList!=null){
					examDate=new String[requisitionVOList.size()];
					for(int i=0;i<requisitionVOList.size();i++){
						examDate[i]="";
					}
				}
				fb.setExamDate(examDate);
				if(requisitionVOList.size()==0){
					throw new HisRecordNotFoundException("No Schedule Found for this date");
				}
				
				WebUTIL.setAttributeInSession(request,MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST ,requisitionVOList);
				objStatus.add(Status.LIST);
				
			}
			
			catch(HisRecordNotFoundException e){
				objStatus.add(Status.NEW,e.getMessage(),"");	
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
				WebUTIL.setStatus(request,objStatus);
				System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		
}
