/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHelper;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public interface QueryConfig {

    /**
     * Convention Prefix P_ before Procedure Calls Prefix Q_ before Queries
     */
    public String P_GET_CRNO_XML_JOBS = "{call pkg_inv_report_job.getpatientforjobprocessing(?,?,?,?)}";
    //public String Q_GET_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code FROM hivt_parameter_dtl hpd WHERE hpd.hivtnum_req_dno =?";
    public String Q_GET_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code,hivstr_loinc_string, hivstr_ref_range FROM hivt_parameter_dtl hpd WHERE gnum_isvalid = 1 and hpd.hivtnum_req_dno = ? and gnum_hospital_code=?";
    // I think it would be better to pass a String 
    public String Q_DELETE_XML_JOBS = "delete from hivt_jobworkorder_data where  GNUM_STATUS_CODE = 0 and  HIVNUM_REQORCR_NO=? and HIVNUM_REQ_DNO in (?)";

    public String Q_GET_PRINTED_WITHSTANDARD_RANGES = "select hivtnum_test_printing_type from hivt_laboratory_test_mst where gnum_test_code=? and gnum_labcode=? and gnum_hospital_code=? and gnum_isvalid=1";
    // Queries for XML Job
    //  public String Q_GET_XML_WORKORDERLIST = "select crnos,sl from (SELECT HJWD.HIVNUM_REQORCR_NO as crnos, string_agg (HJWD.HIVNUM_REQ_DNO, ',' ORDER BY HJWD.HIVNUM_REQ_DNO) sl FROM hivt_jobworkorder_data hjwd   WHERE   HIVDT_ENTRY_DATE<=(SYSDATE-(1/1440)) AND HJWD.GNUM_STATUS_CODE = 1 GROUP BY HJWD.HIVNUM_REQORCR_NO) where rownum=1";
  //  public String Q_GET_XML_WORKORDERDATALIST = "SELECT HIVNUM_REQORCR_NO as patCRNoorrequestno,HIVNUM_REQ_DNO as requisitionDNo,HIVNUM_UPDATION_TYPE as updationType,GNUM_STATUS_CODE,HIVDT_ENTRY_DATE,HIVTNUM_AS_NO,GNUM_HOSPITAL_CODE as hospitalcode,HIVNUM_REQ_NO as requisitionno,HRGNUM_EPISODE_CODE as patEpisodeCode,HRGNUM_PUK as patCRNo,HGNUM_VISITNO as patVisitNo,HIVT_REQUISITION_DATE as requisitionDate,HRGSTR_EPISODE_CODE as patEpisodeCode,HBNUM_REQUISITION_TYPE as requisitionTypeCode,HGNUM_VISIT_DATE as visitDate,HGNUM_DEPT_CODE as deptCode, HGNUM_DEPTUNITCODE as detpUnitCode,GNUM_TEST_CODE as testcode,HGNUM_GROUP_CODE as groupcode,HGNUM_LAB_NO as labNo, GNUM_CURRENT_LAB_CODE as laboratoryCode,0 as sessionId,HIVNUM_UPDATION_TYPE as updateType,HIVNUM_SAMPLE_CODE AS sampleNo,HIVSTR_PAT_AGE AS patAge,GNUM_GENDER_CODE as patGender FROM hivt_jobworkorder_data WHERE HIVNUM_REQ_DNO = ANY(?)";
    // get stylesheet
    public String Q_GET_STYLESHEET = "SELECT hssm.hivtclob_stylesheet FROM hivt_stylesheet_store_mst hssm WHERE hssm.hivtnum_stylesheet_id = ?";

    // get test template
    public String Q_GET_TESTTEMPLATE_XML = "SELECT hssm.hivtclob_document FROM hivt_document_store_mst hssm WHERE hssm.hivtnum_document_id = 1";

    // get test template status for dynamic printing
    public String Q_GET_DYNAMIC_TESTTEMPLATE_STATUS = "SELECT gnum_isdynamic_template, gnum_dynamictemplate_id FROM hivt_test_mst WHERE gnum_test_code = ? and gnum_isvalid = 1 and gnum_isdynamic_template=1";
    
    // get the dynamic test template
    
    public String Q_GET_DYNAMIC_TESTTEMPLATE = "SELECT hivtstr_template_xml  FROM hivt_printing_template WHERE hivt_dynamictemplate_id=? and gnum_isvalid=1";
    public String Q_GET_DYNAMIC_GROUPTEMPLATE_STATUS =  "SELECT gnum_isdynamic_template, gnum_dynamictemplate_id FROM hivt_test_group_info_mst WHERE hgnum_group_code = ? and gnum_hospital_code = ? and gnum_lab_code = ? and gnum_is_valid = 1 LIMIT 1";
    /**
     * Queries and Procedures for generating PDF
     */
    public String Q_GET_PDF_WORKORDERLIST = "SELECT hivnum_req_no, gnum_lab_code, gnum_hospital_code FROM (SELECT   hivnum_req_no, gnum_lab_code, gnum_hospital_code FROM hivt_job_data WHERE gnum_status_code = 1 ORDER BY hivdt_entry_date, sno ASC) WHERE ROWNUM = 1";
    public String P_GET_PDF_WORKORDERLIST = "{call pkg_inv_report_job.SingleDataforPDFjob(?,?,?,?)}";
    public String P_GET_PDF_WORKORDERLISTDATA = "{call pkg_inv_report_job.getworkorderlistforpdfjob(?,?,?,?,?,?,?,?)}";
    public String P_GET_PDF_FILE_WORKORDERLISTDATA = "{call pkg_inv_report_job.getworkorderlistwihfilename(?,?,?,?,?)}";
    public String P_GET_PDF_UNIQUE_CODE = "{call pkg_inv_report_job.getmaxtransactionnoforpdf(?,?,?,?,?,?)}";
    public String Q_PUT_PDF_JOB = "INSERT INTO HIVT_JOB_DATA(GNUM_LAB_CODE, HIVNUM_REQ_NO,GNUM_STATUS_CODE,HIVTNUM_AS_NO, GNUM_HOSPITAL_CODE, SNO,gnum_isdept_entry) VALUES(?,?,?,?,?,?,?)";
    public String Q_SAVE_PDF_WORKORDERS = "INSERT INTO hivt_pdf_workorder_dtl(hrgnum_puk, hivtnum_req_dno, hivtstr_pdf_url, gnum_hospital_code,gnum_isdept_entry) VALUES (?,?,?,?,?)";
    public String Q_UPDATE_REQ_STATUS = "UPDATE HIVT_REQUISITION_DTL SET HIVNUM_REQDTL_STATUS = 26, HIVDT_REPORT_DATE=SYSDATE, HIVSTR_REPORT_URL=? WHERE HIVTNUM_REQ_DNO=?";
    public String Q_GET_REQ_DTL_STATUS_HIVT_REQ_DTL = "select hivnum_reqdtl_status from hivt_requisition_dtl where hivtnum_req_dno=?";
    
    //query for sms service
    public String Q_GET_REQ_SMS_DATA = "select hivnum_req_no,hrgstr_mobile_no,(select count(*) from hivt_requisition_dtl where hivnum_req_no=a.hivnum_req_no and gnum_hospital_code=a.gnum_hospital_code),(select count(*) from hivt_requisition_dtl where hivnum_req_no=a.hivnum_req_no and gnum_hospital_code=a.gnum_hospital_code and hivnum_reqdtl_status in (26,16,15)),(select INITCAP(gstr_lab_name) from hivt_laboratory_mst where gnum_isvalid=1 and gnum_lab_code=a.gnum_lab_code and gnum_hospital_code=a.gnum_hospital_code),INITCAP(hrgstr_patname),( select gnum_report_change from hivt_requisition_dtl where hivnum_req_no=a.hivnum_req_no and gnum_hospital_code=a.gnum_hospital_code and hivnum_reqdtl_status in (26,16,15) ) from hivt_requisition_header a where gnum_isvalid=1 and hivnum_sms_flag=0  group by hivnum_req_no,hrgstr_mobile_no,gnum_hospital_code";
    public String Q_UPDATE_REQ_SMS_DATA="update hivt_requisition_header set hivnum_sms_flag=1 where gnum_isvalid=1 and hivnum_req_no=? ";
    
    //query to get dept test template id
    public String Q_GET_DEPT_TESTTEMPLATE_STATUS = "SELECT gnum_isdept_template, gnum_depttemplate_id FROM hivt_test_mst WHERE gnum_test_code = ? and gnum_isvalid = 1 and gnum_isdept_template=1";
    public String Q_GET_DEPT_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code,hivstr_loinc_string, hivstr_ref_range FROM hivt_dept_parameter_dtl hpd WHERE gnum_isvalid = 1 and hpd.hivtnum_req_dno = ? and gnum_hospital_code=? order by gnum_seq_no";
    public String Q_UPDATE_DEPT_REQ_STATUS = "UPDATE HIVT_REQUISITION_DTL SET hivnum_dept_reqdtl_status = 33, hivdt_dept_report_date=SYSDATE, hivstr_dept_report_url=? WHERE HIVTNUM_REQ_DNO=?";
    
    // query to get n save report history
public String FETCH_SERVICE_ID_REPORT_HISTORY = "select max(gnum_service_id+1) from hivt_report_service_dtl ";
    
    public String INSERT_HIVT_REPORT_SERVICE_DTL = "insert into hivt_report_service_dtl(gnum_service_id,gnum_isvalid,gdt_update_date,gdt_insert_date,gnum_frequency,gstr_mongo_db_name,gstr_mongo_uri,gstr_pg_uri,gstr_pg_username,gstr_report_path,gstr_report_service_path) values(?,1,sysdate,sysdate,?,?,?,?,?,?,?)";
    
    public String UPDATE_HIVT_REPORT_SERVICE_DTL = "update  hivt_report_service_dtl set gdt_update_date=sysdate where gnum_service_id=? and  gnum_isvalid=1 ";
    
    public String DELETE_HIVT_REPORT_SERVICE_DTL = "update  hivt_report_service_dtl set gdt_update_date=sysdate,gdt_lst_date=sysdate,gnum_isvalid=0 where gnum_service_id=? and  gnum_isvalid=1  ";


    public String FETCH_TEST_NAMES_ACCREDITED = "select array_to_string(array_agg(distinct gstr_test_name), ',') from hivt_test_mst where  gnum_isvalid=1 ";
    
    public String FETCH_TEST_CODES_ACCREDITED = " (select array_to_string(array_agg(distinct gnum_test_code), ',') as testCodeAccrebited from hivt_laboratory_test_mst where gnum_isvalid=1 and gnum_is_nabl_accredited_test=1 and gnum_hospital_code=?)";

    public String FETCH_IS_PORTAL = "select hivnum_isportal from hivt_header_dtl where gnum_isvalid=1 and gnum_hospital_code=?";
    
    public String FETCH_DATA = "select * from hrgt_patient_dtl where hrgnum_puk=?";
}