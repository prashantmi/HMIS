package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisSQLManualException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.HisPrinterSupport;
import hisglobal.utility.HisUtil;
import vo.registration.CreditAvailDetailVO;
import vo.registration.EmpMasterVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientIdVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import hisglobal.vo.HospitalMstVO;
import vo.registration.PatientAdhaarDtlVO;
import vo.registration.PatientVO;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

/*import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;


import org.apache.lucene.search.IndexSearcher;

import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;

import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;*/
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;





import registration.QRCodeTest;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.config.RegistrationConfigurationBean;
import registration.config.Exceptions.HisInvalidTokenNumberException;
import registration.config.slip.RegistrationSlip;
import registration.transactions.controller.actionsupport.SplPatientRegistrationSUP;
import registration.transactions.controller.actionsupport.NewPatientRegistrationSUP;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.SplRegistrationDATA;
import registration.transactions.controller.data.patDtlModifiacationDATA;
import registration.transactions.controller.fb.newSplRegFB;
import registration.config.slip.NewRegistrationSlip;
import vo.registration.BPLDetailsVO;

public class NewSplRegUTIL extends ControllerUTIL
{
/**
 * sets all new registration essentials
 * @param _request -HttpServletRequest
 */
	public static void setAllNewRegistrationEssentials(newSplRegFB _fb,HttpServletRequest _request,boolean isAptBased){	
		Status objStatus=new Status();
		Map mp=new HashMap();
		List tempList=new ArrayList();
		try
		{
			UserVO userVO=getUserVO(_request);
			HospitalMstVO hospitalVO=getHospitalVO(_request);
			userVO.setModuleId(Config.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
			HttpSession session =_request.getSession();
			
			//To Check the Cash Collection Back Date Day End Flag Check, Added by Singaravelan on 03-Jun-2015
			System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
			if(userVO.getCheckBackDateDayEndFlag().equals("1"))
				throw new HisRecordNotFoundException("Please do your day end first !");
			if(userVO.getCheckBackDateDayEndFlag().equals("2"))
				throw new HisRecordNotFoundException("Please deposit the money first at cash collection counter !");
			
			HelperMethods.setNullToEmpty(_fb);
			
		//	if(((String)session.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
		//	{
				setSysdate(_request);
				//mp=newSplRegDATA.specialClinicNewRegEssential(userVO);
				
				_fb.setIsActualDob("0");
				_fb.setIsAddressDelhi("1");
				_fb.setPatCasteCode("");
				_fb.setPatAddStateCode(hospitalVO.getState());	 	
				_fb.setPatAddCountryCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
				_fb.setPatIsUrban(RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE);
				
				_fb.setPatNationalityCode("IND");
				_fb.setPatAddDistrictCode(hospitalVO.getDistrictCode());
				_fb.setlocalLanguage(hospitalVO.getLocalLangCode());
				_fb.setPatAddPIN(hospitalVO.getPinCode());
				
				//_fb.setFileNo(new String[]{""});
				_fb.setPatPrimaryCatCode(Config.PRIMARY_CATEGORY_NORMAL_CODE);
				_fb.setPaymentModeCode(Config.PAYMENT_MODE_OPTION_LIST);
				_request.setAttribute("isAptBased", isAptBased);
				
				mp=SplRegistrationDATA.getRegistrationFormEssentials_AJAX(userVO, hospitalVO.getState(), _request);
				//	WebUTIL.setAttributeInSession(_request, RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE, RegistrationConfig.SESSION_FLAG_YES);
				/**By Mukund on 10.10.2017*/
				int privyCounter = 0;
				List tempCategoryList, tempCategoryList2 = new ArrayList();
				tempCategoryList = (List)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
				int originalLoopCounter = tempCategoryList.size();
				for(int m=0;m<originalLoopCounter;m++)
				{
					Object _catListObj = tempCategoryList.get(m);
					Entry _catListObjEntry = (Entry)_catListObj;
					
					if(_catListObjEntry.getValue().split("#")[17].equals("1"))
					{
						//if(privyCounter==0)tempCategoryList2.clear();
						privyCounter++;
						tempCategoryList2.add(_catListObjEntry);
					}
				}
				if(privyCounter==0){
					Entry _catListObjEntry= new Entry() ;
					_catListObjEntry.setValue("-1");_catListObjEntry.setLabel("Select Value");
					tempCategoryList2.clear();
					tempCategoryList2.add(_catListObjEntry);
				}
				mp.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,tempCategoryList2);
				/**End on 10.10.2017*/
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.REGISTRATION_ESSENTIAL_MAP, mp);
				//Start:Sheeldarshi
				//Reason:Bug 10166 - System should be able to validate senior citizen category
				RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				//renewalConfigElement.setAttribute("seniorCitizenAgeLimit", objRegistrationConfigVO.getSeniorCitizenAgeLimit());
				_fb.setSeniorCitizenAgeLimit(objRegistrationConfigVO.getSeniorCitizenAgeLimit());
				_fb.setSeniorCitizenCatCode(objRegistrationConfigVO.getSeniorCitizenCatCode());
				_fb.setIsBarcodeSlipPrint(objRegistrationConfigVO.getIsBarcodeSlipPrint());
				//End
				//To show the department in appointment details and remove remaining dept Units from dept list, Added by Singaravelan on 01-Sep-2014
				int counter=0;
				if(isAptBased){
				if(_fb.getDepartmentCode()!=null&&_fb.getDepartmentCode()!=""){
					tempList=(List)mp.get(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY);
					
					for(int i=0;i<tempList.size();i++)
					{
						Object _listObj=tempList.get(i);
						Entry objEntry = (Entry)_listObj;
						/*if(objEntry.getValue().equalsIgnoreCase(_fb.getDepartmentCode())){
						counter++;
						tempList.clear();tempList.add(objEntry);
					}*/
					/*Code Changed by Garima to map exactly deptunit*/
					if(objEntry.getValue().split("#")[1].equalsIgnoreCase(_fb.getDepartmentCode().split("#")[1])){
						counter++;
						tempList.clear();tempList.add(objEntry);
					}
					}
					if(counter==0){
						//Object _listObj=new Object();
						Entry objEntry= new Entry() ;
						objEntry.setValue("-1");objEntry.setLabel("Select Value");
						//Entry objEntry = (Entry)_listObj;objEntry.setValue("-1");objEntry.setLabel("Select Value");
						tempList.clear();
						tempList.add(objEntry);
					}
					mp.remove(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY);
					mp.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY, tempList);		
						
				}
			}
				
		//	}
		//	else
			//{
				//mp=(Map)session.getAttribute(RegistrationConfig.REGISTRATION_ESSENTIAL_MAP);
			//}
			
