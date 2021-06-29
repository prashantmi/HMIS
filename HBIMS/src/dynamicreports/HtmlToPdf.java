package dynamicreports;

//import org.docx4j.org.xhtmlrenderer.pdf.ITextRenderer;
//import org.xhtmlrenderer.pdf.ITextRenderer;



import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class HtmlToPdf {
	
	public static void convert(String htmlContent, File pdfFile) throws Exception {
        /*ByteArrayOutputStream os = new ByteArrayOutputStream();

        //step1: render html to memory         

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(os);

        //step2: conver to byte array stream         

        byte[] pdfAsBytes = os.toByteArray();
        os.close();

        //step3: write byte array stream to file         

        FileOutputStream fos = new FileOutputStream(pdfFile);
        fos.write(pdfAsBytes);
        fos.flush();
        fos.close();*/
		
		
		com.itextpdf.text.Document document =
		        new com.itextpdf.text.Document( com.itextpdf.text.PageSize.A4 );
		String fileNameWithPath =  "E:\\PDF-HtmlWorkerParsed.pdf";
		FileOutputStream fos = new FileOutputStream( fileNameWithPath );
		com.itextpdf.text.pdf.PdfWriter pdfWriter =
		        com.itextpdf.text.pdf.PdfWriter.getInstance( document, fos );

		document.open();

		//**********************************************************
		// if required, you can add document meta data
		document.addAuthor( "Ravinder" );
		//document.addCreator( creator );
		document.addSubject( "HtmlWoker Parsed Pdf from iText" );
		document.addCreationDate();
		document.addTitle( "HtmlWoker Parsed Pdf from iText" );
		//**********************************************************/

		com.itextpdf.text.html.simpleparser.HTMLWorker htmlWorker =
		        new com.itextpdf.text.html.simpleparser.HTMLWorker( document );
		htmlWorker.parse( new StringReader( "<html><head><title>Hello</title></head> <body> <h1>Hello Crazy World !!</h1> <br/> <h2> I hope you are doing great.</h2> </body> </html>" ) );

		document.close();
		fos.close();

		System.out.println( "File 3: '" + fileNameWithPath + "' created." );
        
        /*FileInputStream fis =new FileInputStream("C:\\example.html");
        FileOutputStream fos2 =new FileOutputStream("E:\\example.xhtml");   
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        Document D = tidy.parseDOM(fis,fos2);
        
        
        String path_xhtml = "E:\\example.xhtml";
        String path_pdf = "E:\\example.pdf";
        String url = new File(path_xhtml).toURI().toURL().toString();
        OutputStream os2 = new FileOutputStream(path_pdf);
        ITextRenderer renderer2 = new ITextRenderer();
        renderer2.setDocument(url);
        renderer2.layout();
        renderer2.createPDF(os2);*/

    

        
         
    }

    // let's test !! 

    public static void main(String[] args) throws Exception{
        convert("<html> <body> " +
            "<h1>Hello Crazy World !!</h1> <br/> " +
            "<h2> I hope you are doing great.</h2> " +
            "</body> </html>", new File("test.pdf"));
    }

}

