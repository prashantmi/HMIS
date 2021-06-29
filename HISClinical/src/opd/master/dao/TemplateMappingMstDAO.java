package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.TemplateMappingMstVO;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import opd.OpdConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

public class TemplateMappingMstDAO extends DataAccessObject implements TemplateMappingMstDAOi {

	Logger log;

	public TemplateMappingMstDAO(JDBCTransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data

	public String getCategoryName(String templateCategory, UserVO userVO) {

		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String templateCategoryName = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.CATEGORY_NAME.HGBT_TEMPLATE_CATEGORY_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), templateCategory);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				templateCategoryName = rs.getString(1);
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return templateCategoryName;
	}

	public List getDepartmentNotAdded(String templateCategory, UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List departmentList = new ArrayList();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DEPT.GBLT_DEPARTMENT_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			populateMAP.put(sq.next(), templateCategory);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				departmentList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return departmentList;
	}

	public List getUnitNotAdded(TemplateMappingMstVO templateMappingVO,
			UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List unitList = new ArrayList();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DEPTUNIT.HGBT_UNIT_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getDeptCode());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				unitList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return unitList;
	}

	public List getWardNotAdded(TemplateMappingMstVO templateMappingVO,
			UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List wardList = new ArrayList();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.WARD.HIPT_WARD_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getDeptUnitCode());
			populateMAP.put(sq.next(), templateMappingVO.getDeptCode());
			populateMAP.put(sq.next(), templateMappingVO.getDeptUnitCode());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				wardList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return wardList;
	}

	public List getTemplateListNotAdded(String categoryCode,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List templateList = new ArrayList();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.TEMPLATE_NOT_ADDED.HGBT_TEMPLATE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), categoryCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			if (rs.next()) {
				templateList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return templateList;
	}

	public void saveTemplateMapping(TemplateMappingMstVO templateMappingVO,
			UserVO userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_TEMPLATE_MAPPING_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateId());
			populateMAP.put(sq.next(), templateMappingVO.getIsDefault());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), templateMappingVO.getDeptCode());
			populateMAP.put(sq.next(), templateMappingVO.getDeptUnitCode());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), templateMappingVO.getWardCode());

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}

	}
	
	public void modifyTemplateMapping(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_TEMPLATE_MAPPING_MST";
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
					+ e);
		}
		log.info(query);
		
		try {
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}
		
	}
	
	public void modifyInsertTemplateMapping(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_TEMPLATE_MAPPING_MST";
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
					+ e);
		}
		log.info(query);
		
		try {
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateId());
			populateMAP.put(sq.next(), templateMappingVO.getIsDefault());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), templateMappingVO.getDeptCode());
			populateMAP.put(sq.next(), templateMappingVO.getDeptUnitCode());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), templateMappingVO.getWardCode());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
			
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"
					+ e);
		}
		
	}
	
	public List getAddedTemplateList(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List templateList = new ArrayList();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.TEMPLATE_ADDED.HGBT_TEMPLATE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next()) {
				templateList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return templateList;
	}
	
	public TemplateMappingMstVO[] getTemplateMapping(TemplateMappingMstVO templateMappingVO,int flag,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		TemplateMappingMstVO templateMappingVOs[];
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey =""; 
		try {
			switch(flag){
			case 0:	
					queryKey="SELECT.HGBT_TEMPLATE_MAPPING_MST";
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), userVO.getHospitalCode());
					populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
					populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
					populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
					
					break;
			case 1:
					queryKey="SELECT.IF_UNIT_IS_NULL.HGBT_TEMPLATE_MAPPING_MST";
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
					populateMAP.put(sq.next(), userVO.getHospitalCode());
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
										
					break;
			case 2:
					queryKey="SELECT.IF_WARD_IS_NULL.HGBT_TEMPLATE_MAPPING_MST";
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
					populateMAP.put(sq.next(), userVO.getHospitalCode());
					populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
					populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
			}
		
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
					+ e);
		
		} log.info(query);
		
		try {
			int len=0;
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next()) {
				rs.beforeFirst();
				while(rs.next()) len++;
				templateMappingVOs=new TemplateMappingMstVO[len];
				rs.beforeFirst();
				for(int i=0;rs.next();i++){
					templateMappingVOs[i]=new TemplateMappingMstVO();
					templateMappingVOs[i].setTemplateId(rs.getString(1));
					templateMappingVOs[i].setTemplateName(rs.getString(2));
					templateMappingVOs[i].setIsDefault(rs.getString(3));
					templateMappingVOs[i].setDeptCode(rs.getString(4));
					templateMappingVOs[i].setDeptUnitCode(rs.getString(5));
					templateMappingVOs[i].setWardCode(rs.getString(6));
				}
				
			} else {
				
				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {
			
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}
		
		return templateMappingVOs;
	}
	
	
	public TemplateMappingMstVO checkBeforeModify(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="CHECK.BEFORE_MODIFY.HGBT_TEMPLATE_MAPPING_MST"; 
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		log.info(query);

		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateCategory());
			populateMAP.put(sq.next(), templateMappingVO.getTemplateMappingId());
					
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next()) {
				templateMappingVO.setDeptUnitCode(rs.getString(1));
				templateMappingVO.setWardCode(rs.getString(2));
			} else {

				throw new HisRecordNotFoundException("Record Not Found");
			}
		} catch (Exception e) {

			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}

		return templateMappingVO;
	}
	
	public List getAllUnits(String deptCode,UserVO userVO) {
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="SELECT.DEPTUNIT.BY_DEPT_CODE.HGBT_UNIT_MST"; 
		List unitList=new ArrayList();
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
					+ e);
		}
		log.info(query);
		
		try {
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), deptCode);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"TemplateMappingMstDAO.populateMAP::" + e);
		}
		try {
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next()) {
				unitList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			} else {
				
				throw new HisRecordNotFoundException("Unit Not Found");
			}
		} catch (Exception e) {
			
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException(
						"HelperMethodsDAO.getResultset" + e);
		}
		
		return unitList;
	}
	
}
