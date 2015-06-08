<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr" import="classes.*, userSpec.*, java.util.*, java.text.SimpleDateFormat" %>
<% request.setCharacterEncoding("euc-kr"); %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="./Css/userAppCss.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<center>
<h1>${appForm.getAppFormRegisterName()} - ${appForm.getAppFormCompany()}</h1>
<form name="insertUserApp_form" action="main_insertApp.m2" method="post">
	<input type="hidden" name="AppFormNum" value="${appForm.getAppFormNum()}"/>
	<h3>신상정보</h3>
	
	<table>
	<c:set var="appFormUserInfo" value="${appForm.getAppFormUserInfo()}" />
	
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecInfo" value="${userApp.getAppUserInfo()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecInfo" value="${userSpec.getSpecUserInfo()}" />
	</c:otherwise>
	</c:choose>
	
	<c:if test="${appFormUserInfo.getName1().equals('y')}" >
		<tr>
			<td>한글 이름</td>
			<td><input type="text" name="Name1" value="${userSpecInfo.getName1()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getName2().equals('y')}" >
		<tr>
			<td>영문 이름</td>
			<td><input type="text" name="Name2" value="${userSpecInfo.getName2()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getName3().equals('y')}" >
		<tr>
			<td>한문 이름</td>
			<td><input type="text" name="Name3" value="${userSpecInfo.getName3()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getBirthday().equals('y')}" >
		<tr>
			<td>생일</td>
			<td><input type="date" name="Birthday" value="${userSpecInfo.printBirthday()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getAddress().equals('y')}" >
		<tr>
			<td>주소</td>
			<td><input type="text" name="Address" value="${userSpecInfo.getAddress()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getEmail().equals('y')}" >
		<tr>
			<td>이메일</td>
			<td><input type="text" name="Email" value="${userSpecInfo.getEmail()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getPhone1().equals('y')}" >
		<tr>
			<td>전화번호1</td>
			<td><input type="text" name="Phone1" value="${userSpecInfo.getPhone1()}" /></td>
		</tr>
	</c:if>
	
	<c:if test="${appFormUserInfo.getPhone2().equals('y')}" >
		<tr>
			<td>전화번호2</td>
			<td><input type="text" name="Phone2" value="${userSpecInfo.getPhone2()}" /></td>
		</tr>
	</c:if>
	
	</table>
	<br><Br>
	
	
	<h3>고등학교</h3>
	
	<table>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecHighschool" value="${userApp.getAppHighschool()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecHighschool" value="${userSpec.getSpecHighschool()}" />
	</c:otherwise>
	</c:choose>
	<c:if test="${appForm.getAppFormHighschool().equals('y')}" >
		<tr>
			<td>고등학교 이름</td>
			<td><input type="text" name="HighschoolName" value="${userSpecHighschool.getHighschoolName()}" /></td>
		</tr>
		<tr>
			<td>입학년월</td>
			<td><input type="date" name="HEntanceDay" value="${userSpecHighschool.printEntanceDay()}" /></td>
		</tr>
		<tr>
			<td>졸업년월</td>
			<td><input type="date" name="HGraduateDay" value="${userSpecHighschool.printGraduateDay()}" /></td>
		</tr>
	</c:if>
	</table>
	
	<br><br>
	<h3>대학교</h3>
	
	<table>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecUniversity" value="${userApp.getAppUniversity()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecUniversity" value="${userSpec.getSpecUniversity()}" />
	</c:otherwise>
	</c:choose>
	
	<c:if test="${appForm.getAppFormUniversity().equals('y')}" >
		<tr>
			<td>대학교 이름</td>
			<td><input type="text" name="UniversityName" value="${userSpecUniversity.getUniversityName()}" /></td>
		</tr>
		<tr>
			<td>입학년월</td>
			<td><input type="date" name="UEntranceDay" value="${userSpecUniversity.printEntranceDay()}" /></td>
		</tr>
		<tr>
			<td>졸업년월</td>
			<td><input type="date" name="UGraudateDay" value="${userSpecUniversity.printGrauduateDay()}" /></td>
		</tr>
		<tr>
			<td>전공</td>
			<td><input type="text" name="Major" value="${userSpecUniversity.getMajor()}" /></td>
		</tr>
		<tr>
			<td>학점</td>
			<td><input type="text" name="Grade" value="${userSpecUniversity.getGrade()}" /></td>
		</tr>
		<tr>
			<td>총점</td>
			<td><input type="text" name="Credit" value="${userSpecUniversity.getCredit()}" /></td>
		</tr>
	</c:if>	
	</table>
	
	<br><br>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecAwards" value="${userApp.getAppAwards()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecAwards" value="${userSpec.getSpecAwards()}" />
	</c:otherwise>
	</c:choose>
	
	<h3>수상 경력</h3>
	<c:forEach var="i" begin="0" end="${appForm.getAppFormAwards() - 1}">
	<table>	
		<tr>
			<td>수상명</td>
			<td><input type="text" name="Awardname" value="${userSpecAwards[i].getAwardname()}" /></td>
		</tr>
		<tr>
			<td>수상등급</td>
			<td><input type="text" name="Awarddetails" value="${userSpecAwards[i].getAwarddetails()}" /></td>
		</tr>
		<tr>
			<td>주최기관</td>
			<td><input type="text" name="Awardinstitution" value="${userSpecAwards[i].getAwardinstitution()}" /></td>
		</tr>
		<tr>
			<td>수상일자</td>
			<td><input type="date" name="Awardday" value="${userSpecAwards[i].printAwardday()}" /></td>
		</tr>
	</table><br>
	</c:forEach>
	
	<br><br>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecLanguageLevels" value="${userApp.getAppLanguageLevels()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecLanguageLevels" value="${userSpec.getSpecLanguageLevels()}" />
	</c:otherwise>
	</c:choose>
	
	<h3>어학</h3>
	<c:forEach var="i" begin="0" end="${appForm.getAppFormLanguageLevel() - 1}">
	<table>	
		<tr>
			<td>어학종류</td>
			<td><input type="text" name="LanguageType" value="${userSpecLanguageLevels[i].getLanguageType()}" /></td>
		</tr>
		<tr>
			<td>회화수준</td>
			<td><input type="text" name="ConversationLevel" value="${userSpecLanguageLevels[i].getConversationLevel()}" /></td>
		</tr>
		<tr>
			<td>읽기수준</td>
			<td><input type="text" name="ReadingLevel" value="${userSpecLanguageLevels[i].getReadingLevel()}" /></td>
		</tr>
		<tr>
			<td>쓰기수준</td>
			<td><input type="text" name="WritingLevel" value="${userSpecLanguageLevels[i].getWritingLevel()}" /></td>
		</tr>
		</table><br>
	</c:forEach>
	
	<br><br>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecLanguageTest" value="${userApp.getAppLanguageTests()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecLanguageTest" value="${userSpec.getSpecLanguageTests()}" />
	</c:otherwise>
	</c:choose>
	
	<h3>어학시험</h3>
	<c:forEach var="i" begin="0" end="${appForm.getAppFormLanguageTest() - 1}">
	<table>	
		<tr>
			<td>시험명</td>
			<td><input type="text" name="TestName" value="${userSpecLanguageTest[i].getTestName()}" /></td>
		</tr>
		<tr>
			<td>점수</td>
			<td><input type="text" name="TestScore" value="${userSpecLanguageTest[i].getTestScore()}" /></td>
		</tr>
		<tr>
			<td>등급</td>
			<td><input type="text" name="TestLevel" value="${userSpecLanguageTest[i].getTestLevel()}" /></td>
		</tr>
		<tr>
			<td>취득일</td>
			<td><input type="date" name="TestDay" value="${userSpecLanguageTest[i].printTestday()}" /></td>
		</tr>
	</table><br>
	</c:forEach>

	
	<br><br>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecLicence" value="${userApp.getAppLicences()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecLicence" value="${userSpec.getSpecLicences()}" />
	</c:otherwise>
	</c:choose>
	
	<h3>자격증</h3>
	<c:forEach var="i" begin="0" end="${appForm.getAppFormLicences() - 1}">
	<table>
		<tr>
			<td>자격증명</td>
			<td><input type="text" name="LicenceName" value="${userSpecLicence[i].getLicenceName()}" /></td>
		</tr>
		<tr>
			<td>발행처</td>
			<td><input type="text" name="LicenceInstitution" value="${userSpecLicence[i].getLicenceInstitution()}" /></td>
		</tr>
		<tr>
			<td>점수(등급)</td>
			<td><input type="text" name="LicenceScore" value="${userSpecLicence[i].getLicenceScore()}" /></td>
		</tr>
		<tr>
			<td>취득일</td>
			<td><input type="date" name="Licenceday" value="${userSpecLicence[i].printLicenceday()}" /></td>
		</tr>
	</table><br>
	</c:forEach>
	
	<br><br><br>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecVoluteer" value="${userApp.getAppVolunteers()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecVoluteer" value="${userSpec.getSpecVolunteers()}" />
	</c:otherwise>
	</c:choose>
	
	<h3>봉사 활동</h3>
	<c:forEach var="i" begin="0" end="${appForm.getAppFormVolunteers()-1}">
	<table>
		<tr>
			<td>봉사활동내용</td>
			<td><input type="text" name="VolunteerDetails" value="${userSpecVoluteer[i].getVolunteerDetails()}" /></td>
		</tr>
		<tr>
			<td>봉사활동 시작일</td>
			<td><input type="date" name="VolunteerStartDay" value="${userSpecVoluteer[i].printStartDay()}" /></td>
		</tr>
		<tr>
			<td>봉사활동 종료일</td>
			<td><input type="date" name="VolunteerEndDay" value="${userSpecVoluteer[i].printEndDay()}" /></td>
		</tr>
		<tr>
			<td>봉사활동 기관</td>
			<td><input type="text" name="VolunteerInstitution" value="${userSpecVoluteer[i].getVolunteerInstitution()}" /></td>
		</tr>
	</table><br>
	</c:forEach>
	
	
	<br><br>
	<c:choose>
	<c:when test="${haveUserApp.equals('y')}">
		<c:set var="userSpecCareer" value="${userApp.getAppCareers()}" />
	</c:when>
	<c:otherwise>
		<c:set var="userSpecCareer" value="${userSpec.getSpecCareers()}" />
	</c:otherwise>
	</c:choose>
	
	<h3>경력</h3>
	<c:forEach var="i" begin="0" end="${appForm.getAppFormCareers() - 1}">
	<table>
		<tr>
			<td>경력종류</td>
			<td><input type="text" name="CareerType" value="${userSpecCareer[i].getCareerType()}" /></td>
		</tr>
		<tr>
			<td>근무 시작일</td>
			<td><input type="date" name="CareerStartDay" value="${userSpecCareer[i].printStartDay()}" /></td>
		</tr>
		<tr>
			<td>근무 종료일</td>
			<td><input type="date" name="CareerEndDay" value="${userSpecCareer[i].printEndDay()}" /></td>
		</tr>
		<tr>
			<td>근무부서</td>
			<td><input type="text" name="CareerDpName" value="${userSpecCareer[i].getCareerDpName()}" /></td>
		</tr>
		<tr>
			<td>연봉</td>
			<td><input type="text" name="AnnualSalary" value="${userSpecCareer[i].getAnnualSalary()}" /></td>
		</tr>
		<tr>
			<td>회사명</td>
			<td><input type="text" name="CompanyName" value="${userSpecCareer[i].getCompanyName()}" /></td>
		</tr>
		<tr>
			<td>직위</td>
			<td><input type="text" name="Position" value="${userSpecCareer[i].getPosition()}" /></td>
		</tr>
	</table><br>
	</c:forEach>
	
	<br><br>
	<h3>자기소개서</h3>
	<c:forEach var="selfintroduction" items="${appForm.getSelfIntroductions()}">
	<table>
		<tr>
			<td>번호</td>
			<td><input type="hidden" name="SelfIntroNum" value="${selfintroduction.getSelfIntroNum()}" />${selfintroduction.getSelfIntroNum()}</td>
		</tr>
		<tr>
			<td>최대 크기</td>
			<td><input type="hidden" name="IntroSize" value="${selfintroduction.getIntroSize()}" />${selfintroduction.getIntroSize()} Bytes</td>
		</tr>
		<tr>
			<td>항목</td>
			<td><input type="hidden" name="ItemName" value="${selfintroduction.getItemName()}" />${selfintroduction.getItemName()}</td>
		</tr>
		<tr>
			<td>내용</td>
			
			<c:choose>
			<c:when test="${selfintroductions != null}">
				<c:forEach var="selfintroductions" items="${selfintroductions}">
					<c:if test="${selfintroduction.getSelfIntroNum() == selfintroductions.getSelfIntroNum()}"><td><textarea name="selfIntroContents" cols="40" rows="15">${selfintroductions.getSelfIntroContents()}</textarea></td></c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<td><textarea name="selfIntroContents" cols="40" rows="15"></textarea></td>
			</c:otherwise>
			</c:choose>
		</tr>
		
	</table><br>
	</c:forEach>
	
	<input type="hidden" name="command" value="userAppUpdate">
	<input type="submit" value="등록">
</form>
</center>
</body>
</html>