			//setSysdate(_request);
			//mp=newSplRegDATA.getAllNewRegistrationEssentials(userVO);
			//List deptList=(List)mp.get(Config.ESSENTIALBO_OPTION_DEPARTMENT);
			//mp.put(Config.REGISTRATION_MANDATORY_DEPT_LIST,deptList);
			//System.out.println("-------IN BO1-----------"+deptList+"-----------------");
			//_fb.setConfigFlag(Config.CLIENT);
			WebUTIL.setMapInSession(mp,_request,"NewSplRegwithAptmntACTION");
			//_fb.setPatAmountCollected((String)mp.get(RegistrationConfig.AMOUNT_COLLECTED));
			//_fb.setPatPrimaryCatCode(RegistrationConfig.DEFAULT_PRIMARY_CATEGORY);
			NewSplRegUTIL.setDeptOptions(_request, _fb);
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL,getUserVO(_request));
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			
			//objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			//objStatus.add(Status.ERROR_DA,"",e.getMessage());
			objStatus.add(Status.ERROR_DA,"","<div id='divNoAppointmentAvailableLabel'>"+e.getMessage()+"</div>");

			//WebUTIL.setMapInSession(e.getEssentialMap(),_request,"NewSplRegwithAptmntACTION");
			Collection newCol=new ArrayList(); 
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);

			objStatus.add(Status.LIST,"","");
			
			e.printStackTrace();
		}		
		catch(HisRegistrationTimingExpiredException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisDataAccessException e){
		//	WebUTIL.setMapInSession(e.getEssentialMap(),_request,"NewSplRegwithAptmntACTION");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}
	
	
	public static void setBillAmountBasedOncategory(newSplRegFB _fb,HttpServletRequest _rq,HttpServletResponse _rp){
		Status objStatus=new Status();		
		UserVO userVO=getUserVO(_rq);
		userVO.setTariffID(RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
		
		try{
			String patientCat=_rq.getParameter("patientCat");
			String patientPriCatCode=_rq.getParameter("patientPriCatCode");
			String amount=SplRegistrationDATA.getBillAmountBasedOnCategory(patientCat,userVO);
			_fb.setPatAmountCollected(amount);
			_fb.setPatPrimaryCatCode(patientPriCatCode);
			/*StringBuffer sb=new StringBuffer();
			sb.append("<table width='100%'cellspacing='1px' cellpadding='0'>");
			sb.append("<tr><td width='17%' nowrap class='tdfonthead'>");
			sb.append("<div align='right'><font color='#FF0000' size='1' ");
			sb.append("face='Verdana, Arial, Helvetica, sans-serif'>*</font>");
			sb.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("Card No</font></div></td>");
			sb.append("<td width='19%' class='tdfont'>");
			sb.append("<font color='#000000' size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("<input type='text' name='bplCardNo' value='' class='textbox' onclick='' maxlength='10'>");
			sb.append("</font></td>");
			sb.append("<td width='18%' nowrap class='tdfonthead'><div align='left'><input type='button' name='BPL Search' value='BPL Search' onclick='openBPLPopup(event);'></div></td>");
			sb.append("<td width='19%' class='tdfont'></td>");
			sb.append("<td width='17%' nowrap class='tdfonthead'><div align='right'></div></td>");
			sb.append("<td width='19%' class='tdfont'></td>");
			sb.append("</tr>");
			sb.append("<tr><td width='17%' nowrap class='tdfonthead'>");
			sb.append("<div align='right'>");
			sb.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("Family Head Name</font></div></td>");
			sb.append("<td width='19%' class='tdfont'>");
			sb.append("<font color='#000000' size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("<input type='text' class='textbox' name='bplFamilyHeadName' value='' onclick='' maxlength='10'>");
			sb.append("</font></td>");
			sb.append("<td width='17%' nowrap class='tdfonthead'>");
			sb.append("<div align='right'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("Head Relation</font></div></td>");
			sb.append("<td width='19%' class='tdfont'><select name='bplFamilyHeadRelation' onchange='' class='regcbo'>");
			sb.append("<option value='-1'>Select Value</option></select></td>");
			sb.append("<td width='17%' nowrap class='tdfonthead'><div align='right'>");
			sb.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>State ID No.</font></div></td>");
			sb.append("<td width='20%' class='tdfont'><input type='text' class='textbox' name='bplStateId' value='' onclick='' maxlength='10'></td>");
			sb.append("</tr>");
			sb.append("<tr><td width='17%' nowrap class='tdfonthead'>");
			sb.append("<div align='right'>");
			sb.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("MMJRK No</font></div></td>");
			sb.append("<td width='19%' class='tdfont'>");
			sb.append("<font color='#000000' size='1' face='Verdana, Arial, Helvetica, sans-serif'>");
			sb.append("<input type='text' name='bplMMJRKNo' value='' class='textbox' onclick='' maxlength='10'>");
			sb.append("</font></td>");
			sb.append("<td width='18%' nowrap class='tdfonthead'><div align='right'>");
			sb.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>BPL Family ID</font></div></td>");
			sb.append("<td width='19%' class='tdfont'><input type='text' class='textbox' name='bplMMJRKNo' value='' onclick='' maxlength='10'></td>");
			sb.append("<td width='17%' nowrap class='tdfonthead'><div align='right'></div></td>");
			sb.append("<td width='19%' class='tdfont'></td>");
			sb.append("</tr>");
	  		sb.append("</table></center>");*/
			PrintWriter printWriter=_rp.getWriter();
			//printWriter.write(_fb.getPatAmountCollected()+"^"+sb.toString());
			printWriter.write(_fb.getPatAmountCollected());
			//objStatus.add(Status.TRANSINPROCESS,"","");
		}
		catch(HisRecordNotFoundException e){
			_fb.setPatAmountCollected("");
			objStatus.add(Status.ERROR_DA,"","No Bill amount exist");			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,"","Error");
		}		
		catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally{
			
			WebUTIL.setStatus(_rq,objStatus);		 
		}				
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	/**public static UserVO getUserVO(HttpServletRequest request){
		//UserVO userVO = (UserVO) request.getSession().getAttribute(RegistrationConfig.USERVO);
		//<<hardCoded>>>>>>
		UserVO userVO = new UserVO();
		userVO.setSeatId("10001");
		return userVO;		
	}*/
	
	/**
	 * sets Department Options 
	 * retrieves departments to visit stamp from fb
	* @param _req -HttpServletRequest
	 * @param _fb -newSplRegFB form bean
	 */
	public static void setDeptOptions(HttpServletRequest _req,newSplRegFB _fb)
	{	Collection newCol=null;
		String [] deptVisitArr=	_fb.getDepartmentsToVisitStamp();
		HttpSession ses=_req.getSession();		  
		   
		Collection colOrgDept = (Collection)_req.getSession().getAttribute(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY);
		//Collection colOrgDept = (Collection)_req.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
		//System.out.println("----In setDept----------"+colOrgDept+"-------------------");
		 newCol = new ArrayList(colOrgDept);
		 newCol=WebUTIL.removeEntriesfromOptions(newCol, deptVisitArr);
		//<<why returned collection need to be stored
	    //newCol=WebUTIL.removeEntriesfromOptions(newCol, deptVisitArr);
		WebUTIL.setAttributeInSession(_req,RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT, newCol);		
	}
	
	
	/**
	 * adds a removed department in the department combo 
	 * @param _fb -newSplRegFB form bean
	 */
	public static void addDeptToVist(newSplRegFB _fb)	{
		String[] arrStr = _fb.getDepartmentsToVisitStamp();
		String strDeptCode= _fb.getDepartmentCode();		
		String[] arrTmp = (String[])WebUTIL.addElementToArray(arrStr,strDeptCode);
		String[] arrTmp1=(String[])WebUTIL.addElementToArray(_fb.getFileNo(),"");
		_fb.setDepartmentsToVisitStamp(arrTmp);
		int length=_fb.getFileNo().length;
         if(arrTmp.length==arrTmp1.length)
        	 _fb.setFileNo(arrTmp1);		
	}
	
	public static String getEntryLabel(Collection _col,String _value)
	{
		//List ls= new ArrayList(col);
		Iterator it=_col.iterator();
		while (it.hasNext())
		{
			Entry objEntry=(Entry)it.next();
			if(objEntry.getValue().equals(_value))
				return objEntry.getLabel();		
		}		
		//System.out.println("getEntryLabel:  "+)
		return _value;				
	}
	/**
	 * decides whether the patient has been referred by some hospital or not
	 * @param _fb -newSplRegFB form bean
	 * @param request -HttpServletRequest
	 */
	public static void isReferredDecider(newSplRegFB _fb,HttpServletRequest request){		

		if(_fb.getIsReferred().equalsIgnoreCase("1"))
		  {	
		    if(_fb.getReferringInstType().equalsIgnoreCase("G"))
		    {
		    	_fb.setPatRefHospitalName("");		    	
		    }
		    else
		    {
		    	_fb.setPatRefGnctdHospitalCode("");		    	
		    }			
		  }
		else
		{
			
			_fb.setIsReferred("0");
			_fb.setPatRefGnctdHospitalCode("");
			_fb.setPatRefHospitalName("");
			_fb.setReferringInstType("");			
		}		
	}
	
	
	
	public static RegistrationSlip preapareSlip(EpisodeVO episodeVO[],PatientVO _patVO,newSplRegFB _fb,HttpServletRequest _request) throws ParseException{		
		
		RegistrationSlip regSlip=new RegistrationSlip();
		HelperMethods.setNullToEmpty(_patVO);
		//HelperMethods.setNullToEmpty(_patVO.getPatAddress());
		for (int i=0;i<episodeVO.length;i++)
		{
			HelperMethods.setNullToEmpty(episodeVO[i]);
			regSlip.setEpisodeVisitType(episodeVO[i].getEpisodeVisitType());
		}
		HelperMethods.populate(regSlip,_patVO);
		UserVO _userVO=getUserVO(_request);
		regSlip.setPatAge(_patVO.getPatAge()+" "+_patVO.getPatAgeUnit() );
		
		//HospitalMstVO hospitalVO=(HospitalMstVO)_request.getSession().getAttribute(Config.HOSPITAL_VO);
		HospitalMstVO hospitalVO=(HospitalMstVO)ControllerUTIL.getHospitalVO(_request);
		
		regSlip.setHospitalName(hospitalVO.getHospitalName());
		regSlip.setHospitalAddress1(hospitalVO.getAddress1());
		regSlip.setHospitalAddress2(hospitalVO.getAddress2());
		regSlip.setHospitalCity(hospitalVO.getCity());
		regSlip.setHospitalDistrict(hospitalVO.getDistrictName());
		regSlip.setHospitalState(hospitalVO.getStateName());
		regSlip.setHospitalpinCode(hospitalVO.getPinCode());
		regSlip.setHospitalPhone(hospitalVO.getPhone());
		regSlip.setHospitalFax(hospitalVO.getFax());
		regSlip.setHospitalEmail(hospitalVO.getEmail());
		
		if(_patVO.getPatPrimaryCatGrp().equals("0"))
		{
			regSlip.setPatPrimaryCatGrp("Payment"); 
		   regSlip.setPatPrimaryCatGrpCode("0"); 
		}
		else if(_patVO.getPatPrimaryCatGrp().equals("1"))
		{
			regSlip.setPatPrimaryCatGrp("Staff"); 
		regSlip.setPatPrimaryCatGrpCode("1");
		}
		else if(_patVO.getPatPrimaryCatGrp().equals("2"))
		{
		regSlip.setPatPrimaryCatGrp("Free"); 
		regSlip.setPatPrimaryCatGrpCode("2");
		}
		else if (_patVO.getPatPrimaryCatGrp().equals("3"))
		{
		regSlip.setPatPrimaryCatGrp("Credit/Beneficery"); 
		regSlip.setPatPrimaryCatGrpCode("3");
		}
		else if (_patVO.getPatPrimaryCatGrp().equals("4"))
		{
		regSlip.setPatPrimaryCatGrp("Credit/Beneficery Without Reference"); 
		regSlip.setPatPrimaryCatGrpCode("4");
		}

		
		if(regSlip.getPatPrimaryCat()==null&&regSlip.getPatPrimaryCat()==""){
			String primaryCatName=getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY),_fb.getPatPrimaryCatCode());
			regSlip.setPatPrimaryCat(primaryCatName);			
		}
		regSlip.setPatGender(getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_GENDER),_fb.getPatGenderCode()));
		//System.out.println("-------------"+_fb.getPatOccupation()+"--------------------");
		regSlip.setPatOccupation(getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL),_fb.getPatOccupation()));
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
		//System.out.println("-------------"+_fb.getPatCasteCode()+"--------------------");
		regSlip.setPatCasteCode(getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_CASTE),_fb.getPatCasteCode()));
		//System.out.println("-------------"+regSlip.getPatOccupation()+"--------------------");
	
		String fileNoView[] =new String[episodeVO.length];
		String fileNo[] =new String[episodeVO.length];
		String episodeDate[] =new String[episodeVO.length];
		String roomName[] =new String[episodeVO.length];
		String depName[]=new String[episodeVO.length];
		String depUnit[]=new String[episodeVO.length];
		String queueNo[]=new String[episodeVO.length];
		String workingDays[]=new String[episodeVO.length];
		String timing[]=new String[episodeVO.length];
		// disclaimers
		String disclaimer1[]=new String[episodeVO.length];
		String disclaimer2[]=new String[episodeVO.length];
		String disclaimer3[]=new String[episodeVO.length];
		String isHeader[]=new String[episodeVO.length];
		String alignment[]=new String[episodeVO.length];
		String unitConsultant[]=new String[episodeVO.length];
		String filePrintFlag[]=new String[episodeVO.length];
		String cardPrintFlag[]=new String[episodeVO.length];
		String expDate[]=new String[episodeVO.length];
		String billNo[]=new String[episodeVO.length];
		String patAgeWithUnit="";
		
		String patMiddleName=regSlip.getPatMiddleName();
		
		//Commented By Mukund on 18.04.2017
				/*if(!(patMiddleName==null || patMiddleName.equals("")))
				{
					patMiddleName=patMiddleName.substring(0,1).toUpperCase();
					regSlip.setPatMiddleName(patMiddleName);
				}*/
		
		////Address For card
		String cityLocation="";
		String district="";
		String state="";
	
		String country=getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY),_fb.getPatAddCountryCode());
		if(_patVO.getPatAddCityLocCode()==null || _patVO.getPatAddCityLocCode().equals("") || _patVO.getPatAddCityLocCode().equals("-1"))
			cityLocation=_patVO.getPatAddCityLoc();
		else
			cityLocation=getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION),_fb.getPatAddCityLocCode());
		
		if(_patVO.getPatAddDistrictCode()==null || _patVO.getPatAddDistrictCode().equals(""))
			district=_patVO.getPatAddDistrict();
		else
			district=getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),_fb.getPatAddDistrictCode());
		
		if(_patVO.getPatAddStateCode()==null || _patVO.getPatAddStateCode().equals(""))
			state=_patVO.getPatAddStateName();
		else
			state=getEntryLabel((Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_STATE),_fb.getPatAddStateCode());
		
		//inorder to prevent null showing for the location textbox on the print slip
		cityLocation=cityLocation==null?" ":cityLocation;
		
		
		String address=_patVO.getPatAddHNo();
		address=address+" "+_patVO.getPatAddStreet();
		address=address+" "+cityLocation;
		address=address+" " +_patVO.getPatAddCity();
		if(district.equals("-1"))
			district="";
			String address2=district;
	
		if(!address2.equals(""))
			address2=address2+","+state;
		else
			address2=state;
		
			address2=address2+" "+country;
			if(_patVO.getPatAddPIN() != null && !_patVO.getPatAddPIN().equals(""))
			address2=address2+"-"+_patVO.getPatAddPIN();
			if(_patVO.getPatAddMobileNo() != null && !_patVO.getPatAddMobileNo().equals(""))
				address2=address2+" Mobile: "+_patVO.getPatAddMobileNo();
			
			//String patOccupation="";
			//patOccupation=_patVO.getPatOccupation();
		
		///////////////////////////////////////////////////////////////////////////////////////
		for(int i=0;i<episodeVO.length;i++){	
			if(episodeVO[i].getFileNo()==null)
			{
				episodeVO[i].setFileNo("");
				episodeVO[i].setFileNoView("");
			}
			
			
			fileNoView[i]=episodeVO[i].getFileNoView();
			fileNo[i]=episodeVO[i].getFileNo();
			roomName[i]=episodeVO[i].getRoom();
			
			if(episodeVO[i].getDepartment().indexOf("(")>0){
			String[] tempdeptUnit=episodeVO[i].getDepartment().replace("(", "#").split("#");
			depName[i]=tempdeptUnit[0];			
			//depUnit[i]=episodeVO[i].getDepartmentUnit();
			depUnit[i]=tempdeptUnit[1].substring(0, tempdeptUnit[1].indexOf(")"));// as department name contains dep+unit name..	
			}
			else
			{
				depName[i]=episodeVO[i].getDepartment();			
				//depUnit[i]=episodeVO[i].getDepartmentUnit();
				depUnit[i]=episodeVO[i].getDepartmentUnit();// as department name contains dep+unit name..
			}
			
			//To Set the QueueNo from Appointment Details
			if(_patVO.getPatAptNo()!=null&&_patVO.getPatAptNo()!="")
				queueNo[i]=_patVO.getPatAptQueueNO();
			else
				queueNo[i]=episodeVO[i].getQueNo();
				
			expDate[i]=episodeVO[i].getExpiryDate();
			unitConsultant[i]=episodeVO[i].getUnitConsultant();
			//added to set the card and file print format
			filePrintFlag[i]=episodeVO[i].getFilePrintSetting();
			cardPrintFlag[i]=episodeVO[i].getCardPrintSetting();
			billNo[i]=episodeVO[i].getBillNo();
			patAgeWithUnit=episodeVO[i].getPatAgeWithUnit();
			
			// disclaimers
			if(episodeVO[i].getDisclaimer()!="" && episodeVO[i].getDisclaimer()!=null){
			disclaimer1[i]=episodeVO[i].getDisclaimer().split("@")[0];
			disclaimer2[i]=episodeVO[i].getDisclaimer().split("@")[1];
			disclaimer3[i]=episodeVO[i].getDisclaimer().split("@")[2];
			isHeader[i]=episodeVO[i].getDisclaimer().split("@")[3];
			alignment[i]=episodeVO[i].getDisclaimer().split("@")[4];
			}
			else{
				disclaimer1[i]="";
				disclaimer2[i]="";
				disclaimer3[i]="";
				isHeader[i]="";
				alignment[i]="";
			}
			
			/*String[] temp=episodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			System.out.println("temp[0]"+temp[0]);
			System.out.println("temp[1]"+temp[1]);
			workingDays[i]=temp[0];
			timing[i]=temp[1];*/
			//workingDays[i]=episodeVO[i].getUnitWorkingDays();
			//episodeDate[i]=episodeVO[i].getEntryDate();
			episodeDate[i]=(String)_request.getSession().getAttribute(Config.SYSDATE);
			/*Calendar cal = Calendar.getInstance(); 
			String DATE_FORMAT = "dd/MM/yyyy HH:MM";
		    SimpleDateFormat sdf =new SimpleDateFormat(DATE_FORMAT);
			String date=sdf.format(cal.getTime());
			episodeDate[i]=date;*/
			
			String[] temp=episodeVO[i].getUnitWorkingDays().split("#");
			if(temp.length==0)
				temp=new String[]{"",""};
			System.out.println("temp[0]"+temp[0]);
			//System.out.println("temp[1]"+temp[1]);
			workingDays[i]=temp[0];
			
			String DATE_FORMAT = "dd/MM/yyyy HH:mm";
		    SimpleDateFormat sdf =new SimpleDateFormat("dd-MMM-yyyy");
		    if(expDate[i]!=null&&expDate[i]!=""){
				Date _expdate = sdf.parse(expDate[i]);
			    SimpleDateFormat _sdf =new SimpleDateFormat(DATE_FORMAT);
				//String date=sdf.format(cal.getTime());
			    expDate[i]=_sdf.format(_expdate);
		    }
		}
		/***/
		
		RegistrationConfigurationBean objRegConfigbean  = (RegistrationConfigurationBean)WebUTIL.getSession(_request).getAttribute(RegistrationConfig.Registration_Configuration_Bean_Special);
		regSlip.setIsQRCodePrintFlag(objRegConfigbean.getIsQRCodePrint());
		
		/***/
		regSlip.setHostName(_request.getRemoteAddr());
		regSlip.setPatCrNo(episodeVO[0].getPatCrNo());
		regSlip.setRoom(roomName);
		regSlip.setDepartmentToBeVisited(depName);
		
		/*for(int k=0;k<regSlip.getDepartmentToBeVisited().length;k++)
		{
			System.out.println("department"+regSlip.getDepartmentToBeVisited()[k]);
		}*/
		regSlip.setFileNoView(fileNoView);
		regSlip.setFileNo(fileNo);
		regSlip.setExpDate(expDate);
		regSlip.setDepartmentUnit(depUnit);		
		regSlip.setUnitConsultant(unitConsultant);		
		regSlip.setQueNo(queueNo);
		regSlip.setEpisodeDate(episodeDate);	
		regSlip.setWorkingDays(workingDays);
		regSlip.setTiming(timing);
		regSlip.setAddress1(address);
		
		regSlip.setAddress2(address2);
		regSlip.setDisclaimer1(disclaimer1);
		regSlip.setDisclaimer2(disclaimer2);
		regSlip.setDisclaimer3(disclaimer3);
		regSlip.setIsHeader(isHeader);
		regSlip.setAlignment(alignment);
		regSlip.setUnitConsultant(unitConsultant);
		regSlip.setFilePrintFlag(filePrintFlag);
		regSlip.setCardPrintFlag(cardPrintFlag);
		//regSlip.setPatOccupation(patOccupation);
		regSlip.setBillNo(billNo);
		if(_patVO.getPatAge()==null||_patVO.getPatAge().equals(""))
			regSlip.setPatAge(patAgeWithUnit);
		regSlip.setLoginUserName(_userVO.getUsrName());//By Mukund
		return regSlip;		
	}	
	
	public static void setEmployeeDtls(newSplRegFB _fb,HttpServletRequest _request){		
		Status objStatus=new Status();
		EmpMasterVO empVO=null;
		try{
			
			EmpMasterVO[] empMstVO=(EmpMasterVO[])_request.getSession().getAttribute(RegistrationConfig.EMPLOYEE_MASTER_VO_ARRAY);
			for(int i=0;i<empMstVO.length;i++){
				if(empMstVO[i].getPatIdNo().equalsIgnoreCase(_fb.getEmpIdChk())){
					empVO=empMstVO[i];
					break;
				}						
			}
			HelperMethods.populate(_fb,empVO);
			_fb.setIsActualDob("1");			
			//objStatus.add(Status.INPROCESS);
			objStatus.add(Status.TRANSINPROCESS);
		  }
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.RECORDFOUND,"","Record Not Found Error");			
		}
		catch(HisDataAccessException e){			
			objStatus.add(Status.ERROR_DA,"Data Access Error","");			
		}
		catch(HisApplicationExecutionException e){			
			objStatus.add(Status.ERROR_AE,"Application Execution Error","");
		}		
		catch(Exception e){			
			objStatus.add(Status.ERROR,"Error","");
		}		
		finally{
			objStatus.add(Status.INPROCESS);
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	
	
	

	
	
	/**
	 * saves New Patient Registration Details
	 * generates a String of Queue, Departement, unit and room 
	 * @param _request -HttpServletRequest
	 * @param _fb -newSplRegFB form bean
	 * @return return true if any duplicate record found against the patient detail
	 * else save the patient details and register and return false
	 * @throws ParseException 
	 */
	public static Integer saveNewPatientRegistration(HttpServletRequest _request, newSplRegFB _fb,ServletContext context) throws ParseException{
		boolean exists=false;
		Integer flag=0;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		boolean flagSaveMsgObjCreated=false;
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="",strIsDuplicatePatient="", isBarcodeSlipPrint="";
		UserVO userVO=getUserVO(_request);
		HospitalMstVO hospitalVO=getHospitalVO(_request);
		userVO.setTariffID(RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
		userVO.setModuleId(Config.MODULE_ID_REGISTRATION);
		//gets the hospital information
		getHospitalVO(_request);
		setSysdate(_request);
		EmpMasterVO empVO=null;
		Status objStatus =new Status();	
		String patPriCat=_fb.getPatPrimaryCatCode();
		Document responseDocument=null;
		//String patEmployeeCode=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE;
		Map _mp=new HashMap();
		
		//For validating the pat amount collected
		if(_fb.getPatAmountCollected()==null)
		{
			_fb.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
			//return false;
			return 0;
		}
		else if(_fb.getPatAmountCollected().equals(""))
		{
			_fb.setErrorMessage("Registration Fee cannot be empty.\nPlease relogin.");
			//return false;
			return 0;
		}
		
		
		/*if(patPriCat.equals(patEmployeeCode))//Employee Category
		{
			EmpMasterVO[] empMstVO=(EmpMasterVO[])_request.getSession().getAttribute(RegistrationConfig.EMPLOYEE_MASTER_VO_ARRAY);
			if(empMstVO!=null)
			{
				if(empMstVO!=null)
				{
					empVO=empMstVO[Integer.parseInt(_fb.getEmpIdChk())];
					if(empVO.getPatMiddleName()==null)
					{
						empVO.setPatMiddleName("");
					}
					if(empVO.getPatLastName()==null){
						empVO.setPatLastName("");
					}
				}
				HelperMethods.populate(_fb, empVO);
				if((_fb.getPatAddStateCode()!=null) && (_fb.getPatAddStateCode().equals(hospitalVO.getState())))
				{
					if(Config.IS_LOCATION_COMBO_REQ.equals("1"))
					{
						_fb.setPatAddDistrictCode(_fb.getPatAddDistrictCodeHidden());
						_fb.setPatAddCity(_fb.getPatAddCityHidden());
						_fb.setPatAddPIN(_fb.getPatAddPINHidden());
						_fb.setPatIsUrban(_fb.getPatIsUrbanHidden());
					}
				}
			}
		}*/
		
		try
		{
			//start:sheeldarshi
			/*responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);*/
			//End:sheeldarshi
			
			if(_fb.getPatAddCountryCode()!=null && _fb.getPatAddCountryCode().equals(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE))
			{
				_fb.setPatAddStateName("");
			}
			else
			{
				_fb.setPatAddStateCode("");
			}
			
			/*if(!_fb.getPatAddStateCode().equalsIgnoreCase(hospitalVO.getState()))
			{	
				_fb.setPatAddCityLocCode("");				
				_fb.setIsAddressDelhi("0");
			}
			else
			{
				if(Config.IS_LOCATION_COMBO_REQ.equals("1"))
				{
					_fb.setPatAddCityLoc("");
					_fb.setIsAddressDelhi("1");
				}
				else
				{
					_fb.setIsAddressDelhi("0");
				}
			}*/
		
		isReferredDecider(_fb,_request);
		if(_fb.getPatRefGnctdHospitalDept().equals("0"))
		{
			_fb.setPatRefGnctdHospitalDept(_fb.getPatRefHospitalDeptOther());
		}
		PatientVO patientVO = new PatientVO();	
		CreditAvailDetailVO objCreditAvailDtlVO=new CreditAvailDetailVO();

		setNotMandatoryDefaultCmbValue(_fb);

		HelperMethods.populate(patientVO, _fb);
		
		patientVO.setPatIsDead("0");
		
		if(_fb.getPatRefGnctdHospitalDept().equals("0"))
			patientVO.setPatRefGnctdHospitalDept(_fb.getPatRefHospitalDeptOther());
	
		if(_fb.getPatRefGnctdHospitalCode().indexOf("#")>0){
			String[] tempRefHosDeptCode=_fb.getPatRefGnctdHospitalCode().split("#");
			patientVO.setPatRefGnctdHospitalCode(tempRefHosDeptCode[0]);
		}
		
		patientVO.getPatAddress().setPatVerificationStatus("2");
		if(hospitalVO.getDistrictCode()!=null && _fb.getPatAddDistrictCode()!=null && hospitalVO.getDistrictCode().equals(_fb.getPatAddDistrictCode()) )
			patientVO.getPatAddress().setPatIsLocal("1");
		else
			patientVO.getPatAddress().setPatIsLocal("0");
		
		/*if(_fb.getIsIdRequired().equals("1"))
		{
			if(!patPriCat.equals(patEmployeeCode))
			{
				patientVO.setPatIdNo(_fb.getPatCatIdNo());
			}
		}
		if(_fb.getIsIdRequired().equals("0"))
		{
			if(!patPriCat.equals(patEmployeeCode))
			{
				patientVO.setPatIdNo("");
			}
		}*/
		String[] deptToVisit = _fb.getDepartmentsToVisitStamp();
	
		EpisodeVO[] arrEpisodeVO;		
			
		Collection col=(Collection)_request.getSession().getAttribute(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY);
		//Collection col=(Collection)_request.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
		String deptName="";
		String occupation="";
		//for radiotherapy
		String referDeptname=_fb.getPatRefGnctdHospitalDept();
				
		if(deptToVisit.length==0)
		{
			arrEpisodeVO = new EpisodeVO[1];
			
			arrEpisodeVO[0]=new EpisodeVO();
			arrEpisodeVO[0].setDepartmentCode(_fb.getDepartmentCode());
			
			deptName=getEntryLabel(col,_fb.getDepartmentCode());
			//since department contains department name plus unit name
			//arrEpisodeVO[0].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
			arrEpisodeVO[0].setDepartment(deptName);
			arrEpisodeVO[0].setPaymentModeCode(_fb.getPaymentModeCode());
			if(!_fb.getPaymentModeCode().equals("1"))
				arrEpisodeVO[0].setPaymentModeCodeRefId(_fb.getPaymentModeRefId());
		}
		else
		{
			arrEpisodeVO = new EpisodeVO[deptToVisit.length];		
			for(int i=0; i<arrEpisodeVO.length; i++)
			{
				arrEpisodeVO[i]=new EpisodeVO();
				arrEpisodeVO[i].setDepartmentCode(deptToVisit[i]);
				deptName=getEntryLabel(col,deptToVisit[i]);
				//since department contains department name plus unit name
				//arrEpisodeVO[i].setDepartment(deptName.substring(0,deptName.lastIndexOf("-")));
				arrEpisodeVO[i].setDepartment(deptName);
				////for radiotherapy
				//arrEpisodeVO[0].setDepartment(referDeptname);
			}
		}
		patientVO.setSystemIPAddress(_request.getRemoteAddr());
		patientVO.setRegistrationType(RegistrationConfig.REGISTRATION_TYPE_SPECIAL_CLINIC);	
		HttpSession sess=WebUTIL.getSession(_request);
		
		String sysdate=(String)sess.getAttribute(Config.SYSDATE);
		
		////for generating episode number
		patientVO.setSystemDate(sysdate);
		patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL);
		
		/////setting vo in session for dupllicate registration or new department visit
		_mp.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
		_mp.put(RegistrationConfig.REGISTRATIONDESK_PATIENT_VO, patientVO);
		//WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, arrEpisodeVO);
		//WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_PATIENT_VO, patientVO);
		// checking if patient is already registered.............//
		/*if(checkForPreviousRegistration(_fb, _request, context)){
			exists=true;
			return exists;
			
		}
		else{*/
		//Added for the Adhaar no duplicacy check by Singaravelan on 17-10-13
		//PatientAdhaarDtlVO PatientAdhaarDtlVO_p= new PatientAdhaarDtlVO();
		//PatientAdhaarDtlVO_p =checkForDuplicateAdhaarID(patientVO, _request, userVO);
		PatientIdVO objPatientIdVO = new PatientIdVO();
		objPatientIdVO.setPatIdNo(patientVO.getPatCardNo());
		objPatientIdVO.setPatDocType(patientVO.getPatDocType());
		String strUniqueIdDuplicyFlag = "0";
		strUniqueIdDuplicyFlag = SplRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,patientVO.getPatNationalId());
		
		String strAdhar="";
		/*if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
			if(strUniqueIdDuplicyFlag.equals("1")){
				strAdhar="Duplicate National Id/ Aadhar No";
			}else if(strUniqueIdDuplicyFlag.equals("2")){
				strAdhar="Patient with this Document Name("+patientVO.getPatCardNo()+ ") already registered.";
			}
		}*/
		
		//Start:Sheeldarshi:20 Oct 14:Duplicacy Check
		//To Check the Duplicacy on the Secondary Alternate Id Basis
		/*if((_fb.getPatIdNo()!=null && !_fb.getPatIdNo().equals("") && _fb.getPatCatDocIsAlternateId().equals("1")))
		{
			objPatientIdVO.setPatIdNo(_fb.getPatIdNo());
			//objPatientIdVO.setPatDocType(_fb.getPatCatDocCode());
			strUniqueIdDuplicyFlag = NewRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO,_fb.getPatNationalId());
				
			if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					strErrMsg="Duplicate National Id/ Aadhar No";
					//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
					return 1;
				}else if(strUniqueIdDuplicyFlag.equals("2")){
					strErrMsg="Patient with this Id No ("+_fb.getPatIdNo()+ ") already registered.";
					//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
					return 1;
				}
			}
		
		}*/
		//End:Sheeldarshi:20 Oct 14:Duplicacy Check
		//To Check the Duplicacy on the Reference Letter No for the CREDIT BASED BENEFICIARY WITH REFERENCE Categories
		if(_fb.getPatPrimaryCatGrp()!=null && _fb.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
		{
			HelperMethods.populate(objCreditAvailDtlVO, _fb);
			objCreditAvailDtlVO.setTariffId(userVO.getTariffID());
			strUniqueIdDuplicyFlag = NewRegistrationDATA.checkBeneficiaryIdDuplicy(userVO, objCreditAvailDtlVO);

			if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					strErrMsg="Patient with this Reference Letter No ("+_fb.getCreditLetterRefNo()+ ") already registered.";
					//flagSaveMsgObjCreated = createSaveMsgObject(responseDocument, strIsSavedSuccussfulFlag, strErrMsg, strIsFormFieldResetFlag, strPrintDivContent,"0",strIsDuplicatePatient);
					return 1;
				}
				//Start:Sheeldarshi
				patientVO.setClientCode(_fb.getClientCode());
				patientVO.setClientName(_fb.getClientName());
				//End
			}
		}
		//For the Appointment Unit Basis Queue No. generation
		if(patientVO.getPatAptNo()!=null && !patientVO.getPatAptNo().equals(""))
		{
			patientVO.setRoundRobinUnitFlag("0");
			patientVO.setRoundRobinRoomFlag("0");
			patientVO.setIsAppointment("1");
		}
		
		//if(PatientAdhaarDtlVO_p.getPatCrNo()!=null)
		//{
			//strAdhar="<font color='red' size='2' face='Verdana, Arial, Helvetica, sans-serif'>Patient  with CR No."+PatientAdhaarDtlVO_p.getPatCrNo()+"("+PatientAdhaarDtlVO_p.getPatFirstName()+")"+" is already Registered with  Aadhar id "+patientVO.getPatNationalId()+"</font>" ;
		//}		
		if(strAdhar!=null&&!strAdhar.equals(""))
		{
			exists=true;flag = 1;
			objStatus.add(Status.DONE,"",strAdhar);
			return flag;			
		}
		/*else if(checkForPreviousRegistration(_fb, _request, context))
		{
			exists=true;flag = 2;
			return flag;
			
		}	*/	
		
		// checking if patient is already registered.............//
		/*if(checkForPreviousRegistration(_fb, _request, context)){
			exists=true;
			return exists;
			
		}*/
		else
		{
			//To set the CREDIT LIMIT By Mukund on 25.07.2016
			if(_fb.getPatPrimaryCatGrp()!=null && _fb.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY) )
			{
				patientVO.setCreditLimit(_fb.getCreditLimit());
			}
			if(_fb.getPatPrimaryCatGrp()!=null && _fb.getPatPrimaryCatGrp().equals(RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE) )
			{
				patientVO.setCreditLimit(_fb.getAgsCreditLimit());
			}
			//End: Mukund
			
			//EpisodeVO episodeVO[]= newSplRegDATA.registerNewPatient(patientVO, arrEpisodeVO, userVO,_request);
			//start: Sheeldarshi: 30Sep'14
			RenewalConfigVO objRenewalConfigVO=null;
			Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)_request.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(_fb.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");
			if(objRenewalConfigVO !=null){
			patientVO.setRenewalConfig(objRenewalConfigVO);
			patientVO.getRenewalConfig().setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_SPEACIAL);
			}
			
			patientVO.getPatAddress().setPatAddPhoneOwner(_fb.getPatAddPhoneOwner());
			patientVO.getPatAddress().setPatAddPhoneNo(_fb.getPatAddPhoneNo());
			patientVO.getPatAddress().setPatAddEmailId(_fb.getPatAddEmailId());
			//End: Sheeldarshi: 30Sep'14
			//Start:Sheeldarshi:21stOct'14
			if(patientVO.getPatDOB().equals("dd-mm-yyyy"))
			{
				patientVO.setPatDOB("");
			}
			//End:Sheeldarshi:21stOct'14
			patientVO.setApprovedBy(_fb.getPatRMOEmployee());
			EpisodeVO episodeVO[]= SplRegistrationDATA.registerSplPatient(patientVO, arrEpisodeVO, userVO,_request);
			if(_fb.getIsActualDob().equals("1"))
			_fb.setPatAge(patientVO.getPatAge());

			//By Mukund on 25.09.2017
			//calling barcode slip content creation method
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.EPISODE_FOR_BARCODE, episodeVO);
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATVO_FOR_BARCODE, patientVO);
			BarcodeGenerationUTIL.saveAndCreateBarcodeSlipPrintingDetails(_request, episodeVO, patientVO);
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, episodeVO);
			_mp.put(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, episodeVO);
			
			 String tmpFileName=RegistrationConfig.CARD_SPECIAL_CLINIC+userVO.getSeatId();
			 String str,strBillPrintDiv="";

			 //str=NewPatientRegistrationSlip.print(preapareSlip(episodeVO,patientVO,_fb, _request), tmpFileName,_request,"NR");
			 for(int i=0;i<episodeVO.length;i++)
			 {
				 episodeVO[i].setExpiryDate(episodeVO[i].getEpisodeExpiryDate());
			 }

			/**to decrypt the aadhaar number*/
			if(patientVO.getPatNationalId()!=null && !patientVO.getPatNationalId().equals(""))
			{
				String strPatAadhaarNo =NewRegistrationDATA.getAadhaarDecrypted(patientVO.getPatNationalId(), patientVO.getPatCrNo());
				patientVO.setPatNationalId(strPatAadhaarNo.split("#@#")[0]);
				System.out.println(strPatAadhaarNo.split("#@#")[0]+"\n"+strPatAadhaarNo.split("#@#")[1]+"\n"+strPatAadhaarNo.split("#@#")[2]);
			}
			/***/
			//Calling QRCode Creation 
				QRCodeTest objQRCode = new QRCodeTest();
				String dataQR =  "{\"crno\":\""+patientVO.getPatCrNo()+"\", \"uid\":\""+patientVO.getPatNationalId()+"\", \"uhid\":\""+patientVO.getPatSecUHID()+"\", \"mobileNo\":\""+patientVO.getPatAddMobileNo()+"\", "
						+ "\"patCategoryCode\":\""+patientVO.getPatPrimaryCatCode()+"\", \"name\":\"Patient Full Name\", \"nameArray\":[\""+patientVO.getPatFirstName()+"\",\" "+patientVO.getPatMiddleName()+"\",\" "+patientVO.getPatLastName()+"\"], "
						+ "\"gender\":\""+patientVO.getPatGenderCode()+"\", \"dob\"=\""+patientVO.getPatDOB()+"\", \"ageInYears\":\""+patientVO.getPatAgeWithUnit()+"\", \"yob\":\""+patientVO.getPatDOB()+"\", \"fatherName\":\""+patientVO.getPatGuardianName()+"\", "
						+ "\"motherName\":\""+patientVO.getPatMotherName()+"\", \"spouseName\":\""+(patientVO.getPatHusbandName().equals("")?"N/A":patientVO.getPatHusbandName())+"\", \"house\":\""+patientVO.getPatAddHNo()+"\", \"loc\":\""+patientVO.getPatAddStreet()+" "+patientVO.getPatAddCityLoc()+" "+patientVO.getPatAddCity()+"\", "
						+ "\"vtc\":\""+patientVO.getPatAddCity()+"\", \"dist\":\""+patientVO.getPatAddDistrict()+"\", \"distCode\":\""+patientVO.getPatAddDistrictCode()+"\", "
						+ "\"state\":\""+patientVO.getPatAddState()+"\", \"stateCode\":\""+patientVO.getPatAddStateCode()+"\", \"pc\":\""+patientVO.getPatAddPIN()+"\"}";
				objQRCode.createQRCode(dataQR, _request);
				
			 str=NewRegistrationSlip.print(preapareSlip(episodeVO,patientVO,_fb, _request), tmpFileName,_request,"SR");
				
			 System.out.println("PrintSlip :"+str);			
				
			//Bill Receipt Printing 
			//if((_fb.getPatAmountCollected()!=null)&&!(_fb.getPatAmountCollected().equals("0"))&&!(_fb.getPatAmountCollected().equalsIgnoreCase("0.00")))
			if(false){
				strBillPrintDiv=NewRegistrationSlip.printBillReceipt(preapareSlip(episodeVO,patientVO,_fb, _request), tmpFileName,_request,"SR");
				System.out.println("PrintBillSlip :"+strBillPrintDiv);
				//WebUTIL.setAttributeInSession(_request,"billReceiptString", strBillPrintDiv);
				_mp.put("billReceiptString", strBillPrintDiv);
				//str+="<br><br>"+strBillPrintDiv+"";
			}
			 _fb.setPrint("2");
			 WebUTIL.setMapInSession(_mp, _request, "NewSplRegwithAptmntACTION");

			System.out.println("str1 :"+str);
			objStatus.add(Status.DONE,"","<div style='display:none'><div><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientRegisteredLabel'>"+"Patient Registered "+"</div></font><font color='#000000' weight='normal'>"+str+"</font></div>");
			
			
			
		}
		}
		catch(HisDuplicateRecordException e){
			e.printStackTrace();
			exists=true;
			flag=2;
		/*	Map essentialMap=new HashMap();
			WebUTIL.setMapInSession(essentialMap, _request,"NewSplRegwithAptmntACTION");
		    WebUTIL.setAttributeInSession(_request, RegistrationConfig.ALL_PATIENT_VO_LIST,);
			//objStatus.add(Status.UNSUCESSFULL,"","Duplicate Registration Found");	
		*/}
