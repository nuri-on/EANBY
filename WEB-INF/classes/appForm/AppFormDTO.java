package appForm;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import oracle.sql.BLOB;
import classes.*;

public class AppFormDTO {
	private int AppFormNum;
	private String AppFormRegisterName;
	private String AppFormCompany;
	private Date AppFormStartDate;
	private Date AppFormEndDate;
	private int ManagerId;
	private int AppFormAwards;
	private int AppFormLanguageLevel;
	private int AppFormLanguageTest;
	private int AppFormLicences;
	private int AppFormVolunteers;
	private int AppFormCareers;
	private AppFormUserInfo AppFormUserInfo;
	private BLOB AppFormPoster;
	private String AppFormPosterPath;	
	private String AppFormUniversity;
	private String AppFormHighschool;
	//private int AppFormForeignStudy;
	private String Preference;
	private String Detail;
	private ArrayList<SelfIntroduction> SelfIntroductions;
	
	public String getAppFormRegisterName() {
		return AppFormRegisterName;
	}
	public void setAppFormRegisterName(String appFormRegisterName) {
		AppFormRegisterName = appFormRegisterName;
	}
	public String getAppFormCompany() {
		return AppFormCompany;
	}
	public void setAppFormCompany(String appFormCompany) {
		AppFormCompany = appFormCompany;
	}
	public Date getAppFormStartDate() {
		return AppFormStartDate;
	}
	public void setAppFormStartDate(Date appFormStartDate) {
		AppFormStartDate = appFormStartDate;
	}
	public Date getAppFormEndDate() {
		return AppFormEndDate;
	}
	public void setAppFormEndDate(Date appFormEndDate) {
		AppFormEndDate = appFormEndDate;
	}
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	public int getAppFormAwards() {
		return AppFormAwards;
	}
	public void setAppFormAwards(int appFormAwards) {
		AppFormAwards = appFormAwards;
	}
	public int getAppFormLanguageLevel() {
		return AppFormLanguageLevel;
	}
	public void setAppFormLanguageLevel(int appFormLanguageLevel) {
		AppFormLanguageLevel = appFormLanguageLevel;
	}
	public int getAppFormLanguageTest() {
		return AppFormLanguageTest;
	}
	public void setAppFormLanguageTest(int appFormLanguageTest) {
		AppFormLanguageTest = appFormLanguageTest;
	}
	public int getAppFormLicences() {
		return AppFormLicences;
	}
	public void setAppFormLicences(int appFormLicences) {
		AppFormLicences = appFormLicences;
	}
	public int getAppFormVolunteers() {
		return AppFormVolunteers;
	}
	public void setAppFormVolunteers(int appFormVolunteers) {
		AppFormVolunteers = appFormVolunteers;
	}
	public int getAppFormCareers() {
		return AppFormCareers;
	}
	public void setAppFormCareers(int appFormCareers) {
		AppFormCareers = appFormCareers;
	}
	public AppFormUserInfo getAppFormUserInfo() {
		return AppFormUserInfo;
	}
	public void setAppFormUserInfo(AppFormUserInfo appFormUserInfo) {
		AppFormUserInfo = appFormUserInfo;
	}
	public BLOB getAppFormPoster() {
		return AppFormPoster;
	}
	public void setAppFormPoster(BLOB appFormPoster) {
		AppFormPoster = appFormPoster;
	}		
	public String getAppFormPosterPath() {
		return AppFormPosterPath;
	}
	public void setAppFormPosterPath(String appFormPosterPath) {
		AppFormPosterPath = appFormPosterPath;
	}
	public String getAppFormUniversity() {
		return AppFormUniversity;
	}
	public void setAppFormUniversity(String appFormUniversity) {
		AppFormUniversity = appFormUniversity;
	}
	public String getAppFormHighschool() {
		return AppFormHighschool;
	}
	public void setAppFormHighschool(String appFormHighschool) {
		AppFormHighschool = appFormHighschool;
	}
/*	public int getAppFormForeignStudy() {
		return AppFormForeignStudy;
	}
	public void setAppFormForeignStudy(int appFormForeignStudy) {
		AppFormForeignStudy = appFormForeignStudy;
	}*/
	public String getPreference() {
		return Preference;
	}
	public void setPreference(String preference) {
		Preference = preference;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public ArrayList<SelfIntroduction> getSelfIntroductions() {
		return SelfIntroductions;
	}
	public void setSelfIntroductions(ArrayList<SelfIntroduction> selfIntroductions) {
		SelfIntroductions = selfIntroductions;
	}

	public String printStartDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(AppFormStartDate);	
	}
	
	public String printEndDay() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(AppFormEndDate);	
	}
	public int getAppFormNum() {
		return AppFormNum;
	}
	public void setAppFormNum(int appFormNum) {
		AppFormNum = appFormNum;
	}
}
