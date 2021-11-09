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
<table width="800" border = "1">
<tr height="200">
<td width="800">
<jsp:include page="Top.jsp"></jsp:include>
</td>
</tr>

<tr height="600">
<td width="800">
<jsp:include page="Center.jsp"></jsp:include>
</td>
</tr>

<tr height="100">
<td width="800">
<jsp:include page="Bottom.jsp"></jsp:include>
</td>
</tr>


</table>

</center>



</body>
</html>