<%@page import="model.MemberBean"%>
<%@page import="model.MemberDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberBean" class="model.MemberBean"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<%

String id = request.getParameter("id");
memberBean.setId(id);

String password = request.getParameter("password");
memberBean.setPassword(password);

String email = request.getParameter("email");
memberBean.setEmail(email);


if(memberBean.getId() == null || memberBean.getPassword()==null || memberBean.getEmail() ==null){
	
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안된 사항이 있습니다')");
	script.println("history.back()");
	script.println("</script>");
}else {
	
	MemberDAO memberDAO = new MemberDAO();
	int result = memberDAO.join(memberBean);
		if(result == -1){
	
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디입니다.'.')");
		script.println("history.back()");
		script.println("</script>");
		
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='../Main/index.jsp' ");
		script.println("</script>");
		
		
	}
	
}

%>


</body>
</html>