package classes;

import java.sql.Blob;

public class Photo {
	private int UserNum;
	private Blob Photo;
	private String PhotoPath;
	private String pwd;
	private String photoExisted;
	
	public int getUserNum() {
		return UserNum;
	}
	public void setUserNum(int userNum) {
		UserNum = userNum;
	}	
	public Blob getPhoto() {
		return Photo;
	}	
	public void setPhoto(Blob photo) {
		Photo = photo;
	}
	public String getPhotoPath() {
		return PhotoPath;
	}
	public void setPhotoPath(String photoPath) {
		PhotoPath = photoPath;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhotoExisted() {
		return photoExisted;
	}
	public void setPhotoExisted(String photoExisted) {
		this.photoExisted = photoExisted;
	}
}
