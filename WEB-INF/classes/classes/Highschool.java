 package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Highschool {
	private int UserNum;
	private String HighschoolName;
	private Date EntanceDay;
	private Date GraduateDay;
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getHighschoolName() {
		return HighschoolName;
	}
	public void setHighschoolName(String highschoolName) {
		HighschoolName = highschoolName;
	}
	public Date getEntanceDay() {
		return EntanceDay;
	}
	public void setEntanceDay(Date entanceDay) {
		EntanceDay = entanceDay;
	}
	public Date getGraduateDay() {
		return GraduateDay;
	}
	public void setGraduateDay(Date graduateDay) {
		GraduateDay = graduateDay;
	}
	
	public String printEntanceDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(EntanceDay);
	}
	
	public String printGraduateDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(GraduateDay);
	}
	
}
