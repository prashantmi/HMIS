package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.UserVO;

public class CertificateArchivalDATA extends ControllerDATA
{
	public static Map getEssentialForRecordArchived(String recordType,String mrdCode,UserVO userVO,String admNo)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getEssentialForRecordArchived(recordType,mrdCode,userVO,admNo);
	}
	//added by swati sagr on date:14-may-2019
	
	public static Map getRackDtlForCrAdmNo(String recordType,String mrdCode,UserVO userVO,String admNo)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getRackDtlForCrAdmNo(recordType,mrdCode,userVO,admNo);
	}
	
	//added by swati sagar
	//date:13-may-2019
	public static Map getEssentialForRecordArchived_AdmNo(String recordType,String mrdCode,UserVO userVO,String admNo)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getEssentialForRecordArchived_AdmNo(recordType,mrdCode,userVO,admNo);
	}
	
	
	//added by swati sagar
		//date:13-may-2019
		public static Map getEssentialForRecordArchived_CrNo(String recordType,String mrdCode,UserVO userVO,String admNo,String crno)
		{
			MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
			return mrdEssDelegate.getEssentialForRecordArchived_CrNo(recordType,mrdCode,userVO,admNo,crno);
		}
	
	public static List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getShelfBasedOnRack(recordType,rackId,userVO);
	}
	
	public static void saveRecordArchivalInMrd(List<MrdRecordDtlVO> lstMrdRecordVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveRecordArchivalInMrd(lstMrdRecordVO,userVO);
	}
	
}
