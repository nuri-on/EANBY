package userSpec;

import java.sql.Blob;
import java.util.ArrayList;

import oracle.sql.BLOB;
import classes.*;

public class UserSpecDTO {
	Blob specPhoto;
	ArrayList<Award> specAwards;
	ArrayList<LanguageLevel> specLanguageLevels;
	ArrayList<LanguageTest> specLanguageTests;
	ArrayList<Licence> specLicences;
	ArrayList<Volunteer> specVolunteers;
	ArrayList<Career> specCareers;
	UserInfo specUserInfo;
	Highschool specHighschool;
	University specUniversity;
	ArrayList<ForeignStudy> specForeignStudies;
	
	
	public UserSpecDTO() {
		specAwards = new ArrayList<Award>();
		specLanguageLevels  = new ArrayList<LanguageLevel>();
		specLanguageTests = new ArrayList<LanguageTest>();
		specLicences = new ArrayList<Licence>();
		specVolunteers = new ArrayList<Volunteer>();
		specCareers  = new ArrayList<Career>();
		specForeignStudies = new ArrayList<ForeignStudy>();
		
	}
	
	
	public void setSpecAwards(ArrayList<Award> specAwards) {
		this.specAwards = specAwards;
	}


	public void setSpecLanguageLevels(ArrayList<LanguageLevel> specLanguageLevels) {
		this.specLanguageLevels = specLanguageLevels;
	}


	public void setSpecLanguageTests(ArrayList<LanguageTest> specLanguageTests) {
		this.specLanguageTests = specLanguageTests;
	}


	public void setSpecLicences(ArrayList<Licence> specLicences) {
		this.specLicences = specLicences;
	}


	public void setSpecVolunteers(ArrayList<Volunteer> specVolunteers) {
		this.specVolunteers = specVolunteers;
	}


	public void setSpecCareers(ArrayList<Career> specCareers) {
		this.specCareers = specCareers;
	}


	public void setSpecForeignStudies(ArrayList<ForeignStudy> specForeignStudies) {
		this.specForeignStudies = specForeignStudies;
	}


	public Blob getSpecPhoto() {
		return specPhoto;
	}
	
	public void setSpecPhoto(Blob specPhoto) {
		this.specPhoto = specPhoto;
	}
	
	public ArrayList<Award> getSpecAwards() {
		return specAwards;
	}
	
	public ArrayList<LanguageLevel> getSpecLanguageLevels() {
		return specLanguageLevels;
	}

	public ArrayList<LanguageTest> getSpecLanguageTests() {
		return specLanguageTests;
	}

	public ArrayList<ForeignStudy> getSpecForeignStudies() {
		return specForeignStudies;
	}
	
	public ArrayList<Licence> getSpecLicences() {
		return specLicences;
	}
	
	public ArrayList<Volunteer> getSpecVolunteers() {
		return specVolunteers;
	}
	
	public ArrayList<Career> getSpecCareers() {
		return specCareers;
	}
	
	public UserInfo getSpecUserInfo() {
		return specUserInfo;
	}
	
	public void setSpecUserInfo(UserInfo specUserInfo) {
		this.specUserInfo = specUserInfo;
	}
	
	public Highschool getSpecHighschool() {
		return specHighschool;
	}
	
	public void setSpecHighschool(Highschool specHighschool) {
		this.specHighschool = specHighschool;
	}
	
	public University getSpecUniversity() {
		return specUniversity;
	}
	
	public void setSpecUniversity(University specUniversity) {
		this.specUniversity = specUniversity;
	}
	
	
	
}
