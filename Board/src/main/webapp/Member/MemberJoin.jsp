<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center>

<form action="MemberJoinProc.jsp" method="post">

<h2>회원가입 </h2>

<table width="600" border ="1">

<tr height="40">
<td>아이디 </td><td> <input type="text" name="id" /> </td>
</tr>

<tr height="40">
<td>비밀번호  </td><td> <input type="password" name="password" /> </td>
</tr>

<tr height="40">
<td>이메일   </td><td> <input type="email"  name="email"/> </td>
</tr>
<tr height = "40">
<td align="center" colspan="3"><input type="submit" value="회원가입   "> &nbsp; 
<input type="button" value="로그인"  onclick="location.href = 'MemberLogin.jsp' ">&nbsp; 
<input type="button" value="홈"  onclick="location.href = '../Main/index.jsp' "> </td>
</tr>


</table>
</form>


</center>


</body>
</html>