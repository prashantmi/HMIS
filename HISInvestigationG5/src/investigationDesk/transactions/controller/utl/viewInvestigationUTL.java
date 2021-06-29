package investigationDesk.transactions.controller.utl;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import investigationDesk.transactions.controller.data.viewInvestigationDATA;
import investigationDesk.transactions.controller.fb.viewInvestigationFB;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






























public class viewInvestigationUTL
  extends ControllerUTIL
{
  public static void getReqData(viewInvestigationFB fb, HttpServletRequest objRequest_p) {
    HttpSession session = objRequest_p.getSession();
    String strMsgText = "";
    String strTestCombo = "";
    Map mp = new HashMap();
    Status status = new Status();






    
    try {
      UserVO userVO = ControllerUTIL.getUserVO(objRequest_p);
      String CrNo = fb.getPatCrNo();


      
      mp = viewInvestigationDATA.getReqData(CrNo, userVO, (fb.getCallingmode() == null) ? "0" : fb.getCallingmode());
      
      WebUTIL.setMapInSession(mp, objRequest_p);




      
      status.add(Status.TRANSINPROCESS, "", "");
    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();

    
    }
    finally {


      
      WebUTIL.setStatus(objRequest_p, status);
    } 
  }





  
  public static void printReport(viewInvestigationFB fb, HttpServletRequest objRequest_p, HttpServletResponse response) {
    HttpSession session = objRequest_p.getSession();
    String strMsgText = "";
    String strTestCombo = "";
    Map mp = new HashMap();
    Status status = new Status();
    List<byte[]> pdfs = new ArrayList<byte[]>();


    
    try {
      System.out.println("Chandan: Beginning PDF Debug");
      System.out.println("File Name: " + fb.getFileName());
      byte[] Pdf = MongoXmlHandler.getInstance().latestFetchFile(fb.getFileName());
      
      if (Pdf.length > 0) {
        System.out.println("pdf len" + Pdf.length);
      } else {
        System.out.println("pdf len:null");
      }  pdfs.clear();
      pdfs.add(Pdf);
      System.out.println("pdf lenn:" + Pdf.length);
      response.setContentType("application/pdf");
      response.setHeader("Cache-Control", "no-cache");
      ServletOutputStream servletOutputStream = response.getOutputStream();
      System.out.println("concate all pdfs to start");
      MergeAllPdfNewInv.concatallPDFs(pdfs, servletOutputStream, null, objRequest_p);
      System.out.println("concate all pdfs successfully");
      servletOutputStream.flush();
      servletOutputStream.close();
      System.out.println("Returning the PDF");

    
    }
    catch (Exception e) {
      
      strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
    } 
  }
}
