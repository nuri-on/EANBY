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
				<!-- 플로팅 프레임 -->
				<tr>
					<td>
						<!-- 예약 프레임 -->
						<table border="0" width="800">
							<%
								// 스크립트잇 변수 선언
								String showDate = request.getParameter("showDate");
								Calendar cal = Calendar.getInstance();

								int nowYear = cal.get(Calendar.YEAR); // 오늘이 속한 년도
								int nowMonth = cal.get(Calendar.MONTH); // 오늘이 속한 달
								int nowDate = cal.get(Calendar.DATE); // 오늘의 날짜

								String sYear = request.getParameter("year");
								String sMonth = request.getParameter("month");
								int year = 0, month = 0; // 바꿀 날짜와 달

								if (sYear == null && sMonth == null) {
									year = cal.get(Calendar.YEAR); // 년도
									month = cal.get(Calendar.MONTH); // 월
								} else {
									year = Integer.parseInt(sYear);
									month = Integer.parseInt(sMonth);

									if (month < 0) // 1월달 이전으로 이동 불가
										month = 0;
									else if (month > 11) // 12월달 이후로 이동 불가
										month = 11;
								}
								cal.set(year, month, 1); // 기준 날짜 변경
								int day = cal.get(Calendar.DAY_OF_WEEK); // 요일
								int start = cal.getActualMinimum(Calendar.DATE); // 시작일 표시
								int end = cal.getActualMaximum(Calendar.DATE); // 끝날짜 표시
								int line = 0;
								String fontColor;
							%>

							<tr align="center">
								<td><h1><%=(month + 1)%>월 공채달력</h1></td>
							</tr>
							<tr align="center">
								<!-- 시작 날짜 달력 -->
								<td>
									<table border=1>
										<tr align=center valign=middle>
											<td><font color="red">일</font></td>
											<td>월</td>
											<td>화</td>
											<td>수</td>
											<td>목</td>
											<td>금</td>
											<td>토</td>
										</tr>
										<%
											out.println("<tr height='80'>"); // 시작 날짜까지 빈칸 삽입
											for (int i = 1; i < day; i++) {
												out.println("<td>&nbsp;</td>");
												line++;
											}

											for (int i = start; i <= end; i++) { // 날짜 출력        
												fontColor = (line == 0) ? "red" : "black"; // 일요일 색깔 지정
										%>
										<td align="right" height=80px width="110" valign="top"><b><%=i%></b>
						
										<%
										for (AppFormDTO af : (ArrayList<AppFormDTO>) appFormList ) {								
											if (af.getAppFormStartDate().getDate() == i && af.getAppFormStartDate().getMonth() == month) { %>
												<br>(시)<%=af.getAppFormCompany() %>
												
										<% } 
											if (af.getAppFormEndDate().getDate() == i && af.getAppFormEndDate().getMonth() == month) { %>
												<br>(마)<%=af.getAppFormCompany() %>
										<% } }%>

										</td>
										<%
											line++;

												if (line == 7 && i != end) { // 주별로 줄넘김
													out.println("</tr><tr height='80'>");
													line = 0;
												}
											}

											while (line > 0 && line < 7) { // 날짜 끝나고 난 뒤의 빈칸 채우기
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
<H1>채용 공고 리스트</H1> <a href="employeement_list.m2?command=appList&page=1">more</a>
<form name="userapp_insert_form" method="post">
<input type="hidden" name="AppFormNum"/>
<table border="1">


	<th>공고명</th>
	<th>회사명</th>
	<th>시작일</th>
	<th>마감일</th>
	<th>세부사항</th>
	<th>우대사항</th>
	<c:forEach var="appForm" items="${appFormList}">
	<tr>
		<td><a href="employeement_detail.m2?command=appDetail&AppFormNum=${appForm.getAppFormNum()}">${appForm.getAppFormRegisterName()}</a></td>
		<td>${appForm.getAppFormCompany()}</td>
		<td>${appForm.printStartDay()}</td>
		<td>${appForm.printEndDay()}</td>
		<td>${appForm.getDetail()}</td>
		<td>${appForm.getPreference()}</td>
		<td><input type="button" name="registerbtn" value="등록" onClick="AppRegister(${appForm.getAppFormNum()})" /></td>
	</tr>
	</c:forEach>
</table>

<input type="hidden" name="command" value="userAppInsert">

</form>
</center>
</body>
</html>