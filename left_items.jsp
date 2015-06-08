<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
<link rel="stylesheet" href="./Css/left_items.css" type="text/css" />
<SCRIPT>
	function ShowMenu(bMenu) {
		document.all.idFinder.style.display = (bMenu) ? "none" : "block"
		document.all.idMenu.style.display = (bMenu) ? "block" : "none"
		idML.className = (bMenu) ? "cOn" : "cOff"
		idRL.className = (bMenu) ? "cOff" : "cOn"
		return false
	}

	function searchUserAppList() {
		search_form.command.value = "userAppList";
		search_form.action = "left_items.m2";
		search_form.submit();
	}

	function goAllList() {
		list_form.command.value = "userAppList";
		list_form.action = "left_items.m2"
		list_form.submit();
	}

	function clearTxt(txt) {
		if (txt.defaultValue == txt.value) {
			txt.value = "";
		}
	}

	function disp(el) {
		el.children[1].style.display = (el.children[1].style.display == '' ? 'block'
				: '')
	}

	function checkKey(el) {
		if (event.keyCode == 13)
			el.click();
	}
</SCRIPT>

<STYLE>
<!--
A.cOn {
	text-decoration: none;
	font-weight: bolder
}

#article {
	font: 12pt Verdana, geneva, arial, sans-serif;
	background: white;
	color: black;
	padding: 10pt 15pt 0 5pt
}

#article P.start {
	text-indent: 0pt
}

#article P {
	margin-top: 0pt;
	font-size: 10pt;
	text-indent: 12pt
}

#article #author {
	margin-bottom: 5pt;
	text-indent: 0pt;
	font-style: italic
}

#pageList P {
	padding-top: 10pt
}

#article H3 {
	font-weight: bold
}

#article DL, UL, OL {
	font-size: 10pt
}

-->
#demo {
	cursor: hand;
	padding: 0px;
	margin: 2px 0 2px 13px;
}

#demo UL {
	display: none;
	cursor: hand;
	list-style: none;
	margin: 0;
	padding: 0;
}

#demo LI {
	cursor: hand;
	list-style-image: url(./Image/folder.png);
	text-align: left;
	margin: 3px 0 0 7px;
}

#demo LI UL LI {
	cursor: hand;
	list-style-image: url(./Image/app.png);
	text-align: left;
	margin: 1px 0 0 17px;
}
</STYLE>
</HEAD>


<body>

	<SCRIPT>
		
	</SCRIPT>
	<div align="center">
		<form name="search_form" method="post" target="_self">
			<table>
				<tr>
					<td><input type="text" name="search"
						value="입력하세요" size="13"
						onFocus="clearTxt(search_form.search)" /><input type=hidden
						name="command" value="" /><input type="button" name="searchBtn"
						value="찾기" onClick="searchUserAppList(search_form.search)" /></td>
				</tr>
			</table>
		</form>



		<form name="list_form">
			<input type=hidden name="command" value="" />
			<UL ID=demo>
				<c:if test="${resultKeyword.equals('')}">
					<LI style="cursor: hand;"><SPAN ONKEYPRESS="checkKey(this)"
						ONCLICK="disp(this.parentElement)">2014 하반기</SPAN>
						<UL>
							<c:forEach var="userAppFormList" items="${userAppFormList}">
								<c:if
									test="${userAppFormList.getAppFormStartDate().getYear() == 114 && userAppFormList.getAppFormStartDate().getMonth() > 6}">
									<LI ID=demoLI><span><a
											href="main_inserApp.m2?command=userAppInsert&AppFormNum=${userAppFormList.getAppFormNum()}"
											target="contents">${userAppFormList.getAppFormCompany()}</a></span>
								</c:if>
							</c:forEach>
						</UL>
					<LI><SPAN ONKEYPRESS="checkKey(this)"
						ONCLICK="disp(this.parentElement)">2014 상반기</SPAN>
						<UL>
							<c:forEach var="userAppFormList" items="${userAppFormList}">
								<c:if
									test="${userAppFormList.getAppFormStartDate().getYear() == 114 && userAppFormList.getAppFormStartDate().getMonth() < 6}">
									<span><LI ID=demoLI><a
											href="main_inserApp.m2?command=userAppInsert&AppFormNum=${userAppFormList.getAppFormNum()}"
											target="contents">${userAppFormList.getAppFormCompany()}</a></span>
								</c:if>
							</c:forEach>
						</UL>
				</c:if>
				<c:if test="${!resultKeyword.equals('')}">
					<c:forEach var="userAppFormList" items="${userAppFormList}">
						<LI style="list-style-image: url(./Image/app.png);"><a
							href="main_inserApp.m2?command=userAppInsert&AppFormNum=${userAppFormList.getAppFormNum()}"
							target="contents">${userAppFormList.getAppFormCompany()}</a>
					</c:forEach>
					<br>
					<br>
					<br>
					<div align="right">
						<input type="button" value="All List" onClick="goAllList()" />
					</div>
				</c:if>
		</form>

	</div>
</body>
</html>

