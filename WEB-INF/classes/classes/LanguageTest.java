package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LanguageTest {
	private int UserNum;
	private String TestName;
	private String TestScore;
	private String TestLevel;
	private Date TestDay;
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getTestName() {
		return TestName;
	}
	public void setTestName(String testName) {
		TestName = testName;
	}
	public String getTestScore() {
		return TestScore;
	}
	public void setTestScore(String testScore) {
		TestScore = testScore;
	}
	public String getTestLevel() {
		return TestLevel;
	}
	public void setTestLevel(String testLevel) {
		TestLevel = testLevel;
	}
	public Date getTestDay() {
		return TestDay;
	}
	public void setTestDay(Date testDay) {
		TestDay = testDay;
	}
	
	public String printTestday() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(TestDay);
	}
	
}
