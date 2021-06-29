package lucene;

import icdsearchengine.ICDSearchEngineConfig;
import icdsearchengine.vo.Entry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

public class ReadLuceneIndices
{
	static Document doc = null;
	static IndexWriter writer;

	// Creating Lucene Index fro Main Disease Type
	public static StringBuffer readIndexMainDiseaseType()
	{
		List<Entry> lst = new ArrayList<Entry>();

		IndexSearcher searcher=null;
		try
		{
			Directory index=null;
			String OS_NAME = System.getProperty("os.name");
			String path = "";
			if (OS_NAME.startsWith("Linux"))
			{
				path = (ICDSearchEngineConfig.LUCENE_MAIN_DISEASE_TYPE_OBJECT_FOR_LINUX);
			}
			else
			{
				path = (ICDSearchEngineConfig.LUCENE_MAIN_DISEASE_TYPE_OBJECT_FOR_WINDOWS);
			}
			String directoryObjectFileName = path + ICDSearchEngineConfig.PATH_MAIN_DISEASE_TYPE;

			//Creating queries 
			/*BooleanQuery a1AndT1 = new BooleanQuery();
			a1AndT1.add(new TermQuery(new Term("strMainDiseaseType", "*")), BooleanClause.Occur.SHOULD);*/
			/*a1AndT1.add(new TermQuery(new Term("genderCode", gender)), BooleanClause.Occur.MUST);
			a1AndT1.add(new TermQuery(new Term("fatherName", fatherName)), BooleanClause.Occur.MUST);
				if(!age.equals("")){
					a1AndT1.add(new TermQuery(new Term("age", age)), BooleanClause.Occur.MUST);
				}*/
			BooleanQuery query = new BooleanQuery();
			query.add(new WildcardQuery(new Term("strMainDiseaseType", "*")), BooleanClause.Occur.SHOULD);
			//query.add(a1AndT1, BooleanClause.Occur.SHOULD);
			//System.out.println("lucene query==="+query);
			 
			int hitsPerPage=200000;	//maximum no. of hits to be collected
			//hitsPerPage=(Integer)context.getAttribute("TOTAL_NO_OF_PATIENT"); 
			//hitsPerPage = RegistrationConfig.SIZE_OF_LUCENE_DIRECTORY;
		 
			/* TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		    searcher.search(query, collector);*/
			
			ScoreDoc[] hits=null;
			index = (NIOFSDirectory)new NIOFSDirectory(new File(directoryObjectFileName));
			searcher = new IndexSearcher(index);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
			searcher.search(query, collector);
			hits = collector.topDocs().scoreDocs;
			for(int j=0;j<hits.length;++j) 
			{
				int docId = hits[j].doc;
				Document d = searcher.doc(docId);
				Entry entObj = new Entry();
				entObj.setLabel(d.get("strMainDiseaseType"));
				entObj.setValue(d.get("strMainDiseaseTypeCode"));
				lst.add(entObj);
			}
			searcher.close();
			index.close();
		}
		catch (CorruptIndexException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		StringBuffer sb = null;
		try
		{
			sb = new StringBuffer();
			sb.append("[");
			for(Entry vo : lst)
			{
				sb.append("{");
				sb.append("label");sb.append(":");
				sb.append("\'");sb.append(vo.getLabel());sb.append("\'");sb.append(",");
				sb.append("value");sb.append(":");
				sb.append("\'");sb.append(vo.getValue());sb.append("\'");
				sb.append("}");sb.append(",");
			}
			if(lst.size()>0)	sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			sb = null;
		}
		finally
		{
		}
		return sb;
	}
	
	
	
	


	/*public static void addTODocument(ResultSet rs)
	{
	public static boolean checkForPreviousRegistration(NewRegistrationFBDuplicate fb,HttpServletRequest request,ServletContext context) {
		HttpSession session=request.getSession();

		List allPatientVoList=new ArrayList();
		PatientVO patientVo=null;

		String firstName=fb.getPatFirstName().toUpperCase().trim();
		String gender=fb.getPatGenderCode();
		String age=fb.getPatAge();
		String dob=fb.getPatDOB();
		String fatherName=fb.getPatGuardianName().toUpperCase().trim();
		IndexSearcher searcher=null;
		boolean flagFound=false;
		try{
		Directory index=null;
		//creating a single index for every request
		//searcher=(IndexSearcher)context.getAttribute(RegistrationConfig.LUCENE_INDEX_SEARCH_OBJECT);
		////if searcher object is not available in the context
		String[] directoryObjectFileName=(String[])context.getAttribute("DIRECTORY_OBJECT_FILE_NAME_ARRAY") ;
		File indexFileName=null;
//		if(searcher==null)
//		{
//			File indexFileName=null;
//			String OS_NAME = System.getProperty("os.name");
//			
//				if (OS_NAME.startsWith("Linux"))
//				{
//					indexFile=new File(RegistrationConfig.LUCENE_DIRECTORY_OBJECT_FOR_LINUX);
//				}
//				else
//				{
//					indexFile=new File(RegistrationConfig.LUCENE_DIRECTORY_OBJECT_FOR_WINDOWS);
//				}
//			
//				//index = (NIOFSDirectory)new NIOFSDirectory(new File(directoryObjectFileName));
//				//searcher = new IndexSearcher(index);
//			//	context.setAttribute(RegistrationConfig.LUCENE_INDEX_SEARCH_OBJECT, searcher);
//			
//			
//		
//		}
			//Creating queries 
			BooleanQuery a1AndT1 = new BooleanQuery();
			a1AndT1.add(new TermQuery(new Term("firstName", firstName)), BooleanClause.Occur.MUST);
			a1AndT1.add(new TermQuery(new Term("genderCode", gender)), BooleanClause.Occur.MUST);
			a1AndT1.add(new TermQuery(new Term("fatherName", fatherName)), BooleanClause.Occur.MUST);
			if(!age.equals("")){
				a1AndT1.add(new TermQuery(new Term("age", age)), BooleanClause.Occur.MUST);
			}
		BooleanQuery query = new BooleanQuery();
		query.add(a1AndT1, BooleanClause.Occur.SHOULD);
		System.out.println("lucene query==="+query);
		
		 
		int hitsPerPage;	//maximum no. of hits to be collected
		//hitsPerPage=(Integer)context.getAttribute("TOTAL_NO_OF_PATIENT"); 
		hitsPerPage=RegistrationConfig.SIZE_OF_LUCENE_DIRECTORY;

//		 TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
//	    searcher.search(query, collector);
		
		ScoreDoc[] hits=null;
		for(int i=0;i<directoryObjectFileName.length;i++)
		{
			index = (NIOFSDirectory)new NIOFSDirectory(new File(directoryObjectFileName[i]));
			searcher = new IndexSearcher(index);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
			searcher.search(query, collector);
			hits = collector.topDocs().scoreDocs;
		//	System.out.println("vaue of i="+i);
			//System.out.println("Found " + hits.length + " hits.");
			for(int j=0;j<hits.length;++j) 
				{
			      int docId = hits[j].doc;
			      Document d = searcher.doc(docId);
			      patientVo=new PatientVO();
			      patientVo.setPatCrNo(d.get("crNo"));
			      patientVo.setPatFirstName(d.get("firstName"));
			      patientVo.setPatMiddleName(d.get("middleName").toUpperCase());
			      patientVo.setPatLastName(d.get("lastName").toUpperCase());
			      patientVo.setPatGenderCode(d.get("genderCode"));
			      patientVo.setPatGender(d.get("gender"));
			      patientVo.setPatAge(d.get("age"));
			      patientVo.setPatDOB(d.get("dob"));
			      patientVo.setRegisterDate(d.get("RegistrationDate"));
			      patientVo.setPatGuardianName(d.get("fatherName"));
			      patientVo.setPatAddCity(d.get("address"));
			      String result=d.get("crNo")+"  " + d.get("firstName")+"  " + d.get("gender");
			      allPatientVoList.add(patientVo);
			
				}
			searcher.close();
			index.close();
		}
			//hits = collector.topDocs().scoreDocs;
			if(allPatientVoList.size()>0)
				flagFound=true;
			System.out.println("Found " + hits.length + " hits.");
//		ScoreDoc[] hits = collector.topDocs().scoreDocs;
//		if(hits.length>0)
//			flagFound=true;
//		System.out.println("Found " + hits.length + " hits.");
//		
//		for(int i=0;i<hits.length;++i) {
//	      int docId = hits[i].doc;
//	      Document d = searcher.doc(docId);
//	      patientVo=new PatientVO();
//	      patientVo.setPatCrNo(d.get("crNo"));
//	      patientVo.setPatFirstName(d.get("firstName"));
//	      patientVo.setPatMiddleName(d.get("middleName").toUpperCase());
//	      patientVo.setPatLastName(d.get("lastName").toUpperCase());
//	      patientVo.setPatGenderCode(d.get("genderCode"));
//	      patientVo.setPatGender(d.get("gender"));
//	      patientVo.setPatAge(d.get("age"));
//	      patientVo.setPatDOB(d.get("dob"));
//	      patientVo.setRegisterDate(d.get("RegistrationDate"));
//	      patientVo.setPatGuardianName(d.get("fatherName"));
//	      patientVo.setPatAddCity(d.get("address"));
//	      String result=d.get("crNo")+"  " + d.get("firstName")+"  " + d.get("gender");
//	      allPatientVoList.add(patientVo);
//	    //  System.out.println((i + 1) + ". " + d.get("crNo")+"  " + d.get("firstName")+"  " + d.get("gender"));
//		//}

	    // searcher can only be closed when there
	    // is no need to access the documents any more. 
	  //  searcher.close();
	   // index.close();
	    WebUTIL.setAttributeInSession(request, RegistrationConfig.ALL_PATIENT_VO_LIST, allPatientVoList);
			 
		}catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return flagFound;
	}
	}*/

}
