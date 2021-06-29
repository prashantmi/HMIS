package hisglobal.utility.burnDisc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;

import javax.swing.JApplet;

public class CreateFileApplet extends JApplet {
	
	String fileName="";
	String []fileNameArray;
	String winPath="";
	String linuxPath="";
	String serverOS="";
	String path="";
	String clientPath="";
	File f=null;
	public void init() {
			
		winPath=getParameter("winPath");
		linuxPath=getParameter("linuxPath");
		serverOS=getParameter("serverOS");
		fileName=getParameter("fileName");
		fileNameArray=fileName.split("#");
		if(serverOS.startsWith("Win"))
			path=winPath;
		else
			path=linuxPath;
		try{
			System.out.println("Inside init-----------------------------------");
			System.out.println("get code Base :" + getCodeBase());
			 
			URL url=null;
			
			String systemPath=System.getProperties().getProperty("java.library.path");
			String pathArray[]=systemPath.split(";");
			String osPath="";
			for(int i=0;i<pathArray.length;i++){
				if(pathArray[i].indexOf("system32")!=-1){
					osPath=pathArray[i];
					break;
				}
			}
			f=new File(osPath+"\\"+"JStarBurn.dll");
			url=new URL(getCodeBase() + "readPdfServlet?path=" +"C:\\AHIMS\\burn" + "&fileName=JStarBurn.dll");
			if(!f.exists()){
				FileOutputStream fos=new FileOutputStream(f);
				//fos.write(getFile(url).array());
				fos.write(getFile(url));
				fos.close();
				f=null;
			}
			
			f=new File(osPath+"\\"+"StarBurn.dll");
			url=new URL(getCodeBase() + "readPdfServlet?path=" +"C:\\AHIMS\\burn"  + "&fileName=StarBurn.dll");
			if(!f.exists()){
				FileOutputStream fos=new FileOutputStream(f);
				//fos.write(getFile(url).array());
				fos.write(getFile(url));
				fos.close();
				f=null;
			}
			
			for(int i=0;i<fileNameArray.length;i++){
				url=new URL(getCodeBase() + "readPdfServlet?path=" +path + "&fileName=" + fileNameArray[i]);
				
				if(System.getProperties().getProperty("os.name").startsWith("Win")){
					//f=new File(winPath+fileNameArray[i]);
					
					clientPath=winPath;
				}
				else{
					//f=new File(linuxPath+fileNameArray[i]);
					clientPath=linuxPath;
				}
				
				f=new File(clientPath+"\\"+fileNameArray[i]);
				
				if(!f.exists()){
					f=new File(clientPath);
					f.mkdir();
					f=new File(clientPath+"\\"+fileNameArray[i]);
				}	
				FileOutputStream fos=new FileOutputStream(f);
				//fos.write(getFile(url).array());
				fos.write(getFile(url));
				
				fos.close();
			}
		}
		catch (Exception e) {
			System.out.println("Exception in init");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * gets the url of the servlet which reads the pdf file.
	 * @param url
	 * @return the byteBuffer read from the url. 
	 */
	//public static ByteBuffer getFile(URL url) {
	public static byte[] getFile(URL url) {
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
	    //return ByteBuffer.wrap(array);
	    return array;
	}
	
}
	
	 

