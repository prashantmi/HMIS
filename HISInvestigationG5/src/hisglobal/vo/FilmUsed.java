package hisglobal.vo;

import java.util.List;

public class FilmUsed extends ValueObject 
{
	private String filmId ;
	private String  filmNo;
	private String  filmSize;
	private String  filmStatus;
	private String  filmUsedType;
	private String reason;
	private String selectCheck;
	private String filmRemarks;
	
	public String getFilmRemarks() {
		return filmRemarks;
	}
	public void setFilmRemarks(String filmRemarks) {
		this.filmRemarks = filmRemarks;
	}
	public String getSelectCheck() {
		return selectCheck;
	}
	public void setSelectCheck(String selectCheck) {
		this.selectCheck = selectCheck;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFilmId() {
		return filmId;
	}
	public void setFilmId(String filmId) {
		this.filmId = filmId;
	}
	public String getFilmNo() {
		return filmNo;
	}
	public void setFilmNo(String filmNo) {
		this.filmNo = filmNo;
	}
	public String getFilmSize() {
		return filmSize;
	}
	public void setFilmSize(String filmSize) {
		this.filmSize = filmSize;
	}
	public String getFilmStatus() {
		return filmStatus;
	}
	public void setFilmStatus(String filmStatus) {
		this.filmStatus = filmStatus;
	}
	public String getFilmUsedType() {
		return filmUsedType;
	}
	public void setFilmUsedType(String filmUsedType) {
		this.filmUsedType = filmUsedType;
	}
	
	
}
