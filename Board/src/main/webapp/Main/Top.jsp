<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <script type="text/javascript">
        
        // 로그아웃 담당 JSP로 이동
        function logoutPro(){
            location.href="../Member/MemberLogoutProc.jsp";
        }
    </script>


<!--Top  -->
		<table width = "800" border ="1"> 
			<tr height="150">
				<td width="800" align = "center">
					<font size="20">DROP THE BEAT</font>
				</td>
			</tr>
			</table>
			<table>
			<tr height="50">
				<td width = "110" align = "center">정치 </td>
				<td width = "110" align = "center">경제 </td>
				<td width = "110" align = "center">문화 </td>
				<td width = "110" align = "center">연애 </td>
				<td width = "110" align = "center"> <a href="../Board/BoardList.jsp"></a> 공지사항 </td>		
				<td width = "140" align = "center">
				<%
				String id=null;
				if(id == null)
 				id = (String)session.getAttribute("id");
				%>
				<%=id %>님 <button id="btn1" onclick="location.href='../Member/MemberLogin.jsp' ">로그인</button>
				&nbsp;&nbsp;		
				<input type="button" value="로그아웃" onclick="logoutPro()" />

				</td>		

			</tr>
		</table>


</body>
</html>