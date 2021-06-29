package new_investigation.transactions.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import new_investigation.transactions.controller.data.SampleCollectionDATA;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.fb.invRaisingCumSamCollectionFB;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



























public class SampleCollectionUTL2
  extends ControllerUTIL
{
  public SampleCollectionUTL2() {}
  
  public static void setPatientRegistrationEssentials(InvestigationRaisingDtlFB fb, HttpServletRequest request)
  {
    Status status = new Status();
    
    List<Inv_EpisodeVO> lstEpisodeVO = new ArrayList();
    List<Inv_PatientAdmissionDtlVO> lstPatientVO = new ArrayList();
    Map mp = new HashMap();
    try {
      Inv_RequisitionRaisingPatientVO patVO = null;
      
      ControllerUTIL.setSysdate(request);
      Date dt = (Date)request.getSession().getAttribute("SYSDATEOBJECT");
      WebUTIL.getSession(request).setAttribute("sysdate", dt);
      
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    }
    finally {
      WebUTIL.setStatus(request, status);
    }
  }
  

  public static void getPatList(SampleCollectionFB fb, HttpServletRequest request)
  {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    try {
      List<Inv_SampleCollectionVO> lstsamplePatinetVO = null;
      
      lstsamplePatinetVO = new ArrayList();
      Inv_SampleCollectionVO objSampleCollectionVO = new Inv_SampleCollectionVO();
      


      objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
      objSampleCollectionVO.setFromDate(fb.getFromDate());
      objSampleCollectionVO.setToDate(fb.getToDate());
      
      objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
      
      session.removeAttribute("voPatAdm");
      session.removeAttribute("lstSamplePatientVO");
      
      session.removeAttribute("lstBilledPat");
      session.removeAttribute("lstUnBilledPat");
      
      session.removeAttribute("mapBilledPat");
      session.removeAttribute("mapUnBilledPat");
      




      UserVO userVO = ControllerUTIL.getUserVO(request);
      lstsamplePatinetVO = SampleCollectionDATA.getPatList(objSampleCollectionVO, userVO);
      


      WebUTIL.setAttributeInSession(request, "lstSamplePatientVO", lstsamplePatinetVO);
      

      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    }
    finally {
      WebUTIL.setStatus(request, status);
    }
  }
  

  public static void getPatListBarcode(SampleCollectionFB fb, HttpServletRequest request)
  {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    try {
      List<Inv_SampleCollectionVO> lstsamplePatinetVO = null;
      
      lstsamplePatinetVO = new ArrayList();
      Inv_SampleCollectionVO objSampleCollectionVO = new Inv_SampleCollectionVO();
      



      objSampleCollectionVO.setFromDate(fb.getFromDate());
      objSampleCollectionVO.setToDate(fb.getToDate());
      
      objSampleCollectionVO.setSampleAreaCode(fb.getSampleAreaCode());
      
      session.removeAttribute("voPatAdm");
      session.removeAttribute("lstSamplePatientVO");
      
      session.removeAttribute("lstBilledPat");
      session.removeAttribute("lstUnBilledPat");
      
      session.removeAttribute("mapBilledPat");
      session.removeAttribute("mapUnBilledPat");
      




      UserVO userVO = ControllerUTIL.getUserVO(request);
      lstsamplePatinetVO = SampleCollectionDATA.getPatListBarcode(objSampleCollectionVO, userVO);
      


      WebUTIL.setAttributeInSession(request, "lstSamplePatientVObarcode", lstsamplePatinetVO);
      

      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    }
    finally {
      WebUTIL.setStatus(request, status);
    }
  }
  
  public static void getSampleCollectionArea(SampleCollectionFB fb, HttpServletRequest request)
  {
    Status status = new Status();
    
    HttpSession session = request.getSession();
    try {
      List<Inv_SampleCollectionVO> lstsampleAreaVO = null;
      List<Inv_SampleCollectionVO> lstsamplePatinetVO = null;
      ControllerUTIL.setSysdate(request);
      Date dt = (Date)request.getSession().getAttribute("SYSDATEOBJECT");
      String todayDate = WebUTIL.getCustomisedSysDate(dt, "dd-MMM-yyyy");
      
      lstsampleAreaVO = new ArrayList();
      Inv_SampleCollectionVO objSampleVO = new Inv_SampleCollectionVO();
      
      UserVO userVO = ControllerUTIL.getUserVO(request);
      lstsampleAreaVO = SampleCollectionDATA.getSampleCollectionArea(userVO);
      if (lstsampleAreaVO != null)
      {
        WebUTIL.populate(fb, lstsampleAreaVO);
        

        List<Entry> sampleList = new ArrayList();
        for (Inv_SampleCollectionVO sampleAreaVO : lstsampleAreaVO)
        {
          Entry entObj = new Entry();
          
          entObj.setValue(sampleAreaVO.getSampleAreaCode());
          entObj.setLabel(sampleAreaVO.getSampleAreaName());
          sampleList.add(entObj);
        }
        
        WebUTIL.setAttributeInSession(request, "lstsampleAreaVO", sampleList);
        lstsamplePatinetVO = (List)session.getAttribute("lstSamplePatientVO");
        if (sampleList.size() == 1)
        {
          lstsamplePatinetVO = new ArrayList();
          
          Inv_SampleCollectionVO objSampleCollectionVO = new Inv_SampleCollectionVO();
          

          objSampleCollectionVO.setPatCRNo(fb.getPatCRNo());
          objSampleCollectionVO.setFromDate(todayDate);
          objSampleCollectionVO.setToDate(todayDate);
          
          objSampleCollectionVO.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
          
          session.removeAttribute("voPatAdm");
          session.removeAttribute("lstSamplePatientVO");
          
          session.removeAttribute("lstBilledPat");
          session.removeAttribute("lstUnBilledPat");
          
          session.removeAttribute("mapBilledPat");
          session.removeAttribute("mapUnBilledPat");
          

          lstsamplePatinetVO = SampleCollectionDATA.getPatList(objSampleCollectionVO, userVO);
          if (lstsamplePatinetVO != null)
          {
            WebUTIL.populate(fb, lstsamplePatinetVO);
            WebUTIL.setAttributeInSession(request, "lstSamplePatientVO", lstsamplePatinetVO);
          }
          

          fb.setSampleAreaCode(((Entry)sampleList.get(0)).getValue());
          fb.setSampleAreaName(((Entry)sampleList.get(0)).getLabel());
          fb.setIsSampleAreaSelected("1");
        }
      }
      



      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    }
    finally {
      WebUTIL.setStatus(request, status);
    }
  }
  
  public static void saveSampleCollectionDetails(SampleCollectionFB _fb, HttpServletRequest _request)
    throws BarcodeException, OutputException
  {
    Status objStatus = new Status();
    HttpSession session = _request.getSession();
    

    Map<String, Map<String, Map<String, List<Inv_SampleCollectionVO>>>> mp = new LinkedHashMap();
    OutputStream os = new ByteArrayOutputStream();
    
    try
    {
      Date sysdate = (Date)session.getAttribute("SYSDATEOBJECT");
      UserVO _userVO = getUserVO(_request);
      

      String mobileNo = _fb.getPatMobile() == null ? "" : _fb.getPatMobile();
      String emailId = _fb.getPatEmail() == null ? "" : _fb.getPatEmail();
      String patAddress = _fb.getPatAddress() == null ? "" : _fb.getPatAddress();
      
      String collAreaCode = _fb.getSampleAreaCode() == null ? "" : _fb.getSampleAreaCode();
      String printStatus = "0";
      

      String[] selectedLabTestCodeArray = _fb.getChkSamplePatient();
      
      int labRowCount = selectedLabTestCodeArray.length;
      
      for (int i = 0, k = 0; i < labRowCount; i++)
      {



        String[] testCodeArray = selectedLabTestCodeArray[i].split("#");
        
        String crNo = testCodeArray[0];
        String requisitionNo = testCodeArray[1];
        String sampleCode = testCodeArray[2];
        String requisitionDNo = testCodeArray[3];
        String labCode = testCodeArray[4];
        String billNo = testCodeArray[5];
        String testCode = testCodeArray[6];
        String sampleName = testCodeArray[7];
        int index = Integer.parseInt(testCodeArray[8]);
        String samplenoConfig = testCodeArray[9];
        String patType = testCodeArray[20];
        String sampleFormate = testCodeArray[13];
        String initDate = testCodeArray[14];
        String noofSegDigits = testCodeArray[15];
        String fromSeries = testCodeArray[16];
        String toSeries = testCodeArray[17];
        String initType = testCodeArray[18];
        String runningSampleNo = testCodeArray[19];
        String requisitionDate = testCodeArray[12];
        String labName = testCodeArray[11];
        
        String configLab = testCodeArray[21];
        String configType = testCodeArray[22];
        String configSeq = testCodeArray[23];
        String configTest = testCodeArray[24];
        String configArea = testCodeArray[25];
        


        Map<String, Map<String, List<Inv_SampleCollectionVO>>> mpReqNo = (Map)mp.get(crNo);
        


        if (mpReqNo == null)
        {
          mpReqNo = new LinkedHashMap();
          
          Map<String, List<Inv_SampleCollectionVO>> mpSample = new LinkedHashMap();
          
          List<Inv_SampleCollectionVO> lstSample = new ArrayList();
          Inv_SampleCollectionVO voSample = new Inv_SampleCollectionVO();
          

          if ((!samplenoConfig.equals("1")) || (!samplenoConfig.equals("2")))
          {

            voSample.setTempSampleNo(_fb.getSampleNo()[index]);
          }
          else
          {
            voSample.setTempSampleNo(samplenoConfig);
          }
          
          voSample.setPatCRNo(crNo);
          voSample.setSampleCode(sampleCode);
          voSample.setRequisitionDNo(requisitionDNo);
          voSample.setRequisitionNo(requisitionNo);
          voSample.setLabCode(labCode);
          voSample.setPatMobile(mobileNo);
          voSample.setPatEmail(emailId);
          voSample.setPatAddress(patAddress);
          
          voSample.setSampleAreaCode(collAreaCode);
          voSample.setPrintStatus(printStatus);
          voSample.setSampleQnty(_fb.getSampleQnty()[index]);
          voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
          voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
          voSample.setTypeOfComponent("1");
          


          voSample.setBillNo(billNo);
          voSample.setTestCode(testCode);
          voSample.setSampleName(sampleName);
          
          voSample.setCheckAutoLabConfig(samplenoConfig);
          
          voSample.setPatType(patType);
          








          voSample.setSampleNoFormat(sampleFormate);
          voSample.setInitDate(initDate);
          voSample.setNoOfSeqDigit(noofSegDigits);
          voSample.setFromSeries(fromSeries);
          voSample.setToSeries(toSeries);
          voSample.setInitType(initType);
          voSample.setRunningSampleNo(runningSampleNo);
          voSample.setRequisitionDate(requisitionDate);
          voSample.setLabName(labName);
          
          voSample.setConfigLab(configLab);
          voSample.setConfigArea(configArea);
          voSample.setConfigSeq(configSeq);
          voSample.setConfigTest(configTest);
          voSample.setConfigType(configType);
          




          lstSample.add(voSample);
          

          mpSample.put(sampleCode + "#" + labCode, lstSample);
          

          mpReqNo.put(requisitionNo, mpSample);





        }
        else
        {





          Map<String, List<Inv_SampleCollectionVO>> mpSample = (Map)mpReqNo.get(requisitionNo);
          

          if (mpSample == null)
          {
            mpSample = new LinkedHashMap();
            
            List<Inv_SampleCollectionVO> lstSample = new ArrayList();
            Inv_SampleCollectionVO voSample = new Inv_SampleCollectionVO();
            

            voSample.setPatCRNo(crNo);
            voSample.setSampleCode(sampleCode);
            voSample.setRequisitionDNo(requisitionDNo);
            voSample.setRequisitionNo(requisitionNo);
            voSample.setLabCode(labCode);
            voSample.setPatMobile(mobileNo);
            voSample.setPatEmail(emailId);
            voSample.setPatAddress(patAddress);
            

            voSample.setSampleAreaCode(collAreaCode);
            voSample.setPrintStatus(printStatus);
            voSample.setSampleQnty(_fb.getSampleQnty()[index]);
            voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
            voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
            voSample.setTypeOfComponent("1");
            

            voSample.setBillNo(billNo);
            voSample.setTestCode(testCode);
            voSample.setSampleName(sampleName);
            voSample.setRequisitionDate(_fb.getRequisitionDate());
            voSample.setLabName(_fb.getLabName());
            






            if ((!samplenoConfig.equals("1")) || (!samplenoConfig.equals("2")))
            {

              voSample.setTempSampleNo(_fb.getSampleNo()[index]);
            }
            else
            {
              voSample.setTempSampleNo(samplenoConfig);
            }
            
            voSample.setCheckAutoLabConfig(samplenoConfig);
            voSample.setPatType(patType);
            
            voSample.setSampleNoFormat(sampleFormate);
            voSample.setInitDate(initDate);
            voSample.setNoOfSeqDigit(noofSegDigits);
            voSample.setFromSeries(fromSeries);
            voSample.setToSeries(toSeries);
            voSample.setInitType(initType);
            voSample.setRunningSampleNo(runningSampleNo);
            voSample.setRequisitionDate(requisitionDate);
            voSample.setLabName(labName);
            voSample.setConfigLab(configLab);
            voSample.setConfigArea(configArea);
            voSample.setConfigSeq(configSeq);
            voSample.setConfigTest(configTest);
            voSample.setConfigType(configType);
            

            lstSample.add(voSample);
            

            mpSample.put(sampleCode + "#" + labCode, lstSample);





          }
          else
          {





            List<Inv_SampleCollectionVO> lstSample = (List)mpSample.get(sampleCode + "#" + labCode);
            if ((lstSample == null) || (lstSample.size() == 0))
            {
              lstSample = new ArrayList();
              Inv_SampleCollectionVO voSample = new Inv_SampleCollectionVO();
              

              voSample.setPatCRNo(crNo);
              voSample.setSampleCode(sampleCode);
              voSample.setRequisitionDNo(requisitionDNo);
              voSample.setRequisitionNo(requisitionNo);
              voSample.setLabCode(labCode);
              voSample.setPatMobile(mobileNo);
              voSample.setPatEmail(emailId);
              voSample.setPatAddress(patAddress);
              

              voSample.setSampleAreaCode(collAreaCode);
              voSample.setPrintStatus(printStatus);
              voSample.setSampleQnty(_fb.getSampleQnty()[index]);
              voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
              voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
              voSample.setTypeOfComponent("1");
              

              voSample.setBillNo(billNo);
              voSample.setTestCode(testCode);
              voSample.setSampleName(sampleName);
              
              if ((!samplenoConfig.equals("1")) || (!samplenoConfig.equals("2")))
              {

                voSample.setTempSampleNo(_fb.getSampleNo()[index]);
              }
              else
              {
                voSample.setTempSampleNo(samplenoConfig);
              }
              
              voSample.setCheckAutoLabConfig(samplenoConfig);
              voSample.setPatType(patType);
              
              voSample.setSampleNoFormat(sampleFormate);
              voSample.setInitDate(initDate);
              voSample.setNoOfSeqDigit(noofSegDigits);
              voSample.setFromSeries(fromSeries);
              voSample.setToSeries(toSeries);
              voSample.setInitType(initType);
              voSample.setRunningSampleNo(runningSampleNo);
              voSample.setRequisitionDate(requisitionDate);
              voSample.setLabName(labName);
              
              voSample.setConfigLab(configLab);
              voSample.setConfigArea(configArea);
              voSample.setConfigSeq(configSeq);
              voSample.setConfigTest(configTest);
              voSample.setConfigType(configType);
              



              lstSample.add(voSample);

            }
            else
            {
              Inv_SampleCollectionVO voSample = new Inv_SampleCollectionVO();
              

              voSample.setPatCRNo(crNo);
              voSample.setSampleCode(sampleCode);
              voSample.setRequisitionDNo(requisitionDNo);
              voSample.setRequisitionNo(requisitionNo);
              voSample.setLabCode(labCode);
              voSample.setPatMobile(mobileNo);
              voSample.setPatEmail(emailId);
              voSample.setPatAddress(patAddress);
              

              voSample.setSampleAreaCode(collAreaCode);
              voSample.setPrintStatus(printStatus);
              voSample.setSampleQnty(_fb.getSampleQnty()[index]);
              voSample.setDefaultContainerCode(_fb.getDefaultContainerCode()[index]);
              voSample.setDefaultUOMCode(_fb.getDefaultUOMCode()[index]);
              voSample.setTypeOfComponent("1");
              

              voSample.setBillNo(billNo);
              voSample.setTestCode(testCode);
              voSample.setSampleName(sampleName);
              


              if ((!samplenoConfig.equals("1")) || (!samplenoConfig.equals("2")))
              {

                voSample.setTempSampleNo(_fb.getSampleNo()[index]);
              }
              else
              {
                voSample.setTempSampleNo(samplenoConfig);
              }
              

              voSample.setCheckAutoLabConfig(samplenoConfig);
              voSample.setPatType(patType);
              voSample.setSampleNoFormat(sampleFormate);
              voSample.setInitDate(initDate);
              voSample.setNoOfSeqDigit(noofSegDigits);
              voSample.setFromSeries(fromSeries);
              voSample.setToSeries(toSeries);
              voSample.setInitType(initType);
              voSample.setRunningSampleNo(runningSampleNo);
              voSample.setRequisitionDate(requisitionDate);
              voSample.setLabName(labName);
              
              voSample.setConfigLab(configLab);
              voSample.setConfigArea(configArea);
              voSample.setConfigSeq(configSeq);
              voSample.setConfigTest(configTest);
              voSample.setConfigType(configType);
              



              lstSample.add(voSample);
            }
            

            mpSample.put(sampleCode + "#" + labCode, lstSample);
          }
          


          mpReqNo.put(requisitionNo, mpSample);
        }
        





        mp.put(crNo, mpReqNo);
      }
      



      List listReqdtlId = SampleCollectionDATA.saveSampleCollectionDetails(mp, _userVO);
      StringBuffer htmlContents = new StringBuffer();
      
























      StringBuilder str = new StringBuilder();
      

      for (int i = 0; i < listReqdtlId.size(); i++)
      {
        String saveString = (String)listReqdtlId.get(i);
        String[] arrSaveString = saveString.split("#");
        
        str.append(" Sample Collected Succesfully for Patient CR Number::\t");
        str.append(arrSaveString[0]);
        str.append(" having Sample Name::\t");
        str.append(arrSaveString[1]);
        str.append(" and Sample No::\t");
        str.append(arrSaveString[2]);
        str.append("<br>");
        

        String barCodeGenSiString = String.valueOf(listReqdtlId.size());
        







        System.out.println("len:" + arrSaveString[6].length());
        System.out.println("tempsampleno:" + arrSaveString[6]);
        












        Barcode barcode = BarcodeFactory.createCode128(arrSaveString[6]);
        
        barcode.setBarWidth(1);
        barcode.setResolution(203);
        

        Font font = new Font("Plain", 0, 0);
        barcode.setFont(font);
        BarcodeImageHandler.writePNG(barcode, os);
        

        ByteArrayOutputStream bos = (ByteArrayOutputStream)os;
        byte[] data = bos.toByteArray();
        session.setAttribute("uploadedFileAsArray", data);
        



        htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
        htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:10px;height: 60px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:0px;height: 60px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
        htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='" + i + "diivBarCodeControl'><center>" + arrSaveString[6] + "</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='" + i + "diivBarCodeControlAll'><center>" + arrSaveString[6] + "</center></div></td></tr>");
        htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[5] + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[7] + "</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[0] + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[4] + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[5] + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>" + arrSaveString[7] + "</td></tr> </table></td></tr>");
        


        htmlContents.append("<input type='hidden' id='barCodeGenSize' value='" + barCodeGenSiString + "' name='barCodeGenSize'/></table>");
      }
      


































      session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
      _fb.setSaveConfirmFlag("1");
      
      System.out.println("html Contents" + htmlContents);
      objStatus.add(Status.NEW, str.toString(), 
        "<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>Sample Collection Successful<br></font>");


    }
    catch (HisRecordNotFoundException e)
    {


      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisDataAccessException e)
    {
      objStatus.add(Status.ERROR_DA, e.getMessage(), "");
      System.out.println(e.getMessage());
    }
    catch (HisApplicationExecutionException e)
    {
      objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
      System.out.println(e.getMessage());
    }
    catch (HisException e)
    {
      objStatus.add(Status.ERROR, "", "Error");
      System.out.println(e.getMessage());
    }
    finally
    {
      WebUTIL.setStatus(_request, objStatus);
    }
  }
  

  public static void showPatDetails(SampleCollectionFB fb, HttpServletRequest request)
  {
    Status status = new Status();
    HttpSession session = request.getSession();
    UserVO _userVO = getUserVO(request);
    Map mp = new HashMap();
    boolean flag = false;
    String staffImage = "";
    try
    {
      List<Inv_SampleCollectionVO> lstsamplePatinetVO = null;
      List<String> reqList = new ArrayList();
      Inv_SampleCollectionVO voSample = new Inv_SampleCollectionVO();
      
      List<String> staffDetails = new ArrayList();
      

      lstsamplePatinetVO = (List)session.getAttribute("lstSamplePatientVO");
      String selectedCheckBoxValue = fb.getSelectedCheckbox();
      
      String[] arrSelectedRequisitions = selectedCheckBoxValue.split("@");
      
      String crNo = arrSelectedRequisitions[0].split("#")[0];
      String reqNO = arrSelectedRequisitions[0].split("#")[1];
      

      for (Inv_SampleCollectionVO objSampleCollectionVO : lstsamplePatinetVO)
      {
        if (flag) break;
        if ((objSampleCollectionVO.getPatCRNo().equals(crNo)) && (objSampleCollectionVO.getRequisitionNo().equals(reqNO)))
        {

          WebUTIL.setAttributeInSession(request, "voPatAdm", objSampleCollectionVO);
          voSample = objSampleCollectionVO;
          flag = true;
          break;
        }
      }
      
      for (String str : arrSelectedRequisitions) {
        reqList.add(str);
      }
      


      mp = SampleCollectionDATA.getBilledPatList(reqList, voSample, _userVO);
      
      WebUTIL.setMapInSession(mp, request);
      
      session.setAttribute("staffImage", "");
      
      staffDetails = SampleCollectionDATA.getStaffDetails(crNo, _userVO);
      
      if (staffDetails != null)
      {
        staffImage = getStaffImage((String)staffDetails.get(0), (String)staffDetails.get(1), (String)staffDetails.get(2));
        
        if ((staffImage != null) && 
          (!staffImage.equals("0")))
        {
          session.setAttribute("staffImage", staffImage);
        }
      }
      




      status.add(Status.NEW, "", "");
      status.add(Status.TRANSINPROCESS, "", "");
    }
    catch (Exception e) {
      status.add(Status.ERROR_AE, "Application Execution Exception", "");
      e.printStackTrace();
    }
    finally {
      WebUTIL.setStatus(request, status);
    }
  }
  
  public static String getStaffImage(String empId, String slNo, String hosCode)
  {
    String base64EncodedImage = "";
    String args = empId + "/" + slNo + "/" + hosCode;
    StringBuffer buf = new StringBuffer();
    

    try
    {
      URL url = new URL("http://10.226.2.186:8780/HBIMS/services/restful/hisImageRetrievalUtil/" + args);
      System.out.println("URL STAFF DEPENDENT IMAGE -> " + url);
      
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      
      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + 
          conn.getResponseCode());
      }
      

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      
      String output;
      
      while ((output = br.readLine()) != null) {
        //String output;
        buf.append(output);
      }
      
      JSONParser parser = new JSONParser();
      
      JSONObject jsonObject = (JSONObject)parser.parse(buf.toString());
      



      JSONArray getArray = (JSONArray)jsonObject.get("data");
      


      for (int i = 0; i < getArray.size(); i++) {
        JSONObject objects = (JSONObject)getArray.get(i);
        Set keyS = objects.keySet();
        
        Iterator<String> key = keyS.iterator();
        while (key.hasNext()) {
          String k = ((String)key.next()).toString();
          if (k.equalsIgnoreCase("HRGBYTE_IMAGE"))
          {

            base64EncodedImage = (String)objects.get(k);
          }
        }
      }
      

      conn.disconnect();
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    
    return base64EncodedImage;
  }
  








  public static StringBuffer checkSampleNoDuplicacy(SampleCollectionFB fb, HttpServletRequest objRequest_p)
  {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    try
    {
      UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
      
      Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
      

      voSampleCollection.setSampleAreaCode(fb.getSampleAreaCode());
      voSampleCollection.setTempSampleNo(fb.getStrSampleNo());
      
      boolean isTempSamplePresent = SampleCollectionDATA.checkSampleNoDuplicacy(voSampleCollection, voUser);
      
      sbAjaxRes.append(isTempSamplePresent);


    }
    catch (Exception e)
    {


      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
      
      new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
    }
    




    return sbAjaxRes;
  }
  








  public static StringBuffer checkAutoGenFormate(SampleCollectionFB fb, HttpServletRequest objRequest_p)
  {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    String strFormate = "";
    Map mp = new HashMap();
    try
    {
      UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
      
      Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
      

      voSampleCollection.setLabCode(fb.getLabCode());
      voSampleCollection.setTestCode(fb.getTestCode());
      voSampleCollection.setPatType(fb.getPatType());
      voSampleCollection.setTempSampleNo(fb.getTempSampleNo());
      voSampleCollection.setConfigArea(fb.getSampleAreaCode());
      

      strFormate = SampleCollectionDATA.checkAutoGenFormate(voSampleCollection, voUser);
      

      sbAjaxRes.append(strFormate);



    }
    catch (Exception e)
    {



      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
      
      new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
    }
    




    return sbAjaxRes;
  }
  











  public static StringBuffer checkSampleNoDuplicacyRaisingCumSampleCollection(invRaisingCumSamCollectionFB fb, HttpServletRequest objRequest_p, String sampleNo, String sampleAreaCode)
  {
    StringBuffer sbAjaxRes = new StringBuffer();
    String strMsgText = "";
    try
    {
      UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
      
      Inv_SampleCollectionVO voSampleCollection = new Inv_SampleCollectionVO();
      

      voSampleCollection.setSampleAreaCode(sampleAreaCode);
      voSampleCollection.setTempSampleNo(sampleNo);
      
      boolean isTempSamplePresent = SampleCollectionDATA.checkSampleNoDuplicacy(voSampleCollection, voUser);
      
      sbAjaxRes.append(isTempSamplePresent);


    }
    catch (Exception e)
    {


      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
      
      new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
    }
    




    return sbAjaxRes;
  }
  
  public static void duplicateBarCodeDetails(SampleCollectionFB fb, HttpServletRequest request)
    throws BarcodeException, OutputException
  {
    OutputStream os = new ByteArrayOutputStream();
    HttpSession session = request.getSession();
    StringBuffer htmlContents = new StringBuffer();
    
    for (int i = 0; i < fb.getChkSamplePatient().length; i++)
    {

      String[] values = fb.getChkSamplePatient()[i].split("#");
      
      String sampleno = values[2];
      String crno = values[0];
      String samplename = values[1];
      String reqdate = values[5];
      String labname = values[4];
      
      String barCodeGenSiString = String.valueOf(fb.getChkSamplePatient().length);
      
      Barcode barcode = BarcodeFactory.createCode128(sampleno);
      
      barcode.setBarWidth(1);
      barcode.setResolution(203);
      

      Font font = new Font("Plain", 0, 0);
      barcode.setFont(font);
      BarcodeImageHandler.writePNG(barcode, os);
      

      ByteArrayOutputStream bos = (ByteArrayOutputStream)os;
      byte[] data = bos.toByteArray();
      session.setAttribute("uploadedFileAsArray", data);
      



      htmlContents.append("<table width='105%' height='30' cellspacing='0'  cellpadding='0'   >");
      htmlContents.append("<tr><td width='50%'  ><div id='" + i + "diivBarCodeControl'><img style='margin-left:10px;height: 60px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td><td width='50%' align='left' ><div id='" + i + "diivBarCodeControlAll'><img style='margin-left:0px;height: 60px;' src=\"/HISInvestigationG5/ShowImageOutofAnArray\" alt=\"no image found in session\"   width='100%' ></div></td></tr>");
      htmlContents.append("<tr><td width='50%'  style='font-size:15px;' ><div id='" + i + "diivBarCodeControl'><center>" + sampleno + "</center></div></td><td width='50%' align='left' style='font-size:15px;'><div id='" + i + "diivBarCodeControlAll'><center>" + sampleno + "</center></div></td></tr>");
      htmlContents.append("<tr><td width='50%' height='8' ><table cellspacing='0'  cellpadding='0' style='margin-left:9px'><tr><td style='font-size:9px; height='10'><b>CR No.</b></td><td height='8' style='font-size:9px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>" + reqdate + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>" + samplename + "</td></tr> </table></td><td width='50%' height='8'><table cellspacing='0'  cellpadding='0' ><tr><td height='8' style='font-size:9px;'><b>CR No.</b></td><td height='8' style='font-size:9px;'>" + crno + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Lab :</b></td><td height='8' style='font-size:9px;'>" + labname + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Req. Date:</b></td><td height='8' style='font-size:9px;'>" + reqdate + "</td></tr><tr><td height='8' style='font-size:9px;'><b>Sample:</b></td><td height='8' style='font-size:9px;'>" + samplename + "</td></tr> </table></td></tr>");
      

      htmlContents.append("<input type='hidden' id='barCodeGenSize' value='" + barCodeGenSiString + "' name='barCodeGenSize'/></table>");
    }
    









    session.setAttribute("sampleNoLabelBarCodeString", htmlContents.toString());
    fb.setSaveConfirmFlag("1");
    
    System.out.println("html Contents" + htmlContents);
  }
}