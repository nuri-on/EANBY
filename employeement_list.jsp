<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"
	import="classes.*, appForm.*, java.util.*, java.text.SimpleDateFormat"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="appFormList" class="java.util.ArrayList"
	scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./Css/main_contents_table.css"
	type="text/css" />
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
		<H1>채용 공고 리스트</H1>
		<form name="userapp_insert_form" method="post">
		<input type="hidden" name="AppFormNum"/>
			<table border="1">
				<th>공고명</th>
				<th>회사명</th>
				<th>시작일</th>
				<th>마감일</th>
				<th>세부사항</th>
				<th>우대사항</th>
				<th>&nbsp</th>
				<c:forEach var="appForm" items="${appFormList}">
					<tr>
						<td><a
							href="employeement_detail.m2?command=appDetail&AppFormNum=${appForm.getAppFormNum()}">${appForm.getAppFormRegisterName()}</a></td>
						<td>${appForm.getAppFormCompany()}</td>
						<td>${appForm.printStartDay()}</td>
						<td>${appForm.printEndDay()}</td>
						<td>${appForm.getDetail()}</td>
						<td>${appForm.getPreference()}</td>
						<td><input type="button" name="registerbtn" value="등록"
							onClick="AppRegister(${appForm.getAppFormNum()})" /></td>
					</tr>
				</c:forEach>
			</table>

			<input type="hidden" name="command" value="userAppInsert">

		</form>
	</center>
</body>
</html>