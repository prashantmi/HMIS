package ipd.transactions;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientLeaveJoinRecordTransDATA
{
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String admitted = hisProp.getString("Admitted");
	public static String onLeave = hisProp.getString("On_Leave");
	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
  public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
    
  public static  String now(String frmt)
    {
      HisUtil util=null;
      String a="";
      util=new HisUtil("transaction","PatientLeaveJoinRecordTransHLP");
      try{
       a= util.getASDate(frmt);
      }
      catch(Exception e){
    	
      }
      /*Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
      return sdf.format(cal.getTime());*/
      return a;
    }

	///////////////////////////AJAX RESPONSE//////////////////////////////////////
	public static String AjaxResponse(PatientLeaveJoinRecordTransFB formBean,String strValmode,HttpServletRequest request)
	{
	    PatientLeaveJoinRecordTransVO vo = new PatientLeaveJoinRecordTransVO();
		String movDetails = null;
		try
		{
			vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);
			String tmp=request.getParameter("currDtl");
			String currStat[]=tmp.replace('^','#').split("#");
			vo.setStrdeptproperty(formBean.getStrdeptproperty());
		    vo.setStrUnitName("<option>"+currStat[1]+"</option>");
		    vo.setStrWard("<option>"+currStat[2]+"</option>");
		    vo.setStrRoom("<option>"+currStat[3]+"</option>");
		    vo.setStrBed("<option>"+currStat[4]+"</option>");
		    vo.setStrwardType(formBean.getStrwardType());
		    vo.setStrRoomType(formBean.getStrRoomType());
		    vo.setStrBedType(formBean.getStrBedType());
		    vo.setStrServArea(formBean.getStrServArea());
		    vo.setStrIsBedVacant(formBean.getStrIsBedVacant());	
	      movDetails =  PatientLeaveJoinRecordTransHLP.jd(vo);
	      formBean.setStrErrMsgString(vo.getStrErrMsgString());
	      formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
	    } catch (Exception e) {
	    	  String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->AjaxResponse()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {
			formBean.setStrCtDate(vo.getStrCtDate());
		}
     return movDetails;
	}

	public static void getRsnRmk(PatientLeaveJoinRecordTransFB formBean)
	{
		PatientLeaveJoinRecordTransVO vo = new PatientLeaveJoinRecordTransVO();
		PatientLeaveJoinRecordTransBO bo= new  PatientLeaveJoinRecordTransBO();
		try{
		vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);	
		bo.getRsnRmk(vo);
		
  	    formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
		formBean.setStrDisBy(vo.getStrDisBy());
		formBean.setStrDisRsn(vo.getStrDisRsn());
		formBean.setStrRmk(vo.getStrRmk());
		formBean.setStrRsn(vo.getStrRsn());
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		  formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
		}
		catch(Exception e)
		{
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->getRsnRmk()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally{
		bo=null;
		vo=null;}
	}
	///////////////////////////////////////////////////////////////////////////////

	public static void initClientDetail(String strChk,PatientLeaveJoinRecordTransFB formBean)
	{
		PatientLeaveJoinRecordTransVO vo = new PatientLeaveJoinRecordTransVO();
	    vo.setStrChk(strChk);
		HisUtil util = null;
		try
		{
		   formBean.setStrCrNo(strChk);
		   formBean.setStrMsgType(vo.getStrMsgType());
		   formBean.setStrErrMsgString(vo.getStrErrMsgString());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
 		}
		}
		catch (Exception e) {
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->initClientDetail()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {

			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (util != null)
				util = null;
		}
	}

//this function is called for populating ward value on the basis of unit combo
/*
public static void ward(PatientLeaveJoinRecordTransFB beanObj,HttpServletRequest request, HttpServletResponse response){
	PatientLeaveJoinRecordTransVO VO = null;
	String modWardTpe= request.getParameter("modWardTpe");
	String temp[]=modWardTpe.replace('^', '#').split("#");
	System.out.println("modUnit in ward=="+modWardTpe);
	//VO.setStrHospitalCode(beanObj.getStrHospitalCode());
	try {
		//we do not need to call populate() function.
		//that is why we create an instance and pass it to the BO
		VO = new PatientLeaveJoinRecordTransVO();
		VO = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",beanObj);
		PatientLeaveJoinRecordTransBO bo = new PatientLeaveJoinRecordTransBO();
		//System.out.println("ward codein cnt=="+VO.getStrprivatewardName());
		VO.setStrDeptCode(temp[0]);
	    VO.setStrDeptUnitCode(temp[1]);
	    VO.setStrWardTypeCode(temp[2]);
		bo.ward(VO);
		beanObj.setStrErrMsgString(VO.getStrErrMsgString());
		beanObj.setStrMsgType(VO.getStrMsgType());
			if(beanObj.getStrMsgType().equals("1")) {		//error
			throw new Exception(beanObj.getStrErrMsgString());
		}
		else {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(VO.getStrWard());
			//beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
		}
		}
		catch(Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->ward()", strmsgText);
			   beanObj.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally {
			if(VO != null) VO = null;
			if(beanObj != null) beanObj = null;
		}
	}*/
//this function is called for populating room value on the basis of ward combo

public static void room(PatientLeaveJoinRecordTransFB beanObj,HttpServletRequest request, HttpServletResponse response){

	PatientLeaveJoinRecordTransVO VO = null;
	String modRoomType= request.getParameter("modRoomType");
	String temp[]=modRoomType.replace('^', '#').split("#");
//	System.out.println("modRoomType=="+modRoomType);
	try {
		VO = new PatientLeaveJoinRecordTransVO();
		VO = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",beanObj);
		VO.setStrUnitValue(request.getParameter("deptUnitCode"));
		VO.setStrUnitCode(request.getParameter("ageCode"));
		VO.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
		VO.setStrUnitCode(request.getParameter("ageCode"));
		VO.setStrSex(request.getParameter("sexCode"));
		VO.setStrAge(request.getParameter("age"));
		VO.setStrCrNo(request.getParameter("strCrNo"));
		PatientLeaveJoinRecordTransBO bo = new PatientLeaveJoinRecordTransBO();
	    VO.setStrWardCode(temp[0]);
		VO.setStrRoomTypeCode(temp[1]);
		if(temp.length>2)
		{
			/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
			IpdConfigUtil ipdC=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//if (temp[3].equals("11")) {
			if (temp[3].equals(ipdC.getStrPrivateWardType())) 
		 {
		  VO.setStrMsApprovalFlag(temp[2]);
		  VO.setStrWardTypeCode(temp[3]);
		  VO.setStrCrNo(temp[4]);
		  VO.setStrAdviceAdmNo(temp[5]);
		 }
		}
		bo.room(VO);
		beanObj.setStrRoomCode(VO.getStrRoomCode());
		beanObj.setStrErrMsgString(VO.getStrErrMsgString());
		beanObj.setStrMsgType(VO.getStrMsgType());
		if(beanObj.getStrMsgType().equals("1")) {		//error
			throw new Exception(beanObj.getStrErrMsgString());
		}
		else {
			    response.setHeader("Cache-Control", "no-cache");
		 //   System.out.println("response str->"+"<option selected value="+VO.getStrRoomCode()+">"+VO.getStrRoom()+"</option>");
			    /* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
				IpdConfigUtil ipdC=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			    //if(VO.getStrWardTypeCode().equals("11") && VO.getStrMsApprovalFlag().equals("1"))
				if(VO.getStrWardTypeCode().equals(ipdC.getStrPrivateWardType()) && VO.getStrMsApprovalFlag().equals("1"))
			{
			  if(VO.getStrMsApprovalStatus().equals("2") && !VO.getStrRoomCode().equals(""))
			    response.getWriter().print("<option selected value="+VO.getStrRoomCode()+">"+VO.getStrRoom()+"</option>");
			  else
			  {
				response.getWriter().print("<option selected value=''>N/A</option>");
			  }
			}
			else
			    response.getWriter().print(VO.getStrRoom());		//beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->room()", strmsgText);
			   beanObj.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally {
			if(VO != null) VO = null;
			if(beanObj != null) beanObj = null;
		}
	}

public static void bed(PatientLeaveJoinRecordTransFB beanObj,HttpServletRequest request, HttpServletResponse response){	
	
	PatientLeaveJoinRecordTransVO VO = null;
	String modBedType= request.getParameter("modBedType");
	String temp[]=modBedType.replace('^', '#').split("#");
//	System.out.println("modBedType=="+modBedType);
	//VO.setStrHospitalCode(beanObj.getStrHospitalCode());
	try {		
		//we do not need to call populate() function.
		//that is why we create an instance and pass it to the BO
		VO = new PatientLeaveJoinRecordTransVO();
		VO = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",beanObj);
		PatientLeaveJoinRecordTransBO bo = new PatientLeaveJoinRecordTransBO();
		//System.out.println("ward codein cnt=="+VO.getStrprivatewardName());
	    VO.setStrWardCode(temp[0]);
	    VO.setStrRoomCode(temp[1]);
	    VO.setStrBedTypeCode(temp[2]);
	    VO.setStrDeptUnitCode(temp[3]);
		bo.bed(VO);
		beanObj.setStrMsgType(VO.getStrMsgType());
		if(beanObj.getStrMsgType().equals("1")) {		//error
			throw new Exception(beanObj.getStrErrMsgString());
		}
		else {
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(VO.getStrBed());
			
			//beanObj.setStrProvisionDiagnosis(VO.getStrProvisionDiagnosis());
	
		}
		}
		catch(Exception e) {
			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->bed()", strmsgText);
			   beanObj.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
		finally {
			if(VO != null) VO = null;
			if(beanObj != null) beanObj = null;
		}
	}

public static void beddetail(PatientLeaveJoinRecordTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
	
//	System.out.println("inside DATA-->beddetail");
	
	PatientLeaveJoinRecordTransVO VO=new PatientLeaveJoinRecordTransVO();
	PatientLeaveJoinRecordTransBO BO=new PatientLeaveJoinRecordTransBO();
	try {
	VO = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);	
	String tmp=request.getParameter("modPopUp");
	String temp[]=tmp.replace('^','#').split("#");
	VO.setStrWardCode(temp[0]);
	VO.setStrRoomCode(temp[1]);
	VO.setStrBedTypeCode(temp[2]);
	VO.setStrDeptUnitCode(temp[3]);
	VO.setStrPopUp("1");
	BO.setBedDetails(VO);
	String strbed=PatientLeaveJoinRecordTransHLP.getBedDetails(VO);
	//System.out.println("strbed String in data=="+strbed);
	formBean.setStrBedProperty(strbed);		
		formBean.setStrMsgType(VO.getStrMsgType());
			if(formBean.getStrMsgType().equals("1")) {		//error
			throw new Exception(formBean.getStrErrMsgString());
		}
	}
	catch(Exception e) {
		String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->beddetail()", strmsgText);
		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
	finally {
		if(VO != null) VO = null;
		if(formBean != null) formBean = null;
	}
}

/************INIT All Combos *******************/
public static void initBedStatus(PatientLeaveJoinRecordTransFB formBean,HttpServletRequest request) {
	PatientLeaveJoinRecordTransVO vo=new PatientLeaveJoinRecordTransVO();
	PatientLeaveJoinRecordTransBO bo=new PatientLeaveJoinRecordTransBO();
	try {
		vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		vo.setStrWardCode(request.getParameter("wardCode"));
		vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
		vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
		vo.setStrDeptCode(request.getParameter("deptCode"));
		vo.setStrCrNo(request.getParameter("crNo"));
		bo.setBedStatusDtl(vo);
		formBean.setStrwardType(vo.getStrwardType());
		formBean.setStrRoomType(vo.getStrRoomType());
		formBean.setStrBedType(vo.getStrBedType());
		formBean.setStrServArea(vo.getStrServArea());
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		  formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
	} catch (Exception e) {
		String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->room()", strmsgText);
		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
}

 /*   public static void insert(PatientLeaveJoinRecordTransFB formBean,String strInsert)
    {
    	try{
    	PatientLeaveJoinRecordTransVO vo=new PatientLeaveJoinRecordTransVO();
    	vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);
    	String tmp[]=strInsert.replace('^','#').split("#");
    	vo.setStrCrNo(tmp[0]);
    	vo.setStrDeptCode(tmp[1]);
    	vo.setStrDeptUnitCode(tmp[2]);
    	vo.setStrWardCode(tmp[3]);
    	vo.setStrRoomCode(tmp[4]);
    	vo.setStrBedCode(tmp[5]);
    	vo.setStrRsn(tmp[6]);
    	vo.setStrEntryDate(tmp[7]);
    	vo.setStrOldWardCode(tmp[8]);
    	vo.setStrOldRoomCode(tmp[9]);
    	vo.setStrOldBedCode(tmp[10]);
    	vo.setStrAdviceAdmNo(tmp[11]);
    	vo.setStrTransFlg(tmp[12]);
    	PatientLeaveJoinRecordTransBO.insert(vo);
    	formBean.setStrErrMsgString(vo.getStrErrMsgString());
    	  formBean.setStrMsgType(vo.getStrMsgType());
		   if(formBean.getStrMsgType().equals("1")) {		//error
				throw new Exception(formBean.getStrErrMsgString());
		}
    	}
    	catch(Exception e)
    	{
    		String strmsgText = e.getMessage();
 		   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->insert()", strmsgText);
 		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
 		     eObj = null;
    	}
    }*/

    public static boolean admstat(PatientLeaveJoinRecordTransFB formBean)
    {
    	PatientLeaveJoinRecordTransVO vo=new PatientLeaveJoinRecordTransVO();
        boolean retVal=false;
        String isDead="";
    	try
    	{
    	  vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);
    	  vo.setStrCrNo(formBean.getStrCrNo());
    	  PatientLeaveJoinRecordTransBO.admstat(vo);
    	  formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
    	  isDead=vo.getStrIsDead();
    	  formBean.setStrIsDead(vo.getStrIsDead());
    	  //vo.setStrAdmStatCode(admitted);//hard code...delete after
    	  //isDead="1";//hard code...delete after
    	  formBean.setStrMsgType(vo.getStrMsgType());
	   if(formBean.getStrMsgType().equals("1")) 
	   {		//error
		   formBean.setStrErrMsgString(vo.getStrErrMsgString());
		   throw new Exception(formBean.getStrErrMsgString());
	   }
	   else
	   {	   
    		//  System.out.println("isDead->"+isDead);
    		  if(isDead.equals("1")){
    			  formBean.setStrErrMsgString("Patient is Dead!!");
        		  formBean.setStrCrNo("");  
        		  retVal=false;
    		  } else if(vo.getStrBelongingClear().equals("1")){
    			  formBean.setStrErrMsgString("Patient Belonging has not been Cleared!!");
        		  formBean.setStrCrNo("");  
        		  retVal=false;
    		  } else
    			  retVal=true;
    	}
      }
      catch(Exception e)
    	{
    	   String strmsgText = e.getMessage();
  		   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->admstat()", strmsgText);
  		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
  		   eObj = null;
    	}
    	return retVal;
    }
    public static void upadateLeaveApprovalDtl(PatientLeaveJoinRecordTransFB formBean)
    {
    	String tmp1=formBean.getCurAdmNo();
    	String tmp2=formBean.getCurDept_Unt_RomCode();
    	String tmp3=formBean.getCurWrdBedCode();
    //	String tmp4=formBean.getCurDtUtWrRmBd();
    //	String tmp5=formBean.getCurAdmAdvNo();
    	String tmpArray1[]=tmp1.replace('^','#').split("#");
    	String tmpArray2[]=tmp2.replace('^','#').split("#");
    	String tmpArray3[]=tmp3.replace('^','#').split("#");
    //	String tmpArray4[]=tmp4.replace('^','#').split("#");
    //	String tmpArray5[]=tmp5.replace('^','#').split("#");
    	try{
        formBean.setStrAdmNo(tmpArray1[0]);
        formBean.setStrDeptCode(tmpArray2[0]);
        formBean.setStrDeptUnitCode(tmpArray2[1]);
        formBean.setStrWardCode(tmpArray3[0]);
        formBean.setStrRoomCode(tmpArray2[2]);
        formBean.setStrBedCode(tmpArray3[1]);
     //   System.out.println("isBedVacantDATA->"+formBean.getStrIsBedVacant());
        PatientLeaveJoinRecordTransVO vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);
        PatientLeaveJoinRecordTransBO.upadateLeaveApprovalDtl(vo);
    	formBean.setStrMsgType(vo.getStrMsgType());
		if(formBean.getStrMsgType().equals("1")) {
			   formBean.setStrErrMsgString(vo.getStrErrMsgString());//error
				throw new Exception(formBean.getStrErrMsgString());
		}
		   else
		   {
			   formBean.setStrNormalMsgString(vo.getStrNormalMsgString());
			   formBean.setStrCrNo("");
		   }
		   
    	}
    	catch(Exception e)
    	{
    		String strmsgText = e.getMessage();
   		   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->upadateLeaveApprovalDtl()", strmsgText);
   		   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
   		     eObj = null;
    	}
    }
    
    public static boolean setLeaveDtl(PatientLeaveJoinRecordTransFB formBean)
	{
    	PatientLeaveJoinRecordTransVO vo = new PatientLeaveJoinRecordTransVO();
    	PatientLeaveJoinRecordTransBO bo= new  PatientLeaveJoinRecordTransBO();
    	boolean retVal=false;
		try{
		vo.setStrCrNo(formBean.getStrCrNo());
		vo.setStrTempLeaveJoin(formBean.getStrTempLeaveJoin());
		//System.out.println("leave or join_Flag->"+formBean.getStrTempLeaveJoin());
		vo = (PatientLeaveJoinRecordTransVO)TransferObjectFactory.populateData("ipd.transactions.PatientLeaveJoinRecordTransVO",formBean);
		retVal=bo.setLeaveDtl(vo);
	
	if(vo.getStrMsgType().equals("1")) // Changed formBean to vo Pragya 25-Feb-2011
	{		//error
		throw new Exception(formBean.getStrErrMsgString());
	}
	else
	{	
	  if(retVal==true)
	  {	
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
		formBean.setStrCtDateTime(now(DATE_FORMAT_NOWwt));
  	    formBean.setStrAdmStatCode(vo.getStrAdmStatCode());
		formBean.setStrDisBy(vo.getStrDisBy());
		formBean.setStrDisRsn(vo.getStrDisRsn());
		formBean.setStrRmk(vo.getStrRmk());
		formBean.setStrLeaveSlNo(vo.getStrLeaveSlNo());
		formBean.setStrRsn(vo.getStrRsn());
		//System.out.println("leaveReason->"+formBean.getStrRsn());
		formBean.setStrPatCondL(vo.getStrPatCondL());
		formBean.setStrPhoneNo(vo.getStrPhoneNo());
		formBean.setStrAddrLeave(vo.getStrAddrLeave());
		formBean.setStrIsBedVacant(vo.getStrIsBedVacant());
		formBean.setStrEntryDate(vo.getStrEntryDate());
		formBean.setStrValidFrom(vo.getStrValidFrom());
		formBean.setStrValidTo(vo.getStrValidTo());
		formBean.setStrErrMsgString(vo.getStrErrMsgString());
		formBean.setStrMsgType(vo.getStrMsgType());
		formBean.setStrLeaveTypeText(vo.getStrLeaveTypeText());
	  }
	  else
	  {
		  formBean.setStrErrMsgString(vo.getStrErrMsgString());
		  formBean.setStrCrNo("");
	  }
	}	   
	}
	catch(Exception e)
	{
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PatientLeaveJoinRecordTransDATA->setLeaveDtl()", strmsgText);
			   formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
	}
	finally{
	bo=null;
	vo=null;}
	return retVal;
 }
    
}
