<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr" import="classes.*, appForm.*, java.util.*, java.text.SimpleDateFormat" %>
<% request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="appFormList" class="java.util.ArrayList" scope="request" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./Css/main.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function AppRegister(appformnum) {
		userapp_insert_form.AppFormNum.value = appformnum;
		userapp_insert_form.action = "main_insertApp.m2";
		userapp_insert_form.submit();
	}

</script>
</head>
<body>
 <center>
		<form name="calendar_form">
			<table>
				<!-- �÷��� ������ -->
				<tr>
					<td>
						<!-- ���� ������ -->
						<table border="0" width="800">
							<%
								// ��ũ��Ʈ�� ���� ����
								String showDate = request.getParameter("showDate");
								Calendar cal = Calendar.getInstance();

								int nowYear = cal.get(Calendar.YEAR); // ������ ���� �⵵
								int nowMonth = cal.get(Calendar.MONTH); // ������ ���� ��
								int nowDate = cal.get(Calendar.DATE); // ������ ��¥

								String sYear = request.getParameter("year");
								String sMonth = request.getParameter("month");
								int year = 0, month = 0; // �ٲ� ��¥�� ��

								if (sYear == null && sMonth == null) {
									year = cal.get(Calendar.YEAR); // �⵵
									month = cal.get(Calendar.MONTH); // ��
								} else {
									year = Integer.parseInt(sYear);
									month = Integer.parseInt(sMonth);

									if (month < 0) // 1���� �������� �̵� �Ұ�
										month = 0;
									else if (month > 11) // 12���� ���ķ� �̵� �Ұ�
										month = 11;
								}
								cal.set(year, month, 1); // ���� ��¥ ����
								int day = cal.get(Calendar.DAY_OF_WEEK); // ����
								int start = cal.getActualMinimum(Calendar.DATE); // ������ ǥ��
								int end = cal.getActualMaximum(Calendar.DATE); // ����¥ ǥ��
								int line = 0;
								String fontColor;
							%>

							<tr align="center">
								<td><h1><%=(month + 1)%>�� ��ä�޷�</h1></td>
							</tr>
							<tr align="center">
								<!-- ���� ��¥ �޷� -->
								<td>
									<table border=1>
										<tr align=center valign=middle>
											<td><font color="red">��</font></td>
											<td>��</td>
											<td>ȭ</td>
											<td>��</td>
											<td>��</td>
											<td>��</td>
											<td>��</td>
										</tr>
										<%
											out.println("<tr height='80'>"); // ���� ��¥���� ��ĭ ����
											for (int i = 1; i < day; i++) {
												out.println("<td>&nbsp;</td>");
												line++;
											}

											for (int i = start; i <= end; i++) { // ��¥ ���        
												fontColor = (line == 0) ? "red" : "black"; // �Ͽ��� ���� ����
										%>
										<td align="right" height=80px width="110" valign="top"><b><%=i%></b>
						
										<%
										for (AppFormDTO af : (ArrayList<AppFormDTO>) appFormList ) {								
											if (af.getAppFormStartDate().getDate() == i && af.getAppFormStartDate().getMonth() == month) { %>
												<br>(��)<%=af.getAppFormCompany() %>
												
										<% } 
											if (af.getAppFormEndDate().getDate() == i && af.getAppFormEndDate().getMonth() == month) { %>
												<br>(��)<%=af.getAppFormCompany() %>
										<% } }%>

										</td>
										<%
											line++;

												if (line == 7 && i != end) { // �ֺ��� �ٳѱ�
													out.println("</tr><tr height='80'>");
													line = 0;
												}
											}

											while (line > 0 && line < 7) { // ��¥ ������ �� ���� ��ĭ ä���
												out.println("<td>&nbsp;</td>");
												line++;
											}
											out.println("</tr>");
										%>
									</table>
								</td>
							</tr>
						</table>

					</td>
				</tr>
			</table>
		</form>
	</center>

<center>
<H1>ä�� ���� ����Ʈ</H1> <a href="employeement_list.m2?command=appList&page=1">more</a>
<form name="userapp_insert_form" method="post">
<input type="hidden" name="AppFormNum"/>
<table border="1">


	<th>�����</th>
	<th>ȸ���</th>
	<th>������</th>
	<th>������</th>
	<th>���λ���</th>
	<th>������</th>
	<c:forEach var="appForm" items="${appFormList}">
	<tr>
		<td><a href="employeement_detail.m2?command=appDetail&AppFormNum=${appForm.getAppFormNum()}">${appForm.getAppFormRegisterName()}</a></td>
		<td>${appForm.getAppFormCompany()}</td>
		<td>${appForm.printStartDay()}</td>
		<td>${appForm.printEndDay()}</td>
		<td>${appForm.getDetail()}</td>
		<td>${appForm.getPreference()}</td>
		<td><input type="button" name="registerbtn" value="���" onClick="AppRegister(${appForm.getAppFormNum()})" /></td>
	</tr>
	</c:forEach>
</table>

<input type="hidden" name="command" value="userAppInsert">

</form>
</center>
</body>
</html>