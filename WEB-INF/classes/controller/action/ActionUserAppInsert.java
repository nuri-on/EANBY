package controller.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import classes.SelfIntroduction;
import appForm.AppFormDTO;
import appForm.AppFormManager;
import user.UserDTO;
import userApp.UserAppDTO;
import userApp.UserAppManager;
import userSpec.UserSpecDAO;
import userSpec.UserSpecDTO;
import userSpec.UserSpecManager;
import controller.ActionForward;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appForm.AppFormDTO;
import appForm.AppFormManager;
import user.UserDTO;
import userSpec.UserSpecDAO;
import userSpec.UserSpecDTO;
import userSpec.UserSpecManager;
import controller.ActionForward;

public class ActionUserAppInsert implements Action {
		public ActionForward execute(
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
			
			UserSpecDTO userSpec = new UserSpecDTO();
			UserSpecDAO userSpecDAO = new UserSpecDAO();
			
			ActionForward forward = new ActionForward();
			int AppFormNum = Integer.parseInt(request.getParameter("AppFormNum"));
			AppFormDTO appForm;
			ArrayList<SelfIntroduction> selfintroductions = null;
			UserDTO user = null;
			
			try {
				HttpSession session = request.getSession();
				UserSpecManager userSpecManager = UserSpecManager.getInstance();
				
				user = (UserDTO)session.getAttribute("user");
				
				
				/*
				
				String posterPath = request.getParameter("photoPath");
				System.out.println(posterPath);
				int userNum = user.getUserNum();
				
				
				Connection conn = null;
				PreparedStatement pstmt = null;
		 
				String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
				String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
			
				try{
					Class.forName(jdbc_driver);
					conn = DriverManager.getConnection(db_url,"eanby","eanby2012");
					create table mytable (bfile BLOB, cfile CLOB) ���� ���̺��� ������ �Ŀ�....
					 * ���̳ʸ� ������ �÷��� ���̳ʸ� �����͸� �����Ѵ�.
					 
					pstmt = conn.prepareStatement("insert into APPPOSTER (APPFORMNUM, USERAPPPHOTO) values(?, ?)");
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
				*/
				//�ش� ��� ��������
				AppFormManager appFormManager = AppFormManager.getInstance();
				appForm = appFormManager.getAppForm(AppFormNum);
				request.setAttribute("appForm", appForm);
				
				UserAppDTO userApp = null;
				UserAppManager userAppManager = UserAppManager.getInstance();
				userApp = userAppManager.getUserApp(user.getUserNum(), AppFormNum);
				selfintroductions = userAppManager.getSelfIntrocduction(user.getUserNum(), AppFormNum);
				
				if (userApp != null) {
					request.setAttribute("haveUserApp", "y");
					request.setAttribute("userApp", userApp);
					if (selfintroductions != null) {
						request.setAttribute("selfintroductions", selfintroductions);
					}
					
				}
				else {	
					// userNum���� ���� ���� ��������.
					userSpec = userSpecManager.getUserSpec(user.getUserNum());
					request.setAttribute("userSpec", userSpec);
					request.setAttribute("userSpecDAO", userSpecDAO);
				}
				// �̵��� �������� ����.
				forward.setPath("main_insertApp.jsp");
				forward.setRedirect(false);
				
			} catch (Exception e) {
				request.setAttribute("exception", e);
				forward.setPath("main_insertApp.jsp");
				forward.setRedirect(false);
			}

			return forward;	
			
		}
}
