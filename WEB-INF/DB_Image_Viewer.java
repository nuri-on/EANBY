package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DB_Image_Viewer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png"); 
		Connection conn = null;
		PreparedStatement pstmt = null;

		String appFormNo = request.getParameter("AppFormNum");
		int AppFormNum = Integer.parseInt(appFormNo);
				
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";

		try{
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(db_url, "eanby", "eanby2012");
			/*create table mytable (bfile BLOB, cfile CLOB) 으로 테이블을 생성한 후에....
			 * 바이너리 데이터 컬럼에 바이너리 데이터를 저장한다.
			 */

			// DB에 저장된 이미지를 가져와서 웹브라우저에 출력하는 예
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT APPFORM_POSTER FROM APPFORM_POSTER WHERE APPFORMNUM= " + AppFormNum);
			if (rs.next()) {
				// 바이너리 데이터를 저장하고 있는 컬럼으로부터 데이터를 가져온다
				InputStream in = rs.getBinaryStream("APPFORM_POSTER");
				// BufferedImage를 생성하면 ImageIO를 통해 브라우저에 출력하기가 쉽다.
				BufferedImage bimg = ImageIO.read(in);
				in.close();

				ServletOutputStream sos = response.getOutputStream();

				ImageIO.write(bimg, "png", sos);
			}
			rs.close();
			stmt.close();
			conn.close();

		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}