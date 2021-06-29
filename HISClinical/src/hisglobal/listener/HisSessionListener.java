package hisglobal.listener;

import hisglobal.exceptions.TransactionException;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;

import startup.UserMgmtConfigXmlHandler;
import startup.login;

public class HisSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {
		UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
		int sessionTimeOutMinutes=configHandler.getSessionTimeOut();
		int sessionTimeOutSeconds=sessionTimeOutMinutes*60;
		arg0.getSession().setMaxInactiveInterval(sessionTimeOutSeconds);
		
		
		////////////////////////////////////////////////////////////////
		/*ArrayList name=new ArrayList();
		StandardAnalyzer analyzer = new StandardAnalyzer();
		Directory index = new RAMDirectory();
		
		JDBCTransactionContext tx=new JDBCTransactionContext();
		tx.begin();
		
		
		IndexWriter writer;
		try {
			
			writer = new IndexWriter(index, analyzer, true,
			        IndexWriter.MaxFieldLength.UNLIMITED);
		

		String sql = "select distinct(HRGNUM_PUK), HRGSTR_FNAME from HRGT_DAILY_PATIENT_DTL";
		ResultSet rs;
			System.out.println("Query= "+sql);
			rs = HelperMethodsDAO.executeQuery(tx.getConnection(),sql);
		
		
			while (rs.next()) {
				Document doc = new Document();
			    doc.add(new Field("id", rs.getString("HRGNUM_PUK"), Field.Store.YES, Field.Index.UN_TOKENIZED));
			    doc.add(new Field("firstname", rs.getString("HRGSTR_FNAME"), Field.Store.YES, Field.Index.TOKENIZED));  
			    // ... repeat for each column in result set
			    
			    writer.addDocument(doc);

			}
			
			 writer.close();
			 
			 ServletContext context  =arg0.getSession().getServletContext();
			 context.setAttribute("RAM_INDEX", index);
			} catch (CorruptIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransactionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} 

		
		
		tx.close();
		
		
		*/
		
		
		
		//////////////////////////////////////////////////////////////////
		

	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		ServletContext context  =arg0.getSession().getServletContext();
		HttpSession session = arg0.getSession();
		
		///////Removing user from LOGIN_SUER list
		ArrayList loginUserList = (ArrayList) context.getAttribute("LOGIN_USER");
		String[] temp;
		String val="";	
		String userId=(String)session.getAttribute("USER_ID");
		String ipAddress=(String)session.getAttribute("IP_ADDR");
		login loginObj=new login();
		
		if(loginUserList != null)
		{
			for(int i=0;i<loginUserList.size();i++)
			{
				val = (String)loginUserList.get(i);
				temp =val.split("#");
				System.out.println("User Id From Context Session In HisSessionListener"+userId);
				System.out.println("Login User List UserName"+temp[1]);
				
				if(temp[0].equals(userId) && temp[2].equals(ipAddress))
				{
					////Removing user from Login user List
					System.out.println("Context session Id"+session.getId());
					System.out.println("Login User List session Id"+temp[3]);
					loginUserList.remove(i);
					
					//updating user Log
					loginObj.logoutUser((String)session.getAttribute("SEATID"),temp[2],(String)session.getAttribute("HOSPITAL_CODE"));
				}
			}
		}
		context.removeAttribute("LOGIN_USER");
		context.setAttribute("LOGIN_USER",loginUserList);
		
		///////////Adding To session Invalid List///////
		
		/*ArrayList invalidSessionList = (ArrayList) context.getAttribute("INVALID_SESSION_USER");
		if(invalidSessionList==null)
			invalidSessionList=new ArrayList();
		invalidSessionList.add(session.getAttribute("USER_ID"));
		context.setAttribute("INVALID_SESSION_USER", invalidSessionList);*/
	}

}
