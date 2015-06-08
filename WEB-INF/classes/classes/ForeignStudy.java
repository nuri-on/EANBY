package classes;

import java.util.Date;

public class ForeignStudy {
	private int UserNum;
	private String CountryName;
	private String PurposeAndContents;
	private Date StayStartDay;
	private Date StayEndDay;
	private int ForeignNum;
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public String getPurposeAndContents() {
		return PurposeAndContents;
	}
	public void setPurposeAndContents(String purposeAndContents) {
		PurposeAndContents = purposeAndContents;
	}
	public Date getStayStartDay() {
		return StayStartDay;
	}
	public void setStayStartDay(Date stayStartDay) {
		StayStartDay = stayStartDay;
	}
	public Date getStayEndDay() {
		return StayEndDay;
	}
	public void setStayEndDay(Date stayEndDay) {
		StayEndDay = stayEndDay;
	}
	public int getForeignNum() {
		return ForeignNum;
	}
	public void setForeignNum(int foreignNum) {
		ForeignNum = foreignNum;
	}
	
	
	
}
