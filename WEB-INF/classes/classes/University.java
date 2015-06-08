package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class University {
	private int UserNum;
	private String UniversityName;
	private Date EntranceDay;
	private Date GraudateDay;
	private String Major;
	private String Grade;
	private String Credit;
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getUniversityName() {
		return UniversityName;
	}
	public void setUniversityName(String universityName) {
		UniversityName = universityName;
	}
	public Date getEntranceDay() {
		return EntranceDay;
	}
	public void setEntranceDay(Date entranceDay) {
		EntranceDay = entranceDay;
	}
	public Date getGraudateDay() {
		return GraudateDay;
	}
	public void setGraudateDay(Date graudateDay) {
		GraudateDay = graudateDay;
	}
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		Major = major;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getCredit() {
		return Credit;
	}
	public void setCredit(String credit) {
		Credit = credit;
	}
	
	public String printEntranceDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(EntranceDay);
	}
	
	public String printGrauduateDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(GraudateDay);
	}
	
}
