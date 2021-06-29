package hisglobal.utility.patientAuditLog;

import hisglobal.business.GlobalEssentialDelegate;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.vo.PatientAuditLogMstVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import registration.controller.fb.CRNoFB;
import registration.controller.util.PatDetailUTIL;

public class PatientAuditLogMstUTL  extends ControllerUTIL{
	/**
	 * get the list of the module
	 * @param _request -HttpServletRequest
	 */
		public static void getPatientAuditLogEssentials(PatientAuditLogMstFB _fb,HttpServletRequest _request){	
		
			Status objStatus=new Status();
			//Map mp=new HashMap();
			List <PatientAuditLogMstVO> patientAuditLogVOList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			List auditLogIdList=new ArrayList();
			try{
				UserVO userVO=getUserVO(_request);
				//setSysdate(_request);
				CRNoFB crNoFB=new CRNoFB();
				crNoFB.setPatCrNo(_fb.getPatCrNo());
				PatDetailUTIL.getPatientDtlByCrno(crNoFB, _request);
				patientAuditLogVOList=globalDelegate.getPatientAuditLogEssentials(userVO);
				Entry entry=null;
				/*if(patientAuditLogVOList!=null){
					for(int i=0;i<patientAuditLogVOList.size();i++){
						entry=new Entry();
						entry.setLabel(patientAuditLogVOList.get(i).getAuditHeader());
						entry.setValue(patientAuditLogVOList.get(i).getAuditLogId());
						auditLogIdList.add(entry);
					}
				}*/
				
				Set moduleIdSet=new HashSet();
				if(patientAuditLogVOList!=null){
					for(int i=0;i<patientAuditLogVOList.size();i++){
						entry=new Entry();
						moduleIdSet.add(patientAuditLogVOList.get(i).getModuleId()+"#"+patientAuditLogVOList.get(i).getModuleName());
						//entry.setLabel(patientAuditLogVOList.get(i).getModuleId());
						//entry.setValue(patientAuditLogVOList.get(i).getModuleName());
						//auditLogIdList.add(entry);
					}
					Iterator itr=moduleIdSet.iterator();
					while(itr.hasNext()){
						entry=new Entry();
						String value=itr.next().toString().replace("[","");
						value=value.replace("]","");
						entry.setValue(value.split("#")[0]);
						entry.setLabel(value.split("#")[1]);
						auditLogIdList.add(entry);
					}
				}
				PatientVO patVO=(PatientVO)_request.getSession().getAttribute(RegistrationConfig.PATIENT_VO);
				_fb.setRegisterDate(patVO.getRegisterDate());
				_request.getSession().setAttribute(Config.PATIENT_AUDIT_LOG_MST_VO_LIST ,patientAuditLogVOList);
				//WebUTIL.setAttributeInSession(_request,Config.PATIENT_AUDIT_LOG_OPTIONS ,auditLogIdList);
				WebUTIL.setAttributeInSession(_request,Config.MASTER_VERIFICATION_MODULE_LIST ,auditLogIdList);
		        objStatus.add(Status.TRANSINPROCESS);
			
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
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
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
			    //System.out.println("objStatus in finally"+objStatus);		 
			    System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		//get the list of the audit header based on module Id
		
		public static void getAuditLogHeader(PatientAuditLogMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			//Map mp=new HashMap();
			List <PatientAuditLogMstVO> patientAuditLogVOList=null;
			List <Entry> auditLogHeaderList=null;
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			List auditLogIdList=new ArrayList();
			try{
				UserVO userVO=getUserVO(_request);
				//setSysdate(_request);
				List auditHeaderList=(List)_request.getSession().getAttribute(Config.AUDIT_HEADER_LIST);
				if(auditHeaderList==null) auditHeaderList=new ArrayList();
				patientAuditLogVOList=globalDelegate.getPatientAuditLogEssentials(userVO);
				Entry entry=null;
				if(patientAuditLogVOList!=null){
					for(int i=0;i<patientAuditLogVOList.size();i++){
						entry=new Entry();
						if(patientAuditLogVOList.get(i).getModuleId().equals(_fb.getModuleId())){
							entry.setLabel(patientAuditLogVOList.get(i).getAuditHeader());
							entry.setValue(patientAuditLogVOList.get(i).getAuditLogId());
							auditLogIdList.add(entry);
						}	
					}
				}
				
				/*for(int i=0;i<auditLogIdList.size();i++){
					entry=new Entry();
					entry=(Entry)auditLogIdList.get(i);
					for(int j=0;j<auditHeaderList.size();j++){
						PatientAuditLogMstVO patientAuditLogVO=(PatientAuditLogMstVO)auditHeaderList.get(j);
						if(patientAuditLogVO.getModuleId().equals(_fb.getModuleId().trim())
									&& patientAuditLogVO.getAuditLogId().equals(entry.getValue().trim())){
							auditLogIdList.remove(i);
							//break;
						}
					}	
				}*/
				for(int j=0;j<auditHeaderList.size();j++){
					PatientAuditLogMstVO patientAuditLogVO=(PatientAuditLogMstVO)auditHeaderList.get(j);
					if(patientAuditLogVO.getModuleId().equals(_fb.getModuleId().trim())){
						auditLogIdList=(List)WebUTIL.removeEntriesfromOptions(auditLogIdList, patientAuditLogVO.getAuditLogId());
					}
				}	
				
				_fb.setAuditLogId("-1");
				//_request.getSession().setAttribute(Config.PATIENT_AUDIT_LOG_MST_VO_LIST ,patientAuditLogVOList);
				WebUTIL.setAttributeInSession(_request,Config.PATIENT_AUDIT_LOG_OPTIONS ,auditLogIdList);
		        objStatus.add(Status.TRANSINPROCESS);
			
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
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
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
			    //System.out.println("objStatus in finally"+objStatus);		 
			    System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		//add the selected moduleId and auditheader to list
		public static void addAuditLogHeader(PatientAuditLogMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			List auditHeaderList=null;
			HttpSession session=_request.getSession();
			try{
				
				addHeaderToList(_fb,_request);
				/*List <PatientAuditLogMstVO> patientAuditLogVOList=(List)session.getAttribute(Config.PATIENT_AUDIT_LOG_MST_VO_LIST); 
				List moduleIdList=(List)session.getAttribute(Config.MASTER_VERIFICATION_MODULE_LIST);
				List auditLogIdList=(List)session.getAttribute(Config.PATIENT_AUDIT_LOG_OPTIONS);
				auditHeaderList=(List)session.getAttribute(Config.AUDIT_HEADER_LIST);
				if(auditHeaderList==null){
					auditHeaderList=new ArrayList();
				}
				Iterator itr=patientAuditLogVOList.listIterator();
				while (itr.hasNext()) {
					PatientAuditLogMstVO patientAuditLogVO = (PatientAuditLogMstVO) itr.next();
					if(patientAuditLogVO.getModuleId().equals(_fb.getModuleId())
							 && patientAuditLogVO.getAuditLogId().equals(_fb.getAuditLogId())){
						auditHeaderList.add(patientAuditLogVO);
						break;
					}
					
				}
				
				WebUTIL.setAttributeInSession(_request,Config.AUDIT_HEADER_LIST ,auditHeaderList);*/
				_fb.setAuditLogId("-1");
				_fb.setModuleId("-1");
				session.removeAttribute(Config.PATIENT_AUDIT_LOG_OPTIONS);
				objStatus.add(Status.TRANSINPROCESS);
				
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				//System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		private static void addHeaderToList(PatientAuditLogMstFB _fb,HttpServletRequest _request) {
			List auditHeaderList=null;
			HttpSession session=_request.getSession();
			try{
				List <PatientAuditLogMstVO> patientAuditLogVOList=(List)session.getAttribute(Config.PATIENT_AUDIT_LOG_MST_VO_LIST); 
				//List moduleIdList=(List)session.getAttribute(Config.MASTER_VERIFICATION_MODULE_LIST);
				//List auditLogIdList=(List)session.getAttribute(Config.PATIENT_AUDIT_LOG_OPTIONS);
				auditHeaderList=(List)session.getAttribute(Config.AUDIT_HEADER_LIST);
				if(auditHeaderList==null){
					auditHeaderList=new ArrayList();
				}
				Iterator itr=patientAuditLogVOList.listIterator();
				while (itr.hasNext()) {
					PatientAuditLogMstVO patientAuditLogVO = (PatientAuditLogMstVO) itr.next();
					if(patientAuditLogVO.getModuleId().equals(_fb.getModuleId())
							 && patientAuditLogVO.getAuditLogId().equals(_fb.getAuditLogId())){
						auditHeaderList.add(patientAuditLogVO);
						break;
					}
					
				}
				_fb.setAuditHeaderSize(String.valueOf(auditHeaderList.size()));
				WebUTIL.setAttributeInSession(_request,Config.AUDIT_HEADER_LIST ,auditHeaderList);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}

		//remove the selected moduleId and auditheader from the list
		public static void removeAuditLogHeader(PatientAuditLogMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			List auditHeaderList=null;
			HttpSession session=_request.getSession();
			try{
				
				auditHeaderList=(List)session.getAttribute(Config.AUDIT_HEADER_LIST);
				String patientAuditLodId=_fb.getAuditLogIdToRemove();
				if(auditHeaderList!=null){
					Iterator itr=auditHeaderList.listIterator();
					while (itr.hasNext()) {
						PatientAuditLogMstVO patientAuditLogVO = (PatientAuditLogMstVO) itr.next();
						if((patientAuditLogVO.getModuleId()+"#"+patientAuditLogVO.getAuditLogId()).equals(patientAuditLodId)){
							auditHeaderList.remove(patientAuditLogVO);
							break;
						}
					}
				}
				WebUTIL.setAttributeInSession(_request,Config.AUDIT_HEADER_LIST ,auditHeaderList);
				_fb.setAuditLogId("-1");
				_fb.setModuleId("-1");
				_fb.setAuditHeaderSize(String.valueOf(auditHeaderList.size()));
				objStatus.add(Status.TRANSINPROCESS);
				
			}
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				//System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
		private static String getLabelByValue(String value, List list) {
			String label="";
			Iterator itr=list.iterator();
			while (itr.hasNext()) {
				Entry entry = (Entry) itr.next();
				if(entry.getValue().equals(value)){
					label=entry.getLabel();
					break;
				}
			}
			return label;
		}

		//get the data of the selected master based on the criteria given and order by selected
		public static void getData(PatientAuditLogMstFB _fb,HttpServletRequest _request){	
			
			Status objStatus=new Status();
			PatientAuditLogMstVO patientAuditLogVO=new PatientAuditLogMstVO();
			GlobalEssentialDelegate globalDelegate=new GlobalEssentialDelegate(); 
			//List dataList=null;
			//Map dataMap=null;
			List <PatientAuditLogMstVO> oldPatientAuditLogVOList=new ArrayList<PatientAuditLogMstVO>();
			List <PatientAuditLogMstVO> patientAuditLogVOList=new ArrayList<PatientAuditLogMstVO>();
			Map map=new HashMap();
			try{
				
				UserVO userVO=getUserVO(_request);
				String patCrNo=_fb.getPatCrNo();
				ControllerUTIL.getHospitalVO(_request);
				oldPatientAuditLogVOList=(List)_request.getSession().getAttribute(Config.PATIENT_AUDIT_LOG_MST_VO_LIST);
				if(!_fb.getModuleId().equals("-1") && !_fb.getAuditLogId().equals("-1")){
					addHeaderToList(_fb, _request);
				}
				List <PatientAuditLogMstVO>auditHeaderList=(List)_request.getSession().getAttribute(Config.AUDIT_HEADER_LIST);
				
				if(oldPatientAuditLogVOList!=null && auditHeaderList!=null){
					//Iterator itr=null;
					for(int i=0;i<oldPatientAuditLogVOList.size();i++){
						for(int j=0;j<auditHeaderList.size();j++){
							//String auditHeader=auditHeaderList.get(j).getModuleId()+"#"+auditHeaderList.get(j).getAuditLogId();
							patientAuditLogVO=new PatientAuditLogMstVO();
							patientAuditLogVO=oldPatientAuditLogVOList.get(i);
							String moduleIdAuditLogId=patientAuditLogVO.getModuleId()+"#"+patientAuditLogVO.getAuditLogId();
							if(moduleIdAuditLogId.equals(auditHeaderList.get(j).getModuleId()+"#"+auditHeaderList.get(j).getAuditLogId())){
								patientAuditLogVO.setFromDate(_fb.getFromDate());
								patientAuditLogVO.setToDate(_fb.getToDate());
								patientAuditLogVO.setIsDateWise(_fb.getIsDateWise());
								if(patientAuditLogVO.getDisplayLogic()==null) 
									patientAuditLogVO.setDisplayLogic("");
								patientAuditLogVOList.add(patientAuditLogVO);
							}
						}
					}
				}
				//_fb.setAuditHeader(patientAuditLogVO.getAuditHeader());
				/*String mainQuery=patientAuditLogVO.getCurrentRecordQuery();
				String auditLogQuery=patientAuditLogVO.getAuditLogQuery();
				if(_fb.getIsDateWise().equals("1")){
					auditLogQuery=patientAuditLogVO.getAuditLogDateQuery();
				}*/
				//_fb.setDisplayLogic(patientAuditLogVO.getDisplayLogic());
				map=globalDelegate.getPatientAuditLogDataList(patientAuditLogVOList,patCrNo,userVO);
				/*List list=(List)map.get(Config.AUDIT_LOG_ROW_LIST);
				if(list!=null && patientAuditLogVO.getDisplayLogic().equals(Config.AUDIT_LOG_DISPLAY_LOGIC_NEEDED)){
					List columnList=null; 
					for(int i=0;i<list.size();i++){
						columnList=(List)list.get(i);
					}
					
				}*/
				WebUTIL.setAttributeInSession(_request,Config.PATIENT_AUDIT_LOG_MST_VO_LIST ,patientAuditLogVOList);
				WebUTIL.setMapInSession(map,_request);
				//objStatus.add(Status.NEW);
				objStatus.add(Status.LIST);
				
			}
			catch(HisRecordNotFoundException e){
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.NEW,"",e.getMessage());
				WebUTIL.setMapInSession(map,_request);
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
			catch(Exception e){
				System.out.println("Inside Exception");
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			finally
			{
				WebUTIL.setStatus(_request,objStatus);
				//System.out.println("objStatus in finally"+objStatus);		 
				System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}


		public static void convertToPDF(PatientAuditLogMstFB fb,
				HttpServletRequest request,HttpServletResponse response ) {
			String htmlContent=fb.getHtmlContent();
			//Status objStatus=new Status();
			ByteArrayOutputStream baosPDF=null;
			ServletOutputStream os=null;
			try{
				
				//baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request, c);
				baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request, htmlContent);
				response.setContentType("application/pdf");
				os=response.getOutputStream();
				os.flush();
				baosPDF.writeTo(os);
				//byte array[]=baosPDF.toByteArray();
				
				//objStatus.add(Status.DONE);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try{
					os.close();
					baosPDF.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				//WebUTIL.setStatus(request,objStatus);
				//System.out.println("objStatus in finally"+objStatus);		 
				//System.out.println("objStatus list"+objStatus.getStatusList());
			}	
		}
		
		
}//end class
