package hisglobal.vo;

/**
 * DocumentUploadDtlVO is the class that specifies getters and setters for all the identifiers which are used for retrieving
 * and inserting data in the DB tables.
 * 
 * @author AHIS
 */

public class DocumentUploadDtlVO extends ValueObject
{
	private String patCrNo;
	private String serialNo;
	private String episodeCode;// for HRGNUM_EPISODE_NO
	private String episodeVisitNo;// for HRGNUM_VISIT_NO
	private String documentTitle;
	private String documentCode;
	private String documentName;
	private String documentDirectoryPath;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String documentTypeName;
	private byte[] docFile;
	private String mlcNo;
	private String removeReason;
	private String fileType;
	private String removeDate;
	private String removeSeatId;
	private String pathForWindows;
	private String pathPathLinux;
	

	public String getMlcNo() {
		return mlcNo;
	}

	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}

	public String getRemoveReason() {
		return removeReason;
	}

	public void setRemoveReason(String removeReason) {
		this.removeReason = removeReason;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(String removeDate) {
		this.removeDate = removeDate;
	}

	public String getRemoveSeatId() {
		return removeSeatId;
	}

	public void setRemoveSeatId(String removeSeatId) {
		this.removeSeatId = removeSeatId;
	}

	public byte[] getDocFile()
	{
		return docFile;
	}

	public void setDocFile(byte[] docFile)
	{
		this.docFile = docFile;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getDocumentTitle()
	{
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle)
	{
		this.documentTitle = documentTitle;
	}

	public String getDocumentCode()
	{
		return documentCode;
	}

	public void setDocumentCode(String documentCode)
	{
		this.documentCode = documentCode;
	}

	public String getDocumentName()
	{
		return documentName;
	}

	public void setDocumentName(String documentName)
	{
		this.documentName = documentName;
	}

	public String getDocumentDirectoryPath()
	{
		return documentDirectoryPath;
	}

	public void setDocumentDirectoryPath(String documentDirectoryPath)
	{
		this.documentDirectoryPath = documentDirectoryPath;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public void reset()
	{
		this.setDocumentCode("");
		this.setDocumentDirectoryPath("");
		this.setDocumentName("");
		this.setDocumentTitle("");
		this.setEntryDate("");
		this.setEpisodeCode("");
		this.setEpisodeVisitNo("");
		this.setIsValid("");
		this.setPatCrNo("");
		this.setSeatId("");
		this.setSerialNo("");

	}

	public String getDocumentTypeName()
	{
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName)
	{
		this.documentTypeName = documentTypeName;
	}

	public String getPathForWindows() {
		return pathForWindows;
	}

	public void setPathForWindows(String pathForWindows) {
		this.pathForWindows = pathForWindows;
	}

	public String getPathPathLinux() {
		return pathPathLinux;
	}

	public void setPathPathLinux(String pathPathLinux) {
		this.pathPathLinux = pathPathLinux;
	}
}
