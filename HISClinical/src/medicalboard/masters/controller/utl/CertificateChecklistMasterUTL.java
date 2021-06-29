package medicalboard.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.MBCertificateChecklistVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.data.CertificateChecklistMasterDATA;
import medicalboard.masters.controller.fb.CertificateChecklistMasterFB;
/**
 * <pre>Contains the presentation logic</pre>
 */
public class CertificateChecklistMasterUTL extends ControllerUTIL
{
	public static void setEssentials(CertificateChecklistMasterFB _fb, HttpServletRequest _request)
	 {
		Status objStatus = new Status();
		Map essentialMap=new HashMap();	
		try
		{
			UserVO userVO = getUserVO(_request);
			String certificateTypeID="";
			certificateTypeID=_fb.getControls()[0];
			if(certificateTypeID==null){
				certificateTypeID=_fb.getCertificateTypeID();
			}
			essentialMap=CertificateChecklistMasterDATA.getEssentialsForCertificateChecklist(certificateTypeID,userVO);
			_fb.setCertificateTypeID(certificateTypeID);
			_fb.setCertificateType((String)essentialMap.get(MedicalBoardConfig.CERTIFICATE_TYPE_NAME));
			WebUTIL.setMapInSession(essentialMap, _request);
			if(essentialMap.get(MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE)==null){
				throw new HisRecordNotFoundException("No Checklist found to add");
			}
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.NEW, e.getMessage(), "");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
	
	
	public static void addChecklist(CertificateChecklistMasterFB _fb, HttpServletRequest _request){
		Status objStatus = new Status();
		List certificateChecklist=null;
		List checklist=null;
		try
		{
//			UserVO userVO = getUserVO(_request);
//			String certificateTypeID=_fb.getControls()[0];
			certificateChecklist=(List)_request.getSession().getAttribute(MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST);
			checklist=(List)_request.getSession().getAttribute(MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE);
			List isCompulsoryOptionList=(List)_request.getSession().getAttribute(MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST);
			
			if(certificateChecklist==null){
				certificateChecklist=new ArrayList();
			}
			MBCertificateChecklistVO checklistVO=new MBCertificateChecklistVO();
			checklistVO.setCertificateTypeID(_fb.getCertificateTypeID());
			checklistVO.setChecklistID(_fb.getChecklistID().split("@")[0]);
			checklistVO.setIsCompulsory(_fb.getIsCompulsory());
			checklistVO.setIsCompulsoryFromChecklist(_fb.getChecklistID().split("@")[1]);
			checklistVO.setChecklist(getNameById(checklist, _fb.getChecklistID()));
			checklistVO.setIsCompulsoryLabel(getNameById(isCompulsoryOptionList, _fb.getIsCompulsory()));
			
			certificateChecklist.add(checklistVO);
			if(checklist!=null)
				removeEntryFromOption(checklist, _fb.getChecklistID());
			int noOfCheckListAdded=_fb.getNoOfChecklistAdded()+1;
			_fb.setNoOfChecklistAdded(noOfCheckListAdded);			
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST, certificateChecklist);
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE, checklist);
			objStatus.add(Status.NEW);
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	public static void removeChecklist(CertificateChecklistMasterFB _fb, HttpServletRequest _request){
		Status objStatus = new Status();
		List <MBCertificateChecklistVO> certificateChecklist=null;
		List checklist=null;
		MBCertificateChecklistVO checklistVO=null;
		try
		{
//			UserVO userVO = getUserVO(_request);
			certificateChecklist=(List)_request.getSession().getAttribute(MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST);
			checklist=(List)_request.getSession().getAttribute(MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE);
					
			if(certificateChecklist!=null){
				for(int i=0;i<certificateChecklist.size();i++){
					checklistVO=certificateChecklist.get(i);
					if(checklistVO.getChecklistID().equals(_fb.getChecklistToRemove())){
						certificateChecklist.remove(i);
						break;
					}
				}
				
			}
			
			if(checklist!=null){
				Entry entry=new Entry();
				entry.setValue(checklistVO.getChecklistID()+"@"+checklistVO.getIsCompulsoryFromChecklist());
				entry.setLabel(checklistVO.getChecklist());
				addEntryToOption(checklist,entry);
			}
			int noOfCheckListAdded=_fb.getNoOfChecklistAdded()-1;
			_fb.setNoOfChecklistAdded(noOfCheckListAdded);
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST, certificateChecklist);
			WebUTIL.setAttributeInSession(_request, MedicalBoardConfig.CHECKLIST_NOT_ADDED_TO_CERTIFICATE_TYPE, checklist);
			objStatus.add(Status.NEW);
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	
	public static void removeEntryFromOption(List list, String value){
		Entry entry=null;
		for(int i=0;i<list.size();i++){
			entry=(Entry)list.get(i);
			if(entry.getValue().equals(value)){
				list.remove(i);
				break;
			}
		}
	}
	
	public static void addEntryToOption(List list, Entry entry){
		list.add(entry);
	}
	
	
	public static String getNameById(List list, String value){
		Entry entry=null;
		String name="";
		for(int i=0;i<list.size();i++){
			entry=(Entry)list.get(i);
			if(entry.getValue().equals(value)){
				name=entry.getLabel();
				break;
			}
		}
		return name;
	}

	
	
	public static void saveCerificateChecklistMst(CertificateChecklistMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List <MBCertificateChecklistVO> certificateChecklist=null;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			certificateChecklist=(List)_request.getSession().getAttribute(MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST);
			
			if(certificateChecklist!=null){
				_fb.setCertificateTypeID(certificateChecklist.get(0).getCertificateTypeID());
				CertificateChecklistMasterDATA.saveCerificateChecklistMst(certificateChecklist,userVO);
			}	
			objStatus.add(Status.DONE,"","Record Saved Successfully");
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	
	
	public static void modifySaveCertificateChecklist(CertificateChecklistMasterFB _fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		MBCertificateChecklistVO checklistVO=new MBCertificateChecklistVO();
		List <MBCertificateChecklistVO> certificateChecklist=null;
		List <MBCertificateChecklistVO> certificateChecklistUpdateVOList=null;
		List <MBCertificateChecklistVO> certificateChecklistOld=null;
		try
		{
			UserVO userVO = getUserVO(_request);
			certificateChecklist=(List)_request.getSession().getAttribute(MedicalBoardConfig.CERTIFICATE_CHECKLIST_VO_LIST);
			certificateChecklistOld=(List)_request.getSession().getAttribute(MedicalBoardConfig.CHECKLIST_ADDED_TO_CERTIFICATE_TYPE);
			
			if(_fb.getSelectedChecklistID()!=null){
				certificateChecklistUpdateVOList=new ArrayList<MBCertificateChecklistVO>();
				for(int i=0,k=0;i<certificateChecklistOld.size();i++){
					if(k<_fb.getSelectedChecklistID().length && i==Integer.parseInt(_fb.getSelectedChecklistID()[k])){
						checklistVO=certificateChecklistOld.get(Integer.parseInt(_fb.getSelectedChecklistID()[k]));
						checklistVO.setIsCompulsory(_fb.getExistingIsCompulsory()[k]);
						certificateChecklistUpdateVOList.add(checklistVO);
						k++;
					}	
				}
			}
				
			CertificateChecklistMasterDATA.modifySaveCertificateChecklist(certificateChecklist,certificateChecklistUpdateVOList,userVO);
			
			objStatus.add(Status.DONE);
		}
		catch (HisDuplicateRecordException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.DONE, "",e.getMessage());
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	


}
