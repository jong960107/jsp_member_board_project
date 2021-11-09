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

<h2>로그인  </h2>
<form action="MemberLoginProc.jsp" method="post">
<table width="600" border ="1">

<tr height="40">
<td align="center">아이디 </td><td> <input type="text" name="id" size="60" /> </td>
</tr>

<tr height="40">
<td align="center">비밀번호  </td><td> <input type="password" name="password" size="60"/> </td>
</tr>


<tr height = "40">
<td align="center" colspan="3"><input type="submit" value="로그인">&nbsp;<input type="button" value="회원가입" onclick="location.href='MemberJoin.jsp' "></td>
</tr>


</form>
</center>

</body>
</html>