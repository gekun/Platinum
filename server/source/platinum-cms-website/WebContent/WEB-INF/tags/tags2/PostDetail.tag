<%@ tag import="platinum.cms.common.PostType"%>
<%@ tag import="platinum.common.util.DateUtil"%>
<%@ tag import="platinum.cms.common.entity.PostEntity"%>
<%@ tag import="platinum.cms.runtime.service.PostRuntimeManager"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/tags2" %>
<%@ attribute name="id" required="true" rtexprvalue="true" %>
<%@ attribute name="postId" rtexprvalue="true" %>
<%@ attribute name="categoryId" rtexprvalue="true" %>

<%
PostRuntimeManager postManager = PostRuntimeManager.getInstance();
PostEntity post = postManager.getPost(postId, categoryId);
%>

<div id="<%= id%>">
    <cms:CategoryNavigationBar id="categoryNavigationBar" categoryId="<%= categoryId%>" subcategoryId="<%= post.getSubcategoryId()%>"></cms:CategoryNavigationBar>

    <% if (post.getPostType() == PostType.NEWS) {%>
        <h1 id="title"><%= post.getTitle()%></h1>    
        
        <div id="info">
            <% if (post.getSource() != null) {%>
            <span id="source"><span>来源：</span><a href="#"><%= post.getSource()%></a></span>
            <% } %>
            <span id="updateTime"><span>更新时间：</span><a><%= DateUtil.formatDate(post.getUpdateTime(), "yyyy年M月d日HH:mm")%></a></s>
        </div>
    
        <% if (post.getContentText() != null) {%>
        <div id="summary"><%= post.getSummary()%></div>
        <% } %>
        
        <% if (post.getContentText() != null) {%>
        <div id="content"><%= post.getContentText()%></div>
        <% } %>
    <%} else {%>
        <div id="imageContent">
            <% if (post.getPhotoURL() != null) {%>
            <img src="<%=post.getPhotoURL()%>"/>
            <%} %>
        </div>
        <div id="textContent">
            <h1 id="title"><%= post.getTitle()%></h1>
            <div id="info">
                <span id="updateTime"><span>更新时间：</span><a><%= DateUtil.formatDate(post.getUpdateTime(), "yyyy年M月d日")%></a></s>
            </div>
            <div id="content"><%= post.getContentText()%></div>
        </div>
    <%} %>
</div>