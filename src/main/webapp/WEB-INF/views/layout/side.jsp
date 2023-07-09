<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Sidebar-->
<div class="sidebar-heading border-bottom bg-light">
	<a id="logo" href="#"></a>
	<img alt="logo" src="<%=request.getContextPath()%>/images/logo.png" width="200">
</div>

<%
	String[] url = request.getRequestURI().split("/");
	String select = url[url.length - 1];
%>

<div class="list-group list-group-flush">
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("member.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/member.do">회원관리</a>
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("appointment.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/appointment.do">예약관리</a>
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("room.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/room.do">객실관리</a>
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("user.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/user.do">직원관리</a>
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("notice.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/notice.do">공지관리</a>
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("event.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/event.do">이벤트</a>
    <a class="list-group-item list-group-item-action list-group-item-light p-3 <%= select.equals("status.jsp") ? "active" : "" %>" href="<%=request.getContextPath()%>/admin/status.do">통계</a>
</div>

