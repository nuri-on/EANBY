package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Volunteer {
	private int UserNum;
	private String VolunteerDetails;
	private Date VolunteerStartDay;
	private Date VolunteerEndDay;
	private String VolunteerInstitution;
	
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getVolunteerDetails() {
		return VolunteerDetails;
	}
	public void setVolunteerDetails(String volunteerDetails) {
		VolunteerDetails = volunteerDetails;
	}
	public Date getVolunteerStartDay() {
		return VolunteerStartDay;
	}
	public void setVolunteerStartDay(Date volunteerStartDay) {
		VolunteerStartDay = volunteerStartDay;
	}
	public Date getVolunteerEndDay() {
		return VolunteerEndDay;
	}
	public void setVolunteerEndDay(Date volunteerEndDay) {
		VolunteerEndDay = volunteerEndDay;
	}
	public String getVolunteerInstitution() {
		return VolunteerInstitution;
	}
	public void setVolunteerInstitution(String volunteerInstitution) {
		VolunteerInstitution = volunteerInstitution;
	}
	public String printStartDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(VolunteerStartDay);
	}
	
	public String printEndDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(VolunteerEndDay);
	}
}
