package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;

import java.util.*;

import opd.bo.delegate.OpdEssentialDelegate;
import registration.bo.delegate.PatientDelegate;
import vo.registration.PatientVO;

public class OpdDocumentArchivalDATA extends ControllerDATA {
	public static Map getDocumentArchivalEssentials(String patCrNo,String episodeCode,UserVO userVO){
		OpdEssentialDelegate opdEssentialDelegate=new OpdEssentialDelegate();
		return (opdEssentialDelegate.getDocumentArchivalEssentials(patCrNo,episodeCode,userVO));
		
	}
	
	public static void saveDocument(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO,UserVO userVO){		
		PatientDelegate opdPatientDelegate=new PatientDelegate();
		opdPatientDelegate.savePatientDocumentdetails(docUploadVos,documentUploadRevokeDtlVO, userVO);
	}
	
	//Added by Vasu
	public static void savePatientDocument(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO,PatientVO patientVO,UserVO userVO){		
		PatientDelegate opdPatientDelegate=new PatientDelegate();
		opdPatientDelegate.savePatientUploadedDocumentdetails(docUploadVos,documentUploadRevokeDtlVO,patientVO, userVO);
	}

	public static byte[] fetchImageFromPostgres(PatientImageDtlVO patientImageDtlVO) {
		PatientDelegate opdPatientDelegate=new PatientDelegate();
		byte[] getdoc = opdPatientDelegate.fetchImageFromPostgres(patientImageDtlVO);
		return getdoc;
	}
	
}
