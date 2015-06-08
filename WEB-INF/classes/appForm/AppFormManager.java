package appForm;
//
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.SelfIntroduction;


public class AppFormManager {
	private static AppFormManager appformmanager = new AppFormManager();
	private AppFormDAO appformDAO;

	private AppFormManager() {
		try {
			appformDAO = new AppFormDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AppFormManager getInstance() {
		return appformmanager;
	}

	public int createAppForm(AppFormDTO appform, int userNum) throws SQLException {
		
		int AppFormNum;
		int val = -1;
		String posterPath = appform.getAppFormPosterPath();
		System.out.println(posterPath);
				
		AppFormNum = appformDAO.createAppFormRegister(userNum);
		if (AppFormNum == 0) return 0;
		
		// �̹��� ó��!
		Connection conn = null;
		PreparedStatement pstmt = null;
 
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
	
		try{
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(db_url,"eanby","eanby2012");
			/*create table mytable (bfile BLOB, cfile CLOB) ���� ���̺��� ������ �Ŀ�....
			 * ���̳ʸ� ������ �÷��� ���̳ʸ� �����͸� �����Ѵ�.
			 */
			pstmt = conn.prepareStatement("insert into APPFORM_POSTER (APPFORMNUM, APPFORM_POSTER) values(?, ?)");
			// ������ �̹��� ��θ� ���� ���Ͻý��ۻ��� ��η� ��ȯ�Ѵ�.
			// byte�迭�� ��ȯ�ؾ߸� blob���� �÷��� ������ �� �ִ�.
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			FileInputStream fin = new FileInputStream(posterPath);
			
			byte[] buf = new byte[1024];
			int read = 0;
			while((read=fin.read(buf,0,buf.length))!=-1){
				bout.write(buf,0,read);
			}
			fin.close();
			// byte�迭�� �ϼ��Ǿ���
			byte[] imageData = bout.toByteArray();
		
			// byte�迭�� DB�� �����Ѵ�
			pstmt.setInt(1, AppFormNum);
			pstmt.setBytes(2, imageData);
			pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			conn.close();
		} catch(Exception e) {
			System.err.println(e);
		}	
		
		val = appformDAO.createAppForm(appform, AppFormNum);
		if (val == 0) return 0;
		
		val = appformDAO.createAppFormInfo(appform, AppFormNum);
		if (val == 0) return 0;
		
		
		val = appformDAO.createAppFormSelfIntro(appform.getSelfIntroductions(), AppFormNum);
		if (val == 0) return 0;
		
		return 1;
	}

	public int updateAppForm(AppFormDTO appform, SelfIntroduction selfintroduction, int userNum, int AppFormNum) throws SQLException {

		int val = -1;
		
		val = appformDAO.updateAppForm(appform, AppFormNum);
		if (val == 0) return 0;
		
		val = appformDAO.updateAppFormInfo(appform.getAppFormUserInfo(), AppFormNum);
		if (val == 0) return 0;
		
		val = appformDAO.updateAppFormSelfIntro(selfintroduction, AppFormNum);
		if (val == 0) return 0;
		
		return 1;
	}


	public AppFormDTO getAppForm(int AppFormNum) {
		AppFormDTO appForm = null;
		
		try {
			appForm = appformDAO.getAppForm(AppFormNum);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appForm;
		
	}

	public ArrayList<AppFormDTO> AppFormList(){
		
		ArrayList<AppFormDTO> appFormList = null;
		
		try {
			appFormList = appformDAO.AppFormList();
		} 
		catch (SQLException e) {	
			e.printStackTrace();
		}
		return appFormList;
		
	}
	public ArrayList<AppFormDTO> AllAppFormList(){
		
		ArrayList<AppFormDTO> appFormList = null;
		
		try {
			appFormList = appformDAO.AllAppFormList();
		} 
		catch (SQLException e) {	
			e.printStackTrace();
		}
		return appFormList;
		
	}
}
