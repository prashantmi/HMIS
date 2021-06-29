/* **************************************************************************************
 * creates a dialog box for printing pdf file.
 * It does following jobs
 * (1) get the list of printer(s) available on the client machine
 * (2) read the pdf file previously created using PrintUTL class using the ReadPdfServlet
 * (3)call the TestPrint's print method to print the pdf file 
 *  
 ****************************************************************************************/

package hisglobal.utility.print;

import hisglobal.hisconfig.Config;
import hisglobal.utility.HTMLParsingUTIL;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HisHTMLParserUtil;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import netscape.javascript.JSObject;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.pdfview.PDFFile;

public class PrintApplet extends JApplet implements ActionListener{
	
	private PrintRequestAttributeSet aset;
	/*
	 * DocFlavor DocFlavor; public Doc doc; DocPrintJob printerJob;
	 */
	JTextField noOfcopiesTxt;
	JLabel printerName;
	JLabel noOfCopies;
	JComboBox printerCombo;
	JButton printButton;
	JButton cancelButton;
	PrintService pservices[];
	String printerList[];
	JPanel panel=new JPanel();
	JLabel msg=new JLabel();
	String copies="";
	boolean isCopiesEditable=true;
	String htmlCode; 
	
	public void init() {
			
		copies=getParameter("noOfCopies");
		if(getParameter("isCopiesEditable")!=null)
		if(getParameter("isCopiesEditable").equals("false")){
			isCopiesEditable=false;
		}
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.out.println("Inside init-----------------------------------");
			// start();
			printButton=new JButton("Print");
			cancelButton=new JButton("Cancel");
			
			htmlCode=getParameter("htmlCode");
			//htmlCode="<table><tr><td>suhukhashf kj gsafk a<td></tr></table>";
			System.out.println(isCopiesEditable);
			// getContentPane().setLayout(null);
			noOfcopiesTxt = new JTextField(5);
			// pane.setContentType("text/html");
			// pane.setSize(150, 100);
			printerName=new JLabel();
			noOfCopies=new JLabel();
			printerName.setText("Printer");
			noOfCopies.setText("No Of Copies");
			noOfcopiesTxt.setEditable(isCopiesEditable);
			noOfcopiesTxt.setText(copies);
			
			getPrinterNames();	
			panel.setLayout(null);
			if(printerList.length>0){
				printerCombo=new JComboBox(printerList);
			}
			else{
				printerCombo=new JComboBox();
				msg.setText("No Printer Found..........");
				printButton.setEnabled(false);
			}
			printerName.setBounds(60, 50, 100, 20);
			printerCombo.setBounds(150, 50, 180, 20);
			noOfCopies.setBounds(60, 80, 100, 20);
			noOfcopiesTxt.setBounds(150, 80, 50, 20);
			printButton.setBounds(110, 130, 80, 20);
			cancelButton.setBounds(200, 130, 80, 20);
			msg.setBounds(120, 170, 250, 20);
			msg.setForeground(Color.RED);
			panel.add(printerName);
			panel.add(printerCombo);
			panel.add(noOfCopies);
			panel.add(noOfcopiesTxt);
			panel.add(printButton);
			panel.add(cancelButton);
			panel.add(msg);
			//msg.setSize(100, 100);
			panel.setVisible(true);
			this.getContentPane().add(panel);
			this.setSize(400, 250);
			printButton.addActionListener(this);
			cancelButton.addActionListener(this);
			//createPdf();
			
		}
		catch (Exception e) {
			System.out.println("Exception in init");
		}
	}
	
	void getPrinterNames() {

	/*aset = new HashPrintRequestAttributeSet();
	// aset.add(MediaSizeName.ISO_A4);
	aset.add(new Copies(1));*/
		try {
			PrintService defaultPs=PrintServiceLookup.lookupDefaultPrintService();
			this.pservices =
			PrintServiceLookup.lookupPrintServices(null,null);
			printerList=new String[pservices.length];
			ArrayList l=new ArrayList();
			if(pservices.length>0){
				l.add(defaultPs.getName());
			}
			for(int i=0;i<pservices.length;i++){
				if(!pservices[i].getName().equals(defaultPs.getName()))
					l.add(pservices[i].getName());
				//System.out.println("Printer Name------------------------------ "+pservices[i].getName());
			}
			
			for(int i=0;i<l.size();i++){
				
				printerList[i]=new String();
				printerList[i]=l.get(i).toString();
				System.out.println("Printer Name------------------------------ "+pservices[i].getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/////////////////////////function is not in use/////////////////////////////////////////
	public static ByteBuffer getAsByteArray(URL url) throws IOException {
	    URLConnection connection = url.openConnection();
	    // Since you get a URLConnection, use it to get the InputStream
	    InputStream in = connection.getInputStream();
	    // Now that the InputStream is open, get the content length
	    int contentLength = connection.getContentLength();

	    // To avoid having to resize the array over and over and over as
	    // bytes are written to the array, provide an accurate estimate of
	    // the ultimate size of the byte array
	    ByteArrayOutputStream tmpOut;
	    if (contentLength != -1) {
	        tmpOut = new ByteArrayOutputStream(contentLength);
	    } else {
	        tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate size
	    }

	    byte[] buf = new byte[512];
	    while (true) {
	        int len = in.read(buf);
	        if (len == -1) {
	            break;
	        }
	        tmpOut.write(buf, 0, len);
	    }
	    in.close();
	    tmpOut.close(); // No effect, but good to do anyway to keep the metaphor alive

	    byte[] array = tmpOut.toByteArray();

	    return ByteBuffer.wrap(array);
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==printButton){
			PrintService printservice=null;
			for(int i=0;i<pservices.length;i++){
				if(printerCombo.getSelectedItem().equals(pservices[i].getName())){
					printservice=pservices[i];
					break;
				}	
			}
			
			try{
				
				if(!validateNoOfCopies(noOfcopiesTxt.getText())){
					JOptionPane.showMessageDialog(this,"Enter valid no of copies","Print" ,JOptionPane.ERROR_MESSAGE);
					return;
				}
				System.out.println("get code Base :" + getCodeBase());
				URL url=null;
				 
				url=new URL(getCodeBase() + "readPdfServlet");
				PDFFile pdfFile=null;
				System.out.println("Print service "+ printservice);
				
				pdfFile = new PDFFile(getFile(url));
				if(printservice!=null)
						new TestPrint(pdfFile, "Test Print").print(Integer.parseInt(noOfcopiesTxt.getText()),printservice);
						msg.setText("Printed Succesfully");
						printButton.setEnabled(false);
					
				JSObject.getWindow (this).eval ("javascript:self.close()") ; 
										
			}
			catch (NumberFormatException ex) {
				msg.setText("Enter valid no of copies.");
			}
			catch (IOException ex) {
				msg.setText("File not Found Cannot Print.");
			}
			catch (Exception ex) {
				msg.setText(ex.getMessage());
				ex.printStackTrace();
			}
			finally{
				//msg.setText(buffer.toString());
				//System.exit(0);
			}
		}
		/**
		 * calling the javascript function on cancel button event
		 * JSObject is available in plugin.jar
		 */
		if(e.getSource()==cancelButton){
			JSObject.getWindow (this).eval("javascript:self.close()") ; 
			
		}
	}
	
	boolean validateNoOfCopies(String copies){
		boolean flag=false;
		if(copies==null){
			return false;
		}
		if(copies.equals("")){
			return false;
		}
		try{
			int i=Integer.parseInt(copies);
			System.out.println(i);
			flag=true;
		}
		catch(Exception e){
			flag=false;
		}
		return flag; 
	}
	
	/**
	 * gets the url of the servlet which reads the pdf file.
	 * @param url
	 * @return the byteBuffer read from the url. 
	 */
	public static ByteBuffer getFile(URL url) {
		byte[] array=null;
		try{
			URLConnection connection = url.openConnection();
		    InputStream in = connection.getInputStream();
		    int contentLength = connection.getContentLength();
		    ByteArrayOutputStream tmpOut;
		    if (contentLength != -1) {
		        tmpOut = new ByteArrayOutputStream(contentLength);
		    } else {
		        tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate size
		    }
	
		    byte[] buf = new byte[512];
		    while (true) {
		        int len = in.read(buf);
		        if (len == -1) {
		            break;
		        }
		        tmpOut.write(buf, 0, len);
		    }
		    in.close();
		    tmpOut.close(); // No effect, but good to do anyway to keep the metaphor alive
	
		    array = tmpOut.toByteArray();
	    }
	    catch (IOException e) {
			System.out.println("Exception in getFile():"+e.getMessage());
			e.printStackTrace();
		}
	    return ByteBuffer.wrap(array);
	}
	
	
	public void createPdf(){
		
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
			
			System.out.println("starting creating pdf");
			System.out.println("Html Content is:"+htmlCode);
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
	
			String path=System.getProperties().getProperty("java.io.tmpdir");
			//File f=new File(path+"print.pdf");
			File f=new File(path+"print.pdf");
			
			/*if(!f.exists()){
				f=new File(path);
				f.mkdir();
				f=new File(path+"/"+"print.pdf");
			}*/
			
			FileOutputStream fos=new FileOutputStream(f);
			baosPDF.writeTo(fos);
			fos.close();
			System.out.println("pdf created");
		}
		catch (DocumentException dex)
		{
			dex.printStackTrace();
			System.out.println("Document Exception :" +dex.getMessage());
		}
		catch (Exception dex)
		{
			dex.printStackTrace();
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
		
	
	/**
	 * @return the byteBuffer read from the url. 
	 */
	public static ByteBuffer getFileContent() {
		byte[] array=null;
		try{
			//URLConnection connection = url.openConnection();
			InputStream in = new FileInputStream(new File(System.getProperties().getProperty("java.io.tmpdir"))+"/print.pdf");
		    int contentLength = in.available();
		    ByteArrayOutputStream tmpOut;
		    if (contentLength != -1) {
		        tmpOut = new ByteArrayOutputStream(contentLength);
		    } else {
		        tmpOut = new ByteArrayOutputStream(16384); // Pick some appropriate size
		    }
	
		    byte[] buf = new byte[512];
		    while (true) {
		        int len = in.read(buf);
		        if (len == -1) {
		            break;
		        }
		        tmpOut.write(buf, 0, len);
		    }
		    in.close();
		    tmpOut.close(); // No effect, but good to do anyway to keep the metaphor alive
	
		    array = tmpOut.toByteArray();
	    }
	    catch (IOException e) {
			System.out.println("Exception in getFile():"+e.getMessage());
			e.printStackTrace();
		}
	    return ByteBuffer.wrap(array);
	}
}
	

	
	 

