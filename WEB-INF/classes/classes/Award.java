package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Award {
	private int UserNum;
	private String Awarddetails;
	private String Awardinstitution;
	private Date Awardday;
	private String Awardname;
	
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getAwarddetails() {
		return Awarddetails;
	}
	public void setAwarddetails(String awarddetails) {
		Awarddetails = awarddetails;
	}
	public String getAwardinstitution() {
		return Awardinstitution;
	}
	public void setAwardinstitution(String awardinstitution) {
		Awardinstitution = awardinstitution;
	}
	public Date getAwardday() {
		return Awardday;
	}
	public void setAwardday(Date awardday) {
		Awardday = awardday;
	}
	public String getAwardname() {
		return Awardname;
	}
	public void setAwardname(String awardname) {
		Awardname = awardname;
	}
	public String printAwardday() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(Awardday);
	}
	
}
