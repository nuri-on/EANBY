package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Licence {
	private int UserNum;
	private String LicenceName;
	private String LicenceInstitution;
	private String LicenceScore;
	private Date Licenceday;
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	public String getLicenceName() {
		return LicenceName;
	}
	public void setLicenceName(String licenceName) {
		LicenceName = licenceName;
	}
	public String getLicenceInstitution() {
		return LicenceInstitution;
	}
	public void setLicenceInstitution(String licenceInstitution) {
		LicenceInstitution = licenceInstitution;
	}
	public String getLicenceScore() {
		return LicenceScore;
	}
	public void setLicenceScore(String licenceScore) {
		LicenceScore = licenceScore;
	}
	public Date getLicenceday() {
		return Licenceday;
	}
	public void setLicenceday(Date licenceday) {
		Licenceday = licenceday;
	}
	
	public String printLicenceday() {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		return transFormat.format(Licenceday);
	}
	
}
