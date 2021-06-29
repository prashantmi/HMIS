package uploadfilesonserver;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.sun.faces.facelets.util.Path;



public class BloodGroupingUploadACT extends DispatchAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		BloodGroupingUploadFB fb=(BloodGroupingUploadFB)form;
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	public ActionForward UPLOAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException
	{
		/*System.out.println("Inside BloodGroupingUpload --- > Upload Action");//commented code
		BloodGroupingUploadFB fb=(BloodGroupingUploadFB)form;
		String status="0";
		System.out.println("Upload");
		ByteArrayInputStream is = null;
		BufferedReader br = null;
		FormFile file = fb.getUploadedFileName();
		System.out.println("filename "+fb.getUploadedFileName());
		byte[] array= file.getFileData();
		System.out.println("length of array "+array.length);
		try {
			
		    is = new ByteArrayInputStream(array);
		    br = new BufferedReader(new InputStreamReader(is));
			System.out.println("Buffer Reader Created ");

		    
		  		
		List<String> list=new  ArrayList<String>();
		

		list=CellSerumRegroupingUTL.readFromBuffer(br);
		ArrayList<BloodGroupVO> vos=CellSerumRegroupingUTL.getSamples(list);
		
		System.out.println("list  :" +list.size());

		status = CellSerumRegroupingUTL.saveData(request,vos);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "Failed Due to unknown error");
	          e.printStackTrace();
		  }

		//BloodGroupVO.printList(vos);
		//String status=(String)request.getAttribute("status");
		if(status=="0"){System.out.println("Status:- "+status);}
		else
		{System.out.println("Status:- "+status);}*/ //commented code
		
		BloodGroupingUploadFB fb=(BloodGroupingUploadFB)form;
		String status="0";
		System.out.println("Upload");
		ByteArrayInputStream is = null;
		BufferedReader br = null;
		FormFile file = fb.getUploadedFileName();
		System.out.println("filename "+fb.getUploadedFileName());
		byte[] array= file.getFileData();
		String fileName = file.getFileName();	//		file name
		CellSerumRegroupingUTL.getData(fb,request);
		//filePath = path + strFileName + ".txt";
       /* FileWriter outFile = new FileWriter("D:\\myfilee.txt");
        PrintWriter out = new PrintWriter(outFile);
        out.println(array);
        out.flush();*/
		
		return mapping.findForward("NEW"); 
	}
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL"); 
	}
}
