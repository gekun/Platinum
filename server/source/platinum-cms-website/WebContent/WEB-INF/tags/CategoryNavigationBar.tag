<%@tag import="platinum.cms.runtime.service.CategoryRuntimeManager"%>
<%@tag import="platinum.cms.common.entity.SubcategoryEntity"%>
<%@tag import="platinum.cms.common.entity.CategoryEntity"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true" rtexprvalue="true"%>
<%@ attribute name="categoryId" required="true" rtexprvalue="true"%>
<%@ attribute name="subcategoryId" rtexprvalue="true"%>

<%
CategoryEntity category = CategoryRuntimeManager.getInstance().getCategory(categoryId);
SubcategoryEntity subcategory = null;

if (subcategoryId != null)
{
    subcategory = CategoryRuntimeManager.getInstance().getSubcategory(subcategoryId);
}
%>

<ul id="${id}" class="CategoryNavigationBar">
	<li>当前位置:</li>
    <li><a href="/">首页</a></li>
    <li> &gt; <a href="<%= categoryId.equals("news") || categoryId.equals("service") ? "../../../news/more/" : "../../../"+categoryId%>"><%= category != null ? category.getCategoryName() : categoryId%></a></li>
    <% if (subcategoryId != null) {%>
    <li> &gt; <a  id="subcategoryIda" href="/${categoryId}/${subcategoryId}/more/"><%= subcategory != null ? subcategory.getSubcategoryName() : subcategoryId%></a></li>
    <%} %>
</ul>