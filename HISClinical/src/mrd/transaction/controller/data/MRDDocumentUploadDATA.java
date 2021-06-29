package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import mrd.vo.MRDDocumentUploadVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;

import java.util.*;

import registration.bo.delegate.PatientDelegate;
import vo.registration.PatientVO;
import mrd.transaction.delegate.*;


public class MRDDocumentUploadDATA extends ControllerDATA {
	public static List<MRDDocumentUploadVO>  getDocumentArchivalEssentials(String patCrNo,String episodeCode,UserVO userVO){
		MrdEssentialDelegate mrdEssentialDelegate=new MrdEssentialDelegate();
		return (mrdEssentialDelegate.getMRDDocumentEssentials(patCrNo,episodeCode,userVO));
		
	}
	
	public static void saveDocument(MRDDocumentUploadVO[] docUploadVos,MRDDocumentUploadVO[] documentUploadRevokeDtlVO,UserVO userVO){		
		MrdDelegate mrdPatientDelegate=new MrdDelegate();
		mrdPatientDelegate.savePatientMRDDocumentdetails(docUploadVos,documentUploadRevokeDtlVO, userVO);
	}
	
	//Added by Dheeraj
	public static byte[] fetchImageFromPostgres(MRDDocumentUploadVO mrdDocumentUploadVO) {
		MrdDelegate mrdPatientDelegate=new MrdDelegate();
		byte[] getdoc = mrdPatientDelegate.fetchImageFromPostgres(mrdDocumentUploadVO);
		return getdoc;
	}
	
	
}
