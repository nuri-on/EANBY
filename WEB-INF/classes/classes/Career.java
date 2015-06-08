package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Career {
	private int UserNum;
	private String CareerType;
	private Date CareerStartDay;
	private Date CareerEndDay;
	private String CareerDpName;
	private String AnnualSalary;
	private String CompanyName;
	private String Position;
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getCareerType() {
		return CareerType;
	}
	public void setCareerType(String careerType) {
		CareerType = careerType;
	}
	public Date getCareerStartDay() {
		return CareerStartDay;
	}
	public void setCareerStartDay(Date careerStartDay) {
		CareerStartDay = careerStartDay;
	}
	public Date getCareerEndDay() {
		return CareerEndDay;
	}
	public void setCareerEndDay(Date careerEndDay) {
		CareerEndDay = careerEndDay;
	}
	public String getCareerDpName() {
		return CareerDpName;
	}
	public void setCareerDpName(String careerDpName) {
		CareerDpName = careerDpName;
	}
	public String getAnnualSalary() {
		return AnnualSalary;
	}
	public void setAnnualSalary(String annualSalary) {
		AnnualSalary = annualSalary;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String printStartDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(CareerStartDay);
	}
	
	public String printEndDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(CareerEndDay);
	}
	
	
}
