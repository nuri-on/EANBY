<%@ page language="java" contentType="text/html; charset=euc-kr"
	pageEncoding="euc-kr"
	import="classes.*, userSpec.*, java.util.*, java.text.SimpleDateFormat, oracle.sql.BLOB, java.io.*, java.sql.*"%>
<%
	request.setCharacterEncoding("euc-kr");
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="userSpec" scope="request" class="userSpec.UserSpecDTO" />
<jsp:useBean id="user" scope="session" class="user.UserDTO" />
<%
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

	ServletContext context = this.getServletContext();						
	String path = context.getRealPath("\\user");
	String realPath = path.toString();
	session.setAttribute("realPath", realPath);
%>

<html>
<head>
<link rel="stylesheet" href="./Css/login.css" type="text/css" />
<title>My Spec Insert</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="../css/user.css" type="text/css">
<script type="text/javascript">	
	function delSpecRef(SpecId) {
		var tableId = SpecId;
		var table = document.getElementById(tableId);
		table.parentNode.removeChild(table);

		spec_form.delSpecElement.value = SpecId;
		spec_form.command.value = "specElementDelete";
		spec_form.action = "user_spec_write.m2";
		spec_form.submit();
	}

	var languageLevelFormCnt = 100;
	function addLanguageLevels() {
		++languageLevelFormCnt;
		msg = "<table id=LanguageLevels"
				+ languageLevelFormCnt
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>��������"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input type=text style='width: 200' name=LanguageType"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('LanguageLevels"
				+ languageLevelFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>ȸȭ����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=ConversationLevel"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�б����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=ReadingLevel"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=WritingLevel"
				+"></tr></table>";

		divLanguageLevelForm.innerHTML += msg;
	}

	var languageTestFormCnt = 100;
	function addLanguageTests() {
		++languageTestFormCnt;
		msg = "<table id=LanguageTests"
				+ languageTestFormCnt
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>�����"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input type=text style='width: 200' name=TestName"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('LanguageTests"
				+ languageTestFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=TestScore"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>���</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=TestLevel"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=TestDay"
				+"></tr></table>";

		divLanguageTestForm.innerHTML += msg;
	}

	var licenceFormCnt = 100;
	function addLicences() {
		++licenceFormCnt;
		msg = "<table id=Licences" 
				+ licenceFormCnt 
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>�ڰ�����"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input type=text style='width: 200' name=LicenceName"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('Licences"
				+ licenceFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����ó</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=LicenceInstitution"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����(���)</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=LicenceScore"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=Licenceday"
				+"></tr></table>";

		divLicenceForm.innerHTML += msg;
	}

	var awardFormCnt = 100;
	function addAwards() {
		++awardFormCnt;
		msg = "<table id=Awards"
				+ awardFormCnt
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>�����"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input type=text style='width: 200' name=Awardname"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('Awards"
				+ awardFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>���� ���</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=Awarddetails"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>���ֱ��</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=Awardinstitution"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>��������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=Awardday"
				+"></tr></table>";

		divAwardForm.innerHTML += msg;
	}

	var foreignstudyFormCnt = 100;
	function addForeignstudies() {
		++foreignstudyFormCnt;
		msg = "<table id=Foreignstudies"
				+ foreignstudyFormCnt
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>�����̸�"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input	type=text style='width: 200' name=CountryName"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('Foreignstudies"
				+ foreignstudyFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�������� �� ����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=PurposeAndContents"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=StayStartDay"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=StayEndDay"
				+"></tr></table>";

		divForeignstudyForm.innerHTML += msg;
	}

	var volunteerFormCnt = 100;
	function addVolunteers() {
		++volunteerFormCnt;
		msg = "<table id=Volunteers"
				+ volunteerFormCnt
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>����Ȱ������"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input	type=text style='width: 200' name=VolunteerDetails"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('Volunteers"
				+ volunteerFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����Ȱ�� ������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=VolunteerStartDay"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����Ȱ�� ������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=VolunteerEndDay"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����Ȱ�� ���</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=VolunteerInstitution"
				+"></tr></table>";

		divVolunteerForm.innerHTML += msg;
	}

	var careerFormCnt = 100;
	function addCareers() {
		++careerFormCnt;
		msg = "<table id=Careers"
				+ careerFormCnt
				+" border=0 cellpadding=0 cellspacing=1 width=590 style='margin-top: 7px'>"
				+ "<tr><td width=140 align=center bgcolor=E6ECDE height=22>�������"
				+ "</td><td width=470 bgcolor=ffffff style='padding-left: 10'><input type=text style='width: 200' name=CareerType"
				+">&nbsp;&nbsp;<input type=button value=���� onClick=\"delSpec('Careers"
				+ careerFormCnt
				+ "')\"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�ٹ� ������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=CareerStartDay"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�ٹ� ������</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=CareerEndDay"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>�ٹ� �μ�</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=CareerDpName"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=AnnualSalary"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>ȸ���</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=CompanyName"
				+"></td></tr><tr><td width=140 align=center bgcolor='E6ECDE' height='22'>����</td><td width=470 bgcolor='ffffff' style='padding-left: 10'><input type=text style='width: 200' name=Position"
				+"></tr></table>";

		divCareerForm.innerHTML += msg;
	}

	function delSpec(SpecId) {
		var tableId = SpecId;
		var table = document.getElementById(tableId);

		if (SpecId.replace(/[^0-9]/g, "") < 100) {

			spec_form.delSpecElement.value = SpecId;
			spec_form.command.value = "specElementDelete";
			spec_form.action = "user_spec_write.m2";
			spec_form.submit();
		} else {
			table.parentNode.removeChild(table);
		}
	}

	// �̹��� function ����
	function previewImage(targetObj, View_area) {
		var preview = document.getElementById(View_area); //div id
		var ua = window.navigator.userAgent;

		//ie�϶�(IE8 ���Ͽ����� �۵�)
		if (ua.indexOf("MSIE") > -1) {
			targetObj.select();
			try {
				var src = document.selection.createRange().text; // get file full path(IE9, IE10���� ��� �Ұ�)
				var ie_preview_error = document
						.getElementById("ie_preview_error_" + View_area);

				if (ie_preview_error) {
					preview.removeChild(ie_preview_error); //error�� ������ delete
				}

				var img = document.getElementById(View_area); //�̹����� �ѷ��� ��

				//�̹��� �ε�, sizingMethod�� div�� ���缭 ����� �ڵ����� �ϴ� ����
				img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
						+ src + "', sizingMethod='scale')";
			} catch (e) {
				if (!document.getElementById("ie_preview_error_" + View_area)) {
					var info = document.createElement("<p>");
					info.id = "ie_preview_error_" + View_area;
					info.innerHTML = e.name;
					preview.insertBefore(info, null);
				}
			}
			//ie�� �ƴҶ�(ũ��, ���ĸ�, FF)
		} else {
			var files = targetObj.files;
			for (var i = 0; i < files.length; i++) {
				var file = files[i];
				var imageType = /image.*/; //�̹��� �����ϰ�츸.. �ѷ��ش�.
				if (!file.type.match(imageType))
					continue;
				var prevImg = document.getElementById("prev_" + View_area); //������ �̸����Ⱑ �ִٸ� ����
				if (prevImg) {
					preview.removeChild(prevImg);
				}
				var img = document.createElement("img");
				img.id = "prev_" + View_area;
				img.classList.add("obj");
				img.file = file;
				img.style.width = '100px';
				img.style.height = '100px';
				preview.appendChild(img);
				if (window.FileReader) { // FireFox, Chrome, Opera Ȯ��.
					var reader = new FileReader();
					reader.onloadend = (function(aImg) {
						return function(e) {
							aImg.src = e.target.result;
						};
					})(img);
					reader.readAsDataURL(file);
				} else { // safari is not supported FileReader
					//alert('not supported FileReader');
					if (!document.getElementById("sfr_preview_error_"
							+ View_area)) {
						var info = document.createElement("p");
						info.id = "sfr_preview_error_" + View_area;
						info.innerHTML = "not supported FileReader";
						preview.insertBefore(info, null);
					}
				}
			}
		}
	}
	// �̹��� function ��
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<br>
<!--  <a href="javascript:document.location.reload();">ddddddd</a> -->	
	<table width=780 border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td width="20"></td>
			<td>
				<!--contents-->
				<table width=590 border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td height="22"><h2>My Spec</h2></td>
					</tr>
				</table> <br> <!-- write Form  -->
				<form name="spec_form" method="post" action="user_spec_write.m2">
					<input type="hidden" name="delSpecElement" value="" />
					<table border="0" cellpadding="0" cellspacing="1" width="590">
						<tr>
							<td width=140 height="22" colspan="2"><b>�Ż� ����</b></td>
						</tr>
						<tr>	
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�������</td>						
							<td width=470 bgcolor="ffffff" style="padding-left: 10">								
								<div id='View_area2' style='position:relative;left:0px;top:0px;; z-index: 1; width: 115px; height: 152px; color: black; border: 0px solid black; dispaly: inline; '>
								<%								
								String photoExisted = request.getAttribute("photoExisted").toString();
								
								if (photoExisted.equals("true")) {
									Connection conn = null;

									String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
									String db_url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
									
									int UserNum = userSpec.getSpecUserInfo().getUserNum();
									String image_name = userSpec.getSpecUserInfo().getUserNum() + ".jpg";

									try {
										Class.forName(jdbc_driver);
										conn = DriverManager.getConnection(db_url,"eanby","eanby2012");
										/*create table mytable (bfile BLOB, cfile CLOB) ���� ���̺��� ������ �Ŀ�....
										 * ���̳ʸ� ������ �÷��� ���̳ʸ� �����͸� �����Ѵ�.
										 */
										Statement stmt = conn.createStatement();
										ResultSet rs = stmt.executeQuery("select USERPHOTO from SPEC_PHOTO where USERNUM=" + UserNum);
										
										while (rs.next()) {
											BLOB blob = (BLOB)rs.getBlob(1);

											InputStream instream = blob.getBinaryStream();
											FileOutputStream outstream = new FileOutputStream(path + image_name);

											int size = blob.getBufferSize();
											byte[] buffer = new byte[size];
											int length = -1;

											while ((length = instream.read(buffer)) != -1) {
												outstream.write(buffer, 0, length);
											}

											outstream.close();
											instream.close();
										}
										
										rs.close();
										stmt.close();
										conn.commit();
										conn.close();
									}
									catch (SQLException e) {
										e.printStackTrace();
									}									
									
									out.println("<img src=\"user" + image_name + "\" width=115 height=152>");			
								}								
								%>
								</div>
								<div id='View_area' style='position:relative;left:0px;top:0px;; z-index: 2; width: 115px; height: 152px; color: black; border: 0px solid black; dispaly: inline; '></div>
								<input type="file" name="profile_pt" id="profile_pt" onchange="previewImage(this,'View_area')">
							</td>
						</tr>
						<tr style="margin-top: 3px">
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�ѱ��̸�</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Name1"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getName1() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getName1());
																					   else
																						    out.println("value= " + user.getUserName());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�����̸�</td>

							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Name2"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getName2() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getName2());%>>
							</td>
						</tr>

						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�ѹ��̸�</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Name3"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getName3() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getName3());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Birthday"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getBirthDay() != null) 
																							out.println("value= " + transFormat.format(userSpec.getSpecUserInfo().getBirthDay()));
																					   else
																						    out.println("value= " + user.getUserBirth());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�ּ�</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Address"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getAddress() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getAddress());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�̸���
							</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Email"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getEmail() != null && userSpec.getSpecUserInfo().getEmail() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getEmail());
																					   else
																						    out.println("value= " + user.getUserEmail());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">��ȭ
								��ȣ</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Phone1"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getPhone1() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getPhone1());
																					   else
																						    out.println("value= " + user.getUserPhone1());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�ڵ���
								��ȣ</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Phone2"
								<%if (userSpec.getSpecUserInfo() != null && userSpec.getSpecUserInfo().getPhone2() != null) 
																							out.println("value= " + userSpec.getSpecUserInfo().getPhone2());
																					   else
																						    out.println("value= " + user.getUserPhone2());%>>
							</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="1" width="590">
						<tr>
							<td width=140height="22" colspan="2"><b>����б�</b></td>
						</tr>
						<tr style='margin-top: 7px'>
							<td width=140 align=center bgcolor="E6ECDE" height="22">���
								����б�</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="HighschoolName"
								<%if (userSpec.getSpecHighschool() != null && userSpec.getSpecHighschool().getHighschoolName() != null) 
																							out.println("value= " + userSpec.getSpecHighschool().getHighschoolName());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								���г��</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="date" style="width: 200" name="HEntanceDay"
								<%if(userSpec.getSpecHighschool() != null && userSpec.getSpecHighschool().getEntanceDay() != null) 
																							out.println("value= " + transFormat.format(userSpec.getSpecHighschool().getEntanceDay()));%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="date" style="width: 200" name="HGraduateDay"
								<%if (userSpec.getSpecHighschool() != null && userSpec.getSpecHighschool().getGraduateDay() != null) 
																							out.println("value= " + transFormat.format(userSpec.getSpecHighschool().getGraduateDay()));%>>
							</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
					</table>

					<table border="0" cellpadding="0" cellspacing="1" width="590">
						<tr>
							<td width=140 height="22" colspan="2"><b>���б�</b></td>
						</tr>
						<tr style='margin-top: 7px'>
							<td width=140 align=center bgcolor="E6ECDE" height="22" >���
								����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="UniversityName"
								<%if (userSpec.getSpecUniversity() != null && userSpec.getSpecUniversity().getUniversityName() != null) 
																							out.println("value= " + userSpec.getSpecUniversity().getUniversityName());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								���г��</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="date" style="width: 200" name="UEntranceDay"
								<%if (userSpec.getSpecUniversity() != null && userSpec.getSpecUniversity().getEntranceDay() != null) 
																							out.println("value= " + transFormat.format(userSpec.getSpecUniversity().getEntranceDay()));%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">
								�������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="date" style="width: 200" name="UGraudateDay"
								<%if (userSpec.getSpecUniversity() != null && userSpec.getSpecUniversity().getGraudateDay() != null) 
																							out.println("value= " + transFormat.format(userSpec.getSpecUniversity().getGraudateDay()));%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Major"
								<%if (userSpec.getSpecUniversity() != null && userSpec.getSpecUniversity().getMajor() != null) 
																							out.println("value= " + userSpec.getSpecUniversity().getMajor());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Grade"
								<%if (userSpec.getSpecUniversity() != null && userSpec.getSpecUniversity().getGrade() != null) 
																							out.println("value= " + userSpec.getSpecUniversity().getGrade() );%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Credit"
								<%if (userSpec.getSpecUniversity() != null && userSpec.getSpecUniversity().getCredit() != null) 
																							out.println("value= " + userSpec.getSpecUniversity().getCredit());%>>
							</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
					</table>
				
					<table border="0" cellpadding="0"
						cellspacing="1" width="325"><tr><td><b>����</b>&nbsp;&nbsp;&nbsp;<input type="button" value="+" onClick="addLanguageLevels()"></td></tr>
					</table>

					<%
						int CntLanguageLevels = 0;
												for (LanguageLevel ll : (ArrayList<LanguageLevel>)userSpec.getSpecLanguageLevels()) {
					%>

					<table id="LanguageLevels<%=CntLanguageLevels%>" border="0"
						cellpadding="0" cellspacing="1" width="590" style='margin-top: 7px'>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">��������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="LanguageType"
								<%if (!ll.getLanguageType().equals("")  && ll.getLanguageType() != null) 
										out.println("value=" + ll.getLanguageType());%>>&nbsp;&nbsp;<input type=button value=����
								onClick="delSpec('LanguageLevels<%=CntLanguageLevels%>')"></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">ȸȭ����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="ConversationLevel"
								<%if (!ll.getConversationLevel().equals("") && ll.getConversationLevel() != null) 
																							out.println("value=" + ll.getConversationLevel());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�б����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="ReadingLevel"
								<%if (!ll.getReadingLevel().equals("") && ll.getReadingLevel() != null) 
																							out.println("value=" + ll.getReadingLevel());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="WritingLevel"
								<%if (!ll.getWritingLevel().equals("") && ll.getWritingLevel() != null) 
																							out.println("value=" + ll.getWritingLevel());%>></td>
						</tr>
					</table>
					<%
						CntLanguageLevels++; }
					%>

					<div id="divLanguageLevelForm"></div>
					<br>
					<table border="0" cellpadding="0"
						cellspacing="1" width="325"><tr><td><b>���н���</b>&nbsp;&nbsp;&nbsp;<input type="button" value="+" onClick="addLanguageTests()"></td></tr>
					</table>
				
					<%
						int CntLanguageTests = 0;
												for (LanguageTest lt : (ArrayList<LanguageTest>)userSpec.getSpecLanguageTests()) {
					%>
					<table id="LanguageTests<%=CntLanguageTests%>" border="0"
						cellpadding="0" cellspacing="1" width="590" style='margin-top: 7px'>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�����
							</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="TestName"
								<%if (!lt.getTestName().equals("") && lt.getTestName() != null) 
										out.println("value =" + lt.getTestName());%>>&nbsp;&nbsp;<input type=button value=����
								onClick="delSpec('LanguageTests<%=CntLanguageTests%>')">
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="TestScore"
								<%if (!lt.getTestScore().equals("") && lt.getTestScore() != null) 
																							out.println("value =" + lt.getTestScore());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">���</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="TestLevel"
								<%if (!lt.getTestLevel().equals("") && lt.getTestLevel() != null) 
																				out.println("value=" + lt.getTestLevel());%>>
							</td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�����
							</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="date" style="width: 200" name="TestDay"
								<%if (lt.getTestDay() != null && lt.getTestDay() != null) 
																							out.println("value=" + transFormat.format(lt.getTestDay()));%>>
							</td>
						</tr>
					
					</table>
					<%
						CntLanguageTests++; }
					%>
					<div id="divLanguageTestForm"></div>
					<br>
					<table border="0" cellpadding="0"
						cellspacing="1" width="325"><tr><td><b>�ڰ���</b>&nbsp;&nbsp;&nbsp;<input type="button" value="+" onClick="addLicences()"></td></tr>
					</table>
					<%
						int CntLicences = 0;
												for (Licence li : (ArrayList<Licence>)userSpec.getSpecLicences()) {
					%>
					<table id="Licences<%=CntLicences%>" border="0" cellpadding="0"
						cellspacing="1" width="590" style='margin-top: 7px'>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�ڰ�����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="LicenceName"
								<%if (!li.getLicenceName().equals("") && li.getLicenceName() != null) 
										out.println("value=" + li.getLicenceName());%>>&nbsp;&nbsp;<input type=button value=����
								onClick="delSpec('Licences<%=CntLicences%>')"></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����ó</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="LicenceInstitution"
								<%if (!li.getLicenceInstitution().equals("") && li.getLicenceInstitution() != null) 
																							out.println("value=" + li.getLicenceInstitution());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����(���)</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="LicenceScore"
								<%if (!li.getLicenceScore().equals("") && li.getLicenceScore() != null) 
																							out.println("value=" + li.getLicenceScore());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Licenceday"
								<%if (li.getLicenceday() != null && li.getLicenceday() != null) 
																							out.println("value=" + transFormat.format(li.getLicenceday()));%>></td>
						</tr>
					

					</table>
					<%
						CntLicences++;
												}
					%>
					<div id="divLicenceForm"></div>
					<br>
					<table border="0" cellpadding="0"
						cellspacing="1" width="325"><tr><td><b>������</b>&nbsp;&nbsp;&nbsp;<input type="button" value="+" onClick="addAwards()"></td></tr>
					</table>

					<%
						int CntAwards = 0;
												for (Award aw : (ArrayList<Award>)userSpec.getSpecAwards()) {
					%>
					<table id="Awards<%=CntAwards%>" border="0" cellpadding="0"
						cellspacing="1" width="590" style='margin-top: 7px'>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Awardname"
								<%if (!aw.getAwardname().equals("") && aw.getAwardname() != null) 
										out.println("value=" + aw.getAwardname());%>>&nbsp;&nbsp;<input type=button value=����
								onClick="delSpec('Awards<%=CntAwards%>')"></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����
								���</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Awarddetails"
								<%if (!aw.getAwarddetails().equals("") && aw.getAwarddetails() != null) 
																							out.println("value=" + aw.getAwarddetails());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">���ֱ��</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Awardinstitution"
								<%if (!aw.getAwardinstitution().equals("") && aw.getAwardinstitution() != null) 
																							out.println("value=" +  aw.getAwardinstitution());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">��������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Awardday"
								<%if (aw.getAwardday() != null && aw.getAwardday() != null) 
																							out.println("value=" + transFormat.format(aw.getAwardday()));%>></td>
						</tr>
			
					</table>
					<%
						CntAwards++;
												}
					%>

					<div id="divAwardForm"></div>


					<%-- 
					�ؿܿ�������<input type="button" value="+" onClick="addForeignstudies()">
					&nbsp;
					<% 
						int CntForeignStudies = 0;
						for (ForeignStudy fs : (ArrayList<ForeignStudy>)userSpec.getSpecForeignStudies()) {
					%>
					<table id="ForeignStudies<%=CntForeignStudies%>"border="0" cellpadding="0" cellspacing="1" width="590">
						<tr>
							<td colspan="2"><input type=button value=���� onClick="delSpec('ForeignStudies<%=CntForeignStudies%>')"> </td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�����̸�</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="CountryName" <% if (!fs.getCountryName().equals("")  && fs.getCountryName() != null) 
																						out.println("value=" + fs.getCountryName()); %>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">��������
								�� ����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="PurposeAndContents" <% if (!fs.getPurposeAndContents().equals("") && fs.getPurposeAndContents() != null) 
																							out.println("value=" + fs.getPurposeAndContents()); %> ></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����
								������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="StayStartDay" <% if (fs != null && fs.getStayStartDay() != null) 
																							out.println("value=" + transFormat.format(fs.getStayStartDay())); %> ></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����
								������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="StayEndDay" <% if (fs != null && fs.getStayEndDay() != null) 
																							out.println("value=" + transFormat.format(fs.getStayEndDay())); %> ></td>
						</tr>
						<tr>
							<td colspan='2'></td>
						</tr>
					</table>
					<% 
						CntForeignStudies++;
						}
					%>
					<div id="divForeignstudyForm"></div> --%>
					<br>
					<table border="0" cellpadding="0"
						cellspacing="1" width="325"><tr><td><b>���� Ȱ��</b>&nbsp;&nbsp;&nbsp;<input type="button" value="+" onClick="addVolunteers()"></td></tr>
					</table>
					 
					<%
						int CntVolunteers = 0;
												for (Volunteer vl : (ArrayList<Volunteer>)userSpec.getSpecVolunteers()) {
					%>
					<table id="Volunteers<%=CntVolunteers%>" border="0" cellpadding="0"
						cellspacing="1" width="590" style="margin-top: 3px">
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����Ȱ������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="VolunteerDetails"
								<%if (vl != null && vl.getVolunteerDetails() != null) 
										out.println("value=" + vl.getVolunteerDetails());%>>&nbsp;&nbsp;<input type=button value=����
								onClick="delSpec('Volunteers<%=CntVolunteers%>')"></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����Ȱ��
								������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="VolunteerStartDay"
								<%if (vl != null && vl.getVolunteerStartDay() != null) 
																							out.println("value=" + transFormat.format(vl.getVolunteerStartDay()));%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����Ȱ��
								������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="VolunteerEndDay"
								<%if (vl != null && vl.getVolunteerEndDay() != null) 
																							out.println("value=" + transFormat.format(vl.getVolunteerEndDay()));%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����Ȱ��
								���</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="VolunteerInstitution"
								<%if (vl != null && vl.getVolunteerInstitution() != null) 
																							out.println("value=" + vl.getVolunteerInstitution());%>></td>
						</tr>
				
					</table>
					<%
						CntVolunteers++;
												}
					%>
					<div id="divVolunteerForm"></div>
					<br>
					<table border="0" cellpadding="0"
						cellspacing="1" width="325"><tr><td><b>���</b>&nbsp;&nbsp;&nbsp;<input type="button" value="+" onClick="addCareers()"></td></tr>
					</table>
		
					<%
						int CntCareers = 0;
												for (Career ca : (ArrayList<Career>)userSpec.getSpecCareers()) {
					%>
					<table id="Careers<%=CntCareers%>" border="0" cellpadding="0"
						cellspacing="1" width="590" style="margin-top: 3px">
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">���
								����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="CareerType"
								<%if (ca.getCareerType() != null && ca != null) 
										out.println("value=" + ca.getCareerType());%>>&nbsp;&nbsp;<input type=button value=����
								onClick="delSpec('Careers<%=CntCareers%>')"></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�ٹ�
								������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="CareerStartDay"
								<%if (ca.getCareerStartDay() != null && ca != null) 
																							out.println("value=" + transFormat.format(ca.getCareerStartDay()));%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�ٹ�
								������</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="CareerEndDay"
								<%if (ca.getCareerEndDay() != null && ca != null) 
																							out.println("value=" + transFormat.format(ca.getCareerEndDay()));%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">�ٹ�
								�μ�</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="CareerDpName"
								<%if (ca.getCareerDpName() != null && ca != null) 
																							out.println("value=" + ca.getCareerDpName());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="AnnualSalary"
								<%if (ca.getAnnualSalary() != null && ca != null) 
																							out.println("value=" + ca.getAnnualSalary());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">ȸ���</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="CompanyName"
								<%if (ca.getCompanyName() != null && ca != null) 
																							out.println("value=" + ca.getCompanyName());%>></td>
						</tr>
						<tr>
							<td width=140 align=center bgcolor="E6ECDE" height="22">����</td>
							<td width=470 bgcolor="ffffff" style="padding-left: 10"><input
								type="text" style="width: 200" name="Position"
								<%if (ca.getPosition() != null && ca != null) 
																							out.println("value=" + ca.getPosition());%>></td>
						</tr>
				
					</table>
					<%
						CntCareers++;
												}
					%>
					<div id="divCareerForm"></div>
					<br>
					<table width=590 border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td align=center><input type="hidden" name="command"
								value="specInsert">
								 <input type="submit" name ="frm" value="����">
								&nbsp;</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>