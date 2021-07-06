/**
 * GetPatientDtlJob class implements execute method of Job Interface
 * calling of the execute method will depends on the 
 * trigger i.e when the scheduler has been triggered  
 */
package hisglobal.presentation;

import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.TransactionException;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import registration.RegistrationConfig;
import registration.bo.delegate.EssentialDelegate;

public class GetPatientDtlJob extends HttpServlet implements Job{
	
	static StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_24);
	static Directory index = null;
	static ServletContext ctx;
	static Document doc = null;
	static IndexWriter writer;
	static File indexFile=null;
	
	public void init(ServletConfig config)
	{
		ctx=config.getServletContext();
				
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		ResultSet rs=null;
		try {
			
			rs=new EssentialDelegate().getAllPatientList();
			
			}
			catch (TransactionException e) {
				e.printStackTrace();
			} 
			catch (Exception e) {
				e.printStackTrace();
			
		} 

	}
	
public static void addTODocument(ResultSet rs) throws JobExecutionException {
	 	
		int totalSize=0;
		try {
			Runtime rt=Runtime.getRuntime();
			
			String OS_NAME = System.getProperties().getProperty("os.name");
			String indexFileName="";
			if (OS_NAME.startsWith("Linux"))
			{
				indexFileName=(RegistrationConfig.LUCENE_DIRECTORY_OBJECT_FOR_LINUX);
			}
			else
			{
				indexFileName=(RegistrationConfig.LUCENE_DIRECTORY_OBJECT_FOR_WINDOWS);
			}
			rs.last();
			 double sizeOfResultSet=rs.getRow();
			 rs.beforeFirst();
			 int noOfWriters=(int)Math.ceil(sizeOfResultSet/RegistrationConfig.SIZE_OF_LUCENE_DIRECTORY);
			
			 Directory[] indexDirectory=new NIOFSDirectory[noOfWriters];
			 
			
			String[] directoryObjectFIleNameArray=new String[noOfWriters];

			Field crno=new Field("crNo","", Field.Store.YES, Field.Index.NOT_ANALYZED);
			Field firstName=new Field("firstName","", Field.Store.YES, Field.Index.NOT_ANALYZED);
			Field middleName=new Field("middleName","", Field.Store.YES, Field.Index.ANALYZED); 
			Field lastName=new Field("lastName","", Field.Store.YES, Field.Index.ANALYZED);
			Field gender=new Field("gender","", Field.Store.YES, Field.Index.ANALYZED);
			Field genderCode=new Field("genderCode", "", Field.Store.YES, Field.Index.ANALYZED);
			Field age=new Field("age","", Field.Store.YES, Field.Index.ANALYZED);
			Field dob=new Field("dob","" , Field.Store.YES, Field.Index.NOT_ANALYZED);
			Field registrationDate=new Field("RegistrationDate","", Field.Store.YES, Field.Index.ANALYZED);
			Field address=new Field("address", "", Field.Store.YES, Field.Index.ANALYZED);
			Field fatherName=new Field("fatherName", "", Field.Store.YES, Field.Index.NOT_ANALYZED);
			
			for(int i=0;i<indexDirectory.length;i++)
			{
				try{
					int count=0;
					
					indexDirectory[i]=new NIOFSDirectory(new File(indexFileName+i));
					directoryObjectFIleNameArray[i]=indexFileName+i;
					writer=new IndexWriter(indexDirectory[i],analyzer,true,MaxFieldLength.UNLIMITED);
					
					
					doc = new Document();
					//adding the record to the document
					doc.add(crno);
					doc.add(firstName);
					doc.add(middleName);
					doc.add(lastName);
					doc.add(gender);
					doc.add(genderCode);
					doc.add(age);
					doc.add(dob);
					doc.add(registrationDate);
					doc.add(address);
					doc.add(fatherName);
					while(rs.next())
					{
						//System.out.println("menory available before="+rt.freeMemory());
						crno.setValue((rs.getString(1)!=null)?rs.getString(1):"");
						firstName.setValue(rs.getString(2)!=null?rs.getString(2):"");
						middleName.setValue(rs.getString(3)!=null?rs.getString(3):"");
						lastName.setValue(rs.getString(4)!=null?rs.getString(4):"");
						gender.setValue(rs.getString(6)!=null?rs.getString(6):"");
						genderCode.setValue(rs.getString(5)!=null?rs.getString(5):"");
						age.setValue(rs.getString(7)!=null?rs.getString(7):"");
						dob.setValue(rs.getString(8)!=null?rs.getString(8):"");
						registrationDate.setValue(rs.getString(11)!=null?rs.getString(11):"");
						address.setValue(rs.getString(12)!=null?rs.getString(12):"");
						fatherName.setValue(rs.getString(10)!=null?rs.getString(10):"");
					
						writer.addDocument(doc);
						
					    
					    if(count>RegistrationConfig.SIZE_OF_LUCENE_DIRECTORY)
					    {
					    	 
					    	break;
					    }
						count++;
					    
					}
					
					
				}catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (LockObtainFailedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(writer!=null){
						writer.close();
					}	
					if(indexDirectory[i]!=null)
						indexDirectory[i].close();
				}
						//System.out.println("menory available after="+rt.freeMemory());
				}
			
			 ctx.setAttribute("DIRECTORY_OBJECT_FILE_NAME_ARRAY",directoryObjectFIleNameArray);
			 
			System.out.println("finished addDocument");
		}
			catch (HisRecordNotFoundException e) {
				e.printStackTrace();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TransactionException e) {
				e.printStackTrace();
			} 
			catch (Exception e) {
				e.printStackTrace();
			
			} 

	}

	
}
