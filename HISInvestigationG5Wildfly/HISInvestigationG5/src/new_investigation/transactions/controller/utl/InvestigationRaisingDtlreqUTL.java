package new_investigation.transactions.controller.utl;

import com.ibm.icu.text.SimpleDateFormat;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.Inv_RequisitionRaisingEpisodeVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

import new_investigation.InvestigationConfig;
import new_investigation.SMS.SMSHttpPostClient;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlDATA;
import new_investigation.transactions.controller.data.InvestigationRaisingDtlreqDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlreqFB;
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;




























public class InvestigationRaisingDtlreqUTL
  extends ControllerUTIL
{
  public static void setPatientRegistrationEssentials(InvestigationRaisingDtlreqFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    Map mp = new HashMap();
    List<Inv_EpisodeVO> lstEpisodeVO = new ArrayList<Inv_EpisodeVO>();
    List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
    Map<String, Map<String, List<BookMarkVO>>> mpBookMark = new LinkedHashMap<String, Map<String, List<BookMarkVO>>>();
    String accountNo = "";
    
    boolean isCategoryExpired = false;
    
    try {
      Inv_RequisitionRaisingPatientVO patVO = null;
      Inv_ictc_VO ictc = null;
      if (fb.getPatCrNo() != null && !fb.getPatCrNo().equals("")) {
        
        patVO = new Inv_RequisitionRaisingPatientVO();
        Inv_RequisitionRaisingEpisodeVO episodeVO = new Inv_RequisitionRaisingEpisodeVO();
        WebUTIL.populate(patVO, fb);
        UserVO userVO = ControllerUTIL.getUserVO(request);
        patVO.setPatCRNo(fb.getPatCrNo());

        
        String deskcalllabhide = "0";
        
        if (fb.getPatAdmNo() == null || !fb.getPatAdmNo().equals("-1"))
        {
          deskcalllabhide = "1";
        }

        
        mpBookMark = InvestigationRaisingDtlreqDATA.getBookMarkListraising(userVO, deskcalllabhide);

        
       if( request.getSession().getAttribute("setNewReqPATvoEPISODE")!=null)
       {
    	   patVO = (Inv_RequisitionRaisingPatientVO)request.getSession().getAttribute("setNewReqPATvoEPISODE") ;
       
       }
       else
       {
    	   
        patVO = InvestigationRaisingDtlreqDATA.getPatientRegistration_EpisodeDetailEssentials(patVO, userVO);
       }


        
        if (patVO != null && patVO.getPatCRNo() != null)
        {
          WebUTIL.populate(fb, patVO);

          
          if (patVO.getIsCatExpired() != null && patVO.getIsCatExpired().equals("0"))
          {


            
            if (patVO.getPatStatus().equals("IPD")) {



              if(request.getSession().getAttribute("setNewReqPATvoACCOUNTNO")!=null)
            	  accountNo = (String)request.getSession().getAttribute("setNewReqPATvoACCOUNTNO");
              else
                  accountNo = InvestigationRaisingDtlreqDATA.getAccountNo(patVO, userVO);
              
              fb.setAccountNo(accountNo);
              lstPatientAdmissionVO = InvestigationRaisingDtlreqDATA.getPatientAdmissionDetailEssentials(patVO, userVO);
              if (lstPatientAdmissionVO != null && lstPatientAdmissionVO.size() > 0) {
                WebUTIL.populate(patVO, lstPatientAdmissionVO.get(0));
                fb.setAdmissionDate(patVO.getAdmissionDate());
              
              }
            
            }
            else {
              
              lstEpisodeVO = InvestigationRaisingDtlreqDATA.getPatientEpisodeDetailEssentials(patVO, userVO);
            } 


      	 
      	             Inv_PatientAdmissionDtlVO patVOadd=null;
      	           patVOadd=new Inv_PatientAdmissionDtlVO();
      	           
      	             
                request.getSession().setAttribute("InvpatvoCharges_NEWREQPROCESS", patVO);
                request.getSession().setAttribute("InvpatvoAddCharges_NEWREQPROCESS", patVOadd);

            
            mp = InvestigationRaisingDtlreqDATA.getessentialdetailsforxray(userVO, patVO, lstPatientAdmissionVO, request);
            
        	
            
          //  Map<String, LabTestVO> lstprevreq = InvestigationRaisingDtlreqDATA.getPrvTestDtlAJAXMAPxray(fb.getPatCrNo(), userVO, "0");
            
           // WebUTIL.setAttributeInSession(request, "lsitprevreqxray", lstprevreq);


            
            WebUTIL.setAttributeInSession(request, "invPatientVO", patVO);
            System.out.println("setting in the session   ......" + patVO.getDepartmentcode());
            
            WebUTIL.setAttributeInSession(request, "listEpisodeVO", lstEpisodeVO);


            
            WebUTIL.setAttributeInSession(request, "listAdmissionVO", lstPatientAdmissionVO);

            
            WebUTIL.setAttributeInSession(request, "mapBookMarkxrayprcoess", mpBookMark);





            
            WebUTIL.setMapInSession(mp, request);

          
          }
          else
          {
            
            isCategoryExpired = true;
          }
        
        }
        else
        {
          status.add(Status.ERROR_AE, "Patient Details Not Found ", "");
        }
      
      }
      else {
        
        status.add(Status.ERROR_AE, " Please Enter CR.No: ", "");
      } 


      
      if (isCategoryExpired)
      {
        status.add(Status.ERROR_AE, " Patient Category Expired", "");
        status.add(Status.NEW, "", "");

      
      }
      else if (patVO.getPatStatus().equals("IPD"))
      {

        
        if (accountNo == null || "0".equals(accountNo)) {

          
          status.add(Status.ERROR_AE, " Patient Account does not exist", "");
          throw new HisRecordNotFoundException();
        } 

        
        status.add(Status.TRANSINPROCESS, "", "");
      }
      else
      {
        status.add(Status.TRANSINPROCESS, "", "");
      }
    
    } catch (Exception e) {
      
      status.add(Status.NEW, "", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }
  
  public static void searchLaboratoryWiseTest(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();

    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    
    try {
      session.removeAttribute("labWiseGRoupDetails");
      String testcodes = "";
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      InvestigationRaisingDtlreqDATA data = new InvestigationRaisingDtlreqDATA();
      UserVO userVO = ControllerUTIL.getUserVO(request);
      searchVO.setSearchLabName((fb.getSearchLabName() == null) ? "" : fb.getSearchLabName());
      searchVO.setSearchTestName((fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());
      searchVO.setSearchTestGroupName((fb.getSearchTestGroupName() == null) ? "" : fb.getSearchTestGroupName());
      searchVO.setSearchTestGroup((fb.getSearchTestGroup() == null) ? "" : fb.getSearchTestGroup());
      searchVO.setTstOrTestGroupValue((fb.getTstOrTestGroupValue() == null) ? "0" : fb.getTstOrTestGroupValue());
      searchVO.setTestSearchKeyword((fb.getTestSearchKeyword() == null) ? "" : fb.getTestSearchKeyword());
      searchVO.setPatientCrNo(fb.getPatCrNo());
      searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
      
      searchVO.setPatAdmNo(fb.getPatAdmNo());
      
      if (session.getAttribute("IsAddendum") != null) {
        
        String reqno = (String)session.getAttribute("reqNo");
        
        if (session.getAttribute("IsAddendumENTRY") == null) {
          testcodes = InvestigationRaisingDtlreqDATA.getlabcodesaddendum(reqno, userVO);
        } else {
          testcodes = InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno, userVO);
        } 
        
        String crno = (String)session.getAttribute("PatCrNo");
        String labCode = (String)session.getAttribute("labCode");
        String testCode = (String)session.getAttribute("testCode");
        String isAdddnudm = (String)session.getAttribute("IsAddendum");
        fb.setIsAddendum(isAdddnudm);
        fb.setLabCodee(labCode);
        fb.setTestCodee(testcodes);

        
        searchVO.setPatientCrNo(fb.getPatCrNo());
        
        if (fb.getIsAddendum() != null || fb.getIsAddendum().equals("1")) {

          
          searchVO.setIsAddendum(fb.getIsAddendum());
          searchVO.setLabCode(fb.getLabCodee());
          searchVO.setTestCode(fb.getTestCodee());
        } 
      } 

      
      if ((String)request.getSession().getAttribute("arrayLabNames") != null)
      {
        searchVO.setLabEmpty("0");
      }

      
      if ((String)request.getSession().getAttribute("arrayTestNames") != null)
      {
        searchVO.setTestEmpty("0");
      }


      
      if ((String)request.getSession().getAttribute("arrayTestCodeWise") != null)
      {
        searchVO.setTestCodeEmpty("0");
      }

      
      if ((String)request.getSession().getAttribute("userGroupCodesLst") != null)
      {
        searchVO.setGroupCodeEmpty("0");
      }
      
      mp = InvestigationRaisingDtlreqDATA.searchLabWiseTestDetails(searchVO, userVO);
      
      if (searchVO.getSearchTestGroupName() != null) searchVO.getTstOrTestGroupValue().equals("1");



      
      WebUTIL.setMapInSession(mp, request);


      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }





  
  public static void searchLaboratoryWiseTestGroupOnClick(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();

    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    try {
      String testcodes = "";
      InvestigationRaisingDtlreqDATA data = new InvestigationRaisingDtlreqDATA();
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      
      UserVO userVO = ControllerUTIL.getUserVO(request);
      searchVO.setSearchLabName((fb.getSearchLabName() == null) ? "" : fb.getSearchLabName());
      searchVO.setSearchTestName((fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());
      searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
      searchVO.setSearchTestGroup(fb.getSearchTestGroup());
      searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue());
      
      if (session.getAttribute("IsAddendum") != null) {
        
        String crno = (String)session.getAttribute("PatCrNo");
        String labCode = (String)session.getAttribute("labCode");
        String testCode = (String)session.getAttribute("testCode");
        String isAdddnudm = (String)session.getAttribute("IsAddendum");
        fb.setIsAddendum(isAdddnudm);
        fb.setLabCodee(labCode);
        fb.setTestCodee(testcodes);


        
        if (fb.getIsAddendum() != null || fb.getIsAddendum().equals("1")) {

          
          searchVO.setIsAddendum(fb.getIsAddendum());
          searchVO.setLabCode(fb.getLabCodee());
          searchVO.setTestCode(fb.getTestCodee());
        } 
      } 
      
      mp = InvestigationRaisingDtlreqDATA.searchLaboratoryWiseTestGroupOnClick(searchVO, userVO);


      
      WebUTIL.setMapInSession(mp, request);


      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }

  
  public static void deleteRow(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status objStatus=new Status();
    
    HttpSession session = WebUTIL.getSession(request);


    
    try {
      System.out.println("fb.getNewlabtestcodearray() :::::::::::::::::::::=" + fb.getNewlabtestcodearray());
      String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");
      String[] labTestCodeArraynew = fb.getNewlabtestcodearray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      List<String> lstLabTestCodeArraynew = Arrays.asList(labTestCodeArraynew);

      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      List<String> newLstLabTestCodeArray = new ArrayList<String>();
      
      boolean firstIteration = true;
      String strLabTestCodes = "";
      
      Iterator itr = lstLabTestCodeArray.iterator();
      
      while (itr.hasNext()) {
        
        Iterator itrnew = lstLabTestCodeArraynew.iterator();
        String str = (String)itr.next();
        str = str.replace(";", "#");
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[2];
        String site = "null";


        
        while (itrnew.hasNext()) {
          
          String strnew = (String)itrnew.next();
          strnew = strnew.replace(";", "#");
          if (strnew.contains("*")) {
            strnew = strnew.replace("*", "&");
          }
          String[] arrStrnew = strnew.split("#");
          String labCodeHashTestCodenew = String.valueOf(arrStrnew[0]) + "#" + arrStrnew[2];
          
          if (labCodeHashTestCodenew.equals(labCodeHashTestCode)) {
            
            site = arrStrnew[29];
            
            break;
          } 
        } 
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode)) {
          continue;
        }

        
        if (firstIteration) {
          
          if (arrStr.length > 29) {
            
            strLabTestCodes = String.valueOf(arrStr[0]) + "#" + arrStr[1] + "#" + arrStr[2] + "#" + arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5] + "#" + arrStr[6] + "#" + arrStr[7] + "#" + arrStr[8] + "#" + arrStr[9] + "#" + arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12] + "#" + arrStr[13] + "#" + arrStr[14] + "#" + arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17] + "#" + arrStr[18] + "#" + arrStr[19] + "#" + arrStr[20] + "#" + arrStr[21] + "#" + arrStr[22] + "#" + arrStr[23] + "#" + arrStr[24] + "#" + arrStr[25] + "#" + arrStr[26] + "#" + arrStr[27] + "#" + arrStr[28] + "#" + site + "#";
          } else {
            
            strLabTestCodes = String.valueOf(str) + site + "#";
          } 
          firstIteration = false;
          
          continue;
        } 
        
        if (arrStr.length > 29) {
          
          strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + arrStr[0] + "#" + arrStr[1] + "#" + arrStr[2] + "#" + arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5] + "#" + arrStr[6] + "#" + arrStr[7] + "#" + arrStr[8] + "#" + arrStr[9] + "#" + arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12] + "#" + arrStr[13] + "#" + arrStr[14] + "#" + arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17] + "#" + arrStr[18] + "#" + arrStr[19] + "#" + arrStr[20] + "#" + arrStr[21] + "#" + arrStr[22] + "#" + arrStr[23] + "#" + arrStr[24] + "#" + arrStr[25] + "#" + arrStr[26] + "#" + arrStr[27] + "#" + arrStr[28] + "#" + site + "#";
          continue;
        } 
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str + site + "#";
      } 


      
      System.out.println("strLabTestCodes= " + strLabTestCodes);
      
      WebUTIL.setAttributeInSession(request, "labTestCodeArray", strLabTestCodes);


      
      fb.setLabTestCodeArray(strLabTestCodes);
      fb.setNewlabtestcodearray(strLabTestCodes);
      
      System.out.println("fb.getNewlabtestcodearray() after processing :::::::::::::::::::::=" + fb.getNewlabtestcodearray());
      
      objStatus.add(Status.TRANSINPROCESS);
    }
    catch (HisRecordNotFoundException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisDataAccessException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisApplicationExecutionException e) {
      
      objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
      System.out.println(e.getMessage());
    }
    catch (HisException e) {
      
      objStatus.add(Status.ERROR, "", "Error");
      System.out.println(e.getMessage());
    }
    finally {
      
      WebUTIL.setStatus(request, objStatus);
    } 
  }



  
  public static void saveRequisitionDetails(InvestigationRaisingDtlreqFB _fb, HttpServletRequest _request) {
    Status objStatus=new Status();
    HttpSession session = _request.getSession();
    List bldReqIndicationDtlVOList = new ArrayList();
    String testcodess = "";

    
    String deskType = "";
    
    if (_fb.getPatAdmNo() != null && _fb.getPatAdmNo().equals(""))
    {
      deskType = "1";
    }
    
    if (_fb.getPatAdmNo() != null && !_fb.getPatAdmNo().equals("") && !_fb.getPatAdmNo().equals("-1"))
    {
      deskType = "4";
    }
    
    if (_fb.getPatAdmNo() == null || _fb.getPatAdmNo().equals("-1"))
    {
      deskType = "";
    }

    
    String cashCrNo = "";
    
    String flag = "";
    Map<String, Map<String, LabTestVO>> mp = new LinkedHashMap<String, Map<String, LabTestVO>>();
    String priorityAll = "";
    
    Inv_RequisitionRaisingPatientVO patVO = null;
    Inv_RequisitionRaisingPatientVO patVO1 = null;
    
    try {

      _fb.setCashCrNo(_fb.getPatCrNo());
      cashCrNo = _fb.getPatCrNo();
      
      Date sysdate = (Date)session.getAttribute("SYSDATEOBJECT");
      HttpSession session11 = WebUTIL.getSession(_request);

      
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm");
      Date date = new Date();
      String sysadteString = simpleDateFormat.format(date).toString();
      
      UserVO _userVO = getUserVO(_request);
      
      Object objLabTestVo = _request.getSession().getAttribute("listLabWiseTestDtls");
      List<LabTestVO> objLabTest = null;
      if (objLabTestVo != null)
      {
        objLabTest = (List)objLabTestVo;
      }

      
      List<Inv_EpisodeVO> lstPatEpisodeVO = (List)session.getAttribute("listEpisodeVO");
      
      if (lstPatEpisodeVO.size() == 0) {
        
        patVO = new Inv_RequisitionRaisingPatientVO();
        patVO1 = new Inv_RequisitionRaisingPatientVO();
        patVO1 = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
        HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
      } 

      
      for (int k = 0; k < lstPatEpisodeVO.size(); k++) {
        
        String selectedEpisodeCode = _fb.getSelectedEpisode();
        if (((Inv_EpisodeVO)lstPatEpisodeVO.get(k)).getPatepisodecode().equalsIgnoreCase(selectedEpisodeCode)) {
          
          patVO = new Inv_RequisitionRaisingPatientVO();
         
          patVO1 = new Inv_RequisitionRaisingPatientVO();
          
          patVO1 = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");

          
          WebUTIL.populate(patVO, lstPatEpisodeVO.get(k));
          HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
        } 
      } 









      
      priorityAll = _fb.getPrioriotytype();




      
      patVO.setVisitReason((_fb.getNotes()==null?"":_fb.getNotes()));
      patVO.setAdvisedByDocName((_fb.getAdivcedbycode() == null) ? "" : _fb.getAdivcedbycode());
      patVO.setAdvisedByDocNo((_fb.getAdivcedbyname() == null) ? "" : _fb.getAdivcedbyname());
      
      if(_fb.getStrMLCNo()!=null &&  !_fb.getStrMLCNo().equals("") &&  !_fb.getStrMLCNo().equals("0"))
      {
    	  _fb.setStrMLCNo("1");
      }
      
      patVO.setIs_dead(_fb.getIspat_dead()==null?"":_fb.getIspat_dead());
      patVO.setIs_mlc(_fb.getStrMLCNo()==null?"":_fb.getStrMLCNo());
      patVO.setIs_newborn(_fb.getStrIsNewBorn()==null?"":_fb.getStrIsNewBorn());
      patVO.setIs_pregnant(_fb.getIs_pregnant()==null?"":_fb.getIs_pregnant());
      patVO.setIs_vip(_fb.getIspat_vip()==null?"":_fb.getIspat_vip());
      patVO.setIs_unknown(_fb.getIspat_unknown()==null?"":_fb.getIspat_unknown());
      
      patVO.setChief_complaints_code(_fb.getChief_complaints_code());
      patVO.setChief_complaints_name(_fb.getChief_complaints_name());
      patVO.setDiagnosis_code(_fb.getDiagnosis_code());
      patVO.setDiagnosis_name(_fb.getDiagnosis_name());
      //patVO.setNotes(_fb.getNotes()==null?"":_fb.getNotes());
      
    //  patVO.setAdmissionDate(_fb.getAdmissionDate()==null?"":_fb.getAdmissionDate());
      patVO.setPatCategoryCode(_fb.getPatCatCode()==null?"":_fb.getPatCatCode());
      patVO.setFollowup(_fb.getFollowup()==null?"":_fb.getFollowup());
      if (patVO != null)
      {


        
        String[] selectedLabTestCodeArray = _fb.getSelectedtestdataatrray();


        
        int labRowCount = selectedLabTestCodeArray.length;
        
        for (int i = 0; i < labRowCount; i++) {


          
          String reqDNo = "";
          String[] testCodeArray = selectedLabTestCodeArray[i].split("#");
          String labCode = testCodeArray[0];
          String labName = testCodeArray[1];
          String testCode = testCodeArray[2];
          String ispidshoww = "0";

          String instr_testwise="";
      if(_fb.getInstructiontestwise()!=null)
      {
    	  instr_testwise=_fb.getInstructiontestwise()[i];
      }




























          
          testcodess = String.valueOf(testcodess) + testCode + "#";
          
          String testName = testCodeArray[3];
          String strDefSample = "-1";






          
          String sampleCombo = testCodeArray[4];
          
          if (sampleCombo.contains("advised")) {
            flag = "1";
            reqDNo = sampleCombo.split("&")[2];
          } else {
            flag = "";
          } 
          String deskvalues = testCodeArray[26];
          String[] deskvaluessplit = deskvalues.split("\\$");
          String isOpdDesk = deskvaluessplit[0];
          String isBayDesk = deskvaluessplit[1];
          String isIpdDesk = deskvaluessplit[2];
          String islabappbased = testCodeArray[28];
          
          String sitevalue = "null";
          String samplecodewidShortname="";
          String sampleshortname="";
          String samplecodenew="";
          
          if(testCodeArray[5].equals("S"))
          {
        	  samplecodewidShortname=_fb.getSelectedsite()[i];
        	  
        	  if(samplecodewidShortname!=null && samplecodewidShortname.contains("$"))
        	  {
        		  samplecodenew=samplecodewidShortname.split("\\$")[0];
        		  sampleshortname=samplecodewidShortname.split("\\$")[1];
        	  }
        	  
          }
          else
          {
        	  sitevalue = _fb.getSelectedsite()[i];  
          }
          
          if (sitevalue.equals("null"))
          {
            sitevalue = "";
          }















          
          String raiseAdvise = "";
          
          if (!deskType.equals("")) {
            if (deskType.equals("1") || deskType.equals("12")) {
              raiseAdvise = isOpdDesk;
            } else if (deskType.equals("4")) {
              raiseAdvise = isIpdDesk;
            } else {
              raiseAdvise = isOpdDesk;
            } 
          }
          
          String testType = testCodeArray[5];
          String isAppointment = testCodeArray[6];
          String isConfidential = testCodeArray[7];
          String sampleCode1 = (testCodeArray[8] == null || testCodeArray[8].equals(null) || testCodeArray[8].equals("null") || testCodeArray[8].equals("-1")) ? strDefSample : testCodeArray[8];
          String sampleCode = "";
          if (sampleCode1 != null)
            if (sampleCode1.contains("$")) {
              sampleCode = sampleCode1.split("\\$")[0];
            } else {
              sampleCode = sampleCode1;
            }   String priority = (testCodeArray[9] == null || testCodeArray[9].equals(null) || testCodeArray[9].equals("null")) ? "1" : testCodeArray[9];
          String testGroupCode = (testCodeArray[10] == null || testCodeArray[10].equals(null) || testCodeArray[10].equals("null")) ? "0" : testCodeArray[10];
          String testGroupType = (testCodeArray[11] == null || testCodeArray[11].equals(null) || testCodeArray[11].equals("null")) ? "1" : testCodeArray[11];
          
          String isrequisitionformneeded = (testCodeArray[24] == null || testCodeArray[24].equals(null) || testCodeArray[24].equals("null")) ? "0" : testCodeArray[24];
          
          String reqsampleshortname = (testCodeArray[25] == null || testCodeArray[25].equals(null) || testCodeArray[25].equals("null")) ? "" : testCodeArray[25];

          
          String reqdSampleName = (testCodeArray[23] == null) ? "" : testCodeArray[23];
          Map<String, LabTestVO> mpTest = (Map)mp.get(labCode);


          
          if (mpTest == null) {
            
            mpTest = new LinkedHashMap<String, LabTestVO>();
            
            LabTestVO voLabTest = new LabTestVO();

            
            if (flag.equals("1")) {
              voLabTest.setAlreadyRaised("1");
            } else {
              voLabTest.setAlreadyRaised("");
            } 


           
            voLabTest.setAdvisedByDocNo((_fb.getAdivcedbyname() == null) ? "" : _fb.getAdivcedbyname());
            
            voLabTest.setIs_dead(_fb.getIs_dead()==null?"":_fb.getIs_dead());
            voLabTest.setIs_mlc(_fb.getIs_mlc()==null?"":_fb.getIs_mlc());
            voLabTest.setIs_newborn(_fb.getIs_newborn()==null?"":_fb.getIs_newborn());
            voLabTest.setIs_pregnant(_fb.getIs_pregnant()==null?"":_fb.getIs_pregnant());
            voLabTest.setIs_vip(_fb.getIs_vip()==null?"":_fb.getIs_vip());
            
            voLabTest.setChief_complaints_code(_fb.getChief_complaints_code());
            voLabTest.setChief_complaints_name(_fb.getChief_complaints_name());
            voLabTest.setDiagnosis_code(_fb.getDiagnosis_code());
            voLabTest.setDiagnosis_name(_fb.getDiagnosis_name());
            voLabTest.setInstruction_testwise(instr_testwise);
            
            voLabTest.setLabCode(labCode);
            if (ispidshoww.equals("1"))
            {
              
              voLabTest.setPiddata(_fb.getPidd());
            }
            
            voLabTest.setTestCode(testCode);
            if (testType.equals("P")) {
              voLabTest.setSampleCode("-1");
              voLabTest.setReqdSampleName("");
            } else {
            	//samplecodenew
            	voLabTest.setSampleCode(samplecodenew);
            	voLabTest.setReqdSampleName(sampleshortname);
            	
              //voLabTest.setSampleCode(sampleCode);
             // voLabTest.setReqdSampleName(reqdSampleName);
            } 
            voLabTest.setPatCrNo(_fb.getPatCrNo());
            voLabTest.setTestType(testType);
            voLabTest.setIsAppointment(isAppointment);
            voLabTest.setIslabappointmentbased(islabappbased);
            
            voLabTest.setRaiseAdvise(raiseAdvise);
            
            voLabTest.setIsConfidential(isConfidential);
            voLabTest.setPriority(priority);
            
            voLabTest.setLabName(labName);
            voLabTest.setTestName(testName);
            voLabTest.setRequisitionDNo(reqDNo);
            voLabTest.setTestGroupCode(testGroupCode);
            
            voLabTest.setTestGroupType(testGroupType);
            voLabTest.setIsRequisitionFormNeeded(isrequisitionformneeded);
            voLabTest.setReqSampleShortName(reqsampleshortname);
            
            String[] splitsysDate = sysadteString.split(" ");
            
            voLabTest.setAppointmentDate(splitsysDate[0]);
            voLabTest.setAppointmentTime(splitsysDate[1]);
            voLabTest.setFinalMandValues(_fb.getFinalMandValues());
            voLabTest.setAdvisedByDoctorName(_fb.getAdvisedByDoctorName());
            voLabTest.setAdvisedBy(_fb.getAdvisedBy());
            voLabTest.setReqDateHeaderTable(splitsysDate[0]);
            if (_fb.getFinalMandCode() != null && _fb.getFinalMandCode().equals("undefined")) {
              
              voLabTest.setFinalMandCode(_fb.getFinalMandCodeValuesOnBookmark());
            }
            else {
              
              voLabTest.setFinalMandCode(_fb.getFinalMandCode());
            } 


            
            String labbasedappt = "";
            if (session.getAttribute("labbasedapppointment") != null)
              labbasedappt = (String)session.getAttribute("labbasedapppointment"); 
            if (!labbasedappt.equals("")) {
              
              String[] appbaseddata = labbasedappt.split("#");
              
              if (labCode.equals(appbaseddata[4]))
              {
                voLabTest.setAppointmentDate(appbaseddata[2].split(" ")[0]);
                voLabTest.setAppointmentRefNo(appbaseddata[3]);
                voLabTest.setAppoitmentNo(appbaseddata[3]);
                voLabTest.setAppointmentTime(appbaseddata[2].split(" ")[1]);
                voLabTest.setLabbasedaptdatetime(appbaseddata[2]);

























              
              }


























            
            }
            else {

























              
              voLabTest.setAppoitmentNo("0");
            } 















            
            voLabTest.setAdvice(_fb.getAdvice());
            
            voLabTest.setRequisitionFormData(_fb.getRequisitionFormData());
            
            voLabTest.setSite(sitevalue);

            
            int count = 0;
            String viewcode = "";
            if (_fb.getSelectedtestviews() != null) {
              
              String[] views = _fb.getSelectedtestviews();
              
              if (_fb.getSelectedtestviews().length >= i) {
                
                String val = views[i];
                
                String[] newviews = val.split("@");
                
                for (int l = 0; l < newviews.length; l++) {

                  
                  if (val.contains(testCode)) {
                    
                    count++;
                    
                    if (viewcode.equals("")) {
                      
                      viewcode = newviews[l].split("#")[1];
                    }
                    else {
                      
                      viewcode = String.valueOf(viewcode) + "," + newviews[l].split("#")[1];
                    } 
                  } 
                } 
              } 
            } 


            
            voLabTest.setViewscount(Integer.toString(count));
            voLabTest.setViewCode(viewcode);


            
            mpTest.put(testCode, voLabTest);
            
            mp.put(labCode, mpTest);
          
          }
          else {

            
            LabTestVO voLabTest = new LabTestVO();



            
            if (flag.equals("1")) {
              voLabTest.setAlreadyRaised("1");
            } else {
              voLabTest.setAlreadyRaised("");
            } 
            voLabTest.setRaiseAdvise(raiseAdvise);


            
            voLabTest.setLabCode(labCode);
            
            if (ispidshoww.equals("1"))
            {
              
              voLabTest.setPiddata(_fb.getPidd());
            }
            
            voLabTest.setTestCode(testCode);
            if (testType.equals("P")) {
              voLabTest.setSampleCode("-1");
              voLabTest.setReqdSampleName("");
            } else {
              
            	voLabTest.setSampleCode(samplecodenew);
            	voLabTest.setReqdSampleName(sampleshortname);
            	
            	
							/*
							 * voLabTest.setSampleCode(sampleCode);
							 * voLabTest.setReqdSampleName(reqdSampleName);
							 */
            } 
            voLabTest.setPatCrNo(_fb.getPatCrNo());
            voLabTest.setTestType(testType);
            voLabTest.setIsAppointment(isAppointment);
            voLabTest.setIslabappointmentbased(islabappbased);
            
            voLabTest.setIsConfidential(isConfidential);
            voLabTest.setPriority(priority);
            
            voLabTest.setLabName(labName);
            voLabTest.setTestName(testName);
            voLabTest.setRequisitionDNo(reqDNo);
            
            voLabTest.setTestGroupCode(testGroupCode);
            
            voLabTest.setTestGroupType(testGroupType);
            voLabTest.setIsRequisitionFormNeeded(isrequisitionformneeded);
            voLabTest.setReqSampleShortName(reqsampleshortname);
            String[] splitsysDate = sysadteString.split(" ");
            
            voLabTest.setAppointmentDate(splitsysDate[0]);
            voLabTest.setAppointmentTime(splitsysDate[1]);
            
            voLabTest.setReqDateHeaderTable(splitsysDate[0]);
            voLabTest.setFinalMandValues(_fb.getFinalMandValues());
            
            voLabTest.setFinalMandCode(_fb.getFinalMandCode());
            voLabTest.setAdvisedByDoctorName(_fb.getAdvisedByDoctorName());
            voLabTest.setAdvisedBy(_fb.getAdvisedBy());
            
            
              voLabTest.setAdvisedByDocNo((_fb.getAdivcedbyname() == null) ? "" : _fb.getAdivcedbyname());
            
            voLabTest.setIs_dead(_fb.getIs_dead()==null?"":_fb.getIs_dead());
            voLabTest.setIs_mlc(_fb.getIs_mlc()==null?"":_fb.getIs_mlc());
            voLabTest.setIs_newborn(_fb.getIs_newborn()==null?"":_fb.getIs_newborn());
            voLabTest.setIs_pregnant(_fb.getIs_pregnant()==null?"":_fb.getIs_pregnant());
            voLabTest.setIs_vip(_fb.getIs_vip()==null?"":_fb.getIs_vip());
            
            voLabTest.setChief_complaints_code(_fb.getChief_complaints_code());
            voLabTest.setChief_complaints_name(_fb.getChief_complaints_name());
            voLabTest.setDiagnosis_code(_fb.getDiagnosis_code());
            voLabTest.setDiagnosis_name(_fb.getDiagnosis_name());
            voLabTest.setInstruction_testwise(instr_testwise);
            String labbasedappt = "";
            if (session.getAttribute("labbasedapppointment") != null)
              labbasedappt = (String)session.getAttribute("labbasedapppointment"); 
            if (!labbasedappt.equals("")) {
              
              String[] appbaseddata = labbasedappt.split("#");
              
              if (labCode.equals(appbaseddata[4]))
              {
                voLabTest.setAppointmentDate(appbaseddata[2].split(" ")[0]);
                voLabTest.setAppointmentRefNo(appbaseddata[3]);
                voLabTest.setAppoitmentNo(appbaseddata[3]);
                voLabTest.setAppointmentTime(appbaseddata[2].split(" ")[1]);
                voLabTest.setLabbasedaptdatetime(appbaseddata[2]);


              
              }



            
            }
            else {

























              
              voLabTest.setAppoitmentNo("0");
            } 
















            
            voLabTest.setAdvice(_fb.getAdvice());
            voLabTest.setRequisitionFormData(_fb.getRequisitionFormData());
            
            voLabTest.setSite(sitevalue);
            int count = 0;
            String viewcode = "";
            
            if (_fb.getSelectedtestviews() != null) {
              
              String[] views = _fb.getSelectedtestviews();
              
              if (_fb.getSelectedtestviews().length >= i) {
                
                String val = views[i];
                
                String[] newviews = val.split("@");
                
                for (int l = 0; l < newviews.length; l++) {

                  
                  if (val.contains(testCode)) {
                    
                    count++;

                    
                    if (viewcode.equals("")) {
                      
                      viewcode = newviews[l].split("#")[1];
                    }
                    else {
                      
                      viewcode = String.valueOf(viewcode) + "," + newviews[l].split("#")[1];
                    } 
                  } 
                } 
              } 
            } 


            
            voLabTest.setViewscount(Integer.toString(count));
            voLabTest.setViewCode(viewcode);
            
            mpTest.put(testCode, voLabTest);
          } 
        } 


        
        List listReqdtlId = InvestigationRaisingDtlreqDATA.saveRequisitionDetails(mp, patVO, _userVO, priorityAll, session);
        
        String check = InvestigationRaisingDtlreqDATA.getBillingCheck(_userVO);
        
        System.out.println("check1111::::" + check);

        
        System.out.println("alltestcodes:::::::::::::::::::::::::::" + patVO.getPatwardcode() + "::::::::::::::" + patVO.getPatStatus() + "::::::::::::::::" + testcodess);

        
        if (patVO.getPatStatus() != null && patVO.getPatStatus().equalsIgnoreCase("ipd")) {
          
          float pi = 0.0F;
          
          int requisitionTypeForBilling = 0;
          
          if (patVO != null && patVO.getPatStatus().equals("IPD"))
          {
            requisitionTypeForBilling = 2;
          }
          
          String req = Integer.toString(requisitionTypeForBilling);
          
          for (int k = 0; k < testcodess.split("#").length; k++) {

            
            String testcode = testcodess.split("#")[k];
            String rates = InvestigationRaisingDtlreqDATA.getBillingChecktestcode(testcode, patVO.getPatwardcode(), req, patVO.getPatCategoryCode(), _userVO);
            
            if (rates.contains("^"))
            {
              rates = rates.split("\\^")[0];
            }

            
            float number = Float.parseFloat(rates);
            
            pi += number;
            
            System.out.println("rupeeeeeeee::testcode:" + testcode + "::" + pi);
          } 

          
          System.out.println("rupeeeeeeee:total rupeeee" + pi);

          
          if (patVO.getPatMobileNo() != null && pi != 0.0F) {
            
            String message = "Dear " + patVO.getPatFirstName().trim() + ",\n" + "You investigations of Rs " + pi + " has been raised and deducted from your account." + "\n" + "Now you can move for Sample Collection." + "\n" + "AIIMS Patna";
           // SMSHttpPostClient.sendSMS(patVO.getPatMobileNo(), message);
          } 
        } 



        _fb.setCashCrNo(patVO.getPatCRNo());
        cashCrNo = _fb.getPatCrNo();




















        
        if (listReqdtlId.size() <= 0)
        {
          objStatus.add(Status.DONE, "Some error ocuured.Please try again.", 
              "");
        
        }
        else
        {
          
          StringBuilder str = new StringBuilder();
          for (int i = 0; i < 1; i++) {
            
            String saveString = (String)listReqdtlId.get(i);
            String[] arrSaveString = saveString.split("#");
            
            str.append("<br>");

            
            str.append("<font style='color:black' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
            str.append(" Requisition Raised Successfully for Patient CR Number::</font>");

            
            str.append("<font style='color:red' color='red' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
            str.append(String.valueOf(arrSaveString[0]) + "</font>");

		/*
						 * if (!arrSaveString[3].equals("2")) {
						 * 
						 * if (!check.equals("0")) { if (_fb.getCasualitydesk() != null &&
						 * !_fb.getCasualitydesk().equals("")) { str.
						 * append("<font color='#FF0000' size='3' face='Verdana, Arial, Helvetica, sans-serif'>"
						 * ); str.
						 * append("<div id='billingid'><a   href='#' onclick='showCashCollection()' class='forgetPwd' style='font-size: 18px;'><b><u>Go To Cash Collection Desk</u></b></a></div></font>"
						 * );
						 * 
						 * }
						 * 
						 * 
						 * } } else if (!check.equals("0")) {
						 * 
						 * if (!patVO.getPatStatus().equalsIgnoreCase("ipd") &&
						 * !_fb.getCasualitydesk().equals("")) {
						 * 
						 * str.
						 * append("<font color='#FF0000' size='3' face='Verdana, Arial, Helvetica, sans-serif'>"
						 * ); str.
						 * append("<div id='billingid'><a   href='#' onclick='showCashCollection()' class='forgetPwd' style='font-size: 18px;'><b><u>Go To Cash Collection Desk</u></b></a></div></font>"
						 * ); } }
						 */ 

            
            if(!_userVO.getHospitalCode().equals("21917") && !patVO.getPatStatus().equalsIgnoreCase("ipd"))
            {
                str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
                str.append("<div id='billingid'><a   href='#' onclick='showCashCollection()' class='forgetPwd' style='font-size: 14px;'><b><u>Go To Cash Collection Desk</u></b></a></div></font>");
            
            }















            
            if (session.getAttribute("IsAddendum") != null) {

              
              System.out.println("msgg:" + str.toString());
              session.setAttribute("addendumStatusFromRaising", str.toString());
              
              if (_fb.getDeletedtestdataviaresultentry() != null && !_fb.getDeletedtestdataviaresultentry().equals("")) {

                
                InvestigationSearchVO searchVO = new InvestigationSearchVO();
                searchVO.setDeletedtestdataviaresultentry(_fb.getDeletedtestdataviaresultentry());
                mp = InvestigationRaisingDtlreqDATA.deleteReqDtll(searchVO, _userVO, null);
              } 
            } 
          } 



          
          objStatus.add(Status.DONE, str.toString(), 
              "");
        
        }
      
      }
    
    }
    catch (HisRecordNotFoundException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      if (session.getAttribute("IsAddendum") != null)
      {

        
        session.setAttribute("addendumStatusFromRaising", e.getMessage());
      }
      System.out.println(e.getMessage());
    }
    catch (HisDataAccessException e) {
      
      if (session.getAttribute("IsAddendum") != null)
      {

        
        session.setAttribute("addendumStatusFromRaising", e.getMessage());
      }
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisApplicationExecutionException e) {
      
      if (session.getAttribute("IsAddendum") != null)
      {

        
        session.setAttribute("addendumStatusFromRaising", e.getMessage());
      }
      objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
      System.out.println(e.getMessage());
    }
    catch (HisException e) {
      
      if (session.getAttribute("IsAddendum") != null)
      {

        
        session.setAttribute("addendumStatusFromRaising", e.getMessage());
      }
      objStatus.add(Status.ERROR, "", "Error");
      System.out.println(e.getMessage());
    
    }
    finally {
      
      WebUTIL.setStatus(_request, objStatus);
    } 
  }













































































  
  public static void searchBookMark(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		  
		ControllerUTIL.setSysdate(request);
		Date dt= (Date)request.getSession().getAttribute(InvestigationConfig.SYSDATEOBJECT); 
		WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
		
		
		
		
		
		searchVO.setSearchLabName("");
		searchVO.setSearchTestName(fb.getSearchTestName());
		
		searchVO.setBookMarkCode(fb.getBookMarkCode());
		
		//logic For Offline Appoitment Based Test Details
		searchVO.setTestCode(fb.getAptTestCode());
		searchVO.setLabCode(fb.getAptLabCode());
		
String offlineAptDateAndTime=fb.getOfflineAppoitMentDate();
		
		//Logic to check whether the lab/test is already present in the list
		String[] OfflineAppoitmentDtl=offlineAptDateAndTime.split("@");
		
String hidAotNo=fb.getHidAptNo();
		
		//Logic to check whether the lab/test is already present in the list
		String[] hideAptNoDtl=hidAotNo.split("`");
		
		
		int i=0;
		
		//Resetting the LabCodeArray ; for selected lab types
		//fb.setLabTestCodeArray("");
		
		String labTestCodeArray=fb.getLabTestCodeArray();
		String newlabtestcodearray=fb.getNewlabtestcodearray();
		//Logic to check whether the lab/test is already present in the list
		String[] labTestCatalogue=labTestCodeArray.split("@");
		
		Set<String> setLabCatalogue=new HashSet<String>();
		if(labTestCatalogue!=null && labTestCatalogue.length>0)
		{
			for(String str:labTestCatalogue)
			{
				if(!str.equals("")&&str!=null)
				{
					String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
					setLabCatalogue.add(labCodeHashTestCode);
				}
			}
		}
			
		mp=InvestigationRaisingDtlreqDATA.searchBookMark(searchVO, userVO);
		
		List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS_FOR_BOOKMARK);
		
		
		
		if(lstLabTestVO!=null)
		for(LabTestVO vo:lstLabTestVO)
		{
			
			if(OfflineAppoitmentDtl!=null&&!OfflineAppoitmentDtl.equals("")&&searchVO.getBookMarkCode().equals(""))
			{
			vo.setOfflineAppoitMentDate(OfflineAppoitmentDtl[i]);
		    vo.setHideAptNo(hideAptNoDtl[i]);
			}
		    
			vo.setBookMarkCode(fb.getBookMarkCode());
			
		
			//added by prashant
			String appendString=null;
			String newappendString=null;
			if(!(vo.getTestGroupCode()==null) && !vo.getTestGroupCode().equals("-1")) {
				appendString=makeAppendStringC(vo,true);      //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
				
				newappendString=makeAppendString1C(vo,true);
		}
		else {
			appendString=makeAppendStringC(vo,false);      //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
			
			newappendString=makeAppendString1C(vo,false);
		}
		
			
					
					//  String appendString=makeAppendString(vo,false); 
					//  String newappendString=makeAppendString1(vo,false);
					 
			
			String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
			//Add only those Lab/Tests which are not present in the list
			if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
			{
				if(labTestCodeArray.equals(""))
				{
					labTestCodeArray=appendString;
					newlabtestcodearray=newappendString;
				}
					else
					{
					labTestCodeArray=labTestCodeArray+"@"+appendString;
					newlabtestcodearray=newlabtestcodearray+"@"+newappendString;
					}
					}
			i++;
		}
		fb.setLabTestCodeArray(labTestCodeArray);
		fb.setNewlabtestcodearray(newlabtestcodearray);
		//Resetting LabCode,TestCode,SampleCode Arrays
		String[] strNull=null;
		
		fb.setLabCode(strNull);	
		fb.setTestCode(strNull);	
		fb.setSampleInfo(strNull);
		
		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	




  
  public static StringBuffer deleteLabTestCodeArray(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    
    try {
      System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::=" + fb.getLabTestCodeArray());
      String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      List<String> newLstLabTestCodeArray = new ArrayList<String>();
      
      boolean firstIteration = true;
      String strLabTestCodes = "";
      
      Iterator itr = lstLabTestCodeArray.iterator();
      while (itr.hasNext()) {
        
        String str = (String)itr.next();
        str = str.replace(";", "#");
        
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[2];
        
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode)) {
          continue;
        }

        
        if (firstIteration) {
          
          strLabTestCodes = str;
          firstIteration = false;
        } 
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str;
      } 

      
      System.out.println("strLabTestCodes= " + strLabTestCodes);
      
      fb.setLabTestCodeArray(strLabTestCodes);

      
      sbAjaxRes.append(strLabTestCodes);



    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }









  
  public static StringBuffer modifyLabTestCodeArray(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    
    try {
      System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::=" + fb.getLabTestCodeArray());
      String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      
      System.out.println("strLabTestCodes= " + lstLabTestCodeArray);
      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      String tmpSampleCode = fb.getTmpSampleCode();
      
      String sampleshortname = fb.getReqSampleShortName();


      
      String strLabTestCodes = "";
      boolean firstIteration = true;
      
      for (String str : lstLabTestCodeArray) {
        
        str = str.replace(";", "#");
        
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[2];
        
        String site = "null";
        if (arrStr[5].equalsIgnoreCase("p"))
        {
          site = "1";
        }
        
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
        {
          
          str = String.valueOf(arrStr[0]) + "#" + arrStr[1] + "#" + arrStr[2] + "#" + arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5] + "#" + arrStr[6] + "#" + arrStr[7] + "#" + tmpSampleCode + "#" + arrStr[9] + "#" + arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12] + "#" + arrStr[13] + "#" + arrStr[14] + "#" + arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17] + "#" + arrStr[18] + "#" + arrStr[19] + "#" + arrStr[20] + "#" + arrStr[21] + "#" + arrStr[22] + "#" + arrStr[23] + "#" + arrStr[24] + "#" + sampleshortname + "#" + arrStr[26] + "#" + arrStr[27] + "#" + arrStr[28] + "#" + site + "#";
        }

        
        if (firstIteration) {
          
          strLabTestCodes = str;
          firstIteration = false;
          continue;
        } 
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str;
      } 
      
      System.out.println("strLabTestCodes= " + strLabTestCodes);

      
      fb.setNewlabtestcodearray(strLabTestCodes);

      
      sbAjaxRes.append(strLabTestCodes);



    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }



  
  public static StringBuffer modifyLabTestCodeArrayForAppoitmentNo(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    
    try {
      System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::=" + fb.getLabTestCodeArray());
      String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      String aptNo = fb.getAppoitmentNo();
      
      String strLabTestCodes = "";
      boolean firstIteration = true;
      
      for (String str : lstLabTestCodeArray) {
        
        str = str.replace(";", "#");
        
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[2];
        
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
        {
          
          str = String.valueOf(arrStr[0]) + "#" + arrStr[1] + "#" + arrStr[2] + "#" + arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5] + "#" + arrStr[6] + "#" + arrStr[7] + "#" + arrStr[8] + "#" + arrStr[9] + "#" + arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12] + "#" + arrStr[13] + "#" + arrStr[14] + "#" + arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17] + "#" + arrStr[18] + "#" + aptNo + arrStr[20] + arrStr[21] + arrStr[22] + arrStr[23];
        }

        
        if (firstIteration) {
          
          strLabTestCodes = str;
          firstIteration = false;
          continue;
        } 
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str;
      } 
      
      System.out.println("strLabTestCodes= " + strLabTestCodes);
      
      fb.setLabTestCodeArray(strLabTestCodes);

      
      sbAjaxRes.append(strLabTestCodes);



    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }








  
  public static StringBuffer modifyPriority(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    
    try {
      System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::=" + fb.getNewlabtestcodearray());
      String[] labTestCodeArray = fb.getNewlabtestcodearray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      String tmpPriority = fb.getTmpPriority();
      
      String sampleshortname = fb.getReqSampleShortName();
      
      String strLabTestCodes = "";
      boolean firstIteration = true;
      
      for (String str : lstLabTestCodeArray) {
        
        str = str.replace(";", "#");
        
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[2];
        
        String site = "null";
        if (arrStr[5].equalsIgnoreCase("p"))
        {
          site = "1";
        }

        
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
        {

          
          str = String.valueOf(arrStr[0]) + "#" + arrStr[1] + "#" + arrStr[2] + "#" + arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5] + "#" + arrStr[6] + "#" + arrStr[7] + "#" + arrStr[8] + "#" + tmpPriority + "#" + arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12] + "#" + arrStr[13] + "#" + arrStr[14] + "#" + arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17] + "#" + arrStr[18] + "#" + arrStr[19] + "#" + arrStr[20] + "#" + arrStr[21] + "#" + arrStr[22] + "#" + arrStr[23] + "#" + arrStr[24] + "#" + sampleshortname + "#" + arrStr[26] + "#" + arrStr[27] + "#" + arrStr[28] + "#" + site + "#";
        }


        
        if (firstIteration) {
          
          strLabTestCodes = str;
          firstIteration = false;
          continue;
        } 
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str;
      } 
      
      System.out.println("strLabTestCodes= " + strLabTestCodes);

      
      fb.setNewlabtestcodearray(strLabTestCodes);

      
      sbAjaxRes.append(strLabTestCodes);



    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }









  
  public static StringBuffer pouplateTestCombo(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    HttpSession session = objRequest_p.getSession();
    String strMsgText = "";
    String strTestCombo = "";
    Map mp = new HashMap();
    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String labCode = fb.getTmpLabCode();
      objRequest_p.getSession().setAttribute("a", strTestCombo);


      
      mp = InvestigationRaisingDtlreqDATA.getTestComboAJAXMAP(labCode, userVO);
      WebUTIL.setMapInSession(mp, objRequest_p);
      String lstSampleAccepted = (String)mp.get("arrayTestNameAJAX");
      sbAjaxRes.append(lstSampleAccepted);
    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }











































  
  public static void searchTestGroup(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
	try{
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
		
		//Resetting the LabCodeArray ; for selected lab types
		//fb.setLabTestCodeArray("");
		
		String labTestCodeArray=fb.getLabTestCodeArray();
		
		//Logic to check whether the lab/test is already present in the list
		String[] labTestCatalogue=labTestCodeArray.split("@");
		
		Set<String> setLabCatalogue=new HashSet<String>();
		if(labTestCatalogue!=null && labTestCatalogue.length>0)
		{
			for(String str:labTestCatalogue)
			{
				if(!str.equals("")&&str!=null)
				{
					String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
					setLabCatalogue.add(labCodeHashTestCode);
				}
			}
		}
			
		mp=InvestigationRaisingDtlreqDATA.searchTestGroup(searchVO,userVO);
		
		List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
		
		for(LabTestVO vo:lstLabTestVO)
		{
			String appendString=makeAppendString(vo,true);     //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
			
			String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
			//Add only those Lab/Tests which are not present in the list
			if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
			{
				if(labTestCodeArray.equals(""))
					labTestCodeArray=appendString;
				else
					labTestCodeArray=labTestCodeArray+"@"+appendString;
			}
			
		}
		fb.setLabTestCodeArray(labTestCodeArray);
		
		//Resetting LabCode,TestCode,SampleCode Arrays
		String[] strNull=null;
		
		fb.setLabCode(strNull);	
		fb.setTestCode(strNull);	
		fb.setSampleInfo(strNull);
		
		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		status.add(Status.TRANSINPROCESS, "", "");
	}
	catch(Exception e){
		status.add(Status.ERROR_AE,"Application Execution Exception", "");
		e.printStackTrace();
	}
	finally{
		WebUTIL.setStatus(request, status);
	}
	}

  
  public static StringBuffer pouplateTestGroup(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    HttpSession session = objRequest_p.getSession();
    String strMsgText = "";
    String strTestCombo = "";
    Map mp = new HashMap();
    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String labCode = fb.getTmpLabCode();
      strTestCombo = InvestigationRaisingDtlreqDATA.getTestGroupAJAX(labCode, userVO);
      sbAjaxRes.append(strTestCombo);
    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }



  
  public static StringBuffer getUserTestCodeVal(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    HttpSession session = objRequest_p.getSession();
    Map<String, LabTestVO> mapUserCodeLabTestVO = (Map)session.getAttribute("userCodeWiseLabTestDetails");
    String isTestGroup = ((InvestigationRaisingDtlFB)objRequest_p.getAttribute("InvestigationRaisingDtlFB")).getIsTestGroup();
    
    String strMsgText = "";
    String strTestCombo = "";
    Map mp = new HashMap();
    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String userTestCode = fb.getUserTestCode();
      LabTestVO voLabTest = (LabTestVO)mapUserCodeLabTestVO.get(userTestCode);
      strTestCombo = InvestigationRaisingDtlreqDATA.getStringToAddToRowAJAX(isTestGroup, voLabTest);
      sbAjaxRes.append(strTestCombo);

    
    }
    catch (Exception e) {
      
      strMsgText = "---> " + e.getMessage();
    } 







    
    return sbAjaxRes;
  }

  
  public static StringBuffer getRemarksForUnavailableTest(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    HttpSession session = objRequest_p.getSession();
    Map<String, String> mapUnavailableTestCode = (Map)session.getAttribute("userCodeWiseTestAvailibilityDetails");
    
    String strMsgText = "";
    String remarks = "1";
    Map mp = new HashMap();
    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String userTestCode = fb.getUserTestCode();
      
      if (mapUnavailableTestCode.containsKey(userTestCode))
      { remarks = (String)mapUnavailableTestCode.get(userTestCode);


        
        if (remarks == null) {
          sbAjaxRes.append("0");
        } else {
          sbAjaxRes.append(remarks);
        }  }
      else { sbAjaxRes.append(remarks); }

    
    }
    catch (Exception e) {
      
      strMsgText = "---> " + e.getMessage();
    } 







    
    return sbAjaxRes;
  }

  
  public static void getTestsBasedOnGroups(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
			searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			String labTestCodeArray=fb.getLabTestCodeArray();
			
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=InvestigationRaisingDtlreqDATA.getTestsBasedOnGroups(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				 
				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				vo.setTestGroupType("1");
				String appendString=makeAppendString(vo,true);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
						labTestCodeArray=appendString;
					else
						labTestCodeArray=labTestCodeArray+"@"+appendString;
				}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
  
  
  public static void searchTest(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
			searchVO.setBookMarkCode(fb.getBookMarkCode());
			
			
			
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			String labTestCodeArray=fb.getLabTestCodeArray();
			
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=InvestigationRaisingDtlreqDATA.searchBookMark(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_LAB_WISE_TEST_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				String appendString=makeAppendString(vo,false);   //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+"0"+"#"+"1"; //as is not group based test   //(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode());
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
						labTestCodeArray=appendString;
					else
						labTestCodeArray=labTestCodeArray+"@"+appendString;
				}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	
  
  public static String makeAppendString(LabTestVO vo, boolean isGroupBased) {
    String appendString = "";
    
    if (isGroupBased) {
      
      appendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + vo.getSampleComboStr() + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + ((vo.getTestGroupCode() == null) ? "0" : vo.getTestGroupCode()) + "#" + ((vo.getTestGroupType() == null) ? "0" : vo.getTestGroupType()) + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == null) ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == null) ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#";
    }
    else {
      
      appendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + vo.getSampleComboStr() + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + "0" + "#" + "0" + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#";
    } 


    
    return appendString;
  }


  
  public static String makeAppendStringC(LabTestVO vo, boolean isGroupBased) {
    String appendString = "";
    
    if (isGroupBased) {



      
      appendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + vo.getSampleComboStr() + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + ((vo.getTestGroupCode() == null) ? "0" : vo.getTestGroupCode()) + "#" + null + "#" + vo.getIsMandatoryReq() + "#" + null + "#" + ((vo.getOfflineAppoitMentDate() == null) ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "##" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + "0" + "#" + "0" + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#";
    } else {
      
      appendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + vo.getSampleComboStr() + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + "0" + "#" + "0" + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#";
    } 


    
    return appendString;
  }

  
  public static String makeAppendString1(LabTestVO vo, boolean isGroupBased) {
    String appendString = "";
    
    String newappendString = "";
    
    if (vo.getTestCode().equals("12779"))
    {
      System.out.println("match ");
    }
    
    String site = "null";
    if (vo.getTestType().equalsIgnoreCase("p"))
    {
      site = "1";
    }
    
    if (isGroupBased) {

      
      String combostr = "sample not carry";
      
      newappendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + combostr + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + ((vo.getTestGroupCode() == null) ? "0" : vo.getTestGroupCode()) + "#" + ((vo.getTestGroupType() == null) ? "0" : vo.getTestGroupType()) + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == null) ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == null) ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#" + site + "#";


    
    }
    else {


      
      String combostr = "sample not carry";
      
      newappendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + combostr + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + "0" + "#" + "0" + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#" + site + "#";
    } 

    
    return newappendString;
  }

  
  public static String makeAppendString1C(LabTestVO vo, boolean isGroupBased) {
    String appendString = "";
    
    String newappendString = "";
    
    if (vo.getTestCode().equals("12779"))
    {
      System.out.println("match ");
    }
    
    String site = "null";
    if (vo.getTestType().equalsIgnoreCase("p"))
    {
      site = "1";
    }
    
    if (isGroupBased) {

      
      String combostr = "sample not carry";




      
      newappendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + combostr + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + ((vo.getTestGroupCode() == null) ? "0" : vo.getTestGroupCode()) + "#" + null + "#" + vo.getIsMandatoryReq() + "#" + null + "#" + ((vo.getOfflineAppoitMentDate() == null) ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "##" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + "0" + "#" + "0" + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#" + site + "#";


    
    }
    else {


      
      String combostr = "sample not carry";
      
      newappendString = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + combostr + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSampleCode()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + "0" + "#" + "0" + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
        vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#" + site + "#";
    } 

    
    return newappendString;
  }


  
  public static void getAptBasedTest(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    Map mp = new HashMap();
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);
      searchVO.setSearchLabName((fb.getSearchLabName() == null) ? "" : fb.getSearchLabName());
      searchVO.setSearchTestName((fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());

      
      mp = InvestigationRaisingDtlreqDATA.getAptBasedTest(searchVO, userVO);
      
      WebUTIL.setMapInSession(mp, request);


      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }



  
  public static void getTestsCodeWiseDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			String testcodes="";
			InvestigationRaisingDtlreqDATA data=new InvestigationRaisingDtlreqDATA();
			UserVO userVO=ControllerUTIL.getUserVO(request);
		//	searchVO.setSearchTestGroupName(fb.getSearchTestGroupName());
			//searchVO.setSearchTestGroup(fb.getSearchTestGroup());
			
			searchVO.setTestCodeWise(fb.getTestCodeWise());
			//Resetting the LabCodeArray ; for selected lab types
			//fb.setLabTestCodeArray("");
			
			
			if(session.getAttribute("IsAddendum")!=null)
			{
		       String crno=	(String) session.getAttribute("PatCrNo");
		       String labCode=	(String) session.getAttribute("labCode");
		       String testCode=	(String) session.getAttribute("testCode");
		       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
		       fb.setIsAddendum(isAdddnudm);
		       fb.setLabCodee(labCode);
		       fb.setTestCodee(testcodes);
		       
			
			
			if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
			{
				
				searchVO.setIsAddendum(fb.getIsAddendum());
				searchVO.setLabCode(fb.getLabCodee());
				searchVO.setTestCode(fb.getTestCodee());
			}
			}	
			String labTestCodeArray=fb.getLabTestCodeArray();
			String newlabTestCodeArray=fb.getNewlabtestcodearray();
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=InvestigationRaisingDtlreqDATA.getTestsCodeWiseDtl(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_TEST_CODE_WISE_DTLS);
			
			for(LabTestVO vo:lstLabTestVO)
			{
				String []labDetails=new String[vo.getIsLabAvailable().split("#").length];
				labDetails=vo.getIsLabAvailable().split("#");
				if(labDetails[1].equals("1")){
					if(labDetails[0].equals("1"))
					{
						
					}
					else{
						status.add(Status.CODE_TRANS_INPROCESS,"Lab Time Out,Can not Add Test From "+vo.getLabName()+" Lab");
						break;
					}
					
					
					//labDetails 0-is LabAvailable ,1-is TimeBound
				}
				vo.setTestGroupCode(searchVO.getSearchTestGroupName());
				vo.setSearchTestGroup(searchVO.getSearchTestGroup());
				//vo.setTestGroupType("1");
				// add instructionpat in vo by chandan on 15th-july-2016
				String appendString=makeAppendString(vo,false);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType()+"#"+vo.getInstructionPat);
				
				String appendString1=makeAppendString1(vo,false); 
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
					{
						labTestCodeArray=appendString;
						newlabTestCodeArray=appendString1;
					}
						else
						{
						labTestCodeArray=labTestCodeArray+"@"+appendString;
						newlabTestCodeArray=newlabTestCodeArray+"@"+appendString1;
						}
					
					}
				
			}
			fb.setLabTestCodeArray(labTestCodeArray);
			fb.setNewlabtestcodearray(newlabTestCodeArray);
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	   


  
  public static int checkBillDtl(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    int billDetail = 0;
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setDelLabCode(fb.getDelLabCode());
      searchVO.setDelTestCode(fb.getDelTestCode());
      searchVO.setRequisitionNo(fb.getRequisitionNo());


      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      billDetail = InvestigationRaisingDtlreqDATA.checkBillDtl(searchVO, userVO, lstPatVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return billDetail;
  }

  
  public static void deleteReqDtl(InvestigationRaisingDtlreqFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);
      
      String isbilledornot = request.getParameter("isbilledornot");

      
      searchVO.setDelLabCode(fb.getDelLabCode());
      searchVO.setDelTestCode(fb.getDelTestCode());
      searchVO.setRequisitionNo(fb.getRequisitionNo());
      searchVO.setIsbilledornot(isbilledornot);
      if (fb.getGroupraisedalready() != null && !fb.getGroupraisedalready().equals("0")) {
        searchVO.setGroupcode(fb.getGroupraisedalready());
      }
      
      searchVO.setTotalreqviewcount((fb.getTotalreqviewcount() == null) ? "" : fb.getTotalreqviewcount());
      searchVO.setTotalreqviewtyp((fb.getTotalreqviewtyp() == null) ? "" : fb.getTotalreqviewtyp());
      searchVO.setTotalviewcount((fb.getTotalviewcount() == null) ? "" : fb.getTotalviewcount());






      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      mp = InvestigationRaisingDtlreqDATA.deleteReqDtl(searchVO, userVO, lstPatVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }



  
  public static StringBuffer checkRequisitionPending(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    String strTestCombo = "";
    
    try {
      UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
      
      InvestigationSearchVO searchVO = new InvestigationSearchVO();

      
      searchVO.setDupLabCode(fb.getDupLabCode());
      searchVO.setDupTestCode(fb.getDupTestCode());
      searchVO.setPatientCrNo(fb.getPatCrNo());
      
      boolean isTempSamplePresent = InvestigationRaisingDtlreqDATA.checkRequisitionPending(searchVO, voUser);
      strTestCombo = InvestigationRaisingDtlreqDATA.getreqStatusAJAX(searchVO, voUser);
      
      sbAjaxRes.append(isTempSamplePresent);
      sbAjaxRes.append(",");
      sbAjaxRes.append(strTestCombo);

    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }


  
  public static void getAptByDesk(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    Map mp = new HashMap();
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);
      searchVO.setSearchLabName((fb.getSearchLabName() == null) ? "" : fb.getSearchLabName());
      searchVO.setSearchTestName((fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());
      
      searchVO.setFromDate(fb.getFromDate());
      searchVO.setToDate(fb.getToDate());
      
      mp = InvestigationRaisingDtlreqDATA.getAptByDesk(searchVO, userVO);
      
      WebUTIL.setMapInSession(mp, request);


      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }


  
  public static void getAppointment(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    HttpSession session = request.getSession();
    UserVO _userVO = getUserVO(request);
    Map mp = new HashMap();
    boolean flag = false;
    try {
      List<LabTestVO> lstPatientVO = null;
      List<String> reqList = new ArrayList<String>();
      
      LabTestVO labTestVO = new LabTestVO();
      
      lstPatientVO = (List)session.getAttribute("lstAptByDesk");

      
      String selectedCheckBoxValue = fb.getSelectedCheckbox();
      
      String[] arrSelectedRequisitions = selectedCheckBoxValue.split("@");
      
      String crNo = arrSelectedRequisitions[0].split("#")[0];
      String reqNO = arrSelectedRequisitions[0].split("#")[1];
      
      for (LabTestVO objSampleCollectionVO : lstPatientVO) {
        
        if (flag)
          break;  if (objSampleCollectionVO.getPatCrNo().equals(crNo) && objSampleCollectionVO.getReqNo().equals(reqNO)) {

          
          WebUTIL.setAttributeInSession(request, "selectedForAppointment", objSampleCollectionVO);
          labTestVO = objSampleCollectionVO;
          flag = true;
          
          break;
        } 
      } 
      
      mp = InvestigationRaisingDtlreqDATA.getAppointment(reqNO, crNo, _userVO);




      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }

















  
  public static void saveAppointmentDetails(InvestigationRaisingDtlFB _fb, HttpServletRequest _request) {
    Status objStatus=new Status();
    HttpSession session = _request.getSession();
    List<LabTestVO> lstLabTestVO = new ArrayList<LabTestVO>();
    
    Map<String, Map<String, LabTestVO>> mp = new LinkedHashMap<String, Map<String, LabTestVO>>();
    
    try {
      Date sysdate = (Date)session.getAttribute("SYSDATEOBJECT");
      String sysadteString = WebUTIL.getCustomisedSysDate(sysdate, "dd-MMM-yyyy hh:mm");
      UserVO _userVO = getUserVO(_request);

      
      LabTestVO patVO = (LabTestVO)session.getAttribute("selectedForAppointment");


      
      String[] selectedReqDnoArray = _fb.getAllotAppointment();
      int rowCount = selectedReqDnoArray.length;

      
      for (int i = 0; i < rowCount; i++) {



        
        String[] array = selectedReqDnoArray[i].split("#");

        
        String reqNo = array[0];
        String crNo = array[1];
        String reqDNo = array[2];
        String labCode = array[3];
        String testCode = array[4];
        String priorityCode = array[5];
        String patType = array[6];
        String groupType = array[7];
        String testGroupCode = array[8];
        String isAppointment = array[9];
        String sampleCode = array[10];

        
        LabTestVO voLabTest = new LabTestVO();


        
        voLabTest.setLabCode(labCode);
        voLabTest.setTestCode(testCode);
        voLabTest.setRequisitionDNo(reqDNo);
        voLabTest.setPatCrNo(crNo);
        voLabTest.setReqNo(reqNo);
        voLabTest.setPatType(patType);
        voLabTest.setPriority(priorityCode);
        voLabTest.setTestGroupType(groupType);
        voLabTest.setTestGroupCode(testGroupCode);
        voLabTest.setIsAppointment(isAppointment);
        voLabTest.setSampleCode(sampleCode);

        
        String[] splitsysDate = sysadteString.split(" ");

        
        voLabTest.setAppointmentDate(splitsysDate[0]);
        voLabTest.setAppointmentTime(splitsysDate[1]);

        
        if (voLabTest.getIsAppointment().equals("1")) {
          
          voLabTest.setAppointmentRefNo(_fb.getAppointmentRefNo());
          voLabTest.setAppoitmentNo(_fb.getAppointmentRefNo());
          voLabTest.setAppointmentDate(_fb.getAppointmentDate());
          voLabTest.setAppointmentTime(_fb.getAppointmentTime());
        
        }
        else {
          
          voLabTest.setAppoitmentNo("0");
        } 





        
        lstLabTestVO.add(voLabTest);
      } 





      
      List listReqdtlId = InvestigationRaisingDtlreqDATA.saveAppointmentDetails(lstLabTestVO, patVO, _userVO);
      
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < listReqdtlId.size(); i++) {
        
        String saveString = (String)listReqdtlId.get(i);
        String[] arrSaveString = saveString.split("#");
        
        str.append("<br>");

        
        str.append("<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> ");
        str.append(" Requisition Raised Successfully for Patient CR Number::</font>");

        
        str.append("<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>");
        str.append(String.valueOf(arrSaveString[0]) + "</font>");
      } 














      
      objStatus.add(Status.DONE, str.toString(), 
          "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>Requisition Raised</font>");




    
    }
    catch (HisRecordNotFoundException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisDataAccessException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisApplicationExecutionException e) {
      
      objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
      System.out.println(e.getMessage());
    }
    catch (HisException e) {
      
      objStatus.add(Status.ERROR, "", "Error");
      System.out.println(e.getMessage());
    }
    finally {
      
      WebUTIL.setStatus(_request, objStatus);
    } 
  }



  
  public static void getAppointmentDetailsOnClickGO(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    String arrayString = "";
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    try {
      UserVO userVO = ControllerUTIL.getUserVO(request);
      LabTestVO voLabTest = new LabTestVO();
      voLabTest.setPatCrNo(fb.getPatCrNo());


      
      mp = InvestigationRaisingDtlreqDATA.getAppointmentDetailsOnClickGO(voLabTest, userVO);

      
      List<LabTestVO> lstLabTestVO = (List)mp.get("detailsOnClick");
      
      if (lstLabTestVO != null) {
        for (LabTestVO vo : lstLabTestVO) {

          
          arrayString = String.valueOf(arrayString) + vo.getLabCode() + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + "advised1" + "&" + vo.getSampleName() + "&" + vo.getRequisitionDNo() + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + vo.getSampleCode() + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + "0" + "#" + "0" + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#";
          
          arrayString = String.valueOf(arrayString) + "@";
        } 
      }

      
      if (!arrayString.equals("")) {
        arrayString = arrayString.substring(0, arrayString.length() - 1);
      }

      
      fb.setLabTestCodeArray(arrayString);


      
      WebUTIL.setMapInSession(mp, request);


      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }


  
  public static void deleteGroupRow(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status objStatus=new Status();
    
    HttpSession session = WebUTIL.getSession(request);

    
    try {
      System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::=" + fb.getLabTestCodeArray());
      String[] labTestCodeArray = fb.getLabTestCodeArray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      List<String> newLstLabTestCodeArray = new ArrayList<String>();
      
      boolean firstIteration = true;
      String strLabTestCodes = "";
      
      Iterator itr = lstLabTestCodeArray.iterator();
      while (itr.hasNext()) {
        
        String str = (String)itr.next();
        str = str.replace(";", "#");
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[10];
        
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode)) {
          continue;
        }

        
        if (firstIteration) {
          
          strLabTestCodes = str;
          firstIteration = false;
          
          continue;
        } 
        
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str;
      } 


      
      System.out.println("strLabTestCodes= " + strLabTestCodes);
      
      fb.setLabTestCodeArray(strLabTestCodes);
      
      objStatus.add(Status.TRANSINPROCESS);
    }
    catch (HisRecordNotFoundException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisDataAccessException e) {
      
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisApplicationExecutionException e) {
      
      objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
      System.out.println(e.getMessage());
    }
    catch (HisException e) {
      
      objStatus.add(Status.ERROR, "", "Error");
      System.out.println(e.getMessage());
    }
    finally {
      
      WebUTIL.setStatus(request, objStatus);
    } 
  }




  
//to get tests based on user group codes
	public static void getGroupCodeWiseDtl(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		Map mp=new HashMap();
		try{
			InvestigationSearchVO searchVO=new InvestigationSearchVO();
			UserVO userVO=ControllerUTIL.getUserVO(request);
			String testcodes="";
			InvestigationRaisingDtlreqDATA data=new InvestigationRaisingDtlreqDATA();
			searchVO.setUserGroupCodeWise(fb.getUserGroupCodeWise());
			String labTestCodeArray=fb.getLabTestCodeArray();
			String newlabtestcodearray=fb.getNewlabtestcodearray();
			
			if(session.getAttribute("IsAddendum")!=null)
			{
		       String crno=	(String) session.getAttribute("PatCrNo");
		       String labCode=	(String) session.getAttribute("labCode");
		       String testCode=	(String) session.getAttribute("testCode");
		       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
		       fb.setIsAddendum(isAdddnudm);
		       fb.setLabCodee(labCode);
		       fb.setTestCodee(testcodes);
		       
			
			
			if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
			{
				
				searchVO.setIsAddendum(fb.getIsAddendum());
				searchVO.setLabCode(fb.getLabCodee());
				searchVO.setTestCode(fb.getTestCodee());
			}
			}	
			//Logic to check whether the lab/test is already present in the list
			String[] labTestCatalogue=labTestCodeArray.split("@");
			
			Set<String> setLabCatalogue=new HashSet<String>();
			if(labTestCatalogue!=null && labTestCatalogue.length>0)
			{
				for(String str:labTestCatalogue)
				{
					if(!str.equals("")&&str!=null)
					{
						String labCodeHashTestCode=str.split("#")[0]+"#"+str.split("#")[2];// labCode#testCode
						setLabCatalogue.add(labCodeHashTestCode);
					}
				}
			}
				
			mp=InvestigationRaisingDtlreqDATA.getGroupCodeWiseDtl(searchVO,userVO);
			
			List<LabTestVO> lstLabTestVO=(List<LabTestVO>)mp.get(InvestigationConfig.LIST_GROUP_CODE_WISE_DTLS);
			
			if(lstLabTestVO!=null)
			{
			for(LabTestVO vo:lstLabTestVO)
			{
				 
			String []labDetails=new String[vo.getIsLabAvailable().split("#").length];
			labDetails=vo.getIsLabAvailable().split("#");
			if(labDetails[1].equals("1")){
				if(labDetails[0].equals("1"))
				{
					
				}
				else{
					status.add(Status.TRANSINPROCESS, "","Lab Time Out,Can not Add Test From "+vo.getLabName()+" Lab");
					break;
				}
				
				
				//labDetails 0-is LabAvailable ,1-is TimeBound
			}
				vo.setTestGroupType("1");
				String appendString=makeAppendString(vo,true);       //vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+vo.getSampleCode()+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+(vo.getTestGroupType()==null?"0":vo.getTestGroupType());
				
				String newappendString=makeAppendString1(vo,true);
				
				if(vo.getIspidshow()!=null && vo.getIspidshow().equals("1"))
				{
					
					session.setAttribute("pidsetforgrpcase", "1");
				}
				
				String tmpLabCodeHashTestCode=vo.getLabCode()+"#"+vo.getTestCode();
				//Add only those Lab/Tests which are not present in the list
				if(!setLabCatalogue.contains(tmpLabCodeHashTestCode))
				{
					if(labTestCodeArray.equals(""))
					{
						labTestCodeArray=appendString;
						newlabtestcodearray=newappendString;
					}
						else
						{
							labTestCodeArray=labTestCodeArray+"@"+appendString;
							newlabtestcodearray=newlabtestcodearray+"@"+newappendString;
							
						}
						
				}
				
			}

			}
			
			
			Inv_RequisitionRaisingPatientVO patVO=null;

			patVO= (Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			int requisitionTypeForBilling=0;
			String patcatcode=patVO.getPatCategoryCode();
			String wardcode="";
               
			   if(patVO.getPatStatusCode().equals("2"))
				{
				   wardcode=patVO.getPatwardcode();
					requisitionTypeForBilling=2;
				}
				else 
				{
					//visit type code 1-opd, 2,3-emergency, 4 special
					if(patVO.getPatvisittypecode()==null)
						requisitionTypeForBilling=1;
					else{
						
					
					if(patVO.getPatvisittypecode().equals("1"))
						requisitionTypeForBilling=1;
					if(patVO.getPatvisittypecode().equals("4"))
						requisitionTypeForBilling=4;
					if(patVO.getPatvisittypecode().equals("2") ||patVO.getPatvisittypecode().equals("3") )
						requisitionTypeForBilling=3;
					}
					
				}
			   
			InvestigationSearchVO searchVO1=new InvestigationSearchVO();
			UserVO userVO1=ControllerUTIL.getUserVO(request);
		 
			//String reqno=request.getParameter("requisitionNo");
			
			searchVO.setPatientCrNo(fb.getPatCrNo());
			String requisitionTypeForBilling12=Integer.toString(requisitionTypeForBilling);
			searchVO.setRequisitingtypeforbilling(requisitionTypeForBilling12);
			searchVO.setWarcode(wardcode);
			searchVO.setPatcatcode(patcatcode);
			searchVO.setUsertestcode(fb.getUserTestCode());
			searchVO.setGroupcode(fb.getUserGroupCodeWise());
			String grpdata=newlabtestcodearray;
			searchVO.setIsamountsufficientflag(grpdata.replaceAll("#", "^"));
			searchVO.setTestCode(fb.getTestCodee());
			//searchVO.setRequisitionNo(fb.getRequisitionNo());
		
			Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			
			String flag=InvestigationRaisingDtlreqDATA.issufficientbalance(searchVO,userVO,lstPatVO);
			
			if(flag.equals("1"))
			{
				fb.setLabTestCodeArray("");
				fb.setNewlabtestcodearray("");
				session.setAttribute("issuffientamountforgroup", "1");
				
			}
			
			else
			{
			//
			
			fb.setLabTestCodeArray(labTestCodeArray);
			fb.setNewlabtestcodearray(newlabtestcodearray);
			}
			
			
			//Resetting LabCode,TestCode,SampleCode Arrays
			String[] strNull=null;
			
			fb.setLabCode(strNull);	
			fb.setTestCode(strNull);	
			fb.setSampleInfo(strNull);
			
			WebUTIL.setMapInSession(mp, request);
			
			//status.add(Status.NEW, "", "");
			status.add(Status.TRANSINPROCESS, "", "");
		}
		catch(Exception e){
			status.add(Status.ERROR_AE,"Application Execution Exception", "");
			e.printStackTrace();
		}
		finally{
			WebUTIL.setStatus(request, status);
		}
	}
	
	

  
  public static StringBuffer getRequisitionFormMasterData(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    HttpSession session = objRequest_p.getSession();

    
    String strMsgText = "";
    String remarks = "1";
    String lstEpisodeVOo = "";
    Map mp = new HashMap();
    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String testCode = fb.getTestCodee();

      
      lstEpisodeVOo = InvestigationRaisingDtlreqDATA.getRequisitionFormMasterData(testCode, userVO);



      
      sbAjaxRes.append(lstEpisodeVOo);

    
    }
    catch (Exception e) {
      
      strMsgText = "---> " + e.getMessage();
    } 







    
    return sbAjaxRes;
  }


  
  public static StringBuffer pouplatePrvTestDtll(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    
    HttpSession session = objRequest_p.getSession();
    String strMsgText = "";
    String strTestCombo = "";
    Map mp = new HashMap();
    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String CrNo = fb.getRequisitionNo();


      
      if (CrNo != null)
        mp = InvestigationRaisingDtlreqDATA.getPrvTestDtlAJAXMAPP(CrNo, userVO); 
      WebUTIL.setMapInSession(mp, objRequest_p);

      
      String lstSampleAccepted = (String)mp.get("listPrvTestDtlresultentry");
      
      session.setAttribute("raisingdetailsentry", lstSampleAccepted);
      fb.setRequisitionDetailsforResultEntry(lstSampleAccepted);


    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }


  
  public static String deleteReqDtll(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    String data = "";
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setDelLabCode(fb.getDelLabCode());
      searchVO.setDelTestCode(fb.getDelTestCode());
      searchVO.setRequisitionNo(fb.getRequisitionNo());
      
      searchVO.setRequisitionNo(fb.getRequisitionNo());
      
      if (fb.getDeletedtestdataviaresultentry() != null) {
        data = fb.getDeletedtestdataviaresultentry();
      }
      data = String.valueOf(data) + fb.getDelLabCode() + "$" + fb.getDelTestCode() + "$" + fb.getRequisitionNo() + "@";
      
      fb.setDeletedtestdataviaresultentry(data);
      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");


      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    return data;
  }



  
  public static void searchLaboratoryWiseTestNEW(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();

    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    try {
      String testcodes = "";
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      InvestigationRaisingDtlreqDATA data = new InvestigationRaisingDtlreqDATA();
      UserVO userVO = ControllerUTIL.getUserVO(request);
      searchVO.setSearchLabName((fb.getSearchLabName() == null) ? "" : fb.getSearchLabName());
      searchVO.setSearchTestName((fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());
      searchVO.setSearchTestGroupName((fb.getSearchTestGroupName() == null) ? "" : fb.getSearchTestGroupName());
      searchVO.setSearchTestGroup((fb.getSearchTestGroup() == null) ? "" : fb.getSearchTestGroup());
      searchVO.setTstOrTestGroupValue((fb.getTstOrTestGroupValue() == null) ? "0" : fb.getTstOrTestGroupValue());
      searchVO.setTestSearchKeyword((fb.getTestSearchKeyword() == null) ? "" : fb.getTestSearchKeyword());
      searchVO.setPatientCrNo(fb.getPatCrNo());
      searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
      
      if (fb.getSearchTestNamelabwise() != null) {
        searchVO.setSearchTestNamelabwise(fb.getSearchTestNamelabwise());
      }
      if (session.getAttribute("IsAddendum") != null) {
        
        String reqno = (String)session.getAttribute("reqNo");
        
        if (session.getAttribute("IsAddendumENTRY") == null) {
          testcodes = InvestigationRaisingDtlreqDATA.getlabcodesaddendum(reqno, userVO);
        } else {
          testcodes = InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno, userVO);
        } 
        
        String crno = (String)session.getAttribute("PatCrNo");
        String labCode = (String)session.getAttribute("labCode");
        String testCode = (String)session.getAttribute("testCode");
        String isAdddnudm = (String)session.getAttribute("IsAddendum");
        fb.setIsAddendum(isAdddnudm);
        fb.setLabCodee(labCode);
        fb.setTestCodee(testcodes);

        
        searchVO.setPatientCrNo(fb.getPatCrNo());
        
        if (fb.getIsAddendum() != null || fb.getIsAddendum().equals("1")) {

          
          searchVO.setIsAddendum(fb.getIsAddendum());
          searchVO.setLabCode(fb.getLabCodee());
          searchVO.setTestCode(fb.getTestCodee());
        } 
      } 

      
      if ((String)request.getSession().getAttribute("arrayLabNames") != null)
      {
        searchVO.setLabEmpty("0");
      }

      
      if ((String)request.getSession().getAttribute("arrayTestNames") != null)
      {
        searchVO.setTestEmpty("0");
      }


      
      if ((String)request.getSession().getAttribute("arrayTestCodeWise") != null)
      {
        searchVO.setTestCodeEmpty("0");
      }

      
      if ((String)request.getSession().getAttribute("userGroupCodesLst") != null)
      {
        searchVO.setGroupCodeEmpty("0");
      }
      
      mp = InvestigationRaisingDtlreqDATA.searchLabWiseTestDetailsNEW(searchVO, userVO);
      
      if (searchVO.getSearchTestGroupName() == null || !searchVO.getTstOrTestGroupValue().equals("1"))
      {
        session.removeAttribute("labWiseGRoupDetails");
      }
      
      WebUTIL.setMapInSession(mp, request);


      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }


  
  public static String issufficientbalance(InvestigationRaisingDtlFB fb, HttpServletRequest request, int requisitionTypeForBilling, String patcatcode, String wardcode) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    String flag = "";
    Map mp = new HashMap();
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setPatientCrNo(fb.getPatCrNo());
      String requisitionTypeForBilling12 = Integer.toString(requisitionTypeForBilling);
      searchVO.setRequisitingtypeforbilling(requisitionTypeForBilling12);
      searchVO.setWarcode(wardcode);
      searchVO.setPatcatcode(patcatcode);
      searchVO.setUsertestcode(fb.getUserTestCode());
      searchVO.setGroupcode(fb.getUserGroupCodeWise());
      searchVO.setIsamountsufficientflag(fb.getIsamountsufficientflag());
      searchVO.setTestCode(fb.getTestCodee());

      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      flag = InvestigationRaisingDtlreqDATA.issufficientbalance(searchVO, userVO, lstPatVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return flag;
  }


  
  public static String ispidexist(InvestigationRaisingDtlFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    String flag = "";
    Map mp = new HashMap();
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setPatientCrNo(fb.getPatCrNo());
      searchVO.setIspidexist(fb.getIspidexist());






      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      flag = InvestigationRaisingDtlreqDATA.ispidexist(searchVO, userVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return flag;
  }


  
  public static String isduplicatetestraisedtoday(InvestigationRaisingDtlFB fb, HttpServletRequest request, int requisitionTypeForBilling, String patcatcode, String wardcode) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    String flag = "";
    Map mp = new HashMap();
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setPatientCrNo(fb.getPatCrNo());
      searchVO.setTestCode(fb.getTestCodee());

      
      if (fb.getUsergrpcode() != null && !fb.getUsergrpcode().equals("")) {
        
        flag = InvestigationRaisingDtlreqDATA.getgrpcode(fb.getUsergrpcode(), userVO);
        
        String grp = flag.split("#")[0];
        String lab = flag.split("#")[1];
        fb.setLabCodee(lab);
        fb.setGroupraisedalready(grp);
      } 

      
      searchVO.setLabCode(fb.getLabCodee());
      searchVO.setGroupcode(fb.getGroupraisedalready());



      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      flag = InvestigationRaisingDtlreqDATA.isduplicatetestraisedtoday(searchVO, userVO, lstPatVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return flag;
  }

  
  public static String ispidexist(InvestigationRaisingDtlFB fb, HttpServletRequest request, int requisitionTypeForBilling, String patcatcode, String wardcode) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    String flag = "";
    Map mp = new HashMap();
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setPatientCrNo(fb.getPatCrNo());
      searchVO.setTestCode(fb.getTestCodee());
      
      searchVO.setLabCode(fb.getLabCodee());
      searchVO.setGroupcode(fb.getGroupraisedalready());



      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      flag = InvestigationRaisingDtlreqDATA.ispidexist(searchVO, userVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return flag;
  }


  
  public static String AJX_IS_LAB_MANDTORY(InvestigationRaisingDtlFB fb, HttpServletRequest request, int requisitionTypeForBilling, String patcatcode, String wardcode) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    String flag = "";
    Map mp = new HashMap();
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);




      
      searchVO.setLabCode(fb.getTmpLabCode());




      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      flag = InvestigationRaisingDtlreqDATA.AJX_IS_LAB_MANDTORY(searchVO, userVO, lstPatVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return flag;
  }


  
  public static StringBuffer modifysite(InvestigationRaisingDtlFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    
    try {
      System.out.println("fb.getLabTestCodeArray():::::::::::::::::::::=" + fb.getNewlabtestcodearray());
      String[] labTestCodeArray = fb.getNewlabtestcodearray().split("@");
      
      List<String> lstLabTestCodeArray = Arrays.asList(labTestCodeArray);
      
      String tmpLabCodeHashTestCode = String.valueOf(fb.getTmpLabCode()) + "#" + fb.getTmpTestCode();
      
      String tmpsite = fb.getTmpsite();
      
      String sampleshortname = fb.getReqSampleShortName();
      
      String strLabTestCodes = "";
      boolean firstIteration = true;
      
      for (String str : lstLabTestCodeArray) {
        
        str = str.replace(";", "#");
        
        if (str.contains("*")) {
          str = str.replace("*", "&");
        }
        String[] arrStr = str.split("#");
        String labCodeHashTestCode = String.valueOf(arrStr[0]) + "#" + arrStr[2];


        
        if (tmpLabCodeHashTestCode.equals(labCodeHashTestCode))
        {

          
          str = String.valueOf(arrStr[0]) + "#" + arrStr[1] + "#" + arrStr[2] + "#" + arrStr[3] + "#" + arrStr[4] + "#" + arrStr[5] + "#" + arrStr[6] + "#" + arrStr[7] + "#" + arrStr[8] + "#" + arrStr[9] + "#" + arrStr[10] + "#" + arrStr[11] + "#" + arrStr[12] + "#" + arrStr[13] + "#" + arrStr[14] + "#" + arrStr[15] + "#" + arrStr[16] + "#" + arrStr[17] + "#" + arrStr[18] + "#" + arrStr[19] + "#" + arrStr[20] + "#" + arrStr[21] + "#" + arrStr[22] + "#" + arrStr[23] + "#" + arrStr[24] + "#" + arrStr[25] + "#" + arrStr[26] + "#" + arrStr[27] + "#" + arrStr[28] + "#" + tmpsite + "#";
        }


        
        if (firstIteration) {
          
          strLabTestCodes = str;
          firstIteration = false;
          continue;
        } 
        strLabTestCodes = String.valueOf(strLabTestCodes) + "@" + str;
      } 
      
      System.out.println("strLabTestCodes= " + strLabTestCodes);

      
      fb.setNewlabtestcodearray(strLabTestCodes);

      
      sbAjaxRes.append(strLabTestCodes);



    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return sbAjaxRes;
  }



  
  public static String setvisittypeforappointment(InvestigationRaisingDtlFB _fb, HttpServletRequest objRequest_p) {
    String visittype = "";
    String strMsgText = "";
    HttpSession session = objRequest_p.getSession();
    Inv_RequisitionRaisingPatientVO patVO = null;
    Inv_RequisitionRaisingPatientVO patVO1 = null;



    
    try {
      List<Inv_EpisodeVO> lstPatEpisodeVO = (List)session.getAttribute("listEpisodeVO");
      
      if (lstPatEpisodeVO.size() == 0) {
        
        patVO = new Inv_RequisitionRaisingPatientVO();
        patVO1 = new Inv_RequisitionRaisingPatientVO();
        patVO1 = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
        HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
      } 

      
      for (int k = 0; k < lstPatEpisodeVO.size(); k++) {
        
        String selectedEpisodeCode = _fb.getSelectedEpisode();
        if (((Inv_EpisodeVO)lstPatEpisodeVO.get(k)).getPatepisodecode().equalsIgnoreCase(selectedEpisodeCode)) {
          
          patVO = new Inv_RequisitionRaisingPatientVO();
          patVO1 = new Inv_RequisitionRaisingPatientVO();
          
          patVO1 = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");

          
          WebUTIL.populate(patVO, lstPatEpisodeVO.get(k));
          HelperMethods.populatetToNullOrEmpty(patVO, patVO1);
        } 
      } 


      
      visittype = patVO.getPatvisittypecode();

    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return visittype;
  }



  
  public static String getesttdatatestwisexrayprocess(InvestigationRaisingDtlreqFB fb, HttpServletRequest objRequest_p) {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    String finalval = "";
    
    HttpSession session = objRequest_p.getSession();

    
    try {
    	
    	String testratesdata=	(String)session.getAttribute(InvestigationConfig.ALL_TEST_RATE);
    	
    	String grptestratesdata=	(String)session.getAttribute(InvestigationConfig.ALL_GROUP_RATE);
    	
    	
    	Object objtestratesdata = new JSONParser().parse(testratesdata) ;
        JSONObject jotestratesdata = (JSONObject) objtestratesdata; 
        
        Object objgrptestratesdata = new JSONParser().parse(grptestratesdata) ;
        JSONObject jogrptestratesdata = (JSONObject) objgrptestratesdata; 
        
        Map<String,String> mp=new HashMap<String, String>();
        
        if(fb.getSelectedtestdataatrray()!=null)
        {
        	
            String[] selectedLabTestCodeArray = fb.getSelectedtestdataatrray();


            for(int k=0;k<selectedLabTestCodeArray.length;k++)
            {
            	if(!selectedLabTestCodeArray[k].equals(""))
            	{
            	String grpcode=selectedLabTestCodeArray[k].split("#")[10];
            	
            	if(grpcode!=null && !grpcode.equals(""))
            		mp.put(grpcode, "0");
            	else
            		mp.put(selectedLabTestCodeArray[k].split("#")[2], "0");
            }
            	}
            	
        	
        }
        
        
      String testlabcode = fb.getTestlabcode();
      
      String testcode = testlabcode.split("\\^")[0];
      
      if(session.getAttribute("patcrno")!=null)
      
      {
    	  
    	  String crnoo=(String) session.getAttribute("patcrno") ;
    	  
    	  if(crnoo!=null && !crnoo.equals(""))
    		  fb.setPatCrNo(crnoo);
      }
      
      String crno = fb.getPatCrNo();
      
      String labcodecode = testlabcode.split("\\^")[1];
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);

      String searchtype=null ;
      
      if(testlabcode.split("\\^").length>=3)
      {
    	  searchtype=testlabcode.split("\\^")[2];
      }
     
    
      String rates="0";
      Float totalrates=0f;
      
			/*
			 * if(searchtype.equals("2") || searchtype.equals("3")) {
			 * 
			 * rates = (String) jogrptestratesdata.get(testlabcode.split("\\^")[3]); } else
			 * { rates = (String) jotestratesdata.get(testlabcode.split("\\^")[0]); }
			 */
      
      
      Inv_RequisitionRaisingPatientVO patVO = null;
	    
	    patVO = (Inv_RequisitionRaisingPatientVO)objRequest_p.getSession().getAttribute("invPatientVO");
	    
	    if(patVO.getPatStatus()!=null && patVO.getPatStatus().equals("IPD"))
	    {
	    
      if(fb.getTotalselectedtest()!=null)
      {
      
    	  String testrates[]=fb.getTotalselectedtest().split("\\$\\$") ;
      
    	  if(testrates!=null)
    	  {
    		  
    		  for(int l=0;l<testrates.length;l++)
    		  {
    			  String tests=testrates[l];
    			  
    			  if(tests!=null )
    			  {
    				  
    				  if(tests.split("@")[1].equals("2"))
    				  { 
    					  rates = (String) jogrptestratesdata.get(tests.split("@")[0]);
    					  
    					  rates=rates.split("#")[0];
    					  
    					  if(rates.contains("^"))
    					  {
    						  rates=rates.split("\\^")[0] ;
    					  }
    					  else if(rates.contains("@"))
    					  {
    						  rates=rates.split("@")[0] ;
    					  }
    					  
    					  
    					  totalrates=totalrates+Float.parseFloat(rates);
    					 
    				  }else
    				  {
    					  rates = (String) jotestratesdata.get(tests.split("@")[0]);
    				  
    					  if(rates.contains("^"))
    					  {
    						  rates=rates.split("\\^")[0] ;
    					  }
    					  else if(rates.contains("@"))
    					  {
    						  rates=rates.split("@")[0] ;
    					  }
    					  
    					  totalrates=totalrates+Float.parseFloat(rates);
    				  }
    			  }
    		  }
    		  
    	  }
    	  
    	  
      }
      
      String flgg=getcheckbillingnew(crno, totalrates.toString(),  objRequest_p) ;
      
    		  if(flgg.equals("1"))
    		  {
    			  return "1" ;
    		  }
	    
	    }		  
      String lastreqdate = InvestigationRaisingDtlreqDATA.getlastreqdate(testcode, labcodecode, crno, userVO);
      
      String reqdate = "";
      if (lastreqdate != null && !lastreqdate.equals(""))
      {
        reqdate = lastreqdate;
      }

      
      String keyy = String.valueOf(testcode) + "#" + labcodecode;
      
      Map<String, LabTestVO> _mpp = (Map)session.getAttribute("mapalldataxrayprocess");
      
      Map<String, List<LabTestVO>> _mppviews = (Map)session.getAttribute("mapalldataxrayviewsprocess");
      
      String teriffrate = "";
      
      if (_mpp != null && _mpp.size() > 0) {

        
        Iterator itrAuto = _mpp.keySet().iterator();
        
        LabTestVO vo = (LabTestVO)_mpp.get(keyy);
        
        
        
        String strSAmpleCode = vo.getDefaultSampleCode();	//default sample code value
		String sampleComboStr="<option value='-1'>Select Value</option>";
		
		List<LabTestVO> lstLabTestSample=null;

           lstLabTestSample=InvestigationRaisingDtlreqDATA.getsamplelist( userVO);

		//filter out specific samples from all lab test samples
		int noOfSamples=0;
		String singleSampleName="";
		String singleSampleCode="";
		String reqSampleShortName="";
		for(LabTestVO sample_vo:lstLabTestSample)
		{					
			if(vo.getLabCode().equals(sample_vo.getLabCode()) && vo.getTestCode().equals(sample_vo.getTestCode()))
			{
				noOfSamples++;
				singleSampleName=sample_vo.getsName();
				reqSampleShortName=sample_vo.getReqSampleShortName();
				singleSampleCode=sample_vo.getsCode()+"$"+reqSampleShortName;
				
				if(sample_vo.getsCode().equalsIgnoreCase(strSAmpleCode))
				{
				String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
					sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"' selected>"+sample_vo.getsName()+"</option>";
					vo.setSingleSample(singleSampleCode1);

					vo.setReqdSampleName(sample_vo.getsName());
				vo.setReqSampleShortName(reqSampleShortName);
				System.out.println("this is the sample name "+vo.getReqdSampleName());
				System.out.println("this is the sample short name "+vo.getReqSampleShortName());
				
				}
				else
				{
					String	singleSampleCode1=sample_vo.getsCode()+"$"+reqSampleShortName;
					sampleComboStr=sampleComboStr+"<option value='"+singleSampleCode1+"'>"+sample_vo.getsName()+"</option>";
				}
				}
											
		}
		if(noOfSamples==1)//single sample
		{
			
			sampleComboStr="<option value='"+singleSampleCode+"' selected>"+singleSampleName+"</option>";
			vo.setSingleSample(singleSampleCode);
			vo.setReqdSampleName(singleSampleName);
			vo.setReqSampleShortName(reqSampleShortName);
			System.out.println("this is the sample name "+vo.getReqdSampleName());
			System.out.println("this is the sample short name "+vo.getReqSampleShortName());

		}
        
	//	appendString=vo.getLabCode()+"#"+vo.getLabName()+"#"+vo.getTestCode()+"#"+vo.getTestName()+"#"+vo.getSampleComboStr()+"#"+vo.getTestType()+"#"+vo.getIsAppointment()+"#"+vo.getIsConfidential()+"#"+(vo.getSampleCode()==null?vo.getSingleSample():vo.getSampleCode())+"#"+(vo.getPriority()==null?"1":vo.getPriority())+"#"+(vo.getTestGroupCode()==null?"0":vo.getTestGroupCode())+"#"+null+"#"+vo.getIsMandatoryReq()+"#"+null+"#"+(vo.getOfflineAppoitMentDate()==null?"null":vo.getOfflineAppoitMentDate())+"#"+vo.getSetMandTextBoxCombo()+"#"+vo.getMandComboTextBoxComboNames()+"#"+vo.getMandCode()+"##"+vo.getSearchTestGroup()+"#"+vo.getHideAptNo()+"#"+"0"+"#"+"0"+"#"  //islabavailable contains 2 strings with # separated. care to be taken
		//+vo.getReqdSampleName()+"#"+vo.getIsRequisitionFormNeeded()+"#"+vo.getReqSampleShortName()+"#"+vo.getDeskcallingid()+"#"+vo.getIspidshow()+"#"+vo.getIslabappointmentbased()+"#"; 

		if(searchtype!=null && !searchtype.equals(""))
		{
			
			vo.setTestGroupCode(testlabcode.split("\\^")[3]) ;
			
		}
        
        //strMsgText = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + sampleComboStr + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSingleSample()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + (vo.getTestGroupCode()==null?"0":vo.getTestGroupCode()) + "#" + null + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + 
          //vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#" + vo.getTariffTestRate() + "#" + reqdate + "#";
     
        
        strMsgText = String.valueOf(vo.getLabCode()) + "#" + vo.getLabName() + "#" + vo.getTestCode() + "#" + vo.getTestName() + "#" + sampleComboStr + "#" + vo.getTestType() + "#" + vo.getIsAppointment() + "#" + vo.getIsConfidential() + "#" + ((vo.getSampleCode() == null) ? vo.getSingleSample() : vo.getSingleSample()) + "#" + ((vo.getPriority() == null) ? "1" : vo.getPriority()) + "#" + (vo.getTestGroupCode()==null?"0":vo.getTestGroupCode()) + "#" + null + "#" + vo.getIsMandatoryReq() + "#" + ((vo.getBookMarkCode() == "") ? "null" : vo.getBookMarkCode()) + "#" + ((vo.getOfflineAppoitMentDate() == "") ? "null" : vo.getOfflineAppoitMentDate()) + "#" + vo.getSetMandTextBoxCombo() + "#" + vo.getMandComboTextBoxComboNames() + "#" + vo.getMandCode() + "#" + vo.getSearchTestGroup() + "#" + vo.getHideAptNo() + "#" + ((vo.getInstructionPat() == null) ? "NA" : vo.getInstructionPat()) + "#" + vo.getIsLabAvailable() + "#" + vo.getReqdSampleName() + "#" + vo.getIsRequisitionFormNeeded() + "#" + vo.getReqSampleShortName() + "#" + vo.getDeskcallingid() + "#" + vo.getIspidshow() + "#" + vo.getIslabappointmentbased() + "#" + vo.getTariffTestRate() + "#" + reqdate + "#" + vo.getIsanyInstruction() + "#" + (vo.getFastingTime()==null?"":vo.getFastingTime()) + "#" + (vo.getFastingType()==null?"":vo.getFastingType()) + "#" + (vo.getBladderintr()==null?"":vo.getBladderintr())+"#";
        
      
      } 


      
      if (_mppviews != null && _mppviews.size() > 0)
      {
        
        Iterator itrAuto = _mppviews.keySet().iterator();
        List<LabTestVO> vo = (List)_mppviews.get(testcode);
        
        String data = "";
        if (vo != null)
        {
          boolean flg = true;
          int count = 0;
          for (int k = 0; k < vo.size(); k++) {
            
            LabTestVO views = (LabTestVO)vo.get(k);

            
            if (k < 4) {
              
              String finalvall = String.valueOf(testcode) + "!" + labcodecode;
              
              finalval = String.valueOf(finalval) + "<input type='checkbox' onclick='callviews(this)' class='" + finalvall + "' name='views' id='" + views.getTestCode() + "#" + views.getViewCode() + "' value=''>" + views.getViewName() + "&nbsp;";
            }
            else {
              
              count = 1;
              if (flg) {
                finalval = String.valueOf(finalval) + "<a class='btn btn-xs' data-toggle='collapse' href='#" + views.getTestCode() + views.getViewCode() + "' role='button' aria-expanded='false' aria-controls='" + views.getTestCode() + views.getViewCode() + "'> <i class='fa fa-plus-square' aria-hidden='true' style='color:black'></i></a>";
                finalval = String.valueOf(finalval) + "<div class='collapse' id='" + views.getTestCode() + views.getViewCode() + "'> <div class='card card-body'> ";
              } 
              String finalvall = String.valueOf(testcode) + "!" + labcodecode;
              
              finalval = String.valueOf(finalval) + "<input type='checkbox' onclick='callviews(this)' class='" + finalvall + "' name='views' id='" + views.getTestCode() + "#" + views.getViewCode() + "' value=''>" + views.getViewName() + "&nbsp;";
              flg = false;
            } 
          } 



          
          if (count == 1)
          {
            finalval = String.valueOf(finalval) + "</div> </div>";


          
          }

        
        }


      
      }


    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 






    
    return String.valueOf(strMsgText) + "@" + finalval;
  }


  
  public static String getisexistpatcrno(InvestigationRaisingDtlreqFB fb, HttpServletRequest request) {
    Inv_RequisitionRaisingPatientVO patVO = null;
    String accountNo = "0";
    String patfound = "0";
    String patipdoropd = "0";
    String pataccexist = "0";
    
    String patcatexpired = "0";
    
    if (fb.getPatCrNo() != null && !fb.getPatCrNo().equals("")) {


      
      patVO = new Inv_RequisitionRaisingPatientVO();

      request.getSession().setAttribute("setNewReqPATvo", patVO);
      
      WebUTIL.populate(patVO, fb);

      
      UserVO userVO = ControllerUTIL.getUserVO(request);
      patVO.setPatCRNo(fb.getPatCrNo());

      
      patVO = InvestigationRaisingDtlreqDATA.getPatientRegistration_EpisodeDetailEssentials(patVO, userVO);
      
      
      request.getSession().setAttribute("setNewReqPATvoEPISODE", patVO);
      
      if (patVO != null && patVO.getPatCRNo() != null) {
        
        WebUTIL.populate(fb, patVO);
        patfound = "1";

        
        if (patVO.getIsCatExpired() != null && patVO.getIsCatExpired().equals("0"))
        {
          
          if (patVO.getPatStatus().equals("IPD"))
          {
            
            patipdoropd = "1";
            accountNo = InvestigationRaisingDtlreqDATA.getAccountNo(patVO, userVO);
            
            request.getSession().setAttribute("setNewReqPATvoACCOUNTNO", accountNo);

            
            if (accountNo == null || "0".equals(accountNo))
            {
              
              pataccexist = "1";
            
            }
          
          }

        
        }
        else
        {
          patcatexpired = "1";
        }
      
      }
      else {
        
        patfound = "0";
      } 
    } 




    
    return String.valueOf(patfound) + "#" + patcatexpired + "#" + patipdoropd + "#" + pataccexist;
  }




  
  public static String getcheckbilling(InvestigationRaisingDtlreqFB fb, HttpServletRequest request) {
    UserVO userVO = ControllerUTIL.getUserVO(request);
    
    InvestigationSearchVO searchVO = new InvestigationSearchVO();
    
    Inv_RequisitionRaisingPatientVO patVO = null;
    
    patVO = (Inv_RequisitionRaisingPatientVO)request.getSession().getAttribute("invPatientVO");
    
    int requisitionTypeForBilling = 0;
    String patcatcode = patVO.getPatCategoryCode();
    String wardcode = "";
    
    if (patVO.getPatStatusCode().equals("2")) {
      
      wardcode = patVO.getPatwardcode();
      requisitionTypeForBilling = 2;


    
    }
    else if (patVO.getPatvisittypecode() == null) {
      requisitionTypeForBilling = 1;
    }
    else {
      
      if (patVO.getPatvisittypecode().equals("1"))
        requisitionTypeForBilling = 1; 
      if (patVO.getPatvisittypecode().equals("4"))
        requisitionTypeForBilling = 4; 
      if (patVO.getPatvisittypecode().equals("2") || patVO.getPatvisittypecode().equals("3")) {
        requisitionTypeForBilling = 3;
      }
    } 

    
    String requisitionTypeForBilling12 = Integer.toString(requisitionTypeForBilling);
    
    searchVO.setRequisitingtypeforbilling(requisitionTypeForBilling12);
    
    searchVO.setPatientCrNo(fb.getPatCrNo());
    
    Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)request.getSession().getAttribute("invPatientVO");

    
    return InvestigationRaisingDtlreqDATA.issufficientbalancexray(searchVO, userVO, lstPatVO);
  }












  
  public static void getprevreq(InvestigationRaisingDtlreqFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    Map mp = new HashMap();
    List<Inv_EpisodeVO> lstEpisodeVO = new ArrayList<Inv_EpisodeVO>();
    List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
    Map<String, Map<String, List<String>>> mpBookMark = new LinkedHashMap<String, Map<String, List<String>>>();
    String accountNo = "";
    
    boolean isCategoryExpired = false;
    
    try {
      Inv_RequisitionRaisingPatientVO patVO = null;
      Inv_ictc_VO ictc = null;
      
      UserVO userVO = ControllerUTIL.getUserVO(request);

      
      if (fb.getPatCrNo() != null && !fb.getPatCrNo().equals("")) {
        
        Map<String, LabTestVO> lstprevreq = InvestigationRaisingDtlreqDATA.getPrvTestDtlAJAXMAPxray(fb.getPatCrNo(), userVO, (fb.getPrevreqfromwhichcall() == null) ? "0" : fb.getPrevreqfromwhichcall());
        
        WebUTIL.setAttributeInSession(request, "lsitprevreqxray", lstprevreq);
      } 




      
      WebUTIL.setMapInSession(mp, request);






    
    }
    catch (Exception e) {
      
      status.add(Status.NEW, "", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
  }




  
  public static int checkBillDtlbeforedeletion(InvestigationRaisingDtlreqFB fb, HttpServletRequest request) {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    Map mp = new HashMap();
    int billDetail = 0;
    
    try {
      InvestigationSearchVO searchVO = new InvestigationSearchVO();
      UserVO userVO = ControllerUTIL.getUserVO(request);


      
      searchVO.setDelLabCode(fb.getDelLabCode());
      
      if (!fb.getDelTestCode().contains("^")) {
        searchVO.setDelTestCode(fb.getDelTestCode());
      } else {
        
        searchVO.setDelTestCode(fb.getDelTestCode().split("\\^")[0]);
      } 

      
      searchVO.setRequisitionNo(fb.getRequisitionNo());


      
      Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)session.getAttribute("invPatientVO");
      
      billDetail = InvestigationRaisingDtlreqDATA.checkBillDtl(searchVO, userVO, lstPatVO);
      
      WebUTIL.setMapInSession(mp, request);

      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    } finally {
      
      WebUTIL.setStatus(request, status);
    } 
    
    return billDetail;
  }
  
  
  
  public static void setchargestestngroup(InvestigationRaisingDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		
		
		String testcodes="";
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		searchVO.setTestSearchKeyword(fb.getTestSearchKeyword()==null?"":fb.getTestSearchKeyword());
		searchVO.setPatientCrNo(fb.getPatCrNo());
		searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
	       searchVO.setRaisethrough(fb.getCallingdesk());

		searchVO.setPatAdmNo(fb.getPatAdmNo());
		
		if(session.getAttribute("IsAddendum")!=null)
		{
		   String reqno=(String) session.getAttribute("reqNo");
		   
		   
		   if(session.getAttribute("IsAddendumENTRY")==null)
		    testcodes=data.getlabcodesaddendum(reqno,userVO);
		   else
			   testcodes=InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno,userVO);
		    
		    
		    String crno=	(String) session.getAttribute("PatCrNo");
	       String labCode=	(String) session.getAttribute("labCode");
	       String testCode=	(String) session.getAttribute("testCode");
	       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
	       fb.setIsAddendum(isAdddnudm);
	       fb.setLabCodee(labCode);
	       fb.setTestCodee(testcodes);
	       
	       
	       searchVO.setPatientCrNo(fb.getPatCrNo());
		
	       
		if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
		{
			
			searchVO.setIsAddendum(fb.getIsAddendum());
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setTestCode(fb.getTestCodee());
		}
		}	

		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_LABNAMES)!=null)
		{	
			searchVO.setLabEmpty("0");
		
		}
			
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_TESTNAMES)!=null)
		{	
			searchVO.setTestEmpty("0");
		
		}
			
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)!=null)
		{	
			searchVO.setTestCodeEmpty("0");
		}
		
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)!=null)
		{	
			searchVO.setGroupCodeEmpty("0");
		}
			
		
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
		mp=InvestigationRaisingDtlDATA.setchargestestngroup(searchVO,userVO,request);
			
		if(searchVO.getSearchTestGroupName()==null||!searchVO.getTstOrTestGroupValue().equals("1"))
		{			
			//session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
		}

		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
		status.add(Status.TRANSINPROCESS, "", "");
	}
	catch(Exception e){
		status.add(Status.ERROR_AE,"Application Execution Exception", "");
		e.printStackTrace();
	}
	finally{
		WebUTIL.setStatus(request, status);
	}
}
	

  
  public static void setchargestestngroup(InvestigationRaisingDtlreqFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		//status.add(Status.NEW, "", "");
		
		HttpSession session=request.getSession();
		Map mp=new HashMap();
	try{
		
		
		String testcodes="";
		InvestigationSearchVO searchVO=new InvestigationSearchVO();
		InvestigationRaisingDtlDATA data=new InvestigationRaisingDtlDATA();
		UserVO userVO=ControllerUTIL.getUserVO(request);
		searchVO.setSearchLabName(fb.getSearchLabName()==null?"":fb.getSearchLabName());
		searchVO.setSearchTestName(fb.getSearchTestName()==null?"":fb.getSearchTestName());
		searchVO.setSearchTestGroupName(fb.getSearchTestGroupName()==null?"":fb.getSearchTestGroupName());
		searchVO.setSearchTestGroup(fb.getSearchTestGroup()==null?"":fb.getSearchTestGroup());
		searchVO.setTstOrTestGroupValue(fb.getTstOrTestGroupValue()==null?"0":fb.getTstOrTestGroupValue());
		searchVO.setTestSearchKeyword(fb.getTestSearchKeyword()==null?"":fb.getTestSearchKeyword());
		searchVO.setPatientCrNo(fb.getPatCrNo());
		searchVO.setLabwisetestteriff(fb.getLabwisetestteriff());
	       searchVO.setRaisethrough(fb.getCallingdesk());

		searchVO.setPatAdmNo(fb.getPatAdmNo());
		
		if(session.getAttribute("IsAddendum")!=null)
		{
		   String reqno=(String) session.getAttribute("reqNo");
		   
		   
		   if(session.getAttribute("IsAddendumENTRY")==null)
		    testcodes=data.getlabcodesaddendum(reqno,userVO);
		   else
			   testcodes=InvestigationEssentialBO.getlabcodesaddendumResultentry(reqno,userVO);
		    
		    
		    String crno=	(String) session.getAttribute("PatCrNo");
	       String labCode=	(String) session.getAttribute("labCode");
	       String testCode=	(String) session.getAttribute("testCode");
	       String isAdddnudm=	(String) session.getAttribute("IsAddendum");
	       fb.setIsAddendum(isAdddnudm);
	       fb.setLabCodee(labCode);
	       fb.setTestCodee(testcodes);
	       
	       
	       searchVO.setPatientCrNo(fb.getPatCrNo());
		
	       
		if(fb.getIsAddendum()!=null || fb.getIsAddendum().equals("1"))
		{
			
			searchVO.setIsAddendum(fb.getIsAddendum());
			searchVO.setLabCode(fb.getLabCodee());
			searchVO.setTestCode(fb.getTestCodee());
		}
		}	

		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_LABNAMES)!=null)
		{	
			searchVO.setLabEmpty("0");
		
		}
			
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_TESTNAMES)!=null)
		{	
			searchVO.setTestEmpty("0");
		
		}
			
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_TEST_CODE_WISE)!=null)
		{	
			searchVO.setTestCodeEmpty("0");
		}
		
		
		if((String)request.getSession().getAttribute(InvestigationConfig.ARRAY_GROUP_CODE_WISE)!=null)
		{	
			searchVO.setGroupCodeEmpty("0");
		}
			
		
		
		String deskType="";
		if((fb.getPatAdmNo()==null || fb.getPatAdmNo().equals("")) && fb.getDepartmentUnitCode()!=null ) // calling from opd desk also assume bay desk
		{
			deskType="1";
		}
		searchVO.setRaisethrough(deskType);
		
		mp=InvestigationRaisingDtlreqDATA.setchargestestngroup(searchVO,userVO,request);
			
		if(searchVO.getSearchTestGroupName()==null||!searchVO.getTstOrTestGroupValue().equals("1"))
		{			
			//session.removeAttribute(InvestigationConfig.LIST_LAB_WISE_GROUP_DTLS);
		}

		WebUTIL.setMapInSession(mp, request);
		
		//status.add(Status.NEW, "", "");
		 
		status.add(Status.TRANSINPROCESS, "", "");
	}
	catch(Exception e){
		status.add(Status.ERROR_AE,"Application Execution Exception", "");
		e.printStackTrace();
	}
	finally{
		WebUTIL.setStatus(request, status);
	}
}
	

  
  
  public static String getcheckbillingnew(String patcrno,String rates, HttpServletRequest request) {
	    UserVO userVO = ControllerUTIL.getUserVO(request);
	    
	    InvestigationSearchVO searchVO = new InvestigationSearchVO();
	    
	    Inv_RequisitionRaisingPatientVO patVO = null;
	    
	    patVO = (Inv_RequisitionRaisingPatientVO)request.getSession().getAttribute("invPatientVO");
	    
	    int requisitionTypeForBilling = 0;
	    String patcatcode = patVO.getPatCategoryCode();
	    String wardcode = "";
	    
	    if (patVO.getPatStatusCode().equals("2")) {
	      
	      wardcode = patVO.getPatwardcode();
	      requisitionTypeForBilling = 2;


	    
	    }
	    else if (patVO.getPatvisittypecode() == null) {
	      requisitionTypeForBilling = 1;
	    }
	    else {
	      
	      if (patVO.getPatvisittypecode().equals("1"))
	        requisitionTypeForBilling = 1; 
	      if (patVO.getPatvisittypecode().equals("4"))
	        requisitionTypeForBilling = 4; 
	      if (patVO.getPatvisittypecode().equals("2") || patVO.getPatvisittypecode().equals("3")) {
	        requisitionTypeForBilling = 3;
	      }
	    } 

	    
	    String requisitionTypeForBilling12 = Integer.toString(requisitionTypeForBilling);
	    
	    searchVO.setRequisitingtypeforbilling(requisitionTypeForBilling12);
	    
	    searchVO.setPatientCrNo(patcrno);
	    searchVO.setTestrates(rates);
	    
	    Inv_RequisitionRaisingPatientVO lstPatVO = (Inv_RequisitionRaisingPatientVO)request.getSession().getAttribute("invPatientVO");

	    
	    return InvestigationRaisingDtlreqDATA.issufficientbalancexray(searchVO, userVO, lstPatVO);
	  }
  
  
}
