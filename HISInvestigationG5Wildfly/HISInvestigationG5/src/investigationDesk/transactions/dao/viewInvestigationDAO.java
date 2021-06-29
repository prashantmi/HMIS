package investigationDesk.transactions.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import investigationDesk.vo.viewInvestigationVO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;













public class viewInvestigationDAO
  extends DataAccessObject
  implements viewInvestigationDAOi
{
  public viewInvestigationDAO(JDBCTransactionContext _tx) { super(_tx); }




  
  public List<viewInvestigationVO> getPrvTestDtlUsingAJAX(String CrNo, UserVO _UserVO, String fromwhichcall) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List<viewInvestigationVO> lstViewVO = null;
    String filename = "investigationDesk.transactions.investigationTransactionQuery";
    String queryKey = "";
    
    if (fromwhichcall != null && fromwhichcall.equals("1")) {
      queryKey = "SELECT.PRV_TEST_DETAIL_NEW";
    } else {
      queryKey = "SELECT.PRV_TEST_DETAIL";
    }  Sequence sq = new Sequence();
    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    populateMAP.put(sq.next(), CrNo);
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    if (fromwhichcall != null && fromwhichcall.equals("1")) {
      
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), CrNo);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    } 

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
          populateMAP);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("No Test / Lab  Found");
      }

      
      rs.beforeFirst();
      lstViewVO = new ArrayList<viewInvestigationVO>();
      viewInvestigationVO voViewInv = null;
      while (rs.next()) {
        voViewInv = new viewInvestigationVO();
        HelperMethods.populateVOfrmRS(voViewInv, rs);
        lstViewVO.add(voViewInv);
      
      }

    
    }
    catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
      throw new HisDataAccessException(""+e);
    } 
    return lstViewVO;
  }
}
