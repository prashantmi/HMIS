package opd.icdsearchengine.dao;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import opd.icdsearchengine.ICDSearchEngineConfig;
import opd.icdsearchengine.vo.ICDDiseaseDetailVO;
import opd.icdsearchengine.vo.ICDSearchEngineVO;
import opd.icdsearchengine.vo.ResultDiseaseListVO;
import opd.icdsearchengine.vo.ResultIndexListVO;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
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

public class ICDSearchEngineDAO extends DataAccessObject implements ICDSearchEngineDAOi
{
	private static String LUCENE_PATH = "";
	private static int HITS_PER_PAGE = 200000;

	static
	{
			// Changed By Pragya dated 2016.07.19 as per requirement in generic path Fetch 
		//String OS_NAME = System.getProperty("os.name");
		//if (OS_NAME.startsWith("Linux"))
			//LUCENE_PATH = (ICDSearchEngineConfig.LUCENE_PATH_DISEASE_LIST_FOR_LINUX);
		//else
			//LUCENE_PATH = (ICDSearchEngineConfig.LUCENE_PATH_DISEASE_LIST_FOR_WINDOWS);
		LUCENE_PATH = HISConfig.APPLICATION_SERVER_FOLDER_PATH + ICDSearchEngineConfig.LUCENE_PATH_DISEASE_LIST;
	}

	
	public ICDSearchEngineDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	// Getting All Chronic Diseases
	public List<Entry> getAllChronicDiseases(UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_CHRONIC_DISEASES);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_CHRONIC_DISEASES::" + e);
		}

		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllChronicDiseases" + e + errorMsg);
		}
		return lst;
	}

	// Getting All Main Disease Types
	public List<Entry> getAllMainDiseaseTypes(UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_MAIN_DISEASE_TYPES);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_MAIN_DISEASE_TYPES::" + e);
		}

		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllMainDiseaseTypes" + e + errorMsg);
		}
		return lst;
	}

	// Getting All Disease Types
	public List<Entry> getAllDiseaseTypes(UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_DISEASE_TYPES);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_DISEASE_TYPES::" + e);
		}

		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDiseaseTypes" + e + errorMsg);
		}
		return lst;
	}

	// Getting All ICD Groups
	public List<Entry> getAllICDGroups(UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_ICD_GROUPS);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_ICD_GROUPS::" + e);
		}

		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllICDGroups" + e + errorMsg);
		}
		return lst;
	}

	// Getting Result for ICD Disease List
	public List<ResultDiseaseListVO> getResultICDDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		String directoryObjectFilePath = null;
		IndexSearcher searcher = null;
		Directory index = null;
		ScoreDoc[] hits=null;
		List<ResultDiseaseListVO> lst = new ArrayList<ResultDiseaseListVO>();
		try
		{
			directoryObjectFilePath = LUCENE_PATH + ICDSearchEngineConfig.LUCENE_INDEX_NAME_DISEASE_LIST;

			BooleanQuery query = new BooleanQuery();
			if(_engineVO.getStrRecordType().equals("-1"))
				query.add(new TermQuery(new Term("recordType", "2")), BooleanClause.Occur.MUST_NOT);
			else
				query.add(new TermQuery(new Term("recordType", _engineVO.getStrRecordType())), BooleanClause.Occur.MUST);
//			IF _engineVO.getStrRecordType() = '-1' THEN
//			  QRY := QRY || ' AND HGNUM_RECORD_TYPE NOT IN (2)';
//			ELSE
//			  QRY := QRY || ' AND HGNUM_RECORD_TYPE=' || _engineVO.getStrRecordType();
//			END IF;

			if (_engineVO.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE))
			{
				if (_engineVO.getStrExactIcdCode() != null && !_engineVO.getStrExactIcdCode().trim().equals("")) 
					query.add(new TermQuery(new Term("diseaseCode", _engineVO.getStrExactIcdCode().trim().toUpperCase())), BooleanClause.Occur.MUST);
			}