/*		catch(HisInvalidTokenNumberException e){
			objStatus.add(Status.UNSUCESSFULL,"","INVALID TOKEN NUMBER");	
		}*/
		catch(HisUpdateUnsuccesfullException e){
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,"","Registration Failed");		
		}
		catch(HisSQLManualException e){
			objStatus.add(Status.ERROR,"","Department-Unit Limit Exhausted");
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,"","Error");
		} 	
		finally
		{
		WebUTIL.setStatus(_request,objStatus);
		}	
		return flag;
	}//end of method  saveNewPatientRegistration
	
	
	//Start:Sheeldarshi
	/*private static boolean createSaveMsgObject(Document responseDocument, String strIsSavedSuccussfulFlag,String strSavedMsg,
			String strIsFormFieldRestFlag, String strPrintDivContent, String strPrintFlag,String strIsDuplicatePatient) 
{


Element elementSaveMsg =responseDocument.createElement("savedMsgDtl");
elementSaveMsg.setAttribute("isSavedSuccussful", strIsSavedSuccussfulFlag);
elementSaveMsg.setAttribute("savedMessage", strSavedMsg);
elementSaveMsg.setAttribute("isFormFieldTobeReset", strIsFormFieldRestFlag);
elementSaveMsg.setAttribute("isPrintFlag", strPrintFlag);
elementSaveMsg.setAttribute("printDivContent", strPrintDivContent);
elementSaveMsg.setAttribute("isDuplicatePatientPopup", strIsDuplicatePatient);

responseDocument.getFirstChild().appendChild(elementSaveMsg);

return true;
}*/
	//End:Sheeldarshi
	
	//create string which will be printed on jsp after successful registration of patient
