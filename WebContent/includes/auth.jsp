<%@page import="br.com.sirius.model.*"%>
<% User auth = (User)request.getSession().getAttribute("auth");
	if(auth!=null){
		request.setAttribute("auth", auth);
	}
%>