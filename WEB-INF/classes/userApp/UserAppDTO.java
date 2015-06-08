package userApp;

import java.sql.Blob;
import java.util.ArrayList;

import classes.*;

public class UserAppDTO {
	private int AppFormNum;
	private ArrayList<Award> AppAwards;
	private ArrayList<LanguageLevel> AppLanguageLevels;
	private ArrayList<LanguageTest> AppLanguageTests;
	private ArrayList<Licence> AppLicences;
	private ArrayList<Volunteer> AppVolunteers;
	private ArrayList<Career> AppCareers;
	private Blob AppPhoto;
	private UserInfo AppUserInfo;
	private Highschool AppHighschool;
	private University AppUniversity;
	private ArrayList<ForeignStudy> AppForeignStudies;
	private ArrayList<SelfIntroduction> UserAppSelfIntroductions;
	
	public UserAppDTO() {
		AppAwards = new ArrayList<Award>();
		AppLanguageLevels  = new ArrayList<LanguageLevel>();
		AppLanguageTests = new ArrayList<LanguageTest>();
		AppLicences = new ArrayList<Licence>();
		AppVolunteers = new ArrayList<Volunteer>();
		AppCareers  = new ArrayList<Career>();
		AppForeignStudies = new ArrayList<ForeignStudy>();
		
	}

	public int getAppFormNum() {
		return AppFormNum;
	}

	public void setAppFormNum(int appFormNum) {
		AppFormNum = appFormNum;
	}

	public ArrayList<Award> getAppAwards() {
		return AppAwards;
	}

	public void setAppAwards(ArrayList<Award> appAwards) {
		AppAwards = appAwards;
	}

	public ArrayList<LanguageLevel> getAppLanguageLevels() {
		return AppLanguageLevels;
	}

	public void setAppLanguageLevels(ArrayList<LanguageLevel> appLanguageLevels) {
		AppLanguageLevels = appLanguageLevels;
	}

	public ArrayList<LanguageTest> getAppLanguageTests() {
		return AppLanguageTests;
	}

	public void setAppLanguageTests(ArrayList<LanguageTest> appLanguageTests) {
		AppLanguageTests = appLanguageTests;
	}

	public ArrayList<Licence> getAppLicences() {
		return AppLicences;
	}

	public void setAppLicences(ArrayList<Licence> appLicences) {
		AppLicences = appLicences;
	}

	public ArrayList<Volunteer> getAppVolunteers() {
		return AppVolunteers;
	}

	public void setAppVolunteers(ArrayList<Volunteer> appVolunteers) {
		AppVolunteers = appVolunteers;
	}

	public ArrayList<Career> getAppCareers() {
		return AppCareers;
	}

	public void setAppCareers(ArrayList<Career> appCareers) {
		AppCareers = appCareers;
	}

	public Blob getAppPhoto() {
		return AppPhoto;
	}

	public void setAppPhoto(Blob appPhoto) {
		AppPhoto = appPhoto;
	}

	public UserInfo getAppUserInfo() {
		return AppUserInfo;
	}

	public void setAppUserInfo(UserInfo appUserInfo) {
		AppUserInfo = appUserInfo;
	}

	public Highschool getAppHighschool() {
		return AppHighschool;
	}

	public void setAppHighschool(Highschool appHighschool) {
		AppHighschool = appHighschool;
	}

	public University getAppUniversity() {
		return AppUniversity;
	}

	public void setAppUniversity(University appUniversity) {
		AppUniversity = appUniversity;
	}

	public ArrayList<ForeignStudy> getAppForeignStudies() {
		return AppForeignStudies;
	}

	public void setAppForeignStudies(ArrayList<ForeignStudy> appForeignStudies) {
		AppForeignStudies = appForeignStudies;
	}

	public ArrayList<SelfIntroduction> getUserAppSelfIntroductions() {
		return UserAppSelfIntroductions;
	}

	public void setUserAppSelfIntroductions(
			ArrayList<SelfIntroduction> userAppSelfIntroductions) {
		UserAppSelfIntroductions = userAppSelfIntroductions;
	}
	
	
	
}