public static String setPatientRegistrationDetail(EpisodeVO[]episodeVO,PatientVO patientVO){
		String str="";
		 String[] strQueueNo=new String[episodeVO.length];
		 String[] strDeptName=new String[episodeVO.length];
		 String[] strUnitName=new String[episodeVO.length];
		 String[] strRoomName=new String[episodeVO.length];
		 String[] strFileName=new String[episodeVO.length];
		 String[] strUnitConsultant=new String[episodeVO.length];
		 
		 for(int i=0;i<episodeVO.length;i++){
		     strQueueNo[i]=episodeVO[i].getQueNo();
		     strDeptName[i]=episodeVO[i].getDepartment();
		     strUnitName[i]=episodeVO[i].getDepartmentUnit();
		     strRoomName[i]=episodeVO[i].getRoom();
		     strUnitConsultant[i]=episodeVO[i].getUnitConsultant();
		   //  strFileName[i]=episodeVO[i].getFileNo();
		 }
		 if(patientVO.getPatMiddleName()==null)
			 patientVO.setPatMiddleName("");
		 if(patientVO.getPatLastName()==null)
			 patientVO.setPatLastName("");
		 str +="<br>"+ "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> " +"Name"+ "&nbsp;&nbsp; ::"+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</b></font>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+patientVO.getPatFirstName().toUpperCase()+ " "+ patientVO.getPatMiddleName().toUpperCase()+" "+patientVO.getPatLastName().toUpperCase()+"</font>"+ "<br>";
		 str +="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> " +"CR NO"+" ::"+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"</b></font>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+episodeVO[0].getPatCrNo()+"</font>";
		 str += "<table width='50%'><tr><td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " +  "Dept/Unit Name::"+"</font></td>";
		// if(RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILE_NO_GENRATION_MANNUAL_TRUE) ||RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILE_NO_GENRATION_AUTO_TRUE) )
		//	 str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Queue No::"+"</font></td>";
		 //str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Unit No::"+"</font></td>";
		 //str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Room No::"+"</font></td>";
		 //str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " + "Queue No::"+"</font><br></td></tr></table>";
		 str += "</tr></table>";
		 
			 
		 for(int i=0;i<episodeVO.length;i++){
		     
		   str += "<table width='50%'><tr>"+"<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strDeptName[i]+" / "+strUnitName[i]+"</font>"+"</td>";
		  
		//   if(RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILE_NO_GENRATION_MANNUAL_TRUE) ||RegistrationConfig.FILE_NO_GENERATION_FLAG.equals(RegistrationConfig.FILENO_GENRATION_TYPE_AUTO) )
		//		 str += "<td width='25%'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> " +strFileName[i]+"</font></td>";
		   //str += "<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strUnitName[i]+"</font>"+"</td>";
		   //str += "<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strRoomName[i]+"</font>"+"</td>";            
		   //str += "<td width='25%'>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+strQueueNo[i]+"</font>"+"<br>"+"</td>"+"</tr>"+"</table>";
		   str += "</tr>"+"</table>";
		   
		 } 
		return str;
	}
	
	
	
	
	public static void setEmployeeDetailsForRegistration(HttpServletRequest _request, newSplRegFB _fb){
		EmpMasterVO empVO=null;
		HospitalMstVO hospitalVO=getHospitalVO(_request);
		EmpMasterVO[] empMstVO=(EmpMasterVO[])_request.getSession().getAttribute(RegistrationConfig.EMPLOYEE_MASTER_VO_ARRAY);
		if(empMstVO!=null){
			if(empMstVO!=null){
				empVO=empMstVO[Integer.parseInt(_fb.getEmpIdChk())];
			}
		HelperMethods.populate(_fb, empVO);
		if((_fb.getPatAddStateCode()!=null) && (_fb.getPatAddStateCode().equals(hospitalVO.getState())))
		{
			_fb.setPatAddDistrictCode(_fb.getPatAddDistrictCodeHidden());
			_fb.setPatAddCity(_fb.getPatAddCityHidden());
			_fb.setPatAddPIN(_fb.getPatAddPINHidden());
			_fb.setPatIsUrban(_fb.getPatIsUrbanHidden());
		}
		
		
		} 
	}
	
	
	
	/*public static void saveAsNewPatientRegistration(HttpServletRequest request,	newSplRegFB fb) {
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		EpisodeVO []arrEpisodeVO=(EpisodeVO[])session.getAttribute(RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR);
		PatientVO patientVO=(PatientVO)session.getAttribute(RegistrationConfig.REGISTRATIONDESK_PATIENT_VO);
		List <PatientVO> allPatientVOList=(ArrayList)session.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST);
		try{
			UserVO userVo=getUserVO(request);
			userVo.setTariffID(RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
			patientVO.setPatRegCatCode(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);
			HelperMethods.populate(fb, patientVO);
			patientVO.setIsDuplicate("1");
			EpisodeVO episodeVO[]= newSplRegDATA.registerNewPatient(patientVO, arrEpisodeVO, userVo,request);	
			WebUTIL.setAttributeInSession(request,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR, episodeVO);
			//StringBuilder str=new StringBuilder();
			//int episodeVOLength=episodeVO.length;
			// String[] strQueueNo=new String[episodeVOLength];
			// String[] strDeptName=new String[episodeVOLength];
			// String[] strUnitName=new String[episodeVOLength];
			// String[] strRoomName=new String[episodeVOLength];
		//	 String[] strFileName=new String[episodeVOLength];
			// for(int i=0;i<episodeVO.length;i++){
			     //strQueueNo[i]=episodeVO[i].getQueNo();
			    // strDeptName[i]=episodeVO[i].getDepartment();
			     //strUnitName[i]=episodeVO[i].getDepartmentUnit();
			    // strRoomName[i]=episodeVO[i].getRoom();
			   //  strFileName[i]=episodeVO[i].getFileNo();
			// }
			//String str=setPatientRegistrationDetail(episodeVO, patientVO) ; // will print registration information after registration
			//objStatus.add(Status.DONE,str,"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+"Patient Registered "+"</font>");
			String tmpFileName=RegistrationConfig.CARD_NEW_REGISTRATION+userVo.getSeatId();
			//RegistrationSlip regSlip=preapareSlip(episodeVO,patientVO,fb, request);
			//String str=NewPatientRegistrationSlip.print(preapareSlip(episodeVO,patientVO,fb, request), tmpFileName,request);
			//fb.setPrint("1");
			//String str=setPatientRegistrationDetail(episodeVO, patientVO,fb,request) ; // will print registration information after registration
				
		    //objStatus.add(Status.DONE,"","<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientRegisteredLabel'>"+"Patient Registered "+"</div></font>");
				
		    
		    //Changes done for laser printing
		    
		  //RegistrationSlip regSlip=preapareSlip(episodeVO,patientVO,fb, request);
			String str=NewRegistrationSlip.print(preapareSlip(episodeVO,patientVO,fb, request), tmpFileName,request,"NR");
			fb.setPrint("1");
           // String str=setPatientRegistrationDetail(episodeVO, patientVO,_fb,_request) ; // will print registration information after registration
			System.out.println("str1 :"+str);
			objStatus.add(Status.DONE,str,"<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><div id='divPatientRegisteredLabel'>"+"Patient Registered "+"</div></font>");
			
			
	

		}
		catch(HisInvalidTokenNumberException e){
			objStatus.add(Status.UNSUCESSFULL,"","INVALID TOKEN NUMBER");	
		}
		catch(HisUpdateUnsuccesfullException e){
			objStatus.add(Status.UNSUCESSFULL,"","Update Unsuccessful");	
		}	
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,"","Registration Failed");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,"","Error");
		}
		
		finally
		{
			 WebUTIL.setStatus(request,objStatus);
		}
	}
		*/ 
	 
		
	
	public static void setAllNewRegistrationEssentialsForDuplicate(newSplRegFB _fb,HttpServletRequest _request){	
		Status objStatus=new Status();
		try{
			UserVO userVO=getUserVO(_request);
			HospitalMstVO hospitalVO=getHospitalVO(_request);
			userVO.setModuleId(Config.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
			
			_fb.setIsAddressDelhi("1");
			_fb.setPatAddStateCode(hospitalVO.getState());	 	
			_fb.setPatAddCountryCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			_fb.setPatIsUrban(RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE);
			_fb.setFileNo(new String[]{""});	
			_fb.setIsDuplicatePatientPopup("1");
			//_fb.setConfigFlag(Config.CLIENT);
			//NewSplRegUTIL.setDeptOptions(_request, _fb);
			//isRegistrationAllowed(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL,getUserVO(_request));
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE, "","Application Execution Error");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	
	public static void printLastOpdCard(newSplRegFB _fb,HttpServletRequest _request){	
		
		Status objStatus=new Status();
		try{
			UserVO userVO=getUserVO(_request);
			String tmpFileName=RegistrationConfig.CARD_SPECIAL_CLINIC+userVO.getSeatId();
			tmpFileName+="opdCard";
			boolean fileExists=false;
			File f=null;
			if(HisFileControlUtil.isWindowsOS()){
				f=new File(System.getProperties().getProperty("java.io.tmpdir")+ tmpFileName+".dat");
				if(f.exists())
					fileExists=true;	
			}
			else{
				f=new File("/root/"+tmpFileName+".dat");
				if(f.exists())
					fileExists=true;
			}
				
			if(fileExists){
				String clientOS=NewRegistrationSlip.getClientSystemOsType(_request);
				HisPrinterSupport.printSlip(_request.getRemoteHost(),tmpFileName+".dat",clientOS,"HISPRINTER");
				objStatus.add(Status.DONE,"","Card Printed Successfully");
			}
			else
				objStatus.add(Status.DONE);	
		}
		catch(Exception e){
			objStatus.add(Status.ERROR);
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			
		}	
	}
	
	/**
	 * List All the Patients in the Special Clinic Registration with Appointment
	 * retrieves a list of patients registered in the last specified duration
	 * 
	 * @param _request - HttpServletRequest
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public static void setAllSpecialCRNoWithAppointment(HttpServletRequest _request){
		Status objStatus=new Status();
		 PatientVO patientVO[]=null;
		 Map mp=new HashMap();
		try{
			 UserVO userVO =getUserVO(_request);
			 setSysdate(_request);
			
			//To Check the Cash Collection Back Date Day End Flag Check, Added by Singaravelan on 03-Jun-2015
				System.out.println("Back Date Day End Check Flag :::value :::"+userVO.getCheckBackDateDayEndFlag());			
				if(userVO.getCheckBackDateDayEndFlag().equals("1"))
					throw new HisRecordNotFoundException("Please do your day end first !");
				if(userVO.getCheckBackDateDayEndFlag().equals("2"))
					throw new HisRecordNotFoundException("Please deposit the money first at cash collection counter !");
				
			 //checking request is from which desk
			 
			 ////this value is set in SpecialClinicRegDeskUTIL for special clinic
			 String deskType=(String)_request.getAttribute(RegistrationConfig.REGISTRATION_DESK_TYPE);
			 String episodeVisitType;
			 if(deskType==null)
				 deskType="";
			 if(deskType.equalsIgnoreCase(RegistrationConfig.REGISTRATION_DESK_TYPE_SPECIAL))
				 episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL;
			 else
				 episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
			 
 			  patientVO=SplRegistrationDATA.getCRNoWithAppointment(userVO,episodeVisitType);
 			  //Start: Sheeldarshi: 30thSep'14
 			  mp=SplRegistrationDATA.getRegistrationFormInitialEssentials(userVO);
 			  mp.put(RegistrationConfig.ALL_PATIENT_VO_LIST,patientVO);
 			  mp.put(RegistrationConfig.PATIENT_VO_LIST,patientVO);
 			  //By Mukund to populate the deptToSearchFrom Dropdown
 			 mp.put("deptToSearchFrom", mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT));
 			  
 			  WebUTIL.setMapInSession(mp,_request,"NewSplRegwithAptmntACTION");
 			 //End: Sheeldarshi: 30thSep'14
 			  
		//WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,patientVO);
		//WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,patientVO);
		objStatus.add(Status.LIST,"","");	
		   }
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			//objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			objStatus.add(Status.ERROR_DA,"","<div id='divNoAppointmentAvailableLabel'>"+e.getMessage()+"</div>");
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
	}
	
	/**
	 * To Populate the Form bean with the selected Appointment No Details 
	 * Added by Singaravelan on 28-Aug-2014
	 */
	
	public static newSplRegFB setVoEssentials(newSplRegFB _fb,HttpSession _ses,String _aptNo)
	{
		PatientVO[] _voArr=((PatientVO[])_ses.getAttribute(RegistrationConfig.PATIENT_VO_LIST));
		
		for(PatientVO _voTemp:_voArr){			
			if(_voTemp.getPatAptNo().equals(_aptNo)){
				HelperMethods.populate(_fb, _voTemp);
				_fb.setPatAddMobileNo(_voTemp.getPatAddContactNo());
				_fb.setPatAddEmailId(_voTemp.getPatAddEmailId());
			}
		}		
		HelperMethods.setNullToEmpty(_fb);
		
		if(_fb.getPatAge().indexOf('Y')>0)
			_fb.setPatAge(_fb.getPatAge().substring(0, _fb.getPatAge().indexOf('Y')-1));
		
		if(_fb.getPatAge().indexOf('W')>0)
		{
			_fb.setPatAge(_fb.getPatAge().substring(0, _fb.getPatAge().indexOf('W')-1));
			_fb.setPatAgeUnit("Wk");
		}
		
		if(_fb.getPatAge().indexOf('M')>0)
		{
			_fb.setPatAge(_fb.getPatAge().substring(0, _fb.getPatAge().indexOf('M')-1));
			_fb.setPatAgeUnit("Mth");
		}
		
		if(_fb.getPatAge().indexOf('D')>0)
		{
			_fb.setPatAge(_fb.getPatAge().substring(0, _fb.getPatAge().indexOf('D')-1));
			_fb.setPatAgeUnit("D");
		}
		
		
		
		return _fb;
		
		
	}
	
	/**
	 * To Fetch selected Appointment No Details
	 * Added by Singaravelan on 28-Aug-2014
	 */	
	public static newSplRegFB setVoEssentialsOnAptNo(newSplRegFB _fb,HttpServletRequest _request,String _mode)
	{
		Status objStatus=new Status();
		PatientVO patientVO=new PatientVO();
		try{
			UserVO userVO =getUserVO(_request);
			setSysdate(_request);
			HelperMethods.populate(patientVO, _fb);
			patientVO=SplRegistrationDATA.getDetailsWithAppointment(patientVO,userVO,_mode);
			
			if(patientVO.getPatAge().indexOf('Y')>0)
				patientVO.setPatAge(patientVO.getPatAge().substring(0, patientVO.getPatAge().indexOf('Y')-1));
			
			HelperMethods.populatetToNullOrEmpty(_fb, patientVO);
			_fb.setPatAddMobileNo(patientVO.getPatAddContactNo());
			_fb.setPatAddEmailId(patientVO.getPatAddEmailId());
		}
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);				 
		}
		return _fb;
	}
	
	/**
	 * To Populate the Form bean with the selected Appointment No Details 
	 * Added by Singaravelan on 28-Aug-2014
	 */
	
	public static void setVoArrEssentials(HttpServletRequest _request,newSplRegFB _fb)
	{
		Status objStatus=new Status();
		HttpSession _ses=_request.getSession();
		PatientVO[] _voArr=null;
		PatientVO[] _newVoArr={};
		List<PatientVO> lstPatVo=new ArrayList<PatientVO>();
		Map mp=new HashMap();
		try{
			if(_ses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)!=null && _ses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST)!="")
				_voArr=((PatientVO[])_ses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST));
				else
					throw new HisRecordNotFoundException("No Appointments Availabe For Registration");
			for(PatientVO _voTemp:_voArr){			
				if((_fb.getSearchId().equalsIgnoreCase("1")&&_voTemp.getPatAptNo().contains(_fb.getSearchValue()))||
						(_fb.getSearchId().equalsIgnoreCase("2")&&_voTemp.getPatFirstName().toUpperCase().contains(_fb.getSearchValue().toUpperCase()))||
						(_fb.getSearchId().equalsIgnoreCase("3")&&_voTemp.getPatAddContactNo().contains(_fb.getSearchValue()))||
						(_fb.getSearchId().equalsIgnoreCase("4")&&_voTemp.getPatAddEmailId().contains(_fb.getSearchValue())))
					lstPatVo.add(_voTemp);
				
			}	
			_newVoArr=new PatientVO[lstPatVo.size()];
			if(lstPatVo.size()>0){
				for (int i = 0; i < lstPatVo.size(); i++){
					_newVoArr[i] = (PatientVO) lstPatVo.get(i);
				}
			}	
			else{
				mp.put(RegistrationConfig.PATIENT_VO_LIST,null);
				mp.put(RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
				//WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,null);
				//WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
				throw new HisRecordNotFoundException("Patient Appointment Details Not Found");
			}
			
			//By Mukund to get the search result list
			//mp.put(RegistrationConfig.PATIENT_VO_LIST,_voArr);
			mp.put(RegistrationConfig.PATIENT_VO_LIST,_newVoArr);
			
			mp.put(RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
			//WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,_newVoArr);
			WebUTIL.setMapInSession(mp, _request, "NewSplRegwithAptmntACTION");
			objStatus.add(Status.LIST,"","");
		}
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,"","<div id='divNoAppointmentAvailableLabel'>"+e.getMessage()+"</div>");
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
		
	}
	
	/**
	 * To search in the list of Patients in the Special Clinic Registration with Appointment
	 * retrieves a list of patients registered in the last specified duration
	 * 
	 * @param _request - HttpServletRequest
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public static void searchSpecialCRNoWithAppointment(HttpServletRequest _request,newSplRegFB _fb){
		Status objStatus=new Status();
		 PatientVO patientVO[]=null;
		 PatientVO _patVO=new PatientVO();
		 Map _mp=new HashMap();
		try{
			 UserVO userVO =getUserVO(_request);
			 setSysdate(_request);			 
			 HelperMethods.populate(_patVO, _fb);
			 
			 if(_patVO.getSearchId().equalsIgnoreCase("1"))
					 _patVO.setSearchId("HAPNUM_APT_REQ_NO");
			 else if(_patVO.getSearchId().equalsIgnoreCase("2"))
					 _patVO.setSearchId("HAPSTR_PAT_FIRST_NAME");
			 else if(_patVO.getSearchId().equalsIgnoreCase("3"))
					 _patVO.setSearchId("HAPSTR_MOBILE_NO");	
			 else if(_patVO.getSearchId().equalsIgnoreCase("4"))
				 _patVO.setSearchId("HAPSTR_EMAIL");	
				 
			 patientVO=SplRegistrationDATA.searchSpecialCRNoWithAppointment(_patVO,userVO);	
			 _mp.put(RegistrationConfig.PATIENT_VO_LIST,patientVO);
		
	      WebUTIL.setMapInSession(_mp, _request, "NewSplRegwithAptmntACTION");	 
		//WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,patientVO);
		objStatus.add(Status.LIST,"","");	
		   }
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
	}
	
	
	private static void setNotMandatoryDefaultCmbValue(newSplRegFB objNewPatRegSupp_p){
	
		objNewPatRegSupp_p.setPatMaritalStatusCode(objNewPatRegSupp_p.getPatMaritalStatusCode().equals("-1")?"":objNewPatRegSupp_p.getPatMaritalStatusCode());	
		objNewPatRegSupp_p.setPatCasteCode(objNewPatRegSupp_p.getPatCasteCode().equals("-1")?"":objNewPatRegSupp_p.getPatCasteCode());
		objNewPatRegSupp_p.setPatReligionCode(objNewPatRegSupp_p.getPatReligionCode().equals("-1")?"":objNewPatRegSupp_p.getPatReligionCode());
		objNewPatRegSupp_p.setPatOccupation(objNewPatRegSupp_p.getPatOccupation().equals("-1")?"":objNewPatRegSupp_p.getPatOccupation());
		objNewPatRegSupp_p.setPatIsUrban(objNewPatRegSupp_p.getPatIsUrban().equals("-1")?"":objNewPatRegSupp_p.getPatIsUrban());
		objNewPatRegSupp_p.setPatRefGnctdHospitalCode(objNewPatRegSupp_p.getPatRefGnctdHospitalCode().equals("-1")?"":objNewPatRegSupp_p.getPatRefGnctdHospitalCode());
		objNewPatRegSupp_p.setPatRefGnctdHospitalDept(objNewPatRegSupp_p.getPatRefGnctdHospitalDept().equals("-1")?"":objNewPatRegSupp_p.getPatRefGnctdHospitalDept());
		objNewPatRegSupp_p.setPatAgeUnit(objNewPatRegSupp_p.getPatAgeUnit().equals("-1")?"":objNewPatRegSupp_p.getPatAgeUnit());
		//objNewPatRegSupp_p.setPatDocType(objNewPatRegSupp_p.getPatDocType().equals("-1")?"":objNewPatRegSupp_p.getPatDocType());
		//Start:Sheeldarshi
		objNewPatRegSupp_p.setPatAddPhoneOwner(objNewPatRegSupp_p.getPatAddPhoneOwner().equals("-1")?"":objNewPatRegSupp_p.getPatAddPhoneOwner());
		//End:Sheeldarshi
		//System.out.println("getEntryLabel:  "+)
	}
	
	/**
	 * To Set the Config Related Essentials in the Special Clinic Registration with Appointment
	 * retrieves a list of Renewal Config Vo on the Patient Category wise
	 * 
	 * @param _request - HttpServletRequest
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public static Map splRegConfigEssentials(HttpServletRequest _request)
	{
		Map mp=new HashMap();
		Status objStatus=new Status();
		try{
		
    		UserVO userVO =getUserVO(_request);
		    mp=SplRegistrationDATA.getRegistrationFormRegConfigEssentials(userVO);
		    
		}	 
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);		 
		}
		
		return mp;
	}
	/* #Start				:Sheeldarshi 
	#Modify Date(CR/PRS)	:22ndMay'15 
	#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	 */
	public static void getCashCollectionDetail(HttpServletRequest request,HttpServletResponse response, newSplRegFB objPatSup_p) {
		// TODO Auto-generated method stub
		//NewPatientRegistrationSUP voObj = null;
	
		RegEssentialBO  essentialBO=new RegEssentialBO();
		BillConfigUtil bcu = null;
		try {
	
			PatientVO patientVO = new PatientVO();	
			HttpSession session=request.getSession();
			UserVO userVO=getUserVO(request);
			String strHospitalCode = session.getAttribute("HOSPITAL_CODE").toString();
			bcu = new BillConfigUtil(strHospitalCode);
			String strSeatId = session.getAttribute("SEATID").toString();
			
			
					
			patientVO.setStrValue1(strHospitalCode);
			patientVO.setStrValue2(strSeatId);
		
			
			essentialBO.getCashCollectionDetail(patientVO,userVO);
			
			if (patientVO.getStrMsgType().equals("0"))
			{
				objPatSup_p.setStrResultWs(patientVO.getGblWs1());

						
				getCashCollectionDetailView(patientVO,request);
			      
				

			} 
			else 
			{

				throw new Exception(patientVO.getStrMsgString());

			}
			
			
		}
		catch (Exception e) 
		{

			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("Global Billing File", "hisglobal.BillingDATA.getCashCollectionDetail()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (IOException e1) 
			{
				//System.out.println("Inside IInd Else::::"+e1.getMessage());
			}

		} 
		finally {

				essentialBO = null;
				//patientVO = null;
				bcu = null;
		}
	}
	public static void getCashCollectionDetailView(PatientVO voObj, HttpServletRequest _request) {
		WebRowSet ws = voObj.getGblWs1();
		
		try {

			
			if (ws != null) {
				
				if(ws.size() != 0){
					
					ws.next();
							
				
					//Double netAmount=   ws.getDouble(3)+ws.getDouble(4);
				_request.setAttribute("userName",ws.getString(1));
				_request.setAttribute("totalBill", ws.getInt(2));
				_request.setAttribute("recievedAmount", HisUtil.getAmountWithDecimal(ws.getDouble(3),2));
				_request.setAttribute("refundAmount", HisUtil.getAmountWithDecimal(Math.abs(ws.getDouble(4)),2));
				_request.setAttribute("netAmount", HisUtil.getAmountWithDecimal(ws.getDouble(5),2));
			
				System.out.println("recievedAmount"+ws.getDouble(3));
				
			} else {

				throw new Exception("Cash Collection WebRowSet is Null");

			}
			}
			} catch (Exception e) {
			
		 
			
			new HisException("Cash Collection Detail",
					"billing.HLPBilling.getCashCollectionDetailView()-->", e
							.getMessage());
		}

	}
	
	
}//end of class