<%@page import="platinum.platform.web.HttpClientCache"%>
<%@page import="platinum.cms.common.PostType"%>
<%@page import="platinum.cms.common.entity.PostEntity"%>
<%@page import="platinum.cms.runtime.service.PostRuntimeManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags" %>

<%
String id = request.getParameter("id");
String categoryId = request.getParameter("categoryId");
PostRuntimeManager postManager = PostRuntimeManager.getInstance();
PostEntity post = null;
if (categoryId.equals("search"))
{
    post = postManager.getPost(id);
    if (post != null)
    {
        response.sendRedirect(post.getLink());
        return;
    }
}
else
{
	post = postManager.getPost(id, categoryId);
}
if (post == null)
{
    response.setStatus(404);
    return;
}
else if (post.getPostType() == PostType.LINK)
{
    response.sendRedirect(post.getSummary());
    return;
}
%>

<%
HttpClientCache clientCache = new HttpClientCache(request, response);
clientCache.setMaxAge(30 * 24 * 60);
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
<head>
	<cms:Meta/>
    <title><%= post.getTitle()%> - 南京师范大学中北学院</title>
    <link rel="stylesheet" href="/static/common/css/basic.css"/>
    <link rel="stylesheet" href="/static/common/css/post.css"/>
    <script src="/static/common/scripts/lib/jquery.js" type="text/javascript"></script>
    <script src="/static/common/scripts/src/IE6.js" type="text/javascript"></script>
</head>
<body onload="<%=categoryId%>()">

<cms:Header path="<%= categoryId%>" displaySideBar="<%= post.getPostType() != PostType.INFORMATION%>"/>

<cms:PostDetail id="postDetail" postId="<%= id%>" categoryId="<%= categoryId%>"></cms:PostDetail>
</div>
</div>
<cms:Footer path="<%= categoryId%>"/>

</body>
</html>