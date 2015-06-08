package user;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserDTO implements Serializable{

	private int UserNum;
	private String UserName;
	private String UserEmail;
	private String UserPhone1;
	private String UserPhone2;
	private Date UserBirth;
	private String UserNickname;
	private String UserPassword;
	private String ManagerCheck;

	public String getManagerCheck() {
		return ManagerCheck;
	}

	public void setManagerCheck(String managerCheck) {
		ManagerCheck = managerCheck;
	}

	public int getUserNum() {
		return UserNum;
	}
	
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getUserEmail() {
		return UserEmail;
	}
	
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	
	public String getUserPhone1() {
		return UserPhone1;
	}
	
	public void setUserPhone1(String userPhone1) {
		UserPhone1 = userPhone1;
	}
	
	public String getUserPhone2() {
		return UserPhone2;
	}
	
	public void setUserPhone2(String userPhone2) {
		UserPhone2 = userPhone2;
	}
	
	public Date getUserBirth() {
		return UserBirth;
	}
	
	public void setUserBirth(Date userBirth) {
		UserBirth = userBirth;
	}
	
	public String getUserNickname() {
		return UserNickname;
	}
	
	public void setUserNickname(String userNickname) {
		UserNickname = userNickname;
	}
	
	public String getUserPassword() {
		return UserPassword;
	}
	
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	
	public boolean isMatchPassword(String inputPassword){
		if ( getUserPassword().equals(inputPassword)){
			return true;
		} else {
			return false;
		}
	}
}