//			IF exact_icd_code IS NOT NULL THEN
//			  QRY := QRY || ' AND UPPER(HGBSTR_DISEASE_CODE) = UPPER(''' || exact_icd_code || ''')';
//			END IF;
			if (_engineVO.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_ICD_CODE))
			{
				if (_engineVO.getStrIcdCodeName() != null && !_engineVO.getStrIcdCodeName().trim().equals("")) 
					query.add(new WildcardQuery(new Term("diseaseCode", _engineVO.getStrIcdCodeName().trim().toUpperCase()+"*")), BooleanClause.Occur.MUST);
			}
//			IF icd_code IS NOT NULL THEN
//			  QRY := QRY || ' AND UPPER(TRIM(HGBSTR_DISEASE_CODE)) LIKE UPPER(''' || icd_code || '%'')';
//			END IF;

			if (_engineVO.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME))
			{
				if (_engineVO.getStrIcdDiseaseName() != null && !_engineVO.getStrIcdDiseaseName().trim().equals(""))
					query.add(new WildcardQuery(new Term("upperfullDiseaseName", "*"+_engineVO.getStrIcdDiseaseName().toUpperCase()+"*")), BooleanClause.Occur.MUST);
//					IF disease_name IS NOT NULL THEN
//					   QRY := QRY || ' AND UPPER(HGBSTR_DISEASE) LIKE UPPER(''%' || disease_name || '%'')';
//					END IF;
				if (_engineVO.getStrMainDiseaseTypeCode() != null && !_engineVO.getStrMainDiseaseTypeCode().trim().equals(""))
					query.add(new TermQuery(new Term("mainDiseaseTypeCode", _engineVO.getStrMainDiseaseTypeCode())), BooleanClause.Occur.MUST);
//					IF disease_main_type_code IS NOT NULL THEN
//					   QRY := QRY || ' AND EXISTS (SELECT HGBNUM_DISEASE_TYPE_CODE FROM HGBT_DISEASE_TYPE_MST '
//					     || ' WHERE HGBNUM_DISEASE_TYPE_CODE=X.HGBNUM_DISEASE_TYPE_CODE AND HGNUM_MAIN_TYPE=' || disease_main_type_code || ')';
//					END IF;
				if (_engineVO.getStrDiseaseTypeCode() != null && !_engineVO.getStrDiseaseTypeCode().trim().equals(""))
					query.add(new TermQuery(new Term("diseaseTypeCode", _engineVO.getStrDiseaseTypeCode())), BooleanClause.Occur.MUST);
//				IF disease_type_code IS NOT NULL THEN
//				   QRY := QRY || ' AND HGBNUM_DISEASE_TYPE_CODE = ' || disease_type_code;
//				END IF;
				
//--				if (_engineVO.getStrChronicDiseaseCode() != null && !_engineVO.getStrChronicDiseaseCode().trim().equals(""))
//--					query.add(new TermQuery(new Term("diseaseTypeCode", _engineVO.getStrChronicDiseaseCode())), BooleanClause.Occur.MUST);

//				IF chronic_code IS NOT NULL THEN
//				   QRY := QRY || ' AND HGBSTR_DISEASE_CODE IN (SELECT DISTINCT HGBSTR_DISEASE_CODE  FROM HGBT_ICD_MAPPING_MST'
//				     || ' WHERE GNUM_ISVALID=1 AND GNUM_HOSPITAL_CODE=' || hosp_code || ' AND HGBNUM_MAPPING_TYPE=2 '
//					 || ' AND HGBSTR_MAPPING_ID=' || chronic_code || ')';
//				END IF;
				
			}

