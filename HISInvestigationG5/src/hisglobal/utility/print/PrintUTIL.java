package hisglobal.utility.print;

import hisglobal.hisconfig.Config;
import hisglobal.utility.HTMLParsingUTIL;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HisHTMLParserUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

public class PrintUTIL 
{
   
   /**
    * Convert html code to pdf compatible and write to file
    * @param htmlCode
    */
   public static void createPdfFromHtml(String htmlCode) 
   {
	   	//Status objStatus=new Status();
	    //HttpSession session = WebUTIL.getSession(request);	
		ByteArrayOutputStream baosPDF = null;
		PdfWriter docWriter = null;
		com.lowagie.text.Document document = new com.lowagie.text.Document();
		byte [] byteArray=null;
		try
		{
			htmlCode=HisHTMLParserUtil.freezeHTMLCodeElements(htmlCode);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in parsing html");
			//objStatus.add(Status.UNSUCESSFULL,"","");
		}
		try{
		
			htmlCode = HTMLParsingUTIL.makeHTMLPDFCompatible(htmlCode);
			HTMLToPDFUTIL converter = new HTMLToPDFUTIL(null, document, htmlCode);
			baosPDF = new ByteArrayOutputStream();
			docWriter = PdfWriter.getInstance(document, baosPDF);
			docWriter.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			converter.convertToPDF();
			
			if (document != null) document.close();
			if (docWriter != null) docWriter.close();
			if (baosPDF.size() < 1) throw new DocumentException("document has " + baosPDF.size() + " bytes");
	
			System.out.println(document.getPageSize());
	
			/*// Writing to Response
			ServletOutputStream sos;
			sos = reponse.getOutputStream();
			baosPDF.writeTo(sos);
			sos.flush();*/
			String path="";
			System.out.println("Os Name="+System.getProperties().getProperty("os.name"));
			if(System.getProperties().getProperty("os.name").indexOf("Win")!=-1){
				path=Config.PRINT_PDF_FILE_PATH_WINDOWS;
			}
			else
				path=Config.PRINT_PDF_FILE_PATH_LINUX;
			File f=new File(path+"/"+Config.PRINT_PDF_FILE_NAME);
			if(!f.exists()){
				f=new File(path);
				f.mkdir();
				f=new File(path+"/"+Config.PRINT_PDF_FILE_NAME);
			}
			FileOutputStream fos=new FileOutputStream(f);
			baosPDF.writeTo(fos);
			fos.close();
			//byteArray=baosPDF.toByteArray();
			
		}
		catch (DocumentException dex)
		{
			dex.printStackTrace();
			//objStatus.add(Status.UNSUCESSFULL,"","");
			System.out.println("Document Exception :" +dex.getMessage());
		}
		catch (Exception dex)
		{
			dex.printStackTrace();
			//objStatus.add(Status.UNSUCESSFULL,"","");
			System.out.println("Exception :" +dex.getMessage());
		}
		finally
		{
			if (baosPDF != null)
			{
				baosPDF.reset();
			}
		}
	}
    
}
