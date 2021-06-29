package inpatient.transaction.controller.utl;

import java.util.Enumeration;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisPrinterSupport;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InPatientPrintLabel;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.InPatientPrintLabelFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

public class InPatientPrintLabelUTL extends ControllerUTIL
{
	
	public static void setSelectedPatientDetail(InPatientPrintLabelFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{
			/*PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}*/

			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(selectedPatientVO == null || !selectedPatientVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						selectedPatientVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			fb.setPrintLabelType("0");
			/*if(selectedPatientVO==null){
				throw new HisRecordNotFoundException("No Detail Found");
			}*/
			WebUTIL.setAttributeInSession(request, DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO, selectedPatientVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");		
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}
	
	
	/**
	 * prepare the label that has to be printed
	 * @param patientDtlVO
	 * @param _request
	 * @return
	 */
	 
	public static void prepareLabel(InPatientPrintLabelFB fb,HttpServletRequest _request){		
		
		Status objStatus=new Status();
		HttpSession session=_request.getSession();
		InPatientPrintLabel printLabel=new InPatientPrintLabel();
		UserVO userVO=getUserVO(_request);
		try{
			if(fb.getPrintLabelType().equals("0")){
				//PatientDetailVO patientDtlVO=(PatientDetailVO)session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				PatientDetailVO patientDtlVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
				HelperMethods.populate(printLabel,patientDtlVO);
				printLabel.setPatMiddleName(printLabel.getPatMiddleName()==null?"":printLabel.getPatMiddleName());
				printLabel.setPatLastName(printLabel.getPatLastName()==null?"":printLabel.getPatLastName());
				printLabel.setHostName(_request.getRemoteAddr());
				int noOfLabel=Integer.parseInt(fb.getNoOfLabel());
				String tmpFileName=InpatientConfig.INPATIENT_PRINT_LABEL_FILE_NAME+userVO.getSeatId();
				printInPatientLabel(printLabel, tmpFileName, noOfLabel,_request);
				objStatus.add(Status.TRANSINPROCESS,"","Label Printed Successfully");
			}
			else if(fb.getPrintLabelType().equals("1")){
				objStatus.add(Status.TRANSINPROCESS,"","Patient Tag  is not available");
				
			}
			else{
				objStatus.add(Status.TRANSINPROCESS,"","Print CaseSheet is not available");
			}
			fb.setPrintLabelType("0");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
			
	}	
	
	
	public static void printInPatientLabel(InPatientPrintLabel printLabel, String tmpFileName,int noOfLabel,HttpServletRequest _request)
	{
		try{
			StringBuffer fileContents	=	new StringBuffer();
						
			fileContents.append("\n\n\n\t\t  "+RegistrationConfig.HOSPITAL_NAME +"\n");
			fileContents.append("\n\n");				
			fileContents.append("Ward Name   : "+printLabel.getWardName()+"\t\t Admission Date   : "+printLabel.getAdmDateTime());
			fileContents.append("\n");
			fileContents.append("CR.No.      : "+printLabel.getPatCrNo()+	"\t Admission No.    :"+printLabel.getPatAdmNo());
			fileContents.append("\n");
			fileContents.append("Patient Name: "+printLabel.getPatFirstName()+" "+printLabel.getPatMiddleName()+" "+printLabel.getPatLastName()+ "\t Age/Sex          : "+printLabel.getPatAge()+"/"+printLabel.getPatGender());
			fileContents.append("\n");
			fileContents.append("Unit    	: "+printLabel.getDepartmentUnitName()+"  \t\t  ");
			fileContents.append("\n");
						
			System.out.println("befor entering for printing");
			for(int k=0;k<noOfLabel;k++)
			{
				HisPrinterSupport.createTempFile(fileContents.toString(), tmpFileName);				
				System.out.println("print file number sliop");
				HisPrinterSupport.printSlip(printLabel.getHostName(),tmpFileName+".dat",getClientSystemOsType(_request));
			}
			System.out.println("printSlip() Exited");				
		}
		catch(Exception e){
			System.out.println("Exception in printInPatientLabel "+e);	
			e.printStackTrace();
		}	
	}

	public static String getClientSystemOsType(HttpServletRequest request)
	{
		String strOsType = "";
		String strAgentDtls = "";
		Enumeration<?> names = request.getHeaderNames();
		while (names.hasMoreElements())
		{
			String elem = (String) names.nextElement();
			if (elem.contains("agent"))
			{
				strAgentDtls = request.getHeader(elem);
				break;
			}
		}
		if (!strAgentDtls.equals(""))
		{
			if (strAgentDtls.contains("windows") || strAgentDtls.contains("Windows") || strAgentDtls.contains("Win") || strAgentDtls.contains("win"))
				strOsType = "Windows";
			else if (strAgentDtls.contains("linux") || strAgentDtls.contains("Linux"))
				strOsType = "Linux";
			else
				strOsType = "Other";
		}
		return strOsType;
	}
}