//--			IF order_by = '0' THEN
//--	  QRY := QRY || ' ORDER BY HGBSTR_DISEASE';
//--	ELSIF order_by = '1' THEN
//--	  QRY := QRY || ' ORDER BY TRIM(HGBSTR_DISEASE_CODE), HGNUM_RECORD_TYPE, HGBSTR_DISEASE';
//--	END IF;

			BooleanQuery finalQuery = new BooleanQuery();
			finalQuery.add(query, BooleanClause.Occur.SHOULD);
			index = (NIOFSDirectory)new NIOFSDirectory(new File(directoryObjectFilePath));
			searcher = new IndexSearcher(index);
			TopScoreDocCollector collector = TopScoreDocCollector.create(HITS_PER_PAGE, true);
			searcher.search(finalQuery, collector);
			hits = collector.topDocs().scoreDocs;
			for(int j=0;j<hits.length;++j) 
			{
				int docId = hits[j].doc;
				Document d = searcher.doc(docId);
				ResultDiseaseListVO vo = new ResultDiseaseListVO();
				vo.setDiseaseCode(d.get("diseaseCode"));
				vo.setFullDiseaseName(d.get("fullDiseaseName"));
				vo.setRecordType(d.get("recordType"));
				vo.setSlNo(d.get("slNo"));
				vo.setHaveChildren(d.get("haveChildren"));
				lst.add(vo);
			}
			
			//	vo = HelperMethods.populateVOfrmRS(ResultDiseaseListVO.class, rs);
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
			throw new HisDataAccessException("LUCENE_INDEX_NAME_DISEASE_LIST::" + e);
		}

		return lst;
	}

	/*public List<ResultDiseaseListVO> getResultICDDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_RESULT_DISEASE_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _engineVO.getStrRecordType());
			strProc.addInParameter(3, Types.VARCHAR, _engineVO.getStrOrderBy());
			
			strProc.addInParameter(4, Types.VARCHAR, null);
			strProc.addInParameter(5, Types.VARCHAR, null);
			strProc.addInParameter(6, Types.VARCHAR, null);
			strProc.addInParameter(7, Types.VARCHAR, null);
			strProc.addInParameter(8, Types.VARCHAR, null);
			strProc.addInParameter(9, Types.VARCHAR, null);
			if (_engineVO.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_EXACT_ICD_CODE))
			{
				if (_engineVO.getStrExactIcdCode() != null && !_engineVO.getStrExactIcdCode().trim().equals("")) 
					strProc.addInParameter(4, Types.VARCHAR, _engineVO.getStrExactIcdCode());
			}
			else if (_engineVO.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_ICD_CODE))
			{
				if (_engineVO.getStrIcdCodeName() != null && !_engineVO.getStrIcdCodeName().trim().equals("")) 
					strProc.addInParameter(5, Types.VARCHAR, _engineVO.getStrIcdCodeName());
			}
			else if (_engineVO.getStrSearchType().equals(ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME))
			{
				if (_engineVO.getStrIcdDiseaseName() != null && !_engineVO.getStrIcdDiseaseName().trim().equals(""))
					strProc.addInParameter(6, Types.VARCHAR, _engineVO.getStrIcdDiseaseName());
				if (_engineVO.getStrMainDiseaseTypeCode() != null && !_engineVO.getStrMainDiseaseTypeCode().trim().equals(""))
					strProc.addInParameter(7, Types.VARCHAR, _engineVO.getStrMainDiseaseTypeCode());
				if (_engineVO.getStrDiseaseTypeCode() != null && !_engineVO.getStrDiseaseTypeCode().trim().equals(""))
					strProc.addInParameter(8, Types.VARCHAR, _engineVO.getStrDiseaseTypeCode());
				if (_engineVO.getStrChronicDiseaseCode() != null && !_engineVO.getStrChronicDiseaseCode().trim().equals(""))
					strProc.addInParameter(9, Types.VARCHAR, _engineVO.getStrChronicDiseaseCode());
			}

			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.REF);//OracleTypes.CURSOR);

			
			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(10);
			rs = (ResultSet) strProc.getParameterAt(11);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_RESULT_DISEASE_LIST::" + e);
		}

		ValueObject[] vo = {};
		List<ResultDiseaseListVO> lst = new ArrayList<ResultDiseaseListVO>();
		try
		{
			vo = HelperMethods.populateVOfrmRS(ResultDiseaseListVO.class, rs);
			for (ValueObject v : vo)
				lst.add((ResultDiseaseListVO) v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getResultICDDiseaseList" + e + errorMsg);
		}
		return lst;
	}*/

	// Getting Result for ICD Sub Disease List thrrough Lucene
	public List<ResultDiseaseListVO> getResultICDSubDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		String directoryObjectFilePath = null;
		IndexSearcher searcher = null;
		Directory index = null;
		ScoreDoc[] hits=null;
		List<ResultDiseaseListVO> lst = new ArrayList<ResultDiseaseListVO>();
		try
		{
			directoryObjectFilePath = LUCENE_PATH + ICDSearchEngineConfig.LUCENE_INDEX_NAME_DISEASE_LIST;

			BooleanQuery query = new BooleanQuery();
			query.add(new TermQuery(new Term("parentCode", _engineVO.getStrIcdCodeMain().trim().toUpperCase())), BooleanClause.Occur.MUST);

			if(_engineVO.getStrRecordType().equals("-1"))
				query.add(new TermQuery(new Term("recordType", "2")), BooleanClause.Occur.MUST_NOT);
			else
				query.add(new TermQuery(new Term("recordType", _engineVO.getStrRecordType())), BooleanClause.Occur.MUST);
//		  	 ' AND UPPER(HGBSTR_PARENT_CODE) = UPPER(''' || icd_code || ''')';
//
//		  	IF record_type = '-1' THEN
//		  	  QRY := QRY || ' AND HGNUM_RECORD_TYPE NOT IN (2)';
//		  	ELSE
//		  	  QRY := QRY || ' AND HGNUM_RECORD_TYPE=' || record_type;
//		  	END IF;

			BooleanQuery finalQuery = new BooleanQuery();
			finalQuery.add(query, BooleanClause.Occur.SHOULD);
			index = (NIOFSDirectory)new NIOFSDirectory(new File(directoryObjectFilePath));
			searcher = new IndexSearcher(index);
			TopScoreDocCollector collector = TopScoreDocCollector.create(HITS_PER_PAGE, true);
			searcher.search(finalQuery, collector);
			hits = collector.topDocs().scoreDocs;
			for(int j=0;j<hits.length;++j) 
			{
				int docId = hits[j].doc;
				Document d = searcher.doc(docId);
				ResultDiseaseListVO vo = new ResultDiseaseListVO();
				vo.setDiseaseCode(d.get("diseaseCode"));
				vo.setFullDiseaseName(d.get("fullDiseaseName"));
				vo.setRecordType(d.get("recordType"));
				vo.setSlNo(d.get("slNo"));
				vo.setParentCode(d.get("parentCode"));				
				vo.setHaveChildren(d.get("haveChildren"));
				lst.add(vo);
			}
			//vo = HelperMethods.populateVOfrmRS(ResultDiseaseListVO.class, rs);
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
			throw new HisDataAccessException("getResultICDSubDiseaseList::" + e);
		}
		return lst;
	}

	/*// Getting Result for ICD Sub Disease List
	public List<ResultDiseaseListVO> getResultICDSubDiseaseList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_RESULT_SUB_DISEASE_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _engineVO.getStrRecordType());
			strProc.addInParameter(3, Types.VARCHAR, _engineVO.getStrIcdCodeMain());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);

			
			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_DISEASE_TYPES::" + e);
		}

		ValueObject[] vo = {};
		List<ResultDiseaseListVO> lst = new ArrayList<ResultDiseaseListVO>();
		try
		{
			vo = HelperMethods.populateVOfrmRS(ResultDiseaseListVO.class, rs);
			for (ValueObject v : vo)
				lst.add((ResultDiseaseListVO) v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getResultICDSubDiseaseList" + e + errorMsg);
		}
		return lst;
	}*/

	// Getting Detailed ICD Disease VO
	public ICDDiseaseDetailVO getDetailICDDiseaseVO(ICDDiseaseDetailVO _disVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_DETAIL_ICD_DISEASE_VO);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _disVO.getDiseaseCode());
			strProc.addInParameter(3, Types.VARCHAR, _disVO.getRecordType());
			strProc.addInParameter(4, Types.VARCHAR, _disVO.getSlNo());
			strProc.addOutParameter(5, Types.VARCHAR);
			strProc.addOutParameter(6, Types.REF);//OracleTypes.CURSOR);

			
			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(5);
			rs = (ResultSet) strProc.getParameterAt(6);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_DETAIL_ICD_DISEASE_VO::" + e);
		}

		
		ICDDiseaseDetailVO icdDisDtlVO = new ICDDiseaseDetailVO();
		ValueObject[] vo = {}; 
		try
		{
			vo = HelperMethods.populateVOfrmRS(ICDDiseaseDetailVO.class, rs);
			if(vo.length>0)
				icdDisDtlVO = (ICDDiseaseDetailVO) vo[0];
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getDetailICDDiseaseVO" + e + errorMsg);
		}
		return icdDisDtlVO;
	}

	// Getting Result for Index Term List
	public List<ResultIndexListVO> getResultIndexTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_RESULT_INDEX_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _engineVO.getStrIndexSearchString());
			
			strProc.addOutParameter(3, Types.VARCHAR);
			strProc.addOutParameter(4, Types.REF);//OracleTypes.CURSOR);
			
			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_RESULT_INDEX_LIST::" + e);
		}

		ValueObject[] vo = {};
		List<ResultIndexListVO> lst = new ArrayList<ResultIndexListVO>();
		try
		{
			vo = HelperMethods.populateVOfrmRS(ResultIndexListVO.class, rs);
			for (ValueObject v : vo)
				lst.add((ResultIndexListVO) v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getResultIndexTermList" + e + errorMsg);
		}
		return lst;
	}

	// Getting List of Index Term List
	public List<ResultIndexListVO> getIndexTermList(UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_INDEX_TERMS);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(2, Types.VARCHAR);
			strProc.addOutParameter(3, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(2);
			rs = (ResultSet) strProc.getParameterAt(3);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_INDEX_TERMS::" + e);
		}

		ValueObject[] vo = {};
		List<ResultIndexListVO> lst = new ArrayList<ResultIndexListVO>();
		try
		{
			vo = HelperMethods.populateVOfrmRS(ResultIndexListVO.class, rs);
			for (ValueObject v : vo)
				lst.add((ResultIndexListVO) v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getIndexTermList" + e + errorMsg);
		}
		return lst;
	}

	// Getting List of Modifier Term of Index Term
	public List<ResultIndexListVO> getIndexModiTermList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_SUB_TREE_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _engineVO.getStrIndexCode());
			strProc.addOutParameter(3, Types.VARCHAR);
			strProc.addOutParameter(4, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_SUB_TREE_LIST::" + e);
		}

		ValueObject[] vo = {};
		List<ResultIndexListVO> lst = new ArrayList<ResultIndexListVO>();
		try
		{
			vo = HelperMethods.populateVOfrmRS(ResultIndexListVO.class, rs);
			for (ValueObject v : vo)
				lst.add((ResultIndexListVO) v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getIndexModiTermList" + e + errorMsg);
		}
		return lst;
	}

	// Getting List of Modifier Term of Modifiers Terms
	public List<ResultIndexListVO> getModifierTermTreeList(ICDSearchEngineVO _engineVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_SUB_SUB_TREE_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _engineVO.getStrIndexCode());
			strProc.addInParameter(3, Types.VARCHAR, _engineVO.getStrIndexModifierID());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_SUB_SUB_TREE_LIST::" + e);
		}

		ValueObject[] vo = {};
		List<ResultIndexListVO> lst = new ArrayList<ResultIndexListVO>();
		try
		{
			vo = HelperMethods.populateVOfrmRS(ResultIndexListVO.class, rs);
			for (ValueObject v : vo)
				lst.add((ResultIndexListVO) v);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getModifierTermTreeList" + e + errorMsg);
		}
		return lst;
	}

	// Getting List of All Sub Groups By Group
	public List<Entry> getAllSubGroupByGroup(String _icdGroupCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_ICD_SUB_GROUPS);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _icdGroupCode);
			strProc.addOutParameter(3, Types.VARCHAR);
			strProc.addOutParameter(4, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_ICD_SUB_GROUPS::" + e);
		}

		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException  :getAllSubGroupByGroup" + e + errorMsg);
		}
		return lst;
	}

	// Getting List of All Diseases By Sub Groups
	public List<Entry> getAllDiseasesBySubGroup(String _icdSubGroupCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = "";
		try
		{
			Procedure strProc = new Procedure(ICDSearchEngineConfig.PROCEDURE_GET_LIST_ALL_ICD_DISEASES);
			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, _icdSubGroupCode);
			strProc.addOutParameter(3, Types.VARCHAR);
			strProc.addOutParameter(4, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PROCEDURE_GET_LIST_ALL_ICD_DISEASES::" + e);
		}

		List<Entry> lst = new ArrayList<Entry>();
		try
		{
			lst = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDiseasesBySubGroup" + e + errorMsg);
		}
		return lst;
	}
}
